package br.ufpr.tcc.gregs.service;

import java.util.List;

import br.ufpr.tcc.gregs.models.Usuario;


public interface IUsuarioService {
	
	public List<Usuario> findAll();
	
	public Usuario findUsuario(Long id);
		
	public void salvarUsuario(Usuario usuario);
	
	public void deletarUsuario(Usuario usuario);

	void deletarUsuario(long id);
	
	public Usuario findByEmail(String email);
	

}
