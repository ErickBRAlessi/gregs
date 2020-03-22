package br.ufpr.tcc.gregs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.tcc.gregs.jparepositories.PermissaoRepository;
import br.ufpr.tcc.gregs.jparepositories.UsuarioRepository;
import br.ufpr.tcc.gregs.models.Permissao;
import br.ufpr.tcc.gregs.models.Usuario;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired PermissaoRepository repositoryPerm;

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
		if (usuario.isPresent()) {
			return usuario.get();
		}
		return null;
	}

	@Override
	public Usuario findUsuario(String email) {
		// TODO FIX THIS
		List<Usuario> usuarios = (List<Usuario>) repository.findAll();
		for(Usuario user : usuarios) {
			if(user.getEmail().equalsIgnoreCase(email)) {
				return user;
			}
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
