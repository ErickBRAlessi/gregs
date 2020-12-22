package br.ufpr.tcc.gregs.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "imagem")
public class Imagem {
	
	public Imagem() {}

    public Imagem(String nome, byte[] base64Img, String url) {
		this.nome = nome;
		this.base64Img = base64Img;
		this.url = url;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_imagem_id", nullable = false)
	private long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "base_64_img")
	private byte[] base64Img;

	@Column(name = "url",  nullable = true)
	private String url;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getBase64Img() {
		return base64Img;
	}

	public void setBase64Img(byte[] base64Img) {
		this.base64Img = base64Img;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
