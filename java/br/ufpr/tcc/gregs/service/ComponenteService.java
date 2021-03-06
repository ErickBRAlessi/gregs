package br.ufpr.tcc.gregs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.tcc.gregs.jparepositories.ComponenteRepository;
import br.ufpr.tcc.gregs.models.Componente;

@Service
public class ComponenteService implements IComponenteService {

	@Autowired
	private ComponenteRepository repository;

	@Override
	public Componente findComponente(Long id) {
		Optional<Componente> componente = repository.findById(id);
		if (componente.isPresent()) {
			return componente.get();
		}
		return null;
	}

	@Override
	public void salvarComponente(Componente componente) {
		repository.save(componente);
	}

	@Override
	public void deletarComponente(Componente componente) {
		repository.delete(componente);
	}
	

}
