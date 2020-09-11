package br.ufpr.tcc.gregs;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.ufpr.tcc.gregs.models.Componente;
import br.ufpr.tcc.gregs.models.ComponenteFlickr;
import br.ufpr.tcc.gregs.models.ComponenteImagem;
import br.ufpr.tcc.gregs.models.ComponenteTexto;
import br.ufpr.tcc.gregs.models.Imagem;
import br.ufpr.tcc.gregs.models.Pagina;
import br.ufpr.tcc.gregs.models.Pessoa;
import br.ufpr.tcc.gregs.models.Texto;
import br.ufpr.tcc.gregs.models.Usuario;
import br.ufpr.tcc.gregs.service.IPessoaService;
import br.ufpr.tcc.gregs.service.IUsuarioService;

@SpringBootApplication
public class GregsApplication {	
	@Autowired
	IPessoaService iPessoaService;

	@Autowired
	IUsuarioService iUsuarioService;
	

	public static void main(String[] args) {
		SpringApplication.run(GregsApplication.class, args);
	}

	@Bean
	@Transactional
	void dataBaseFirstLoad() {
		// Carga na base
		try {
			Pessoa pessoaAdm = new Pessoa("ADMINISTRADOR", "ADMLASTNAME");
			Pessoa pessoaCli = new Pessoa("CLIENTE", "CLILASTNAME");
			Pessoa pessoaVis = new Pessoa("VISITANTE", "VISLASTNAME");
			
			Usuario userAdm = new Usuario("adm@adm.com", "adm", pessoaAdm);
			Usuario userCli = new Usuario("cli@cli.com", "cli", pessoaCli);
			Usuario userVis = new Usuario("vis@vis.com", "vis", pessoaVis);		
			
			userCli.setPagina(new Pagina("validurl", new ArrayList<Componente>()));
			
			ComponenteImagem c1 = new ComponenteImagem();
			ComponenteTexto c2 = new ComponenteTexto();
			ComponenteFlickr c3 = new ComponenteFlickr();	
			
			c1.setImagem(new Imagem());
			
			ArrayList<Texto> textos = new ArrayList<>();
			textos.add(new Texto("titulo 1","eu sou um texto veridico 1"));
			textos.add(new Texto("titulo 2", "eu sou o segundo texto"));
			textos.add(new Texto("titulo 3", "me come"));
			
			c2.setTextos(textos);
			
			c3.setBackgroundColor("#FFFFFF");
			c3.setForegroundColor("#F4F4F4");
			List<Imagem> imgs = new ArrayList<>();
			imgs.add(new Imagem());
			imgs.add(new Imagem());
			c3.setImagens(imgs);
				
			userCli.getPagina().getComponentes().add(c1);
			userCli.getPagina().getComponentes().add(c2);		
			userCli.getPagina().getComponentes().add(c3);
			
			iUsuarioService.salvar(userAdm);
			iUsuarioService.salvar(userCli);
			iUsuarioService.salvar(userVis);
			
			Usuario u = iUsuarioService.findByEmail("cli@cli.com");
			System.out.println(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
