package br.ufpr.tcc.gregs.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufpr.tcc.gregs.models.Pagina;
import br.ufpr.tcc.gregs.models.Pessoa;
import br.ufpr.tcc.gregs.models.Retorno;
import br.ufpr.tcc.gregs.models.Usuario;
import br.ufpr.tcc.gregs.parser.ParsedUsuarioConfiguracoes;
import br.ufpr.tcc.gregs.parser.requests.UsuarioRequest;
import br.ufpr.tcc.gregs.parser.responses.UsuarioResponse;
import br.ufpr.tcc.gregs.service.IPessoaService;
import br.ufpr.tcc.gregs.service.IUsuarioService;
import br.ufpr.tcc.gregs.utility.MotorBusca;

@RestController
public class UsuarioResource {

	@Autowired
	private IUsuarioService iUsuarioService;

	@Autowired
	private IPessoaService iPessoaService;

	@PostMapping("/usuarios")
	public ResponseEntity<Retorno> inserirUsuario(@RequestBody UsuarioRequest request) {
		Usuario usuario = new Usuario();
		try {
			Pessoa pessoa = new Pessoa();
			pessoa.setNome(request.getNome());
			pessoa.setSobrenome(request.getSobrenome());
			iPessoaService.inserirPessoa(pessoa);

			usuario.setPessoa(pessoa);
			usuario.setEmail(request.getEmail());
			usuario.setPassword((request.getPassword()));

			usuario.setPagina(new Pagina(request.getUrl(), null));

			usuario.setImagemUsuario(request.getImagemUsuario());

			iUsuarioService.salvar(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new Retorno(e.getMessage(), e.getClass()), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new Retorno("Usuario Inserido com Sucesso", new UsuarioResponse(usuario)),
				HttpStatus.CREATED);
	}

	@PutMapping(value = "/usuarios")
	public ResponseEntity<Retorno> updateUsuario(Authentication authentication, @RequestBody UsuarioRequest request) {
		Usuario usuario = null;
		try {
			usuario = iUsuarioService.findByEmail(authentication.getName());
			if (usuario != null) {
				usuario.setEmail(request.getEmail());
				usuario.setPassword(request.getPassword() != null ? request.getPassword() : usuario.getPassword());
				usuario.getPagina().setUrl(request.getUrl());
				usuario.setImagemUsuario(request.getImagemUsuario());

				usuario.getPessoa().setNome(request.getNome());
				usuario.getPessoa().setSobrenome(request.getSobrenome());

				iUsuarioService.atualizar(usuario);

			} else {
				return new ResponseEntity<>(new Retorno("Usuario não Encontrado", null), HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(new Retorno(e.getMessage(), e.getClass()), HttpStatus.OK);
		}
		return new ResponseEntity<>(new Retorno("Usuario Atualizado com Sucesso", new UsuarioResponse(usuario)),
				HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/usuarios")
	public ResponseEntity<?> buscar(@RequestParam(defaultValue = "1000") int limite) {
		return buscar("", limite);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/usuarios", params = "busca")
	public ResponseEntity<?> buscar(@PathVariable String busca, @RequestParam(defaultValue = "1000") int limite) {
		try {
			List<UsuarioResponse> usuarios = MotorBusca.buscar(busca, iUsuarioService, limite);
			return new ResponseEntity<Retorno>(new Retorno("Resultados Encontrados", usuarios), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Retorno>(new Retorno(e.getMessage(), e.getClass()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/usuarios/configs")
	public ResponseEntity<?> configsUsuarioLogado(Authentication authentication) {
		Usuario usuario = iUsuarioService.findByEmail(authentication.getName());
		return new ResponseEntity<>(new Retorno("Login Efetuado com Sucesso", new ParsedUsuarioConfiguracoes(usuario)),
				HttpStatus.OK);
	}

	@GetMapping(value = "/usuarios", params = "email")
	public ResponseEntity<?> buscarEmail(Authentication authentication, @RequestParam String email) {
		Usuario usuario = iUsuarioService.findByEmail(authentication.getName());
		return new ResponseEntity<>(new Retorno("Usuario encontrado com sucesso", new UsuarioResponse(usuario)),
				HttpStatus.OK);
	}
}
