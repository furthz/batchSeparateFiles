package pe.soapros.start.process;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.soapros.constants.ExitCodeBatch;
import pe.soapros.exception.ValidationException;
import pe.soapros.log.LogSoaPros;
import pe.soapros.util.DateUtil;
import pe.soapros.util.PropertiesUtil;

public class StartProcessSeparateFiles {

	private static final LogSoaPros LOG = LogSoaPros.getInstance(StartProcessSeparateFiles.class);

	public static void main(String... args) {

		LOG.info("Initialize process separates");

		// load file configurations to create context
		final String[] batchJobConfig = { "/spring/batch/config/context.xml", "/spring/batch/jobs/job-report.xml" };
		final ApplicationContext context = new ClassPathXmlApplicationContext(batchJobConfig);

		ExitCodeBatch returnCode = null;

		try {

			// creation job launcher object
			final JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");

			// Job for adjustment file for generation
			final Job job = (Job) context.getBean("separateFileJob");

			// parametros de entrada
			final Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
			// parameters.put(ExecutionDoc1GenHelper.PARAMETER_CICLO, new
			// JobParameter(args[ExecutionDoc1GenHelper.INDICE_PARAMETER_CICLO]));

			final JobExecution jobExecution = jobLauncher.run(job, new JobParameters(parameters));

			LOG.info("Exit Status: " + jobExecution.getStatus());

			returnCode = setReturnCode(returnCode, jobExecution, null);

		} catch (ValidationException e) {
			LOG.error("It's necessary parameters for inicialized", e);
			LOG.info("Exit code: " + ExitCodeBatch.INVALID_ARGUMENTS_START_BATCH.getExitCode());
			returnCode = ExitCodeBatch.INVALID_ARGUMENTS_START_BATCH;
		} catch (Exception e) {
			LOG.error("Error during execution batch", e);
			LOG.info("Exit code: " + ExitCodeBatch.ERROR_EXECUTION_BATCH.getExitCode());
			returnCode = ExitCodeBatch.ERROR_EXECUTION_BATCH;
		} finally {
			// fecha recursos
			if (context != null) {
				((ConfigurableApplicationContext) context).close();
			}

			System.exit(returnCode.getExitCode());
		}
	}
	
	/**
	 * Get code return for procesing batch
	 * 
	 * @param returnCode   - ExitCodeBatch
	 * 
	 * @param jobExecution - JobExecution
	 * 
	 * @param ciclo        - String
	 * 
	 * @return ExitCodeBatch
	 */
	private static ExitCodeBatch setReturnCode(ExitCodeBatch returnCode, final JobExecution jobExecution,
			final String ciclo) {
		// status execution success
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			// verify exist logs
			if (existFileLog(ciclo)) {
				LOG.info("There are errors during execution of bacth");
				LOG.info("Exit code: " + ExitCodeBatch.ERROR_ADJUST_FILE.getExitCode());
				returnCode = ExitCodeBatch.ERROR_ADJUST_FILE;
			} else {
				LOG.info("Execution the batch finalized with success");
				LOG.info("Exit code: " + ExitCodeBatch.SUCCESS.getExitCode());
				returnCode = ExitCodeBatch.SUCCESS;
			}
		}

		// executado com falha
		if (jobExecution.getStatus() == BatchStatus.FAILED) {
			LOG.error("An error occurred during batch execution");
			LOG.info("Exit code: " + ExitCodeBatch.ERROR_EXECUTION_BATCH.getExitCode());
			returnCode = ExitCodeBatch.ERROR_EXECUTION_BATCH;
		}

		return returnCode;
	}

	/**
	 * Verify exist file logs
	 * 
	 * @param ciclo - String
	 * 
	 * @return true - Case exist log file false - case dont exist log file
	 * 
	 */
	private static final boolean existFileLog(final String ciclo) {
		try {
			final StringBuilder fileName = new StringBuilder(
					PropertiesUtil.getInstance().getProperty("directory.file.csv.log.separate.file"));

			fileName.append(File.separator).append(ciclo).append(File.separator)
					.append(DateUtil.formatDataPatternDate(null));

			if (new File(fileName.toString()).listFiles() != null) {
				return true;
			}

		} catch (Exception e) {
			LOG.error("", e);
		}

		return false;
	}

}
