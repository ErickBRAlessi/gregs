package br.ufpr.tcc.gregs.parser.responses;

import java.util.ArrayList;
import java.util.List;

import br.ufpr.tcc.gregs.models.Componente;
import br.ufpr.tcc.gregs.models.Pagina;
import br.ufpr.tcc.gregs.models.Pessoa;
import br.ufpr.tcc.gregs.models.Usuario;
import br.ufpr.tcc.gregs.parser.ParsedComponente;

public class UsuarioResponse {
	
	private Long id;

	private String email;

	private Pessoa pessoa;

	private InnerPagina pagina;

	private class InnerPagina {
		private long id;
		private String url;
		private List<ParsedComponente> componentes;

		public InnerPagina(Pagina pagina) {
			this.id = pagina.getId();
			this.url = pagina.getUrl();
			if (pagina.getComponentes() != null) {
				this.componentes = new ArrayList<>();
				for (Componente c : pagina.getComponentes()) {
					this.componentes.add(new ParsedComponente(c));
				}
			}
		}

		public long getIdPagina() {
			return id;
		}

		public void setIdPagina(long idPagina) {
			this.id = idPagina;
		}

		public String getUrlPagina() {
			return url;
		}

		public void setUrlPagina(String urlPagina) {
			this.url = urlPagina;
		}

		public List<ParsedComponente> getComponentes() {
			return componentes;
		}

		public void setComponentes(List<ParsedComponente> componentes) {
			this.componentes = componentes;
		}
		
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
			this.setPagina(new InnerPagina(user.getPagina()));
		}
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


	public InnerPagina getPagina() {
		return pagina;
	}

	public void setPagina(InnerPagina pagina) {
		this.pagina = pagina;
	}


}
