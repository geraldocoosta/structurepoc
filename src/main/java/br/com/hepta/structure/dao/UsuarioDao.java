package br.com.hepta.structure.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.hepta.structure.model.Usuario;

public class UsuarioDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	EntityManager manager;

	public Usuario persiste(Usuario usuario) {
		manager.persist(usuario);
		return usuario;
	}

}
