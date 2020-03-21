package br.ufpr.tcc.gregs.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufpr.tcc.gregs.models.Permissao;
import br.ufpr.tcc.gregs.models.Retorno;
import br.ufpr.tcc.gregs.service.IPermissaoService;

@RestController
public class PermissaoResource {
	
	
	@Autowired
	private IPermissaoService iPermissaoService;
	
	@GetMapping("/permissao/all")
	public Retorno listaPermissoes() {
		List<Permissao> permissoes;
		try {
			permissoes = iPermissaoService.findAll();
		}catch(Exception e) {
			e.printStackTrace();
			return new Retorno(e.getMessage(), e.getClass());
		}
		return new Retorno("Sucesso" , permissoes);
	}
	

}
