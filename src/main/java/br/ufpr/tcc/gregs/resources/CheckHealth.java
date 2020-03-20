package br.ufpr.tcc.gregs.resources;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufpr.tcc.gregs.models.Retorno;

@RestController
public class CheckHealth {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/health")
	public Retorno greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Retorno(String.format(template, name), null);
	}
}
