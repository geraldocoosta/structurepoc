package br.com.hepta.structure.bean;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.hepta.structure.dao.UsuarioDao;
import br.com.hepta.structure.model.Usuario;
import br.com.hepta.structure.transacional.annotation.Transacional;

public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private UsuarioDao usuarioDao;

	@Inject
	public UsuarioBean(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	public Usuario persisteRetornadoId(Usuario usuario) {
		return usuarioDao.persiste(usuario);
	}
	
}
