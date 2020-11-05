package br.ufpr.tcc.gregs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.tcc.gregs.jparepositories.PessoaRepository;
import br.ufpr.tcc.gregs.models.Pessoa;

@Service
public class PessoaService implements IPessoaService {

	@Autowired
	private PessoaRepository repository;

	@Override
	public Pessoa findPessoa(Long id) {
		Optional<Pessoa> pessoa = (Optional<Pessoa>) repository.findById(id);
		if (pessoa.isPresent()) {
			return pessoa.get();
		}
		return null;
	}

	@Override
	public void inserirPessoa(Pessoa pessoa) {
		repository.save(pessoa);
	}

	@Override
	public void deletarPessoa(Pessoa pessoa) {
		repository.delete(pessoa);		
	}

	@Override
	public void deletarPessoa(long id) {
		repository.deleteById(id);		
	}

	@Override
	public List<Pessoa> findAll() {
		return (List<Pessoa>) repository.findAll();
	}

}
