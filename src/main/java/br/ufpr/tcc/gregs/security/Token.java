package br.ufpr.tcc.gregs.security;

import java.util.Base64;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufpr.tcc.gregs.models.Usuario;

public class Token {
	private static byte[] key = "ADMIN".getBytes();

	public static String getTokenEncode(Usuario usuario) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(usuario);
			return new String(Base64.getEncoder().encode(jsonString.getBytes()));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			new Date(System.currentTimeMillis() + 3600000);
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isLoginValid(String token) {
		String str = new String(Base64.getDecoder().decode(token.getBytes()));
		System.out.println(str);
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.readValue(str, Usuario.class);
			return true;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static Usuario getUsuarioLogado(String token) {
		String str = new String(Base64.getDecoder().decode(token.getBytes()));
		System.out.println(str);
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(str, Usuario.class);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static boolean isValid(String token) {

		return false;
	}

}
