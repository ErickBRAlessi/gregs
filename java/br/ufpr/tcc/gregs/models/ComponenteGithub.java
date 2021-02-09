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

import br.ufpr.tcc.gregs.dto.requests.componentes.ComponenteGithubRequest;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ComponenteGithub extends Componente {

	@JsonAlias("tipo")
	private static final String TIPO = "ComponenteGithub";
	
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Repositorio> repos;
    
    @Column(name = "username")
	private String username;
	
	public ComponenteGithub() {}

    public ComponenteGithub(ComponenteGithubRequest componenteGithubRequest) {
		super.setId(componenteGithubRequest.getId());
		super.setOrdem(componenteGithubRequest.getOrdem());
		super.setTitulo(componenteGithubRequest.getTitulo());
		super.setMostrarTitulo(componenteGithubRequest.isMostrarTitulo());
		super.setBackgroundColor(componenteGithubRequest.getBackgroundColor());
		super.setForegroundColor(componenteGithubRequest.getForegroundColor());
		this.setRepos(componenteGithubRequest.getRepos());
		this.setUsername(componenteGithubRequest.getUsername());
	}

	public List<Repositorio> getRepos() {
		return repos;
	}

	public void setRepos(List<Repositorio> repos) {
		this.repos = repos;
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
