package br.com.hepta.structure.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.xml.bind.DatatypeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.hepta.structure.dao.UsuarioDao;
import br.com.hepta.structure.model.Usuario;
import br.com.hepta.structure.util.transacional.annotation.Transacional;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class LoginService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String FRASE_SEGREDO = "capacete0asd*-12AsdWWdsadansidasdasDASCAjscasjicahusid123123dhs9dqw4q8w9d7q8weqweQWDASDHASD12312dsad79817231@#!@dasjidosaidhwEFHIFSDFkasdauhsdasd1a5s9daspkasdASdAKXZXIASdasduwd!@asd";
	private UsuarioDao usuarioDao;
	private SignatureAlgorithm algoritimoAssinatura;

	@Inject
	public LoginService(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@Transacional
	public Usuario persisteRetornadoId(Usuario usuario) {
		return usuarioDao.persiste(usuario);
	}

	public Usuario autentica(Usuario usuario) {
		return usuarioDao.autenticaUsuario(usuario);
	}

	public String emiteToken(Usuario usuario) throws JsonProcessingException {
		algoritimoAssinatura = SignatureAlgorithm.HS512;

		Date agora = new Date();

		Calendar expira = Calendar.getInstance();
		expira.add(Calendar.DAY_OF_MONTH, 1);

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(FRASE_SEGREDO);
		SecretKeySpec key = new SecretKeySpec(apiKeySecretBytes, algoritimoAssinatura.getJcaName());

		ObjectMapper mapperToJson = new ObjectMapper();
		String jsonNivelAcesso = mapperToJson.writeValueAsString(usuario.getNiveisAcesso());
		
		JwtBuilder construtor = Jwts.builder()
				.setIssuedAt(agora)
				.setIssuer(usuario.getNome())
				.signWith(key, algoritimoAssinatura)
				.setExpiration(expira.getTime())
				.claim("niveis-acesso", jsonNivelAcesso);

		return construtor.compact();
	}

	public Claims validaToken(String token) {
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(DatatypeConverter.parseBase64Binary(FRASE_SEGREDO))
					.parseClaimsJws(token)
					.getBody();
			return claims;
		} catch (Exception ex) {
			throw ex;
		}
	}

	public String refreshToken(Claims claims) {
		algoritimoAssinatura = SignatureAlgorithm.HS512;

		Date agora = new Date();

		Calendar expira = Calendar.getInstance();
		expira.add(Calendar.DAY_OF_MONTH, 1);

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(FRASE_SEGREDO);
		SecretKeySpec key = new SecretKeySpec(apiKeySecretBytes, algoritimoAssinatura.getJcaName());

		JwtBuilder construtor = Jwts.builder()
				.setIssuedAt(agora)
				.setIssuer(claims.getIssuer())
				.signWith(key, algoritimoAssinatura)
				.setExpiration(expira.getTime())
				.claim("niveis-acesso",	claims.get("nivelAcesso", Set.class));

		return construtor.compact();
	}

}
