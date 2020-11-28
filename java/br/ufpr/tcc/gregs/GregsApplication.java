package br.ufpr.tcc.gregs;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import br.ufpr.tcc.gregs.service.IPaginaService;
import br.ufpr.tcc.gregs.service.IPessoaService;
import br.ufpr.tcc.gregs.service.IUsuarioService;
import br.ufpr.tcc.gregs.utility.DataGenerator;

@SpringBootApplication
public class GregsApplication {

	// IMPORTANTE, NÃO DELETAR POR NADA!!
	@Value("${pml.lgl}")
	private String pmlLgl;
	
	@Autowired
	DataGenerator dataGenerator;

	public static void main(String[] args) {
		SpringApplication.run(GregsApplication.class, args);
	}

	@Bean
	@Transactional
	void dataBaseFirstLoad() {
		// Carga na base
		dataGenerator.popular();
		System.out.println(pmlLgl);
	}
}
