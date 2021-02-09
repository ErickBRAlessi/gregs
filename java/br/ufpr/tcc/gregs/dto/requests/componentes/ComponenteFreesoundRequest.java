package br.ufpr.tcc.gregs.dto.requests.componentes;

import java.util.List;

import br.ufpr.tcc.gregs.models.Audio;

public class ComponenteFreesoundRequest extends ComponenteRequest {

	
	private List<Audio> audios;
	
	private String username;
	
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
}
