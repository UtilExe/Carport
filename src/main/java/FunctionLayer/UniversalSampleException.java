package FunctionLayer;

/**
 * @author Daniel, Emil, Jannich, Jimmy
 * UniversalSampleException klassen er en Generel exception vi har lavet for at håndtere Exceptions og skrive til log - alt i én exception.
 * Den indeholder de metoder, som er mest generelle exceptions, der skal håndteres. På denne måde undgår man duplet-kode i mapper-klasserne.
 */

public class UniversalSampleException extends Exception {

    public UniversalSampleException(String msg) {
        super(msg);
    }

    /**
     * exceptionIfsDB har til formål at kaste/håndtere de generelle exceptions, der går igen, når det kommer til
     * database-håndtering.
     * @param exMessage den besked som den kastede exception afgiver.
     * @param methodName navnet på den metode, hvor exception'en bliver kastet i (så det kan ses i log'en, hvor
     *                   fejlen er sket).
     * @throws UniversalSampleException kastes, hvis der er problemer med at hente fra databasen.
     */
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


    /**
     * exceptionIfLast har til formål at kaste/håndtere en exception, hvis en exception ikke bliver kastet fra alle
     * de exceptions man havde taget højde for (altså "det sidste sikkerhedsnet"-exception'en eller "jeg har ingen
     * idé om hvorfor denne exception bliver kastet"-exception'en).
     * @param exMessage den besked som den kastede exception afgiver.
     * @param methodName navnet på den metode, hvor exception'en bliver kastet i (så det kan ses i log'en, hvor
     *                   fejlen er sket).
     * @throws UniversalSampleException kastes, når en fejl opstår, som ingen andre exceptions tager sig af.
     */
    public static void exceptionIfLast(String exMessage, String methodName) throws UniversalSampleException {
        Log.severe(methodName + ": " + exMessage);
        throw new UniversalSampleException(exMessage);
    }

}
