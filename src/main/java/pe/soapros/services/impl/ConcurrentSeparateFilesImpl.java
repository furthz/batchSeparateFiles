package pe.soapros.services.impl;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

import org.springframework.stereotype.Component;

import pe.soapros.concurrent.ThreadSeparateFiles;
import pe.soapros.exception.ConcurrencyException;
import pe.soapros.exception.ExecutionBatchException;
import pe.soapros.log.LogSoaPros;
import pe.soapros.services.ConcurrencyManager;
import pe.soapros.util.PropertiesUtil;

@Component(value = "ConcurrentSeparateFilesImpl")
public class ConcurrentSeparateFilesImpl implements ConcurrencyManager {

	private static final LogSoaPros LOG = LogSoaPros.getInstance(ConcurrentSeparateFilesImpl.class);

	@Override
	public void createParallelProcess(Map<String, Object> parameters) throws ConcurrencyException {
		LOG.info("Creating process concurrent for separate files");

		try {

			final PropertiesUtil property = PropertiesUtil.getInstance();

			final File diretorioInputTxt = new File(property.getProperty("directory.root.files.input.spool"));

			// create pool thread
			final ExecutorService executorService = Executors
					.newFixedThreadPool(Integer.valueOf(property.getProperty("quantity.threads.separate.files")));

			//spools files
			final File[] listFiles = diretorioInputTxt.listFiles();
			
			if(listFiles != null) {
				LOG.info("There are files: " + listFiles.length + " for processing");
				for (final File spool : diretorioInputTxt.listFiles()) {

					executorService.execute(new ThreadSeparateFiles(spool));
				}

				executorService.shutdown();
				while (!executorService.isTerminated()) {
				}
				LOG.info("Files Separates");
			}
			
		} catch (ExecutionBatchException e) {
			LOG.error("", e);
			throw e;
		} catch (RejectedExecutionException e) {
			LOG.error("", e);
			throw new ConcurrencyException(e);
		} catch (Exception e) {
			LOG.error("", e);
			throw new ConcurrencyException(e);
		}

	}

}
