package br.ufpr.tcc.gregs.jparepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufpr.tcc.gregs.models.Permissao;


@Repository
public interface PermissaoRepository extends CrudRepository<Permissao, Long> {

}