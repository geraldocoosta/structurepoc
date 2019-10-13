package br.com.hepta.structure.util.rest.security;

import javax.inject.Inject;

import br.com.hepta.structure.service.LoginService;
import br.com.hepta.structure.util.rest.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;

public class CheckTokenHeader {
	private static final String AUTHENTICATION_SCHEME = "BEARER";
	
	@Inject
	LoginService userBean;
	
	public CheckTokenHeader() {
	}

	public boolean isTokenBasedAuthentication(String token) {
		return token != null
				&& token.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
	}
	
	public Claims extractClaim(String token) {
		String tokenSemBaerer = token.substring(AUTHENTICATION_SCHEME.length()).trim();
		Claims claims = userBean.validaToken(tokenSemBaerer);
		if (claims == null) {
			throw new InvalidTokenException("Token invalido");
		}
		return claims;
	}
}
