package br.ufpr.tcc.gregs.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ufpr.tcc.gregs.jparepositories.UsuarioRepository;
import br.ufpr.tcc.gregs.models.Usuario;

@Service
public class UsuarioService implements IUsuarioService {

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
		usuario.removerTodasPermissoes();
		repository.delete(usuario);
	}

	@Override
	public void deletarUsuario(long id) {
		Usuario user = findUsuario(id);
		user.removerTodasPermissoes();
		repository.deleteById(id);
	}

}
