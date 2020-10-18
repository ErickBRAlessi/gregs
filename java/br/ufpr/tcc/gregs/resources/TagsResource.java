package br.ufpr.tcc.gregs.resources;

import static org.neo4j.driver.Values.parameters;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufpr.tcc.gregs.graph.Neo4JSessionFactory;
import br.ufpr.tcc.gregs.models.Retorno;
import br.ufpr.tcc.gregs.models.Usuario;
import br.ufpr.tcc.gregs.parser.responses.UsuarioResponse;
import br.ufpr.tcc.gregs.service.IUsuarioService;

@RestController
@CrossOrigin
public class TagsResource {

	@Autowired
	private IUsuarioService iUsuarioService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/tags")
	public ResponseEntity<?> putTag(Authentication authentication, @RequestBody List<String> tags) {

		try {
			Session s = Neo4JSessionFactory.getSession();

			s.writeTransaction(tx -> {
				Usuario usuario = iUsuarioService.findByEmail(authentication.getName());
				tx.run("MATCH (U:Usuario) WHERE U.idRelacional = $id DETACH DELETE U",
						parameters("id", usuario.getId()));
				tx.run("CREATE (U:Usuario) SET U.idRelacional = $id, U.nome = $nome",
						parameters("id", usuario.getId(), "nome", usuario.getPessoa().getNome().toUpperCase().trim()));
				return 1;
			});

			tags.forEach(t -> s.writeTransaction(tx -> {
				Usuario usuario = iUsuarioService.findByEmail(authentication.getName());

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
			return new ResponseEntity<Retorno>(new Retorno("Tags Inseridas com sucesso", null), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Retorno>(new Retorno(e.getMessage(), e.getClass()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/tags/")
	public ResponseEntity<?> buscar() {
		return buscar("");
	}

	// provavelmente há um url melhor para esse resource
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/tags/{busca}")
	public ResponseEntity<?> buscar(@PathVariable String busca) {
		System.out.println(busca);
		try {
			Session s = Neo4JSessionFactory.getSession();

			List<UsuarioResponse> usuarios = new ArrayList<>();
			s.writeTransaction(tx -> {
				Result result = tx.run(
						"MATCH (U:Usuario)-[*1..2]-(B:Usuario) WHERE U.nome STARTS WITH $busca RETURN DISTINCT U.idRelacional, B.idRelacional",
						parameters("busca", busca.toUpperCase().trim()));
				boolean first = true;
				while (result.hasNext()) {
					Record record = result.next();
					if (first) {
						Usuario u = iUsuarioService.find(record.get("U.idRelacional").asLong());
						if (u != null) {
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

				result = tx.run(
						"MATCH (U:Usuario)-[*1..3]-(T:Tag) WHERE T.nome STARTS WITH $busca RETURN DISTINCT U.idRelacional",
						parameters("busca", busca.toUpperCase().trim()));
				first = true;
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
			return new ResponseEntity<Retorno>(new Retorno("Resultados Encontrados", usuarios), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Retorno>(new Retorno(e.getMessage(), e.getClass()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
