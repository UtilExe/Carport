package FunctionLayer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

/**
 * @author Daniel, Emil, Jannich, Jimmy
 * Log klassen håndterer fejl, således at vi kan logge det til en fil. Eksempelvis. hvis databasen er nede.
 */

public class Log {

    private static String FILENAME;
    private static String FILEPATH;

    private static String PATH = "";

    private Log() {
    }

    private static void setLogPath() {
        String deployed = System.getenv("DEPLOYED");

        if(deployed != null) {
            FILENAME = "rename-til-tomcat-fil";
            FILEPATH = "/var/log/tomcat8/";
        } else {
            // Localhost
            FILENAME = PathForLog.FILENAME;
            FILEPATH = PathForLog.FILEPATH;
        }
        PATH = FILEPATH + FILENAME;
    }

    private static void log(Level lvl, String decription) throws Exception {
        setLogPath();
        Logger logger = Logger.getLogger(Log.class.getName());   // alle operationer på logger er thread safe
        FileHandler fh = new FileHandler(PATH, true);
        fh.setFormatter(new VerySimpleFormatter());
        logger.addHandler(fh);

        logger.setLevel(Level.SEVERE);   // her sætter vi niveauet for logningen.
        logger.log(lvl, decription);

        fh.close();
    }

    public static void severe(String description) {
        try {
            log(Level.SEVERE, description);
        } catch (Exception e) {
        }
    }

    public static void info(String description) {
        try {
            log(Level.INFO, description);
        } catch (Exception e) {
        }
    }

    public static void finest(String description) {
        try {
            log(Level.FINEST, description);
        } catch (Exception e) {
        }
    }
}

class VerySimpleFormatter extends Formatter {

    private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    @Override
    public String format(final LogRecord record) {
        return String.format(
                "%1$s %2$-7s %3$s\n",
                new SimpleDateFormat(PATTERN).format(
                        new Date(record.getMillis())),
                record.getLevel().getName(),
                formatMessage(record));
    }
}