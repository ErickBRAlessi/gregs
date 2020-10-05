package br.ufpr.tcc.gregs.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonAlias;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ComponenteImagem extends Componente {

	@JsonAlias("tipo")
	private static final String TIPO = "ComponenteImagem";

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Imagem imagem;

	@Column(name = "imagem_descricao")
	private String descricao;

	public String getTipo() {
		return TIPO;
	}

	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
