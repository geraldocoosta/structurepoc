package br.com.hepta.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rs")
public class Application extends ResourceConfig {

	public Application() {
		packages(true, "br.com.hepta.rest.resources");
	}
}
