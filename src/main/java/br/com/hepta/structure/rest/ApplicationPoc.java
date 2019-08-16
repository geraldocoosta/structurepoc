package br.com.hepta.structure.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.hepta.structure.rest.resources.LoginService;


@ApplicationPath("")
public class ApplicationPoc extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(LoginService.class);
		return classes;
	}
}
