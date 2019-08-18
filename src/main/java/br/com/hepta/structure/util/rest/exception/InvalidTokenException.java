package br.com.hepta.structure.util.rest.exception;

public class InvalidTokenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidTokenException() {
		super();
	}

	public InvalidTokenException(String message, Throwable exception) {
		super(message, exception);
	}

	public InvalidTokenException(String exception) {
		super(exception);
	}

	
}
