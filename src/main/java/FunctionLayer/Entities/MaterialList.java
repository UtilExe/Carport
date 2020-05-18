package FunctionLayer.Entities;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Daniel, Emil, Jannich, Jimmy
 * MaterialList klassen indeholder de nødvendige variabler for at kunne oprette en Materiale Liste.
 */

public class MaterialList {
    ComparatorMaterial sortAlphabetically = new ComparatorMaterial();

    private ArrayList<ArrayList<String>> list;

    public MaterialList(ArrayList<ArrayList<String>> list) {
        this.list = list;
    }

    public ArrayList<ArrayList<String>> getList() {
        return list;
    }

    public void addToList(ArrayList<String> materials) {
        this.list.add(materials);
    }

    public void sortListAlphabetically() {
        Collections.sort(this.list, sortAlphabetically);
    }

}
