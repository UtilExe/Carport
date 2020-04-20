package FunctionLayer;

import DBAccess.CarportMapper;

import java.util.ArrayList;

public class Initialisation {
    //Creating lists
    private static ArrayList<Integer> lengths;
    private static ArrayList<Integer> widths;
    private static ArrayList<String> roofs;
    private static ArrayList<Integer> shedLengths;
    private static ArrayList<Integer> shedWidths;

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

    public static void initShedLengths() {
        if(shedLengths == null) {
            shedLengths = LogicFacade.getShedLength();
        }
    }

    public static void initShedWidths() {
        if(shedWidths == null) {
            shedWidths = LogicFacade.getShedWidth();
        }
    }



    //Get lists
    public static ArrayList<Integer>getLengths() {return lengths;}
    public static ArrayList<Integer> getWidths() {return widths;}
    public static ArrayList<String> getRoofs() {return roofs;}
    public static ArrayList<Integer>getShedLengths() {return shedLengths;}
    public static ArrayList<Integer>getShedWidths() {return shedWidths;}
}
