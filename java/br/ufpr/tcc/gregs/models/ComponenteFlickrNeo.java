package br.ufpr.tcc.gregs.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonAlias;

import br.ufpr.tcc.gregs.dto.requests.componentes.ComponenteFlickrNeoRequest;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ComponenteFlickrNeo extends Componente {

	@JsonAlias("tipo")
	private static final String TIPO = "ComponenteFlickrNeo";
	
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<FlickrItem> imagensFlickr;
    
    @Column(name = "username")
	private String username;
	
	public ComponenteFlickrNeo() {}

    public ComponenteFlickrNeo(ComponenteFlickrNeoRequest componenteFlickrNeoRequest) {
		super.setId(componenteFlickrNeoRequest.getId());
		super.setTitulo(componenteFlickrNeoRequest.getTitulo());
		super.setMostrarTitulo(componenteFlickrNeoRequest.isMostrarTitulo());
		super.setBackgroundColor(componenteFlickrNeoRequest.getBackgroundColor());
		super.setForegroundColor(componenteFlickrNeoRequest.getForegroundColor());
		this.setImagensFlickr(componenteFlickrNeoRequest.getImagensFlickr());
		this.setUsername(componenteFlickrNeoRequest.getUsername());
	}

	public List<FlickrItem> getImagensFlickr() {
		return imagensFlickr;
	}

	public void setImagensFlickr(List<FlickrItem> imagensFlickr) {
		this.imagensFlickr = imagensFlickr;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTipo() {
		return TIPO;
	}
}
