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
import br.ufpr.tcc.gregs.utility.MotorBusca;

@RestController
@CrossOrigin
public class TagsResource {

	@Autowired
	private IUsuarioService iUsuarioService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/tags")
	public ResponseEntity<?> putTag(Authentication authentication, @RequestBody List<String> tags) {
		Usuario usuario = iUsuarioService.findByEmail(authentication.getName());
		try {
			MotorBusca.inserirTagsUsuario(tags, usuario);
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
		try {
			List<UsuarioResponse> usuarios = MotorBusca.buscar(busca, iUsuarioService);
			return new ResponseEntity<Retorno>(new Retorno("Resultados Encontrados", usuarios), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Retorno>(new Retorno(e.getMessage(), e.getClass()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
