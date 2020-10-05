package br.ufpr.tcc.gregs.parser.responses;

import br.ufpr.tcc.gregs.models.Pagina;
import br.ufpr.tcc.gregs.parser.ParsedPagina;

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
