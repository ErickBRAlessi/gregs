package br.ufpr.tcc.gregs.resources;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufpr.tcc.gregs.models.Permissao;
import br.ufpr.tcc.gregs.models.Retorno;
import br.ufpr.tcc.gregs.models.Usuario;
import br.ufpr.tcc.gregs.requests.UsuarioRequest;
import br.ufpr.tcc.gregs.security.MD5;
import br.ufpr.tcc.gregs.service.IPermissaoService;
import br.ufpr.tcc.gregs.service.IUsuarioService;

@RestController
public class UsuarioResource {

	@Autowired
	private IUsuarioService iUsuarioService;

	@Autowired
	private IPermissaoService iPermissaoService;

	@GetMapping("/usuarios/all")
	public Retorno listarPermissoes() {
		List<Usuario> usuarios;
		try {
			usuarios = iUsuarioService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new Retorno(e.getMessage(), e.getClass());
		}
		return new Retorno("Sucesso", usuarios);
	}

	@PutMapping("/usuario")
	public Retorno inserirUsuario(@RequestBody UsuarioRequest request) {
		Usuario usuario = new Usuario();
		usuario.setNome(request.getNome());
		usuario.setEmail(request.getEmail());
		usuario.setPassword(MD5.toMD5(request.getPassword()));
		Set<Permissao> permissoes = new HashSet<Permissao>();
		permissoes.add(iPermissaoService.buscarId(request.getPermissaoId()));
		usuario.setPermissoes(permissoes);
		try {
			iUsuarioService.inserirUsuario(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return new Retorno(e.getMessage(), e.getClass());
		}
		return new Retorno("Usuario Inserido com Sucesso", usuario);
	}

	@DeleteMapping("/usuario")
	public Retorno deletarUsuario(@RequestBody Usuario usuario) {
		try {
			iUsuarioService.deletarUsuario(usuario.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return new Retorno(e.getMessage(), e.getClass());
		}
		return new Retorno("Usuario Deletado com Sucesso", usuario);
	}

}
