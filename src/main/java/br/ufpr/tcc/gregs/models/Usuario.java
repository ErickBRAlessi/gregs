package br.ufpr.tcc.gregs.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.JoinColumn;

@Entity
@Table(name = "usuario")
public class Usuario {

	public Usuario() {
		this.permissoes.add(new Permissao(3, "visitante"));
	}

	public Usuario(long id, String email, String nome, String password, Set<Permissao> permissoes) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.permissoes = permissoes;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_user_id", nullable = false)
	private long id;

	@Column(name = "user_name")
	private String nome;

	@Column(name = "user_password", nullable = false)
	private String password;

	@Column(name = "user_email", nullable = false, unique = true)
	private String email;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "usuario_permissao", joinColumns = { @JoinColumn(name = "fk_user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "fk_role") })
	private Set<Permissao> permissoes = new HashSet<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Set<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<Permissao> permissoes) {
		if(permissoes != null) {
			this.permissoes = permissoes;		
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
