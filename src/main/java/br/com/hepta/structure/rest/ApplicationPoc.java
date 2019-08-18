package br.com.hepta.structure.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.hepta.structure.rest.resources.LoginService;
import br.com.hepta.structure.util.rest.exception.InvalidTokenException;
import br.com.hepta.structure.util.rest.exception.mapper.AccessDeniedExceptionMapper;
import br.com.hepta.structure.util.rest.security.AuthenticationFilter;
import br.com.hepta.structure.util.rest.security.AuthorizationFilter;


@ApplicationPath("")
public class ApplicationPoc extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(LoginService.class);
		classes.add(AuthorizationFilter.class);
		classes.add(AuthenticationFilter.class);

		classes.add(AccessDeniedExceptionMapper.class);
		classes.add(InvalidTokenException.class);
		return classes;
	}
	
	
}
