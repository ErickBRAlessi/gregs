package br.ufpr.tcc.gregs.service;

import br.ufpr.tcc.gregs.models.Componente;


public interface IComponenteService {
		
	public Componente findComponente(Long id);
		
	public void salvarComponente(Componente componente);
	
	public void deletarComponente(Componente componente);
	
}
