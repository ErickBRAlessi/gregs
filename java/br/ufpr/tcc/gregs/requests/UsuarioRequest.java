package br.ufpr.tcc.gregs.requests;

public class UsuarioRequest {

	private long id;
	private String email;
	private String nome;
	private String sobrenome;
	private String password;
	private long permissaoId;

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

	public long getPermissaoId() {
		return permissaoId;
	}

	public void setPermissaoId(long permissaoId) {
		this.permissaoId = permissaoId;
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

}
