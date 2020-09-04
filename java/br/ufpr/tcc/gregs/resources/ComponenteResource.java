package br.ufpr.tcc.gregs.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.ufpr.tcc.gregs.models.Componente;
import br.ufpr.tcc.gregs.models.Retorno;
import br.ufpr.tcc.gregs.service.IComponenteService;
import br.ufpr.tcc.gregs.service.IPermissaoService;
import br.ufpr.tcc.gregs.service.IPessoaService;
import br.ufpr.tcc.gregs.service.IUsuarioService;

@RestController
public class ComponenteResource {

	@Autowired
	private IUsuarioService iUsuarioService;

	@Autowired
	private IPermissaoService iPermissaoService;

	@Autowired
	private IPessoaService iPessoaService;

	@Autowired
	private IComponenteService iComponenteService;

	// TESTA SE USUARIO É ADM
	@GetMapping("/componente/{id}")
	public Retorno getComponente(@PathVariable("id") Long id) {
		Componente componente;
		try {
			componente = iComponenteService.findComponente(id);
			if (componente == null) {
				return new Retorno("Componente não encontrado", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Retorno(e.getMessage(), e.getClass());
		}
		return new Retorno("Sucesso", componente);
	}

}
