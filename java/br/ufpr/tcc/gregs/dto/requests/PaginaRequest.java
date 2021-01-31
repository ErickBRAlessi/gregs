package br.ufpr.tcc.gregs.dto.requests;

import java.util.List;

import br.ufpr.tcc.gregs.dto.ParsedComponente;

public class PaginaRequest {

	private long id;
	
	private String url;
	
	private List<ParsedComponente> componentes;
	
	private String backgroundColor;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

}
