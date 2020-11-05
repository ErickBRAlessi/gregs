package br.ufpr.tcc.gregs.jparepositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufpr.tcc.gregs.models.Pagina;


@Repository
public interface PaginaRepository extends CrudRepository<Pagina, Long> {

	 @Query("select p from Pagina p join fetch p.componentes where p.url = ?1")
	 Pagina findByUrl(String url);
}