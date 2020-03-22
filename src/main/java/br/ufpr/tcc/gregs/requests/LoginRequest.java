package br.ufpr.tcc.gregs.requests;

import br.ufpr.tcc.gregs.security.MD5;

public class LoginRequest {
	
	private String email;
	
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = MD5.toMD5(password);
	}
	

}
