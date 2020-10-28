package br.ufpr.tcc.gregs.parser.requests;

import br.ufpr.tcc.gregs.models.Imagem;

public class UsuarioRequest {

	private long id;
	private String email;
	private String nome;
	private String sobrenome;
	private String password;
	private String url;
	private Imagem imagemUsuario;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Imagem getImagemUsuario() {
		return imagemUsuario;
	}

	public void setImagemUsuario(Imagem imagemUsuario) {
		this.imagemUsuario = imagemUsuario;
	}

}
