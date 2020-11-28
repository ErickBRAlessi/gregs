package br.ufpr.tcc.gregs.dto.responses;

import br.ufpr.tcc.gregs.dto.ParsedPagina;
import br.ufpr.tcc.gregs.models.Pagina;

public class PaginaResponse {
	
	private ParsedPagina pagina;
	
	public PaginaResponse(Pagina pagina) {
		this.setPagina(new ParsedPagina(pagina));
	}

	public ParsedPagina getPagina() {
		return pagina;
	}

	public void setPagina(ParsedPagina pagina) {
		this.pagina = pagina;
	}

}
