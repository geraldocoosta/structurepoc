package br.com.hepta.structure.entitytest.dto;

import br.com.hepta.structure.entitytest.Entity;
import br.com.hepta.structure.entitytest.dto.utils.GenericDTO;

public class EntityDTO implements GenericDTO<EntityDTO, Entity> {

	private Long id;
	private String nome;
	private int numero;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public Entity toEntity() {
		return new Entity();
	}

}
