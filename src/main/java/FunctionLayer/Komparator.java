package FunctionLayer;

import java.util.ArrayList;
import java.util.Comparator;

public class Komparator implements Comparator<ArrayList<String>> {

    public int compare(ArrayList<String> o1, ArrayList<String> o2) {
        return -o1.get(0).compareTo(o2.get(0));
    }
}
