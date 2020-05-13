package FunctionLayer.Entities;

import java.util.ArrayList;

public class ComparatorMaterial implements java.util.Comparator<ArrayList<String>> {

    public int compare(ArrayList<String> o1, ArrayList<String> o2) {
        return -o1.get(0).compareTo(o2.get(0));
    }
}
