package FunctionLayer;

import DBAccess.CarportMapper;

import java.util.ArrayList;

public class Initialisation {
    //Creating lists
    private static ArrayList<Integer> lengths;
    private static ArrayList<Integer> width;


    //Inits
    public static void initLengths() {
        if(lengths == null) {
            lengths = LogicFacade.getCarportLength();
        }
    }

    public static void initWidth() {
        if(lengths == null) {
            lengths = LogicFacade.getCarportWidth();
        }
    }


    //Get lists
    public static ArrayList<Integer>getLengths() {return lengths;}
    public static ArrayList<Integer>getWidth() {return width;}
}
