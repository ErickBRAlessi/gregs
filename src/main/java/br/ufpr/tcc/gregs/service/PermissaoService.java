package br.ufpr.tcc.gregs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.tcc.gregs.jparepositories.PermissaoRepository;
import br.ufpr.tcc.gregs.models.Permissao;

@Service
public class PermissaoService implements IPermissaoService {

	@Autowired
	private PermissaoRepository repository;

	@Override
	public List<Permissao> findAll() {
		return (List<Permissao>) repository.findAll();
	}

	@Override
	public void inserir(Permissao permissao) {
		repository.save(permissao);
	}

	@Override
	public Permissao buscarId(long id) {
		Optional<Permissao> permissao = repository.findById(id);
		if (permissao.isPresent()) {
			return permissao.get();
		}
		return null;
	}
}
