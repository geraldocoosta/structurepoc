package br.com.hepta.structure.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.hepta.structure.rest.resources.RestLogin;
import br.com.hepta.structure.util.cors.CORSFilter;
import br.com.hepta.structure.util.rest.exception.InvalidTokenException;
import br.com.hepta.structure.util.rest.exception.mapper.AccessDeniedExceptionMapper;
import br.com.hepta.structure.util.rest.security.filters.AuthenticationFilter;
import br.com.hepta.structure.util.rest.security.filters.AuthorizationFilter;


@ApplicationPath("")
public class ApplicationPoc extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(RestLogin.class);
		classes.add(AuthorizationFilter.class);
		classes.add(AuthenticationFilter.class);
		classes.add(CORSFilter.class);

		classes.add(AccessDeniedExceptionMapper.class);
		classes.add(InvalidTokenException.class);
		return classes;
	}
	
	
}
