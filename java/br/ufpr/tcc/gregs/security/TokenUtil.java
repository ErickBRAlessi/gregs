package br.ufpr.tcc.gregs.security;

import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufpr.tcc.gregs.models.Permissao;
import br.ufpr.tcc.gregs.models.Usuario;

public class TokenUtil {
	private TokenUtil() {
	}

	public static String getTokenEncode(Usuario usuario) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Token token = new Token(usuario);
			String jsonString = mapper.writeValueAsString(token);
			return new String(Base64.getEncoder().encode(jsonString.getBytes()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isLoginValid(String token) {
		String str = new String(Base64.getDecoder().decode(token.getBytes()));
		ObjectMapper mapper = new ObjectMapper();
		try {
			Token tk = mapper.readValue(str, Token.class);
			return tk.getExpirassao().after(new Date(System.currentTimeMillis()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Usuario getUsuarioLogado(String token) {
		String str = new String(Base64.getDecoder().decode(token.getBytes()));
		ObjectMapper mapper = new ObjectMapper();
		try {
			Token tk = mapper.readValue(str, Token.class);
			return tk.getUsuario();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean isUsuarioAdmin(String token) {
		return isUsuarioAutorizado(token, 1);
	}

	public static boolean isUsuarioCliente(String token) {
		return isUsuarioAutorizado(token, 2);
	}

	// estou considerando todos visitantes..
	public static boolean isUsuarioVisitante() {
		return true;
	}

	public static boolean isUsuarioAutorizado(String token, int permissaoId) {
		String str = new String(Base64.getDecoder().decode(token.getBytes()));
		ObjectMapper mapper = new ObjectMapper();
		try {
			Token tk = mapper.readValue(str, Token.class);
			return tk.getUsuario().getPermissoes().contains(new Permissao(permissaoId, ""));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Set<Permissao> getPermissaoUsuario(String token) {
		String str = new String(Base64.getDecoder().decode(token.getBytes()));
		ObjectMapper mapper = new ObjectMapper();
		try {
			Token tk = mapper.readValue(str, Token.class);
			return tk.getUsuario().getPermissoes();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return new HashSet<>();
		}
	}
}
