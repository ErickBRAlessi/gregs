package br.ufpr.tcc.gregs.jparepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufpr.tcc.gregs.models.Componente;
import br.ufpr.tcc.gregs.models.Permissao;


@Repository
public interface ComponenteRepository extends CrudRepository<Componente, Long> {
	
	

}