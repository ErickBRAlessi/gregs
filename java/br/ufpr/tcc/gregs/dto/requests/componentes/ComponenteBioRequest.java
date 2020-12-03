package br.ufpr.tcc.gregs.dto.requests.componentes;

import br.ufpr.tcc.gregs.models.Imagem;
import br.ufpr.tcc.gregs.models.Texto;

public class ComponenteBioRequest extends ComponenteRequest{
	
	private Imagem imagem;

	private Texto texto;

	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}

	public Texto getTexto() {
		return texto;
	}

	public void setTexto(Texto texto) {
		this.texto = texto;
	}
	
	
}
