package br.com.hepta.structure.util.rest.security;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.SecurityContext;

import br.com.hepta.structure.model.enums.NivelAcesso;
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
		Set<NivelAcesso> niveisAcesso = extractAuthoritiesFromClaims(claims);
		return niveisAcesso.contains(NivelAcesso.valueOf(role));
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

	private Set<NivelAcesso> extractAuthoritiesFromClaims(@NotNull Claims claims) {
		@SuppressWarnings("unchecked")
		List<String> rolesAsString = (List<String>) claims.getOrDefault("niveis-acesso", new ArrayList<>());
		return rolesAsString.stream().map(NivelAcesso::valueOf).collect(Collectors.toSet());
	}
	
	public Claims getClaims() {
		return claims;
	}

}
