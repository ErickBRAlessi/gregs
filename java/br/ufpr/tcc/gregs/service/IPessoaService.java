package br.ufpr.tcc.gregs.service;

import java.util.List;

import br.ufpr.tcc.gregs.models.Pessoa;


public interface IPessoaService {
	
	public List<Pessoa> findAll();
	
	public Pessoa findPessoa(Long id);	
	
	public void inserirPessoa(Pessoa pessoa);
	
	public void deletarPessoa(Pessoa pessoa);

	void deletarPessoa(long id);
	

}
