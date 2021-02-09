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

import br.ufpr.tcc.gregs.dto.requests.componentes.ComponenteFreesoundRequest;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ComponenteFreesound extends Componente {

	@JsonAlias("tipo")
	private static final String TIPO = "ComponenteFreesound";
	
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Audio> audios;
    
    @Column(name = "username")
	private String username;
	
	public ComponenteFreesound() {}

    public ComponenteFreesound(ComponenteFreesoundRequest componenteFreesoundRequest) {
		super.setId(componenteFreesoundRequest.getId());
		super.setOrdem(componenteFreesoundRequest.getOrdem());
		super.setTitulo(componenteFreesoundRequest.getTitulo());
		super.setMostrarTitulo(componenteFreesoundRequest.isMostrarTitulo());
		super.setBackgroundColor(componenteFreesoundRequest.getBackgroundColor());
		super.setForegroundColor(componenteFreesoundRequest.getForegroundColor());
		this.setAudios(componenteFreesoundRequest.getAudios());
		this.setUsername(componenteFreesoundRequest.getUsername());
	}

	public List<Audio> getAudios() {
		return audios;
	}

	public void setAudios(List<Audio> audios) {
		this.audios = audios;
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
