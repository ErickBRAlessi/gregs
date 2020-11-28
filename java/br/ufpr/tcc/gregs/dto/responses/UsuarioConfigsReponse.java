package br.ufpr.tcc.gregs.dto.responses;

import br.ufpr.tcc.gregs.models.Imagem;

public class UsuarioConfigsReponse {
	String nome;
	String sobrenome;
	String email;
	Imagem imagemUsuario;
	String urlPagina;
	String senhaAntiga;
	String senhaNova;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Imagem getImagemUsuario() {
		return imagemUsuario;
	}
	public void setImagemUsuario(Imagem imagemUsuario) {
		this.imagemUsuario = imagemUsuario;
	}
	public String getUrlPagina() {
		return urlPagina;
	}
	public void setUrlPagina(String urlPagina) {
		this.urlPagina = urlPagina;
	}
	public String getSenhaAntiga() {
		return senhaAntiga;
	}
	public void setSenhaAntiga(String senhaAntiga) {
		this.senhaAntiga = senhaAntiga;
	}
	public String getSenhaNova() {
		return senhaNova;
	}
	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}
	
	
	
}
