package FunctionLayer;

/**
 * @author Daniel, Emil, Jannich, Jimmy
 * Operations klassen indeholder en række hjælpsomme metoder, bl.a. formatering, splitter og lignende, som bliver brugt
 * Flere steder i koden, som har til formål at undgå gentagne kode.
 */

public class Operations {

    public static String[] splitterForDimensions(String strFromDB, String regex) {
        String[] splittedString;
        splittedString = strFromDB.split(regex);
        return splittedString;
    }

    public static String addQuotesAround(String input) {
        String result = "\"" + input + "\"";
        return result;
    }

    public static double roundToTwo(double number) {
        String rounder = String.format("%1.2f", number);
        rounder = rounder.replace(',', '.');
        number = Double.valueOf(rounder);
        return number;
    }

}
