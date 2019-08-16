package br.com.hepta.structure.rest.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.hepta.structure.bean.UsuarioBean;
import br.com.hepta.structure.model.Usuario;

@Path("/login")
public class LoginService {

	@Inject
	UsuarioBean usuarioBean;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response primeiroTeste(Usuario usuario) {
		try {
			Usuario createdUser = usuarioBean.persisteRetornadoId(usuario);
			GenericEntity<Usuario> entidadeRetornada = new GenericEntity<Usuario>(createdUser) {};
			return Response.status(Status.CREATED).entity(entidadeRetornada).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar produto").build();
		}
	}
}
