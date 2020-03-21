package br.ufpr.tcc.gregs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.tcc.gregs.jparepositories.UsuarioRepository;
import br.ufpr.tcc.gregs.models.Usuario;

@Service
public class UsuarioService implements IUsuarioService{
	
	@Autowired
	private UsuarioRepository repository;
 	
	@Override
    public List<Usuario> findAll() {
        return (List<Usuario>) repository.findAll();
    }
	
	@Override
	public void inserirUsuario(Usuario usuario) {
		repository.save(usuario);
	}

	@Override
	public Usuario findUsuario(Long id) {
		Optional<Usuario> usuario = repository.findById(id);
		if(usuario.isPresent()) {
			return usuario.get();
		}
		return null;
	}

	@Override
	public List<Usuario> findUsuario(String nome) {
		//TODO FIX THIS
		return findAll();
	}

	@Override
	public void deletarUsuario(Usuario usuario) {
		repository.delete(usuario);
	}
	
	@Override
	public void deletarUsuario(long id) {
		repository.deleteById(id);
	}
}