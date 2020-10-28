package br.ufpr.tcc.gregs.models;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usuario")
public class Usuario {

	public Usuario() {
	}

	public Usuario(String email, String password, Pessoa pessoa) {
		this.pessoa = pessoa;
		this.email = email;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_user_id", nullable = false)
	private long id;

	@JsonIgnore
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "fk_pessoa_id")
	private Pessoa pessoa;

	@JsonIgnore
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "fk_pagina_id")
	private Pagina pagina;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_imagem_id")
	private Imagem imagemUsuario;

	@Column(name = "password", nullable = false)
	private String password;

	//tratado como username pelo Spring.security
	@Column(name = "email", nullable = false, unique = true)
	private String email;


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", pessoa=" + pessoa + ", pagina=" + pagina + ", password=" + password + ", email="
				+ email + "]";
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

	public Imagem getImagemUsuario() {
		return imagemUsuario;
	}

	public void setImagemUsuario(Imagem imagemUsuario) {
		this.imagemUsuario = imagemUsuario;
	}

}
