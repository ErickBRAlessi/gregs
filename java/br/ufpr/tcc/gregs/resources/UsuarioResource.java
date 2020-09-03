package br.ufpr.tcc.gregs.resources;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import br.ufpr.tcc.gregs.models.Pagina;
import br.ufpr.tcc.gregs.models.Permissao;
import br.ufpr.tcc.gregs.models.Pessoa;
import br.ufpr.tcc.gregs.models.Retorno;
import br.ufpr.tcc.gregs.models.Usuario;
import br.ufpr.tcc.gregs.parser.requests.UsuarioRequest;
import br.ufpr.tcc.gregs.parser.responses.UsuarioResponse;
import br.ufpr.tcc.gregs.security.MD5;
import br.ufpr.tcc.gregs.security.TokenUtil;
import br.ufpr.tcc.gregs.service.IPermissaoService;
import br.ufpr.tcc.gregs.service.IPessoaService;
import br.ufpr.tcc.gregs.service.IUsuarioService;

@RestController
public class UsuarioResource {

	@Autowired
	private IUsuarioService iUsuarioService;

	@Autowired
	private IPermissaoService iPermissaoService;
	
	@Autowired
	private IPessoaService iPessoaService;

	// TESTA SE USUARIO É ADM
	@GetMapping("/usuarios/all")
	public Retorno listarUsuarios(@RequestHeader("Token") String token) {
		if (TokenUtil.isUsuarioAdmin(token)) {
			List<UsuarioResponse> usuariosResp = new ArrayList<>();
			try {
				List<Usuario> usuarios;
				usuarios = iUsuarioService.findAll();
				for(Usuario u : usuarios) {
					usuariosResp.add(new UsuarioResponse(u));
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new Retorno(e.getMessage(), e.getClass());
			}
			return new Retorno("Sucesso", usuariosResp);
		}
		return new Retorno("Usuário sem Autorização ou Expirado", null);
	}

	@PutMapping("/usuario")
	public Retorno inserirUsuario(@RequestBody UsuarioRequest request) {
		Usuario usuario = new Usuario();
		try {
			Pessoa pessoa = new Pessoa();
			pessoa.setNome(request.getNome());
			pessoa.setSobrenome(request.getSobrenome());
			iPessoaService.inserirPessoa(pessoa);
			
			usuario.setPessoa(pessoa);
			usuario.setEmail(request.getEmail());
			usuario.setPassword(MD5.toMD5(request.getPassword()));
			
			usuario.setPagina(new Pagina(request.getUrl(), null));
			
			Set<Permissao> permissoes = new HashSet<>();
			permissoes.add(iPermissaoService.buscarId(request.getPermissaoId()));
			usuario.setPermissoes(permissoes);
			iUsuarioService.inserirUsuario(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return new Retorno(e.getMessage(), e.getClass());
		}
		return new Retorno("Usuario Inserido com Sucesso", new UsuarioResponse(usuario));
	}

	// Deleta pelo email
	@DeleteMapping("/usuario")
	public Retorno deletarUsuario(@RequestHeader("Token") String token, @RequestBody UsuarioRequest request) {
		if (TokenUtil.isUsuarioAdmin(token)) {
			try {
				Usuario usuario = iUsuarioService.findUsuario(request.getEmail());
				iUsuarioService.deletarUsuario(usuario);
			} catch (Exception e) {
				e.printStackTrace();
				return new Retorno(e.getMessage(), e.getClass());
			}
			return new Retorno("Usuario Deletado com Sucesso", request);
		}
		return new Retorno("Usuário sem Autorização", null);
	}

}
