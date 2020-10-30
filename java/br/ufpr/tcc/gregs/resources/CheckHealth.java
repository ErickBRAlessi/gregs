package br.ufpr.tcc.gregs.resources;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufpr.tcc.gregs.models.Retorno;

@RestController
@CrossOrigin
public class CheckHealth {

	private static final String TEMPLATE = "Hello, %s!";

	@GetMapping("/health")
	public Retorno greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Retorno(String.format(TEMPLATE, name), null);
	}
		
}
