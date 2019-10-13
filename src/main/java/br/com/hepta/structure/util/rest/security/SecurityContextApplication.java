package br.com.hepta.structure.util.rest.security;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.SecurityContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.hepta.structure.model.NivelAcesso;
import br.com.hepta.structure.util.rest.security.filters.AuthenticationFilter;
import io.jsonwebtoken.Claims;

public class SecurityContextApplication implements SecurityContext {

	private ContainerRequestContext requestContext;
	private Claims claims;

	public SecurityContextApplication(ContainerRequestContext requestContext, Claims claims) {
		this.requestContext = requestContext;
		this.claims = claims;
	}

	@Override
	public Principal getUserPrincipal() {
		String login = claims.getIssuer();
		return () -> login;
	}

	@Override
	public boolean isUserInRole(String role) {
		List<NivelAcesso> niveisAcesso = extractAuthoritiesFromClaims(claims);
		return niveisAcesso.stream().anyMatch(nivel -> nivel.getNomeNivelAcesso().equals(role));
	}

	@Override
	public boolean isSecure() {
		SecurityContext currentSecurityContext = requestContext.getSecurityContext();
		return currentSecurityContext.isSecure();
	}

	@Override
	public String getAuthenticationScheme() {
		return AuthenticationFilter.AUTHENTICATION_SCHEME;
	}

	private List<NivelAcesso> extractAuthoritiesFromClaims(@NotNull Claims claims) {
		try {
			String jsonNivelAcesso = claims.get("niveis-acesso", String.class);
			ObjectMapper converterJsonToObject = new ObjectMapper();
			List<NivelAcesso> niveisAcesso = converterJsonToObject
												.readValue(jsonNivelAcesso, 
															converterJsonToObject.getTypeFactory().constructCollectionType(List.class, NivelAcesso.class));
			return niveisAcesso;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Claims getClaims() {
		return claims;
	}

}
