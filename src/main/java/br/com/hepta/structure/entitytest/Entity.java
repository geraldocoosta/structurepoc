package br.com.hepta.structure.entitytest;

import br.com.hepta.structure.entitytest.dto.EntityDTO;
import br.com.hepta.structure.entitytest.util.GenericEntity;

public class Entity implements GenericEntity<Entity, EntityDTO> {

	private Long id;
	private String nome;
	private int numero;

	public Entity() {
	}

	private Entity(Builder builder) {
		this.id = builder.id;
		this.nome = builder.nome;
		this.numero = builder.numero;
	}

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

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Long id;
		private String nome;
		private int numero;

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withNome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder withNumero(int numero) {
			this.numero = numero;
			return this;
		}

		public Entity build() {
			return new Entity(this);
		}
	}

	@Override
	public EntityDTO toDto() {
		return new EntityDTO();
	}

}
