package FunctionLayer;

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

    public static boolean getBoolean(String value) {
        int result;
        if(value.equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    // Denne metode forhindrer, at en double til string får erstatet '.' med ','
    public static String fromDoubleToString(double number) {
        String result = "";
        result = String.valueOf(number);
        result = result.replace(',', '.');

        return result;
    }


}
