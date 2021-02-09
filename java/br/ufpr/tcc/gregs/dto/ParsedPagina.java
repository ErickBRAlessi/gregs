package br.ufpr.tcc.gregs.dto;

import java.util.ArrayList;
import java.util.List;

import br.ufpr.tcc.gregs.models.Componente;
import br.ufpr.tcc.gregs.models.Pagina;

public class ParsedPagina {
	public long id;
	public String url;
	public String backgroundColor;
	public List<ParsedComponente> componentes;

	public ParsedPagina(Pagina pagina) {
		this.id = pagina.getId();
		this.url = pagina.getUrl();
		this.backgroundColor = pagina.getBackgroundColor();
		if (pagina.getComponentes() != null) {
			this.componentes = new ArrayList<>();
			for (Componente c : pagina.getComponentes()) {
				this.componentes.add(new ParsedComponente(c));
			}
		}
	}
	
	public ParsedPagina() {
	}
}