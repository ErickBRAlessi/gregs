package br.ufpr.tcc.gregs.service;

import java.util.List;

import br.ufpr.tcc.gregs.models.Pessoa;


public interface IPessoaService {
	
	List<Pessoa> findAll();
	
	Pessoa findPessoa(Long id);
	
	void inserirPessoa(Pessoa pessoa);
	
	void deletarPessoa(Pessoa pessoa);

	void deletarPessoa(long id);
	

}
