package br.ufpr.tcc.gregs.dto.requests.componentes;

import java.util.List;

import br.ufpr.tcc.gregs.models.FlickrItem;

public class ComponenteFlickrNeoRequest extends ComponenteRequest {

	
	private List<FlickrItem> imagensFlickr;
	
	private String username;
	
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

	
}
