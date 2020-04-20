package FunctionLayer;

import DBAccess.CarportMapper;

import java.util.ArrayList;

public class Initialisation {
    //Creating lists
    private static ArrayList<Integer> lengths;

    //Inits
    public static void initLengths() {
        if(lengths == null) {
            lengths = LogicFacade.getCarportLength();
        }
    }

    //Get lists
    public static ArrayList<Integer>getLengths() {return lengths;}
}
