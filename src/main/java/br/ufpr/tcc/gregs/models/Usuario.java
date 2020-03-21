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
import javax.persistence.JoinColumn;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	public Usuario() {
//		this.permissoes.add(new Permissao(3, "visitante"));		
	}
	
	public Usuario(long id, String nome, String password, Set<Permissao> permissoes) {
		this.id = id;
		this.nome = nome;
		this.password = password;
		this.permissoes = permissoes;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_user_id")
	private long id;

	@Column(name = "user_name")
	private String nome;

	@Column(name = "user_password")
	private String password;

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
		this.permissoes = permissoes;
	}
}
