package pe.soapros.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import pe.soapros.exception.DateHelperException;

/**
 * Utility for manipulate date
 * 
 * @author raul.talledo
 *
 */
public class DateUtil {

	/**
	 * Format data to date in pattern yyyyMMdd
	 * 
	 * case data is null return current date
	 * 
	 * @param data
	 * 
	 * @return String - data format
	 * 
	 * @throws DateHelperException
	 * 
	 */
	public static final String formatDataPatternDate(final Date data) throws DateHelperException {
		try {
			final DateFormat df = new SimpleDateFormat("yyyyMMdd");

			Date dataASerFormatada = data;

			if (dataASerFormatada == null) {
				dataASerFormatada = new Date();
			}

			return df.format(dataASerFormatada);

		} catch (Exception e) {
			throw new DateHelperException(e);
		}

	}

	/**
	 * Format data in pattern yyyy-MM-ddTHH:mm:ss
	 * 
	 * Case data is null return current date
	 * 
	 * @param data
	 * 
	 * @return String - format data
	 * 
	 * @throws DateHelperException
	 * 
	 */
	public static final String formatDataPatternDateAndTime(final Date data) throws DateHelperException {
		try {

			final DateFormat df = new SimpleDateFormat("yyyyMMdd");
			final DateFormat dfTime = new SimpleDateFormat("HHmmss");
			final StringBuilder dataFormatada = new StringBuilder();

			Date dataASerFormatada = data;

			if (dataASerFormatada == null) {
				dataASerFormatada = new Date();
			}

			dataFormatada.append(df.format(dataASerFormatada)).append(dfTime.format(dataASerFormatada));

			return dataFormatada.toString();
		} catch (Exception e) {
			throw new DateHelperException(e);
		}
	}

	/**
	 * Format data in patter yyyy-MM-ddTHH.mm.ss
	 * 
	 * Case data is null return current date
	 * 
	 * @param data
	 * 
	 * @return String - format data
	 * 
	 * @throws DateHelperException
	 * 
	 */
	public static final String formatDataPatternDateAndTimeHourWithDotSeparator(final Date data)
			throws DateHelperException {
		try {
			final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			final DateFormat dfTime = new SimpleDateFormat("HH.mm.ss");
			final StringBuilder dataFormatada = new StringBuilder();

			Date dataASerFormatada = data;

			if (dataASerFormatada == null) {
				dataASerFormatada = new Date();
			}

			dataFormatada.append(df.format(dataASerFormatada)).append("T").append(dfTime.format(dataASerFormatada));

			return dataFormatada.toString();

		} catch (Exception e) {
			throw new DateHelperException(e);
		}
	}

	/**
	 * Constructor.
	 */
	private DateUtil() {
	}
}
