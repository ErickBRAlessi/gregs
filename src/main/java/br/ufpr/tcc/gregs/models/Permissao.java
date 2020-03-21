package br.ufpr.tcc.gregs.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "permissao")
public class Permissao {

	public Permissao() {
	}

	public Permissao(long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_role")
	private long id;

	@Column(name = "role_descricao")
	private String descricao;

	@ManyToMany(mappedBy = "permissoes")
	private Set<Usuario> usuarios = new HashSet<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
