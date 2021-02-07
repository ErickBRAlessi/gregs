package br.ufpr.tcc.gregs.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "repositorio")
public class Repositorio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_repositorio_id", nullable = false)
	private long id;

	@Column(name = "secret_id")
	private long secretId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSecretId() {
		return secretId;
	}

	public void setSecretId(long secretId) {
		this.secretId = secretId;
	}
}