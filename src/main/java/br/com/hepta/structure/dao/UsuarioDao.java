package br.com.hepta.structure.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.hepta.structure.model.Usuario;
import br.com.hepta.structure.model.enums.NivelAcesso;

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
			@SuppressWarnings("unchecked")
			List<Object[]> resultList = manager.createNativeQuery("SELECT user.id, user.nome, nivel.niveisAcesso "
					+ "FROM usuario_niveisacesso nivel INNER JOIN usuario user "
					+ "ON nivel.Usuario_id = user.id where user.nome = :nome AND user.pass = :pass")
				.setParameter("nome", usuario.getNome())
				.setParameter("pass", usuario.getPass())
				.getResultList();
			
			Usuario user = montaUsuario(resultList);
			
			return user;
		} catch (NoResultException e) {
			throw new RuntimeException(e);
		}
	}

	private Usuario montaUsuario(List<Object[]> resultList) {
		Usuario user = null;
		Set<NivelAcesso> niveisAcesso = new HashSet<>(); 
		
		for(Object[] obj: resultList) {
			user = new Usuario(Integer.parseInt(obj[0].toString().trim()),obj[1].toString());
			NivelAcesso nivelAcesso = NivelAcesso.valueOf(obj[2].toString().trim());
			niveisAcesso.add(nivelAcesso);
		}
		if (user == null) {
			throw new RuntimeException("Usuario não cadastrado");
		}
		user.setNiveisAcesso(niveisAcesso);
		return user;
	}

}
