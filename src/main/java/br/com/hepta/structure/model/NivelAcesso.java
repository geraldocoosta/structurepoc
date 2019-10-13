package br.com.hepta.structure.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NivelAcesso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false, unique = true)	
	private String nomeNivelAcesso;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeNivelAcesso() {
		return nomeNivelAcesso;
	}

	public void setNomeNivelAcesso(String nomeNivelAcesso) {
		this.nomeNivelAcesso = nomeNivelAcesso;
	}
	
	

}
