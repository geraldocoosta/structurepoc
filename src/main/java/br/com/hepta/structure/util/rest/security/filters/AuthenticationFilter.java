package br.com.hepta.structure.util.rest.security.filters;

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

import br.com.hepta.structure.service.LoginService;
import br.com.hepta.structure.util.rest.security.CheckTokenHeader;
import br.com.hepta.structure.util.rest.security.SecurityContextApplication;
import io.jsonwebtoken.Claims;

@Provider
@Dependent
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	public static final String AUTHENTICATION_SCHEME = "Bearer";

	@Inject
	LoginService userBean;
	
	@Inject
	CheckTokenHeader checkToken;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (checkToken.isTokenBasedAuthentication(authorizationHeader)) {
			try {
				Claims claims = checkToken.extractClaim(authorizationHeader);
				modificarRequestContext(requestContext, claims);
			} catch (Exception e) {
				abortWithUnauthorized(requestContext);
			}
			return;
		}
	}

	private void abortWithUnauthorized(ContainerRequestContext requestContext) {
		requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
	}


	private void modificarRequestContext(ContainerRequestContext requestContext, Claims claims) {
		requestContext.setSecurityContext(new SecurityContextApplication(requestContext, claims));
	}

}
