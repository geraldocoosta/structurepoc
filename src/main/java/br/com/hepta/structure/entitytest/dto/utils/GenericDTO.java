package br.com.hepta.structure.entitytest.dto.utils;

public interface GenericDTO<T, R> {

	public abstract R toEntity();
	
}
