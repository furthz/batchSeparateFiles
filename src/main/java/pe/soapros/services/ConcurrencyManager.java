package pe.soapros.services;

import java.util.Map;

import pe.soapros.exception.ConcurrencyException;

/**
 * Interface of services for controller services concurrent
 * 
 * @author Usuario
 *
 */
public interface ConcurrencyManager {

	/**
	 * Create a concurrent parallel processing 
	 * 
	 * @param parameters	- Map<String, Object>
	 * 
	 * @throws ConcurrencyException
	 * 
	 */
	void createParallelProcess(Map<String, Object> parameters) throws ConcurrencyException;
	
}
