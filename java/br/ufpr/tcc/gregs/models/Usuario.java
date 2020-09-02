package br.ufpr.tcc.gregs.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	public Usuario() {
		this.permissoes.add(new Permissao(3, "visitante"));
	}

	public Usuario(long id, String email, String password, Pessoa pessoa, Set<Permissao> permissoes) {
		this.id = id;
		this.pessoa = pessoa;
		this.email = email;
		this.password = password;
		this.permissoes = permissoes;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_user_id", nullable = false)
	private long id;

	@OneToOne
	@JoinColumn(name = "fk_pessoa_id")
	private Pessoa pessoa;

	@Column(name = "user_password", nullable = false)
	private String password;

	@Column(name = "user_email", nullable = false, unique = true)
	private String email;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "usuario_permissao", joinColumns = { @JoinColumn(name = "fk_user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "fk_role") })
	private Set<Permissao> permissoes = new HashSet<>();

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", pessoa=" + pessoa + ", password=" + password + ", email=" + email
				+ ", permissoes=" + permissoes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return (id == other.id);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		if (permissoes != null) {
			this.permissoes = permissoes;
		}
	}

	public void removerTodasPermissoes() {
		for (Permissao p : permissoes) {
			permissoes.remove(p);
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
