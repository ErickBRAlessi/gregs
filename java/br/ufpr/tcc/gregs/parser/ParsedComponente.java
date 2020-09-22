package br.ufpr.tcc.gregs.parser;

import br.ufpr.tcc.gregs.models.Componente;

public class ParsedComponente {

	private long id;
	
	private String tipo;

	public ParsedComponente() {};
	
	public ParsedComponente(Componente componente) {
		this.id = componente.getId();
		this.tipo = componente.getClass().getSimpleName();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
