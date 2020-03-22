package br.ufpr.tcc.gregs.jparepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufpr.tcc.gregs.models.Usuario;


@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}