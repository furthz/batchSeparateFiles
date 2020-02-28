package pe.soapros.exception;

/**
 * Exception to interrupt the flow when there is an error in handling dates
 * 
 * @author raul.talledo
 *
 */
public class DateHelperException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9109443676847585043L;

	public DateHelperException() {
		super();
	}
	
	/**
	 * Constructor.
	 */
	public DateHelperException(final String message) {
		super(message);
	}

	/**
	 * Constructor.
	 */
	public DateHelperException(final Throwable cause) {
		super(cause);
	}
	
	/**
	 * Constructor.
	 */
	public DateHelperException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
