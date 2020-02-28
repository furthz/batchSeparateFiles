package pe.soapros.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import pe.soapros.exception.ExecutionBatchException;
import pe.soapros.log.LogSoaPros;
import pe.soapros.services.impl.ConcurrentSeparateFilesImpl;

public class SeparateFileTasklet implements Tasklet{

	private static final LogSoaPros LOG = LogSoaPros.getInstance(SeparateFileTasklet.class);
	
	@Autowired
	private ConcurrentSeparateFilesImpl concurrentSeparateFilesImpl;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		LOG.info("EXECUTE: " + chunkContext.getStepContext().getJobName());
		
		try {
			this.concurrentSeparateFilesImpl.createParallelProcess(chunkContext.getStepContext().getJobParameters());
		} catch (ExecutionBatchException e) {
			throw e;
		} catch (Exception e) {
			LOG.error("", e);
			throw e;
		}
		
		return RepeatStatus.FINISHED;
	}

}
