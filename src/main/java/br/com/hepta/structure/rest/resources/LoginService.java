package br.com.hepta.structure.rest.resources;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.hepta.structure.bean.LoginBean;
import br.com.hepta.structure.model.Usuario;
import br.com.hepta.structure.util.rest.exception.InvalidTokenException;
import br.com.hepta.structure.util.rest.security.CheckTokenHeader;
import br.com.hepta.structure.util.rest.security.model.AuthenticationToken;
import io.jsonwebtoken.Claims;

@Path("/login")
public class LoginService {

	@Inject
	LoginBean usuarioBean;
	
	@Inject
	CheckTokenHeader checkToken;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response primeiroTeste(Usuario usuario) {
		try {
			Usuario usuarioLogado = usuarioBean.autentica(usuario);
			AuthenticationToken authenticationToken = new AuthenticationToken(usuarioBean.emiteToken(usuarioLogado));
			return Response.ok(authenticationToken).build();
		} catch (Exception e) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}

	@POST
	@Path("refresh")
	@Produces(MediaType.APPLICATION_JSON)
	public Response refresh(@Context HttpHeaders headers) {
		String header = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
		if(!checkToken.isTokenBasedAuthentication(header)) {
			throw new InvalidTokenException("Token não valido!");
		}
		Claims extractClaim = checkToken.extractClaim(header);
		String newToken = usuarioBean.refreshToken(extractClaim);
		AuthenticationToken authenticationToken = new AuthenticationToken(newToken);
		return Response.ok(authenticationToken).build();
	}

}
