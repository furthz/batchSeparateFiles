package pe.soapros.concurrent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import pe.soapros.bean.Client;
import pe.soapros.helper.ConvertLineClient;
import pe.soapros.log.LogSoaPros;
import pe.soapros.util.PropertiesUtil;

public class ThreadSeparateFiles implements Runnable {

	private static final LogSoaPros LOG = LogSoaPros.getInstance(ThreadSeparateFiles.class);

	private File spool;

	@Override
	public void run() {
		LOG.info("Executing a Thread:" + Thread.currentThread().getName());

		FileInputStream inputStream = null;
		Scanner sc = null;

		try {
			// properties
			final PropertiesUtil property = PropertiesUtil.getInstance();

			// cant client for files
			final int cantClient = Integer.valueOf(property.getProperty("quantity.client.files"));

			StringBuilder destinityFile = new StringBuilder();
			destinityFile.append(property.getProperty("directory.root.files.output.spool")).append(File.separator);

			// read input file stream
			inputStream = new FileInputStream(this.spool.getAbsolutePath());

			// scan
			sc = new Scanner(inputStream, "Cp1252");

			// client anterior
			Client clientBefore = new Client();
			clientBefore.setNumDOc("");
			clientBefore.setPan("");
			
			// buffer
			StringBuilder lineaFile = new StringBuilder();

			FileWriter writer = null;

			int contFile = 1;

			int contClient = 0;

			boolean swFile = false;

			boolean swContinue = false;

			destinityFile.append(this.spool.getName().substring(0, this.spool.getName().length()-4)).append("-").append(contFile).append(".txt");

			writer = new FileWriter(destinityFile.toString(), true);
			
			int contador = 0;
			// recorrer cada linea
			while (sc.hasNextLine()) {

				String line = sc.nextLine();
				
				if ("1DOC1@".equals(line)) {
					contador ++;
					if (swContinue) {
						
						contFile++;
						
						writer.write(lineaFile.toString());
						
						writer.close();
						//fileContent.setLength(0);

						destinityFile.setLength(0);

						destinityFile.append(property.getProperty("directory.root.files.output.spool"))
								.append(File.separator);

						destinityFile.append(this.spool.getName().substring(0, this.spool.getName().length()-4)).append("-").append(contFile).append(".txt");

						writer = new FileWriter(destinityFile.toString(), true);
						
						contClient = 0;
						
						swContinue = false;
						
					}else {
						writer.write(lineaFile.toString());
					}
					//fileContent.append(lineaFile.toString());

					if (contClient == cantClient) {

						swFile = true;
					}

					// System.out.println(lineaFile.toString());

					// limpiar buffer
					lineaFile.setLength(0);

					lineaFile.append(line).append("\n");

					String current = sc.nextLine();

					lineaFile.append(current).append("\n");

					Client clientCurrent = ConvertLineClient.convertCAB1(current);
					// System.out.println(clientCurrent.toString());

					if (clientBefore.equals(clientCurrent)) {
						System.out.println(clientCurrent.toString());
						System.out.println(contFile);
						System.out.println(contClient);
					} else {
						if (swFile) {
							swContinue = true;
							swFile = false;
						}

						clientBefore = clientCurrent;

					}

					contClient++;

				} else {
					lineaFile.append(line).append("\n");
				}
			}

			writer.write(lineaFile.toString());
			
			writer.close();
			
			System.out.println(contador);
			// note that Scanner suppresses exceptions
			if (sc.ioException() != null) {
				throw sc.ioException();
			}

		} catch (Exception e) {
			LOG.error(e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (sc != null) {
				sc.close();
			}
		}

	}

	public ThreadSeparateFiles(File spool) {
		this.spool = spool;
	}
}
