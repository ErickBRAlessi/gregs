package br.ufpr.tcc.gregs;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
