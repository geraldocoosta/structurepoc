package br.com.hepta.structure.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.hepta.structure.model.Usuario;

public class UsuarioDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager manager;

	public Usuario persiste(Usuario usuario) {
		manager.persist(usuario);
		return usuario;
	}

	public Usuario autenticaUsuario(Usuario usuario) {
		try {
			return manager.createQuery("SELECT u from Usuario u "
					+ "JOIN FETCH u.niveisAcesso "
					+ "WHERE u.nome = :nome AND u.pass = :pass", Usuario.class)
				.setParameter("nome", usuario.getNome())
				.setParameter("pass", usuario.getPass())
				.getSingleResult();
		} catch (NoResultException e) {
			throw new RuntimeException(e);
		}
	}

}
