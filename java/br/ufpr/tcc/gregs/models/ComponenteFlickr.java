package br.ufpr.tcc.gregs.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ComponenteFlickr extends Componente {

	@Column(name = "usuario")
	private String username;

	@Column(name = "mostrar_username")
	private boolean mostrarUsername;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "profile_url")
	private String profileUrl;

	@Column(name = "avatar_img")
	private String avatarImgUrl;

	@Column(name = "mostrar_avatar")
	private boolean mostrarAvatar;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Imagem> imagens;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getAvatarImgUrl() {
		return avatarImgUrl;
	}

	public void setAvatarImgUrl(String avatarImgUrl) {
		this.avatarImgUrl = avatarImgUrl;
	}

	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}

	public boolean isMostrarUsername() {
		return mostrarUsername;
	}

	public void setMostrarUsername(boolean mostrarUsername) {
		this.mostrarUsername = mostrarUsername;
	}

	public boolean isMostrarAvatar() {
		return mostrarAvatar;
	}

	public void setMostrarAvatar(boolean mostrarAvatar) {
		this.mostrarAvatar = mostrarAvatar;
	}

	@Override
	public String toString() {
		return "ComponenteFlickr [username=" + username + ", descricao=" + descricao + ", profileUrl=" + profileUrl
				+ ", avatarImgUrl=" + avatarImgUrl + ", imagens=" + imagens + "]";
	}

}
