package br.ufpr.tcc.gregs.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufpr.tcc.gregs.models.Retorno;
import br.ufpr.tcc.gregs.models.Usuario;
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
			return new ResponseEntity<Retorno>(new Retorno("Tags Inseridas com sucesso", null), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Retorno>(new Retorno(e.getMessage(), e.getClass()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/tags")
	public ResponseEntity<?> buscarTodas() {
		try {
			List<String> tags = MotorBusca.buscarTags();
			return new ResponseEntity<Retorno>(new Retorno("Resultados Encontrados", tags), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Retorno>(new Retorno(e.getMessage(), e.getClass()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
