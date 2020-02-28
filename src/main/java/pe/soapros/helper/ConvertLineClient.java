package pe.soapros.helper;

import pe.soapros.bean.Client;
import pe.soapros.log.LogSoaPros;

public class ConvertLineClient {

	private static final LogSoaPros LOG = LogSoaPros.getInstance(ConvertLineClient.class);
	
	public static Client convertCAB1(String cab1) {
		Client client = null; //new Client();
		
		String[] elements = cab1.split("@");
		
		client = new Client(elements[2], elements[3], elements[4], elements[5], elements[6]);
		
		return client;
	}
}
