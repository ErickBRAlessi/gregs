package br.ufpr.tcc.gregs.parser.requests.componentes;

import br.ufpr.tcc.gregs.models.Imagem;

public class ComponenteImagemRequest extends ComponenteRequest{
	
	private Imagem imagem;

	private String descricao;

	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
