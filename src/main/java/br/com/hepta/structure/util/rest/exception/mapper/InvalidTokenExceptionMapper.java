package br.com.hepta.structure.util.rest.exception.mapper;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.hepta.structure.util.rest.ApiErrorDetails;
import br.com.hepta.structure.util.rest.exception.InvalidTokenException;

@Provider
public class InvalidTokenExceptionMapper  implements ExceptionMapper<InvalidTokenException> {
	   @Context
	    private UriInfo uriInfo;

	    @Override
	    public Response toResponse(InvalidTokenException exception) {

	        Status status = Status.FORBIDDEN;

	        ApiErrorDetails errorDetails = new ApiErrorDetails();
	        errorDetails.setStatus(status.getStatusCode());
	        errorDetails.setTitle(status.getReasonPhrase());
	        errorDetails.setMessage("Token Invalido");
	        errorDetails.setPath(uriInfo.getAbsolutePath().getPath());

	        return Response.status(status).entity(errorDetails).type(MediaType.APPLICATION_JSON).build();
	    }

}
