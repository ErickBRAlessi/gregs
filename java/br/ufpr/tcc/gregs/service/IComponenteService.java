package br.ufpr.tcc.gregs.service;

import br.ufpr.tcc.gregs.models.Componente;


public interface IComponenteService {
		
	Componente findComponente(Long id);
		
	void salvarComponente(Componente componente);
	
	void deletarComponente(Componente componente);
	
}
