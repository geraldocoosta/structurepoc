package br.com.hepta.structure.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.hepta.structure.model.enums.NivelAcesso;

@Entity
public class Usuario {

	public Usuario() {
	}

	public Usuario(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Integer id;

	@Column(nullable = false, unique = true)
	private String nome;

	@Column(nullable = false)
	private String pass;

	@ElementCollection(targetClass = NivelAcesso.class)
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Set<NivelAcesso> niveisAcesso;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Set<NivelAcesso> getNiveisAcesso() {
		return niveisAcesso;
	}

	public void setNiveisAcesso(Set<NivelAcesso> niveisAcesso) {
		this.niveisAcesso = niveisAcesso;
	}

}
