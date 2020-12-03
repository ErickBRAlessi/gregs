package br.ufpr.tcc.gregs.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes(// adicionar aqui subtypes
		{ @JsonSubTypes.Type(value = ComponenteBio.class, name = "ComponenteBio"), 
		@JsonSubTypes.Type(value = ComponenteImagem.class, name = "ComponenteImagem"),
		@JsonSubTypes.Type(value = ComponenteFlickr.class, name = "ComponenteFlickr"),
		@JsonSubTypes.Type(value = ComponenteTexto.class, name = "ComponenteTexto") })
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Componente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_componente_id", nullable = false)
	private long id;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "mostrar_titulo")
	private boolean mostrarTitulo;

	@Column(name = "background_color")
	private String backgroundColor;

	@Column(name = "foreground_color")
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
