package pe.soapros.exception;

public class PropertyException extends RuntimeException {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 343972449141173505L;

	/**
	 * Constructor.
	 */
	public PropertyException() {
		super();
	}

	/**
	 * Constructor.
	 */
	public PropertyException(final String message) {
		super(message);
	}

	/**
	 * Constructor.
	 */
	public PropertyException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor.
	 */
	public PropertyException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
