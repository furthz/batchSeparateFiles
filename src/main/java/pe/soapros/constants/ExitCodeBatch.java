package pe.soapros.constants;

/**
 * Enum for indicate return code batch
 * 
 * @author raul.talledo
 *
 */
public enum ExitCodeBatch {

	SUCCESS(0, 									"Sucess"),
	
	INVALID_ARGUMENTS_START_BATCH(1, 			"Invalid argument for batch execution"),
	
	WHITOUT_FILE_TO_EXECUTION(2, 				"No files to process"),
	
	ERROR_EXECUTION_BATCH(-1, 					"Error during batch execution"),
	
	ERROR_DURING_EXECUTION_DOC1GEN(4, 			"Error to execute DOC1Gen"), 
	
	INVALID_TEMPLATE_HTML(5, 					"Invalid Template HTML"),
	
	ERROR_FILLING_CONTENT_HTML(6, 				"Error filling in dynamic HTML"),
	
	ERROR_ADJUST_FILE(-1,						"Error adjustment file");;
	
	/**
	 * codigo de saida.
	 */
	private Integer exitCode;
	
	/**
	 * Mensagem de saida.
	 */
	private String message;
	
	
	private ExitCodeBatch(final Integer paramExitCode, final String paramMessage) {
		this.exitCode = paramExitCode;
		this.message = paramMessage;
	}
	
	/**
	 * @return the exitCode.
	 */
	public Integer getExitCode() {
		return exitCode;
	}

	/**
	 * @return the message.
	 */
	public String getMessage() {
		return message;
	}
	
}
