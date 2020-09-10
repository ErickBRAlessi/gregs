package br.ufpr.tcc.gregs.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "permissao")
public class Permissao implements GrantedAuthority{

	private static final long serialVersionUID = -1838859751890045635L;
	
	public Permissao(String name) {
		this.name = name.trim().toUpperCase();
	}

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	/**
	 * unique and transformed to GrantedAuthority,can be used in Spring expression
	 * hasRole, etc
	 **/
	@Column(nullable = false, unique = true)
	private String name;

	@Override
	public String getAuthority() {
		return name;
	}
}
