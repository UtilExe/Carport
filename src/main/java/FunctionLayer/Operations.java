package FunctionLayer;

public class Operations {

    public static String[] splitterForDimensions(String strFromDB, String regex) {
        String[] splittedString = new String[2];
        splittedString = strFromDB.split(regex);
        return splittedString;
    }

    public static String addQuotesAround(String input) {
        String result = "\"" + input + "\"";
        return result;
    }


}
