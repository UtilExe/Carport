package FunctionLayer.Entities;

import java.util.ArrayList;

/**
 * @author Daniel, Emil, Jannich, Jimmy
 * ComparatorMaterial klassen bruges til at sortere Materialelisten i omvendte alfabetisk rækkefølge.
 */

public class ComparatorMaterial implements java.util.Comparator<ArrayList<String>> {

    public int compare(ArrayList<String> o1, ArrayList<String> o2) {
        return -o1.get(0).compareTo(o2.get(0));
    }
}
