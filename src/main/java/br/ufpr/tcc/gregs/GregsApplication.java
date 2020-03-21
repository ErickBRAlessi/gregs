package br.ufpr.tcc.gregs;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.ufpr.tcc.gregs.models.Permissao;
import br.ufpr.tcc.gregs.models.Usuario;
import br.ufpr.tcc.gregs.service.IPermissaoService;
import br.ufpr.tcc.gregs.service.IUsuarioService;

@SpringBootApplication
public class GregsApplication {
	
	@Autowired
	IPermissaoService iPermissaoService;
	
	@Autowired
	IUsuarioService iUsuarioService;

	public static void main(String[] args) {
		SpringApplication.run(GregsApplication.class, args);
	}

	@Bean
	void dataBaseFirstLoad() {		
		System.out.println("CARGA NA BASE DE DADOS INICIAL");
		Set<Permissao> permissoesUserAdm = new HashSet<>();
		Set<Permissao> permissoesUserCli = new HashSet<>();
		Set<Permissao> permissoesUserVis = new HashSet<>();

		Permissao admin = new Permissao(1,"admin");
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
		
		Usuario userAdm = new Usuario(1, "ADMINISTRADOR", "admin", permissoesUserAdm);
		Usuario userCli = new Usuario(2, "CLIENTE", "admin", permissoesUserCli);
		Usuario userVis = new Usuario(3, "VISITANTE", "admin", permissoesUserVis);
		
		iUsuarioService.inserirUsuario(userAdm);
		iUsuarioService.inserirUsuario(userCli);
		iUsuarioService.inserirUsuario(userVis);		
	}	
}
