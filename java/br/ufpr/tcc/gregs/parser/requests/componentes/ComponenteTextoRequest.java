package br.ufpr.tcc.gregs.parser.requests.componentes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
