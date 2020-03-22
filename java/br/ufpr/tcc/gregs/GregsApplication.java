package br.ufpr.tcc.gregs;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.ufpr.tcc.gregs.models.Permissao;
import br.ufpr.tcc.gregs.models.Usuario;
import br.ufpr.tcc.gregs.security.MD5;
import br.ufpr.tcc.gregs.service.IPermissaoService;
import br.ufpr.tcc.gregs.service.IUsuarioService;

@SpringBootApplication
public class GregsApplication {
	private static final String ADMIN = "admin";
	
	@Autowired
	IPermissaoService iPermissaoService;
	
	@Autowired
	IUsuarioService iUsuarioService;

	public static void main(String[] args) {
		SpringApplication.run(GregsApplication.class, args);
	}

	@Bean
	void dataBaseFirstLoad() {		
		//Carga na base
		Set<Permissao> permissoesUserAdm = new HashSet<>();
		Set<Permissao> permissoesUserCli = new HashSet<>();
		Set<Permissao> permissoesUserVis = new HashSet<>();

		Permissao admin = new Permissao(1, ADMIN);
		Permissao cliente = new Permissao(2,"cliente");
		Permissao visitante = new Permissao(3, "visitante");
		permissoesUserAdm.add(admin);
		permissoesUserAdm.add(cliente);
		permissoesUserAdm.add(visitante);
		//cadastra no bd as permissoes padrões
		for(Permissao p : permissoesUserAdm) {
			iPermissaoService.inserir(p);
		}
		
		permissoesUserCli.add(cliente);
		permissoesUserCli.add(visitante);
		
		permissoesUserVis.add(visitante);
		Usuario userAdm = new Usuario(1, "adm@adm.com", "ADMINISTRADOR", MD5.toMD5(ADMIN), permissoesUserAdm);
		Usuario userCli = new Usuario(2, "cli@cli.com", "CLIENTE", MD5.toMD5(ADMIN), permissoesUserCli);
		Usuario userVis = new Usuario(3, "vis@vis.com", "VISITANTE", MD5.toMD5(ADMIN), permissoesUserVis);
		
		iUsuarioService.inserirUsuario(userAdm);
		iUsuarioService.inserirUsuario(userCli);
		iUsuarioService.inserirUsuario(userVis);		
	}	
}
