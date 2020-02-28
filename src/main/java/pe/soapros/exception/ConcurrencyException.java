package pe.soapros.exception;

/**
 * Exception for interrupt the process when error occurs during execution of thread
 * 
 * @author raul.talledo
 *
 */
public class ConcurrencyException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2722970222176159647L;

	/**
	 * Constructor.
	 */
	public ConcurrencyException() {
		super();
	}

	/**
	 * Constructor.
	 */
	public ConcurrencyException(final String message) {
		super(message);
	}

	/**
	 * Constructor.
	 */
	public ConcurrencyException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor.
	 */
	public ConcurrencyException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
}
