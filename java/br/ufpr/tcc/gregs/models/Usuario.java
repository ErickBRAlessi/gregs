package br.ufpr.tcc.gregs.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {

	private static final long serialVersionUID = -6903393419411737049L;

	public Usuario() {
	}

	public Usuario(String email, String password, Pessoa pessoa, Permissao permissao) {
		this.pessoa = pessoa;
		this.email = email;
		this.password = password;
		Set<Permissao> p = new HashSet<>();
		p.add(new Permissao("permissao_bolada_do_millenium"));
		this.permissoes = p;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_user_id", nullable = false)
	private long id;

	@JsonIgnore
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "fk_pessoa_id")
	private Pessoa pessoa;

	@JsonIgnore
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "fk_pagina_id")
	private Pagina pagina;

	@Column(name = "user_password", nullable = false)
	private String password;

	@Column(name = "user_email", nullable = false, unique = true)
	private String email;

	@Column
	@OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Permissao> permissoes;

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", pessoa=" + pessoa + ", pagina=" + pagina + ", password=" + password + ", email="
				+ email + ", permissoes=" + permissoes + "]";
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

	public Pagina getPagina() {
		return pagina;
	}

	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}

	// ---SECURITY---//

	public Set<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return permissoes;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
