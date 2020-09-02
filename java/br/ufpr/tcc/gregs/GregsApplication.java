package br.ufpr.tcc.gregs;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.SessionFactoryUtils;

import br.ufpr.tcc.gregs.configurations.SessionFactory;
import br.ufpr.tcc.gregs.graph.Neo4JSessionFactory;
import br.ufpr.tcc.gregs.models.Permissao;
import br.ufpr.tcc.gregs.models.Pessoa;
import br.ufpr.tcc.gregs.models.Usuario;
import br.ufpr.tcc.gregs.security.MD5;
import br.ufpr.tcc.gregs.service.IPermissaoService;
import br.ufpr.tcc.gregs.service.IPessoaService;
import br.ufpr.tcc.gregs.service.IUsuarioService;
import br.ufpr.tcc.gregs.service.PessoaService;

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
			
			Usuario userAdm = new Usuario(1, "adm@adm.com", MD5.toMD5(ADMIN), pessoaAdm, permissoesUserAdm);
			Usuario userCli = new Usuario(2, "cli@cli.com", MD5.toMD5(ADMIN), pessoaCli, permissoesUserCli);
			Usuario userVis = new Usuario(3, "vis@vis.com", MD5.toMD5(ADMIN), pessoaVis, permissoesUserVis);
			iUsuarioService.inserirUsuario(userAdm);
			iUsuarioService.inserirUsuario(userCli);
			iUsuarioService.inserirUsuario(userVis);
			
			
			Usuario u = iUsuarioService.findByEmail("adm@adm.com");
			System.out.println(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
