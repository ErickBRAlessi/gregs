package br.ufpr.tcc.gregs.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufpr.tcc.gregs.models.Pagina;
import br.ufpr.tcc.gregs.models.Retorno;
import br.ufpr.tcc.gregs.models.Usuario;
import br.ufpr.tcc.gregs.parser.ParsedComponente;
import br.ufpr.tcc.gregs.parser.requests.PaginaRequest;
import br.ufpr.tcc.gregs.parser.responses.UsuarioResponse;
import br.ufpr.tcc.gregs.service.IComponenteService;
import br.ufpr.tcc.gregs.service.IPaginaService;
import br.ufpr.tcc.gregs.service.IUsuarioService;

@RestController
@CrossOrigin
public class PaginaResource {

	@Autowired
	private IUsuarioService iUsuarioService;

	@Autowired
	private IPaginaService iPaginaService;

	@Autowired
	private IComponenteService iComponenteService;

	@PutMapping(value = "/pagina")
	public ResponseEntity<?> usuarioLogado(Authentication authentication, @RequestBody PaginaRequest paginaRequest) {
		Usuario usuario = iUsuarioService.findByEmail(authentication.getName());
		Pagina p = usuario.getPagina();

		p.getComponentes().clear();


		if (paginaRequest.getComponentes() != null) {
			for (ParsedComponente c : paginaRequest.getComponentes()) {
				p.getComponentes().add(iComponenteService.findComponente(c.getId()));
			}
		}

		iPaginaService.salvar(p);
		usuario = iUsuarioService.find(usuario.getId());
		return new ResponseEntity<>(new Retorno("Pagina Atualizada com Sucesso", new UsuarioResponse(usuario)),
				HttpStatus.OK);
	}

}
