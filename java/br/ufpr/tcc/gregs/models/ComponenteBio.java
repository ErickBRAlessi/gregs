package br.ufpr.tcc.gregs.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonAlias;

import br.ufpr.tcc.gregs.dto.requests.componentes.ComponenteBioRequest;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ComponenteBio extends Componente {

	@JsonAlias("tipo")
	private static final String TIPO = "ComponenteBio";

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Imagem imagem;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Texto texto;
	
	public ComponenteBio() {}

    public ComponenteBio(Imagem imagem, Texto texto) {
		this.imagem = imagem;
		this.texto = texto;
	}
	
	public ComponenteBio(ComponenteBioRequest componenteBioRequest) {
		super.setId(componenteBioRequest.getId());
		super.setTitulo(componenteBioRequest.getTitulo());
		super.setMostrarTitulo(componenteBioRequest.isMostrarTitulo());
		super.setBackgroundColor(componenteBioRequest.getBackgroundColor());
		super.setForegroundColor(componenteBioRequest.getForegroundColor());
		this.imagem = componenteBioRequest.getImagem();
		this.texto = componenteBioRequest.getTexto();
	}

	public String getTipo() {
		return TIPO;
	}

	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}

	public Texto getTexto() {
		return texto;
	}

}
