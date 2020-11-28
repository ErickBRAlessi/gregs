package br.ufpr.tcc.gregs.dto.requests;

import java.util.List;

import br.ufpr.tcc.gregs.dto.ParsedComponente;

public class PaginaRequest {

	private long id;
	
	private String url;
	
	private List<ParsedComponente> componentes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<ParsedComponente> getComponentes() {
		return componentes;
	}

	public void setComponentes(List<ParsedComponente> componentes) {
		this.componentes = componentes;
	}

}
