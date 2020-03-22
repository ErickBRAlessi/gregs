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
import br.ufpr.tcc.gregs.security.TokenUtil;
import br.ufpr.tcc.gregs.service.IUsuarioService;

@RestController
public class LoginResource {

	@Autowired
	private IUsuarioService iUsuarioService;

	@PostMapping("/login")
	public Retorno inserirUsuario(@RequestBody LoginRequest request) {
		Usuario user = iUsuarioService.findUsuario(request.getEmail().trim().toLowerCase());
		// MD5 feito na classe LoginRequest no set
		if (user == null) {
			return new Retorno("Usuario Inválido", null);
		}
		if (!user.getPassword().equals(request.getPassword())) {
			return new Retorno("Senha Inválida", null);
		}
		if(TokenUtil.isLoginValid(TokenUtil.getTokenEncode(user))) {
			return new Retorno("Logado com Sucesso", TokenUtil.getTokenEncode(user));
		}
		return new Retorno("Não foi possível gerar Token de Login", null);
	}

	@GetMapping("/logado")
	public Retorno getUsuario(@RequestParam(value = "token", defaultValue = "") String token) {
		try {
			Usuario user = TokenUtil.getUsuarioLogado(token);
			if (user != null) {
				return new Retorno("Usuario logado", TokenUtil.getUsuarioLogado(token));
			}
			return new Retorno("Usuario desconhecido ou não logado", null);
		} catch (Exception e) {
			return new Retorno("Usuario desconhecido ou não logado", null);
		}
	}

}
