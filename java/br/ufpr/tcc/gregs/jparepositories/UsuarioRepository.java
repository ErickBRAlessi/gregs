package br.ufpr.tcc.gregs.jparepositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufpr.tcc.gregs.models.Usuario;


@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	 @Query("select u from Usuario u join fetch u.permissoes join fetch u.pagina where u.email = ?1")
	 Usuario findByEmail(String email);
}