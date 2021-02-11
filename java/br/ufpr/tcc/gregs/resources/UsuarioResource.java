package br.ufpr.tcc.gregs.resources;

import java.util.ArrayList;
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

import br.ufpr.tcc.gregs.dto.ParsedUsuarioConfiguracoes;
import br.ufpr.tcc.gregs.dto.requests.UsuarioRequest;
import br.ufpr.tcc.gregs.dto.responses.UsuarioResponse;
import br.ufpr.tcc.gregs.models.Componente;
import br.ufpr.tcc.gregs.models.ComponenteBio;
import br.ufpr.tcc.gregs.models.Pagina;
import br.ufpr.tcc.gregs.models.Pessoa;
import br.ufpr.tcc.gregs.models.Retorno;
import br.ufpr.tcc.gregs.models.Texto;
import br.ufpr.tcc.gregs.models.Usuario;
import br.ufpr.tcc.gregs.models.Imagem;

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

			usuario.setImagemUsuario(request.getImagemUsuario());
			usuario.setPessoa(pessoa);
			usuario.setEmail(request.getEmail());
			usuario.setPassword((request.getPassword()));

			//componente automatico bio
			List<Componente> componentes = new ArrayList<>();
			//se não construir uma imagem, ele vai utilizar a mesma referencia no banco, quando for atualizar o perfil ainda estara usando a mesma img e vai dar pau
			componentes.add(new ComponenteBio(request.getNome() + " " + request.getSobrenome(), null, new Texto("", "Escreva aqui seu perfil!")));
			
			usuario.setPagina(new Pagina(request.getUrl(), componentes));

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

				if (request.getPassword() != null) {
					usuario.setPassword(request.getPassword());
				}

				usuario.getPagina().setUrl(request.getUrl());
				usuario.setImagemUsuario(request.getImagemUsuario());

				usuario.getPessoa().setNome(request.getNome());
				usuario.getPessoa().setSobrenome(request.getSobrenome());

				iUsuarioService.salvar(usuario);

			} else {
				return new ResponseEntity<>(new Retorno("Usuario não Encontrado", null), HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(new Retorno(e.getMessage(), e.getClass()), HttpStatus.OK);
		}
		return new ResponseEntity<>(new Retorno("Usuario Atualizado com Sucesso", new UsuarioResponse(usuario)),
				HttpStatus.OK);
	}


	//aciona motor de busca
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/usuarios")
	public ResponseEntity<?> buscar(@RequestParam(defaultValue = "") String busca, @RequestParam(defaultValue = "1000") int limite) {
		try {
			List<UsuarioResponse> usuarios = MotorBusca.buscar(busca, iUsuarioService, limite);
			return new ResponseEntity<Retorno>(new Retorno("Resultados Encontrados", usuarios), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Retorno>(new Retorno(e.getMessage(), e.getClass()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/usuarios/configs")
	public ResponseEntity<?> configsUsuarioLogado(Authentication authentication) {
		Usuario usuario = iUsuarioService.findByEmail(authentication.getName());
		return new ResponseEntity<>(
				new Retorno("Configurações Obtidas com Sucesso", new ParsedUsuarioConfiguracoes(usuario)),
				HttpStatus.OK);
	}

	@GetMapping(value = "/usuarios", params = "email")
	public ResponseEntity<?> buscarEmail(Authentication authentication, @RequestParam String email) {
		Usuario usuario = iUsuarioService.findByEmail(authentication.getName());
		return new ResponseEntity<>(new Retorno("Usuario encontrado com sucesso", new UsuarioResponse(usuario)),
				HttpStatus.OK);
	}
}
