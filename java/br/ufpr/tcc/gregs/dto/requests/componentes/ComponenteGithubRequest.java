package br.ufpr.tcc.gregs.dto.requests.componentes;

import java.util.List;

import br.ufpr.tcc.gregs.models.Repositorio;

public class ComponenteGithubRequest extends ComponenteRequest {

	
	private List<Repositorio> repos;
	
	private String username;
	
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
}
