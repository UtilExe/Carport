package FunctionLayer;

/**
 * @author Daniel, Emil, Jannich, Jimmy
 * ValidationValues klassen hjælper med at undgå fejl på siden og sikre at forskellige castinger ikke går galt.
 * Det er lavet i en metode for at undgå at gentage validerings-koden.
 */

public class ValidationValues {

    public static int getInteger(String value) {
        int result = 0;
        if(!(value == null)) {
            result = Integer.parseInt(value);
        } else {
            result = -1;
        }
        return result;
    }

    // Denne metode forhindrer, at en double til string får erstatet '.' med ','
    public static String fromDoubleToString(double number) {
        String result = "";
        result = String.valueOf(number);
        result = result.replace(',', '.');

        return result;
    }





}
