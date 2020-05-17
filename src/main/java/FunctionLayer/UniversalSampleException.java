package FunctionLayer;

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
