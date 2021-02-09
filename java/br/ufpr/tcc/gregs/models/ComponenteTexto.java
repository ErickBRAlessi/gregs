package br.ufpr.tcc.gregs.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonAlias;

import br.ufpr.tcc.gregs.dto.requests.componentes.ComponenteTextoRequest;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ComponenteTexto extends Componente {

	@JsonAlias("tipo")
	private static final String TIPO = "ComponenteTexto";

	public ComponenteTexto() {

	}

	public ComponenteTexto(ComponenteTextoRequest componenteTextoRequest) {
		super.setId(componenteTextoRequest.getId());
		super.setOrdem(componenteTextoRequest.getOrdem());
		super.setTitulo(componenteTextoRequest.getTitulo());
		super.setMostrarTitulo(componenteTextoRequest.isMostrarTitulo());
		super.setBackgroundColor(componenteTextoRequest.getBackgroundColor());
		super.setForegroundColor(componenteTextoRequest.getForegroundColor());
		this.textos = componenteTextoRequest.getTextos();
	}

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Texto> textos;

	public List<Texto> getTextos() {
		return textos;
	}

	public void setTextos(List<Texto> textos) {
		this.textos = textos;
	}

	public String getTipo() {
		return TIPO;
	}

}
