package pe.soapros.exception;

public class ValidationException extends RuntimeException{

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 8120132818874592863L;
	

	/**
	 * Constructor.
	 */
	public ValidationException() {
		super();
	}
	
	/**
	 * Constructor.
	 */
	public ValidationException(final String message) {
		super(message);
	}
	
	/**
	 * Constructor.
	 */
	public ValidationException(final Throwable cause) {
		super(cause);
	}
	
	/**
	 * Constructor.
	 */
	public ValidationException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
