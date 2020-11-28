package br.ufpr.tcc.gregs.utility;

import static org.neo4j.driver.Values.parameters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Component;

import br.ufpr.tcc.gregs.dto.responses.UsuarioResponse;
import br.ufpr.tcc.gregs.graph.Neo4JSessionFactory;
import br.ufpr.tcc.gregs.models.Usuario;
import br.ufpr.tcc.gregs.service.IUsuarioService;

@Component
public abstract class MotorBusca {

	public static List<UsuarioResponse> buscar(String busca, IUsuarioService iUsuarioService, int limite) {
		List<UsuarioResponse> usuarios = new ArrayList<>();
		if (limite > 0) {
				buscarUsuariosTags(busca, iUsuarioService, usuarios, limite);
			if(usuarios.size() < limite) {
				buscarUsuariosNome(busca, iUsuarioService, usuarios, limite - usuarios.size());
			}
		}
		return usuarios;

	}

	private static void buscarUsuariosTags(String busca, IUsuarioService iUsuarioService,
			List<UsuarioResponse> usuarios, int limite) {
		if(limite == 0) {
			return;
		}
		Session s = Neo4JSessionFactory.getSession();
		s.writeTransaction(tx -> {
			Result result = tx.run(
					"MATCH (U:Usuario)-[*1..3]-(T:Tag) WHERE T.nome STARTS WITH $busca RETURN DISTINCT U.idRelacional LIMIT $limite",
					parameters("busca", busca.toUpperCase().trim(), "limite", limite));
			while (result.hasNext()) {
				Record record = result.next();
				Usuario u = iUsuarioService.find(record.get("U.idRelacional").asLong());
				if (u != null) {
					UsuarioResponse usuarioResponse = new UsuarioResponse(u);
					if (!usuarios.contains(usuarioResponse)) {
						usuarios.add(new UsuarioResponse(u));
					}
				}
			}
			return 1;
		});
		s.close();
	}

	private static void buscarUsuariosNome(String busca, IUsuarioService iUsuarioService, List<UsuarioResponse> usuarios, int limite) {
		if(limite == 0) {
			return;
		}
		Session s = Neo4JSessionFactory.getSession();
		s.writeTransaction(tx -> {
			Result result = tx.run(
					"MATCH (U:Usuario)-[*1..3]-(B:Usuario) WHERE U.nome STARTS WITH $busca RETURN DISTINCT U.idRelacional, B.idRelacional LIMIT $limite",
					parameters("busca", busca.toUpperCase().trim(), "limite", limite));
			boolean first = true;
			while (result.hasNext()) {
				Record record = result.next();
				if (first) {
					Usuario u = iUsuarioService.find(record.get("U.idRelacional").asLong());
					UsuarioResponse usuarioResponse = new UsuarioResponse(u);
					if (!usuarios.contains(usuarioResponse)) {
						usuarios.add(new UsuarioResponse(u));
					}
					first = false;
				}
				Usuario u = iUsuarioService.find(record.get("B.idRelacional").asLong());
				if (u != null) {
					UsuarioResponse usuarioResponse = new UsuarioResponse(u);
					if (!usuarios.contains(usuarioResponse)) {
						usuarios.add(new UsuarioResponse(u));
					}
				}
			}
			return 1;
		});
		s.close();
	}

	public static void inserirTagsUsuario(List<String> tags, Usuario usuario) {
		Session s = Neo4JSessionFactory.getSession();
		removerObjetosDuplicados(tags);
		s.writeTransaction(tx -> {

			tx.run("MATCH (U:Usuario) WHERE U.idRelacional = $id DETACH DELETE U", parameters("id", usuario.getId()));
			tx.run("CREATE (U:Usuario) SET U.idRelacional = $id, U.nome = $nome",
					parameters("id", usuario.getId(), "nome", usuario.getPessoa().getNome().toUpperCase().trim()));
			return 1;
		});

		tags.forEach(t -> s.writeTransaction(tx -> {

			Result result = tx.run("MATCH (T:Tag) WHERE T.nome = $tag RETURN T",
					parameters("tag", t.toUpperCase().trim()));
			if (!result.hasNext()) {
				tx.run("CREATE (T:Tag) SET T.nome = $tag", parameters("tag", t.toUpperCase().trim()));
			}
			tx.run("MATCH (U:Usuario),(T:Tag) WHERE U.idRelacional = $idRelacional AND T.nome = $tagNome CREATE (U)-[:CONHECE]->(T)",
					parameters("idRelacional", usuario.getId(), "tagNome", t.toUpperCase().trim()));
			return 1;
		}));

		s.close();
	}

	public static List<String> buscarTags() {
		Session s = Neo4JSessionFactory.getSession();
		List<String> tags = new ArrayList<>();
		s.writeTransaction(tx -> {
			Result result = tx.run("MATCH (T:Tag) RETURN T.nome", parameters());
			while (result.hasNext()) {
				Record record = result.next();
				tags.add(record.get("T.nome").asString());
			}
			return 1;
		});

		s.close();
		return tags;
	}

	public static List<String> buscarTagsdeUsuario(Usuario usuario) {
		Session s = Neo4JSessionFactory.getSession();
		List<String> tags = new ArrayList<>();
		s.writeTransaction(tx -> {
			Result result = tx.run(
					"MATCH (T:Tag)-[*1]-(U:Usuario) WHERE U.idRelacional = $idRelacional RETURN DISTINCT T.nome",
					parameters("idRelacional", usuario.getId()));
			while (result.hasNext()) {
				Record record = result.next();
				tags.add(record.get("T.nome").asString());
			}
			return 1;
		});
		s.close();
		return tags;
	}

	private static <T> void removerObjetosDuplicados(List<T> list) {
		Set<T> set = new HashSet<>(list);
		list.clear();
		list.addAll(set);
	}

}
