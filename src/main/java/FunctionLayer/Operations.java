package FunctionLayer;

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

}
