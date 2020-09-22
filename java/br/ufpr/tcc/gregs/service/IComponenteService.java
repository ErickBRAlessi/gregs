package br.ufpr.tcc.gregs.service;

import java.util.List;

import br.ufpr.tcc.gregs.models.Componente;
import br.ufpr.tcc.gregs.models.Usuario;


public interface IComponenteService {
		
	public Componente findComponente(Long id);
		
	public void salvarComponente(Componente componente);
	
	public void deletarComponente(Componente componente);
	
}
