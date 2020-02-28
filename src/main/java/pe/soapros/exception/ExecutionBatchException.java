package pe.soapros.exception;

public class ExecutionBatchException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6556923564816451248L;

	/**
	 * Constructor.
	 */
	public ExecutionBatchException() {
		super();
	}

	/**
	 * Constructor.
	 */
	public ExecutionBatchException(final String message) {
		super(message);
	}

	/**
	 * Constructor.
	 */
	public ExecutionBatchException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor.
	 */
	public ExecutionBatchException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
