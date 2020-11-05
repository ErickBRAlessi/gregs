package br.ufpr.tcc.gregs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.tcc.gregs.jparepositories.PaginaRepository;
import br.ufpr.tcc.gregs.models.Pagina;

@Service
public class PaginaService implements IPaginaService {

	@Autowired
	private PaginaRepository repository;
	
	@Override
	public List<Pagina> findAll() {
		return (List<Pagina>) repository.findAll();
	}

	@Override
	public void salvar(Pagina Pagina) {
		repository.save(Pagina);
	}

	@Override
	public Pagina find(Long id) {
		Optional<Pagina> Pagina = repository.findById(id);
		if (Pagina.isPresent()) {
			return Pagina.get();
		}
		return null;
	}


	@Override
	public void deletar(Pagina Pagina) {
		repository.delete(Pagina);
	}


	@Override
	public void deletar(long id) {
		repository.deleteById(id);
	}

	@Override
	public Pagina findByUrl(String url) {
		return repository.findByUrl(url);
	}


}
