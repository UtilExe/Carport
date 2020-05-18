package FunctionLayer;

/**
 * @author Daniel, Emil, Jannich, Jimmy
 * UniversalSampleException klassen er en Generel exception vi har lavet for at håndtere Exceptions og skrive til log - alt i én exception.
 */

public class UniversalSampleException extends Exception {

    public UniversalSampleException(String msg) {
        super(msg);
    }

    public static void exceptionIfsDB(String exMessage, String methodName) throws UniversalSampleException {
        if(exMessage.contains("Access denied")) {
            Log.severe(methodName + ": " + exMessage);
            throw new UniversalSampleException( "Kan ikke logge på databasen" );
        }

        if(exMessage.contains("Communications link failure")) {
            Log.severe(methodName + ": " + exMessage);
            throw new UniversalSampleException( "Databasen er i øjeblikket nede. Kontakt IT" );
        }
    }

    public static void exceptionIfLast(String exMessage, String methodName) throws UniversalSampleException {
        Log.severe(methodName + ": " + exMessage);
        throw new UniversalSampleException(exMessage);
    }

}
