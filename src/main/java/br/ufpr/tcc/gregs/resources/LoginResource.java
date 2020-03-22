package br.ufpr.tcc.gregs.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufpr.tcc.gregs.models.Retorno;
import br.ufpr.tcc.gregs.models.Usuario;
import br.ufpr.tcc.gregs.requests.LoginRequest;
import br.ufpr.tcc.gregs.security.Token;
import br.ufpr.tcc.gregs.service.IUsuarioService;

@RestController
public class LoginResource {

	@Autowired
	private IUsuarioService iUsuarioService;

	@PostMapping("/login")
	public Retorno inserirUsuario(@RequestBody LoginRequest request) {
		Usuario user = iUsuarioService.findUsuario(request.getEmail().trim().toLowerCase());
		
		//MD5 feito na classe LoginRequest no set
		if(!user.getPassword().equals(request.getPassword())) {
			return new Retorno("Usuario Invalido", null);
		}
		Token.isLoginValid(Token.getTokenEncode(user));
		return new Retorno("Logado com Sucesso", Token.getTokenEncode(user));
	}
	
	@GetMapping("/logado")
	public Retorno getUsuario(@RequestParam(value = "token", defaultValue = "") String token) {
		try {
		return new Retorno("Usuario logado", Token.getUsuarioLogado(token));
		} catch (Exception e) {
			return new Retorno("Usuario desconhecido ou não logado", null);
		}
	}

}
