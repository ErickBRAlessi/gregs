package br.ufpr.tcc.gregs.parser.responses;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.ufpr.tcc.gregs.models.Componente;
import br.ufpr.tcc.gregs.models.Pagina;
import br.ufpr.tcc.gregs.models.Permissao;
import br.ufpr.tcc.gregs.models.Pessoa;
import br.ufpr.tcc.gregs.models.Usuario;

public class UsuarioResponse {
	
	private Long id;

	private String email;

	private Pessoa pessoa;

	private Set<Permissao> permissoes;

	private InnerPagina pagina;

	private class InnerComponente {
		private long id;
		private String tipo;

		public InnerComponente(Componente componente) {
			this.id = componente.getId();
			this.tipo = componente.getClass().getSimpleName();
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
		
		
	}

	private class InnerPagina {
		private long id;
		private String url;
		private List<InnerComponente> componentes;

		public InnerPagina(Pagina pagina) {
			this.id = pagina.getId();
			this.url = pagina.getUrl();
			if (pagina.getComponentes() != null) {
				this.componentes = new ArrayList<>();
				for (Componente c : pagina.getComponentes()) {
					this.componentes.add(new InnerComponente(c));
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

		public List<InnerComponente> getComponentes() {
			return componentes;
		}

		public void setComponentes(List<InnerComponente> componentes) {
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
		if (user.getPermissoes() != null) {
			this.permissoes = user.getPermissoes();
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

	public Set<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	public InnerPagina getPagina() {
		return pagina;
	}

	public void setPagina(InnerPagina pagina) {
		this.pagina = pagina;
	}


}
