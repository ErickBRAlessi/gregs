package br.ufpr.tcc.gregs.jparepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufpr.tcc.gregs.models.Componente;


@Repository
public interface ComponenteRepository extends CrudRepository<Componente, Long> {
	
	

}