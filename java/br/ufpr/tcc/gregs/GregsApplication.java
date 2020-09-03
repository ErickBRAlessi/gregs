package br.ufpr.tcc.gregs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.ufpr.tcc.gregs.models.Componente;
import br.ufpr.tcc.gregs.models.ComponenteImagem;
import br.ufpr.tcc.gregs.models.ComponenteTexto;
import br.ufpr.tcc.gregs.models.Pagina;
import br.ufpr.tcc.gregs.models.Permissao;
import br.ufpr.tcc.gregs.models.Pessoa;
import br.ufpr.tcc.gregs.models.Usuario;
import br.ufpr.tcc.gregs.security.MD5;
import br.ufpr.tcc.gregs.service.IPermissaoService;
import br.ufpr.tcc.gregs.service.IPessoaService;
import br.ufpr.tcc.gregs.service.IUsuarioService;

@SpringBootApplication
public class GregsApplication {
	private static final String ADMIN = "admin";

	@Autowired
	IPermissaoService iPermissaoService;
	
	@Autowired
	IPessoaService iPessoaService;

	@Autowired
	IUsuarioService iUsuarioService;
	

	public static void main(String[] args) {
		SpringApplication.run(GregsApplication.class, args);
	}

	@Bean
	void dataBaseFirstLoad() {
		// Carga na base
		try {
			Set<Permissao> permissoesUserAdm = new HashSet<>();
			Set<Permissao> permissoesUserCli = new HashSet<>();
			Set<Permissao> permissoesUserVis = new HashSet<>();

			Permissao admin = new Permissao(1, ADMIN);
			Permissao cliente = new Permissao(2, "cliente");
			Permissao visitante = new Permissao(3, "visitante");
			permissoesUserAdm.add(admin);
			permissoesUserAdm.add(cliente);
			permissoesUserAdm.add(visitante);
			// cadastra no bd as permissoes padrões
			for (Permissao p : permissoesUserAdm) {
				iPermissaoService.inserir(p);
			}

			permissoesUserCli.add(cliente);
			permissoesUserCli.add(visitante);
			permissoesUserVis.add(visitante);
			
			Pessoa pessoaAdm = new Pessoa("ADMINISTRADOR", "ADMLASTNAME");
			Pessoa pessoaCli = new Pessoa("CLIENTE", "CLILASTNAME");
			Pessoa pessoaVis = new Pessoa("VISITANTE", "VISLASTNAME");
			
			iPessoaService.inserirPessoa(pessoaAdm);
			iPessoaService.inserirPessoa(pessoaCli);
			iPessoaService.inserirPessoa(pessoaVis);
			
			Usuario userAdm = new Usuario("adm@adm.com", MD5.toMD5(ADMIN), pessoaAdm, permissoesUserAdm);
			Usuario userCli = new Usuario("cli@cli.com", MD5.toMD5(ADMIN), pessoaCli, permissoesUserCli);
			Usuario userVis = new Usuario("vis@vis.com", MD5.toMD5(ADMIN), pessoaVis, permissoesUserVis);
			
			
			userCli.setPagina(new Pagina("validurl", new ArrayList<Componente>()));
			
			ComponenteImagem c1 = new ComponenteImagem();
			ComponenteTexto c2 = new ComponenteTexto();
			
			
			c1.setImagem("img");
			c1.setPagina(userCli.getPagina());
			
			c2.setTexto("texto");
			c2.setPagina(userCli.getPagina());
			
			userCli.getPagina().getComponentes().add(c1);
			userCli.getPagina().getComponentes().add(c2);			
			
			iUsuarioService.inserirUsuario(userAdm);
			iUsuarioService.inserirUsuario(userCli);
			iUsuarioService.inserirUsuario(userVis);
			
			//TODO: fixit
			Usuario u = iUsuarioService.findByEmail("cli@cli.com");
			System.out.println(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
