package br.com.hepta.structure.rest.resources;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.hepta.structure.model.Usuario;
import br.com.hepta.structure.service.LoginService;
import br.com.hepta.structure.util.rest.exception.InvalidTokenException;
import br.com.hepta.structure.util.rest.security.CheckTokenHeader;
import br.com.hepta.structure.util.rest.security.model.AuthenticationToken;
import io.jsonwebtoken.Claims;

@Path("/login")
public class RestLogin {

	@Inject
	LoginService loginService;

	@Context
	HttpServletRequest request;

	@Inject
	CheckTokenHeader checkToken;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response primeiroTeste(Usuario usuario) {
		String setCookieHeader = "teste=qualquer coisa aqui inclusive com espaço; Path=/mercado; HttpOnly";
		try {
			Usuario usuarioLogado = loginService.autentica(usuario);
			AuthenticationToken authenticationToken = new AuthenticationToken(loginService.emiteToken(usuarioLogado));
//			HttpSession session = request.getSession();
//			session.setAttribute("teste", "teste");
			return Response.ok(authenticationToken).header("Set-Cookie", setCookieHeader).build();
		} catch (Exception e) {
			return Response.status(Status.UNAUTHORIZED).cookie(new NewCookie("teste", "qualquer")).build();
		}
	}

	@GET
	@Path("/teste")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({ "DEV" })
	public Response teste(@CookieParam("teste") String strCookie) {
//		HttpSession session = request.getSession();
//		String attribute = (String)session.getAttribute("teste");
		return Response.ok().entity(strCookie).build();
	}

	@POST
	@Path("refresh")
	@Produces(MediaType.APPLICATION_JSON)
	public Response refresh(@Context HttpHeaders headers) {
		String header = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (!checkToken.isTokenBasedAuthentication(header)) {
			throw new InvalidTokenException("Token não valido!");
		}
		Claims extractClaim = checkToken.extractClaim(header);
		String newToken = loginService.refreshToken(extractClaim);
		AuthenticationToken authenticationToken = new AuthenticationToken(newToken);
		return Response.ok(authenticationToken).build();
	}

}
