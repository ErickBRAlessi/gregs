package br.ufpr.tcc.gregs.service;

import java.util.List;

import br.ufpr.tcc.gregs.models.Usuario;


public interface IUsuarioService {
	
	public List<Usuario> findAll();
	
	public Usuario findUsuario(Long id);
	
	public Usuario findUsuario(String email);
	
	public void inserirUsuario(Usuario usuario);
	
	public void deletarUsuario(Usuario usuario);

	void deletarUsuario(long id);
	

}
