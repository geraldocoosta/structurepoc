package br.com.hepta.structure.rest.resources;

import java.security.Principal;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import br.com.hepta.structure.bean.LoginBean;
import br.com.hepta.structure.model.Usuario;

@Path("/login")
public class LoginService {

	@Inject
	LoginBean usuarioBean;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response primeiroTeste(Usuario usuario) {
		try {
			// autentica o usuario com as credenciais
			Usuario usuarioLogado = usuarioBean.autentica(usuario);

			// gera um token para o usuario
			String token = usuarioBean.emiteToken(usuarioLogado, 1);

			// retorna o token na resposta
			return Response.ok(token).build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}

	@POST
	@Path("/queijo")
	@Produces(MediaType.TEXT_PLAIN)
	@RolesAllowed({"ADMIN"})
	public Response segundoTeste(@Context SecurityContext securityContext) {
		Principal principal = securityContext.getUserPrincipal();
		String username = principal.getName();
		return Response.ok("veio tranquilo" + username).build();
	}

}
