package br.ufpr.tcc.gregs.parser.responses;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String authorization;

	public JwtResponse(String authorization) {
		this.authorization = authorization;
	}

	public String getAuthorization() {
		return this.authorization;
	}
}