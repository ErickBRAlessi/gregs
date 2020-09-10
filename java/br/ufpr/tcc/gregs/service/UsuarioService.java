package br.ufpr.tcc.gregs.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import br.ufpr.tcc.gregs.jparepositories.UsuarioRepository;
import br.ufpr.tcc.gregs.models.Usuario;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {

	@Autowired
	private UsuarioRepository repository;
	
	public Usuario findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) repository.findAll();
	}

	@Override
	public void salvarUsuario(Usuario usuario) {
		repository.save(usuario);
	}

	@Override
	public Usuario findUsuario(Long id) {
		Optional<Usuario> usuario = repository.findById(id);
		if (usuario.isPresent()) {
			return usuario.get();
		}
		return null;
	}


	@Override
	public void deletarUsuario(Usuario usuario) {
		repository.delete(usuario);
	}

	@Override
	public void deletarUsuario(long id) {
		Usuario user = findUsuario(id);
		repository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		return findByEmail(email);
	}

}
