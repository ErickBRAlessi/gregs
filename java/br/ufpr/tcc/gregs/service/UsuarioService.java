package br.ufpr.tcc.gregs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	public void salvar(Usuario usuario) {
		usuario.setPassword(bcryptEncoder.encode(usuario.getPassword()));
		repository.save(usuario);
	}

	@Override
	public Usuario find(Long id) {
		Optional<Usuario> usuario = repository.findById(id);
		if (usuario.isPresent()) {
			return usuario.get();
		}
		return null;
	}


	@Override
	public void deletar(Usuario usuario) {
		repository.delete(usuario);
	}

	@Override
	public void deletarUsuario(long id) {
		repository.deleteById(id);
	}
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario user = repository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Usuario não encontrado com email : " + email);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				new ArrayList<>());
	}

}
