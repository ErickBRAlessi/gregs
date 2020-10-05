package br.ufpr.tcc.gregs.service;

import java.util.List;

import br.ufpr.tcc.gregs.models.Pagina;


public interface IPaginaService {
	
	public List<Pagina> findAll();
	
	public Pagina find(Long id);
		
	public void salvar(Pagina pagina);
	
	public void deletar(Pagina pagina);

	public void deletar(long id);
	
	public Pagina findByUrl(String url);
	

}
