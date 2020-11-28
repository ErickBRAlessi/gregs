package br.ufpr.tcc.gregs.dto.requests.componentes;

import java.util.List;

import br.ufpr.tcc.gregs.models.Texto;

public class ComponenteTextoRequest extends ComponenteRequest{
	
	private List<Texto> textos;
	
	public List<Texto> getTextos() {
		return textos;
	}

	public void setTextos(List<Texto> textos) {
		this.textos = textos;
	}
	
	

}
