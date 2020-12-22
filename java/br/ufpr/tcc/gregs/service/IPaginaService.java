package br.ufpr.tcc.gregs.service;

import java.util.List;

import br.ufpr.tcc.gregs.models.Pagina;


public interface IPaginaService {
	
	List<Pagina> findAll();
	
	Pagina find(Long id);
		
	void salvar(Pagina pagina);
	
	void deletar(Pagina pagina);

	void deletar(long id);
	
	Pagina findByUrl(String url);
	

}
