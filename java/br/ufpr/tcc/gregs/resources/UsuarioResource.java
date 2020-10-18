package br.ufpr.tcc.gregs.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import br.ufpr.tcc.gregs.models.Pagina;
import br.ufpr.tcc.gregs.models.Pessoa;
import br.ufpr.tcc.gregs.models.Retorno;
import br.ufpr.tcc.gregs.models.Usuario;
import br.ufpr.tcc.gregs.parser.requests.UsuarioRequest;
import br.ufpr.tcc.gregs.parser.responses.UsuarioResponse;
import br.ufpr.tcc.gregs.security.MD5;
import br.ufpr.tcc.gregs.service.IPessoaService;
import br.ufpr.tcc.gregs.service.IUsuarioService;

@RestController
public class UsuarioResource {

	@Autowired
	private IUsuarioService iUsuarioService;

	@Autowired
	private IPessoaService iPessoaService;

	@PutMapping("/usuario")
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

			iUsuarioService.salvar(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new Retorno(e.getMessage(), e.getClass()), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new Retorno("Usuario Inserido com Sucesso", new UsuarioResponse(usuario)),
				HttpStatus.CREATED);
	}

	@PostMapping(value = "/usuario/")
	public ResponseEntity<Retorno> updateUsuario(Authentication authentication, @RequestBody UsuarioRequest request) {
		Usuario usuario = null;
		try {
			usuario = iUsuarioService.findByEmail(authentication.getName());
			if (usuario != null) {
				usuario.setEmail(request.getEmail());
				usuario.setPassword(request.getPassword());
				usuario.getPagina().setUrl(request.getUrl());
				iUsuarioService.salvar(usuario);
			} else {
				return new ResponseEntity<>(new Retorno("Usuario não Encontrado", null), HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(new Retorno(e.getMessage(), e.getClass()), HttpStatus.CREATED);
		}
		return new ResponseEntity<>(new Retorno("Usuario Atualizado com Sucesso", new UsuarioResponse(usuario)),
				HttpStatus.OK);
	}

}
