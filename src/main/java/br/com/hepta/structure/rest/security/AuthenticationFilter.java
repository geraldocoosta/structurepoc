package br.com.hepta.structure.rest.security;

import java.io.IOException;

import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import br.com.hepta.structure.bean.LoginBean;
import io.jsonwebtoken.Claims;

@Provider
@Dependent
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	public static final String AUTHENTICATION_SCHEME = "Bearer";

	@Inject
	LoginBean userBean;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		// Pegando o header authorization
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		// validando o cabeçalho autorization
		if (!isTokenBasedAuthentication(authorizationHeader)) {
			abortWithUnauthorized(requestContext);
			return;
		}

		// pegando token se ele for valido
		String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

		try {
			validateToken(token, requestContext);
		} catch (Exception e) {
			abortWithUnauthorized(requestContext);
		}

	}

	private void validateToken(String token, ContainerRequestContext requestContext) throws Exception {
		Claims claims = userBean.validaToken(token);

		if (claims == null) {
			throw new Exception("Token invalido");
		}
		modificarRequestContext(requestContext, claims);

	}

	private void abortWithUnauthorized(ContainerRequestContext requestContext) {
		requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());

	}

	private boolean isTokenBasedAuthentication(String authorizationHeader) {
		return authorizationHeader != null
				&& authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
	}

	private void modificarRequestContext(ContainerRequestContext requestContext, Claims claims) {
		requestContext.setSecurityContext(new SecurityContextApplication(requestContext, claims));
	}

}
