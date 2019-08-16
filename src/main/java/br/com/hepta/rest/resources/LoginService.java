package br.com.hepta.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/login")
public class LoginService {

	@GET
	public String primeiroTeste() {
		return "deu bom";
	}
}
