package FunctionLayer;

public class OrderSampleException extends Exception {

    public OrderSampleException(String msg) {
        super(msg);
    }

    public static void exceptionIfsDB(String exMessage, String methodName) throws OrderSampleException {
        if(exMessage.contains("Access denied")) {
            Log.severe(methodName + ": " + exMessage);
            throw new OrderSampleException( "Kan ikke logge på databasen" );
        }

        if(exMessage.contains("Communications link failure")) {
            Log.severe(methodName + ": " + exMessage);
            throw new OrderSampleException( "Databasen er i øjeblikket nede. Kontakt IT" );
        }


    }

    public static void exceptionIfLast(String exMessage, String methodName) throws OrderSampleException {
        Log.severe(methodName + ": " + exMessage);
        throw new OrderSampleException(exMessage);
    }

}
