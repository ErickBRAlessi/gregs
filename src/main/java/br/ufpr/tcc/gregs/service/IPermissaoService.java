package br.ufpr.tcc.gregs.service;

import java.util.List;

import br.ufpr.tcc.gregs.models.Permissao;


public interface IPermissaoService {
	
	public List<Permissao> findAll();
	
	public void inserir(Permissao permissao);
	
	public Permissao buscarId(long id);

}
