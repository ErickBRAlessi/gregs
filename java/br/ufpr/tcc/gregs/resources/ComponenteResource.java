package br.ufpr.tcc.gregs.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufpr.tcc.gregs.models.Componente;
import br.ufpr.tcc.gregs.models.ComponenteImagem;
import br.ufpr.tcc.gregs.models.ComponenteTexto;
import br.ufpr.tcc.gregs.models.Retorno;
import br.ufpr.tcc.gregs.parser.requests.componentes.ComponenteImagemRequest;
import br.ufpr.tcc.gregs.parser.requests.componentes.ComponenteRequest;
import br.ufpr.tcc.gregs.parser.requests.componentes.ComponenteTextoRequest;
import br.ufpr.tcc.gregs.service.IComponenteService;

@RestController
@CrossOrigin
public class ComponenteResource {

	@Autowired
	private IComponenteService iComponenteService;

	@GetMapping("/componente/{id}")
	public ResponseEntity<Retorno> getComponente(@PathVariable("id") Long id) {
		Componente componente;
		try {
			componente = iComponenteService.findComponente(id);
			if (componente == null) {
				return new ResponseEntity<>(new Retorno("Componente não encontrado", null), HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new Retorno(e.getMessage(), e.getClass()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new Retorno("Sucesso", componente), HttpStatus.OK);
	}

	@PostMapping("/componente/")
	public ResponseEntity<Retorno> inserirComponente(@RequestBody ComponenteRequest componenteRequest) {
		Componente componente = null;
		if (componenteRequest instanceof ComponenteTextoRequest) {
			componente = new ComponenteTexto((ComponenteTextoRequest) componenteRequest);
		}
		if (componenteRequest instanceof ComponenteImagemRequest) {
			componente = new ComponenteImagem((ComponenteImagemRequest) componenteRequest);
		}
		// adicionar cast e teste para cada tipo de componente
		if (componente != null) {
			iComponenteService.salvarComponente(componente);
			componente = iComponenteService.findComponente(componente.getId());
			return new ResponseEntity<>(new Retorno("Componente Inserido com Sucesso", componente), HttpStatus.CREATED);
		}
		return new ResponseEntity<>(new Retorno("Request Não Reconhecido", null), HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/componente/{id}")
	public ResponseEntity<Retorno> atualizarComponente(@PathVariable("id") Long id,
			@RequestBody ComponenteRequest componenteRequest) {
		Componente componente = iComponenteService.findComponente(id);
		if (componente != null) {
			componenteRequest.setId(id);
			if (componenteRequest instanceof ComponenteTextoRequest) {
				componente = new ComponenteTexto((ComponenteTextoRequest) componenteRequest);
			}
			iComponenteService.salvarComponente(componente);
			componente = iComponenteService.findComponente(componente.getId());
			return new ResponseEntity<>(new Retorno("Sucesso", componente), HttpStatus.OK);
		}
		return new ResponseEntity<>(new Retorno("Componente Não Reconhecido ou Id não Encontrado", null),
				HttpStatus.NOT_FOUND);
	}
	
/*
	@DeleteMapping("/componente/{id}")
	public ResponseEntity<Retorno> deletarComponente(@PathVariable("id") Long id) {
		try {
			Componente componente = iComponenteService.findComponente(id);
			iComponenteService.deletarComponente(componente);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new Retorno(e.getMessage(), e.getClass()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new Retorno("Componente Deletado Com Sucesso", null), HttpStatus.OK);
	}
*/
}
