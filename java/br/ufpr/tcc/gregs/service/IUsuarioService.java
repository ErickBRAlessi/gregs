package br.ufpr.tcc.gregs.service;

import java.util.List;

import br.ufpr.tcc.gregs.models.Usuario;


public interface IUsuarioService {
	
	List<Usuario> findAll();
	
	Usuario find(Long id);
		
	void salvar(Usuario usuario);
	
	void deletar(Usuario usuario);

	void deletarUsuario(long id);
	
	Usuario findByEmail(String email);


}
