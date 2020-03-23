package br.ufpr.tcc.gregs.security;

import java.util.Date;

import br.ufpr.tcc.gregs.models.Usuario;

public class Token {
	private static final long SESSIONTIME = 60000L * 15L;
	
	public Token() {}
	
	
	public Token(Usuario usuario) {
		this.usuario = usuario;
		this.expirassao = new Date(System.currentTimeMillis() + SESSIONTIME);
	}
	
	private Date expirassao;

	private Usuario usuario;
	
	public Date getExpirassao() {
		return expirassao;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setExpirassao(Date expirassao) {
		this.expirassao = expirassao;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
