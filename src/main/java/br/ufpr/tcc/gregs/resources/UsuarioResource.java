package br.ufpr.tcc.gregs.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufpr.tcc.gregs.models.Permissao;
import br.ufpr.tcc.gregs.models.Retorno;
import br.ufpr.tcc.gregs.models.Usuario;
import br.ufpr.tcc.gregs.service.IUsuarioService;

@RestController
public class UsuarioResource {
	
	
	@Autowired
	private IUsuarioService iUsuarioService;
	
	@GetMapping("/usuarios/all")
	public Retorno listarPermissoes() {
		List<Usuario> usuarios;
		try {
			usuarios = iUsuarioService.findAll();
		}catch(Exception e) {
			e.printStackTrace();
			return new Retorno(e.getMessage(), e.getClass());
		}
		return new Retorno("Sucesso" , usuarios);
	}
	
	@PutMapping("/usuario")
	public Retorno inserirUsuario(@RequestBody Usuario usuario) {
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
