package br.ufpr.tcc.gregs.models;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pagina")
public class Pagina {


	public Pagina() {}

	public Pagina(String url, List<Componente> componentes) {
		this.url = url;
		this.componentes = componentes;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_pagina_id", nullable = false)
	private long id;
	
	@Column(name = "url", nullable = false, unique = true)
	private String url;
	
	@OneToMany(mappedBy = "pagina", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Componente> componentes;

	@Override
	public String toString() {
		return "Pagina [id=" + id + ", url=" + url + ", componentes=" + componentes + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Componente> getComponentes() {
		return componentes;
	}

	public void setComponentes(List<Componente> componentes) {
		this.componentes = componentes;
	}

}