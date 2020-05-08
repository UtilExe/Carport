package PresentationLayer;

import FunctionLayer.Objects.MaterialList;

import java.util.ArrayList;

public class main {

    public static void main(String[] args) {

        ArrayList<String> nummer1 = new ArrayList<>();
        ArrayList<String> nummer2 = new ArrayList<>();

        nummer1.add("1-Hej");
        nummer1.add("1-ib");
        nummer1.add("1-hovsa");
        nummer2.add("2-Hej");
        nummer2.add("2-ib");
        nummer2.add("2-hovsa");

        ArrayList<ArrayList<String>> materials = new ArrayList<>();
        materials.add(nummer1);
        materials.add(nummer2);

        MaterialList allMaterials = new MaterialList(materials);





    }




}
