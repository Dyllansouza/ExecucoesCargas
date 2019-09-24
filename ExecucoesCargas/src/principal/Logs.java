package principal;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Logs {

	public static FileHandler fh;
	public static FileWriter fw;
	public static PrintWriter out;


	public Logger Logger() {
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
		Logger logger = Logger.getLogger("MyLog");

		try {

			// This block configure the logger with handler and formatter

			fh = new FileHandler(
					"C:/Automatizacao/ExecucoesCargas/CargaTeste" + Logs.Data() + ".txt");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			// the following statement is used to log any messages

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return logger;

	}
	
	public static String Data() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy");
		Calendar data = Calendar.getInstance();
		return sdf.format(data.getTime());
	}
}
