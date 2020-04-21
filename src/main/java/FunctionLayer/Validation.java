package FunctionLayer;

public class Validation {

    public static int getInteger(String value) {
        int result = 0;
        if(!(value.isEmpty())) {
            result = Integer.parseInt(value);
        } else {
            result = -1;
        }
        return result;
    }

}
