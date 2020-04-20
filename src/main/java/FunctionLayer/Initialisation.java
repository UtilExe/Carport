package FunctionLayer;

import DBAccess.CarportMapper;

import java.util.ArrayList;

public class Initialisation {
    //Creating lists
    private static ArrayList<Integer> lengths;
    private static ArrayList<Integer> widths;
    private static ArrayList<String> roofs;


    //Inits
    public static void initLengths() {
        if(lengths == null) {
            lengths = LogicFacade.getCarportLength();
        }
    }

    public static void initWidth() {
        if(widths == null) {
            widths = LogicFacade.getCarportWidth();
        }
    }

    public static void initRoof() {
        if(roofs == null) {
            roofs = LogicFacade.getCarportRoof();
        }
    }

    //Get lists
    public static ArrayList<Integer>getLengths() {return lengths;}
    public static ArrayList<Integer> getWidths() {return widths;}
    public static ArrayList<String> getRoofs() {return roofs;}
}
