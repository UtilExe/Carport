package FunctionLayer.Objects;

import FunctionLayer.Komparator;

import java.util.ArrayList;
import java.util.Collections;

public class MaterialList {
    Komparator sortAlphabetically = new Komparator();

    private ArrayList<ArrayList<String>> list;

    public MaterialList(ArrayList<ArrayList<String>> list) {
        this.list = list;
    }

    public ArrayList<ArrayList<String>> getList() {
        return list;
    }

    public void setList(ArrayList<ArrayList<String>> list) {
        this.list = list;
    }

    public void addToList(ArrayList<String> materials) {
        this.list.add(materials);

    }

    public void sortListAlphabetically() {

        Collections.sort(this.list, sortAlphabetically);

    }

}
