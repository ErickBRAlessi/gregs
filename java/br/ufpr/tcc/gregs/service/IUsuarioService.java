package br.ufpr.tcc.gregs.service;

import java.util.List;

import br.ufpr.tcc.gregs.models.Usuario;


public interface IUsuarioService {
	
	public List<Usuario> findAll();
	
	public Usuario find(Long id);
		
	public void salvar(Usuario usuario);
	
	public void deletar(Usuario usuario);

	void deletarUsuario(long id);
	
	public Usuario findByEmail(String email);


}
