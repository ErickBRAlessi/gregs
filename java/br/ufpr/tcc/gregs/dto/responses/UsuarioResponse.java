package br.ufpr.tcc.gregs.dto.responses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import br.ufpr.tcc.gregs.dto.ParsedPagina;
import br.ufpr.tcc.gregs.models.Imagem;
import br.ufpr.tcc.gregs.models.Pessoa;
import br.ufpr.tcc.gregs.models.Usuario;
import br.ufpr.tcc.gregs.utility.MotorBusca;

public class UsuarioResponse {
	
	private MotorBusca motorBusca = new MotorBusca();

	private Long id;

	private String email;

	private Pessoa pessoa;

	private ParsedPagina pagina;

	private Imagem imagemUsuario;
	
	private List<String> tags;
	
	public UsuarioResponse() {
		
	}
	
	public UsuarioResponse(Usuario user) {
		this.id = user.getId();
		if (user.getEmail() != null) {
			this.email = user.getEmail();
		}
		if (user.getPessoa() != null) {
			this.pessoa = user.getPessoa();
		}
		if (user.getPagina() != null) {
			this.setPagina(new ParsedPagina(user.getPagina()));
		}
		if(user.getImagemUsuario() == null) {
			this.imagemUsuario = new Imagem();
		}
		else {
			this.imagemUsuario = user.getImagemUsuario();
		}
		this.setTags(motorBusca.buscarTagsdeUsuario(user));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public ParsedPagina getPagina() {
		return pagina;
	}

	public void setPagina(ParsedPagina pagina) {
		this.pagina = pagina;
	}

	public Imagem getImagemUsuario() {
		return imagemUsuario;
	}

	public void setImagemUsuario(Imagem imagemUsuario) {
		this.imagemUsuario = imagemUsuario;
	}
	
	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public boolean equals(Object obj) {
		if ( obj instanceof UsuarioResponse) {
			UsuarioResponse newObj = (UsuarioResponse) obj;
			return newObj.getId() == this.getId();
		}
		return false;
}

}
