package br.ufpr.tcc.gregs.dto.requests.componentes;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "tipo")
@JsonSubTypes({
	@JsonSubTypes.Type(value = ComponenteBioRequest.class, name = "ComponenteBio"),
    @JsonSubTypes.Type(value = ComponenteTextoRequest.class, name = "ComponenteTexto"),
    @JsonSubTypes.Type(value = ComponenteImagemRequest.class, name = "ComponenteImagem")
//adicionar outros tipos de request aqui
})
public abstract class ComponenteRequest {

	private long id;

	private String titulo;
	
	private boolean mostrarTitulo;

	private String backgroundColor;
	
	private String foregroundColor;

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getForegroundColor() {
		return foregroundColor;
	}

	public void setForegroundColor(String foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	public boolean isMostrarTitulo() {
		return mostrarTitulo;
	}

	public void setMostrarTitulo(boolean mostrarTitulo) {
		this.mostrarTitulo = mostrarTitulo;
	}

	
	
}
