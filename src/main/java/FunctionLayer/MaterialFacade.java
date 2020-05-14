package FunctionLayer;

import DBAccess.MaterialMapper;

import java.util.ArrayList;

public class MaterialFacade {

    public static ArrayList<Integer> getLengthsFromStorage(int ID) {
        ArrayList<Integer> lengthsFromStorage = MaterialMapper.getLengthsFromStorage(ID);
        return lengthsFromStorage;
    }

    public static ArrayList<String> getRoofData(int ID, int measure, int tmpAmount) {
        ArrayList<String> roofData = MaterialMapper.getRoofData(ID, measure, tmpAmount);
        return roofData;
    }

    public static ArrayList<String> getScrewsAndTilesData(int ID, int tmpAmount) {
        ArrayList<String> screwsAndTilesData = MaterialMapper.getScrewsAndTilesData(ID, tmpAmount);
        return screwsAndTilesData;
    }

    public static int getAmountPrUnit(int ID) {
        int amountPrUnit = MaterialMapper.getAmountPrUnit(ID);
        return amountPrUnit;
    }

    public static ArrayList<String> getBandData(int ID, int bandLength, int rolesOfBand) {
        ArrayList<String> bandData = MaterialMapper.getBandData(ID, bandLength, rolesOfBand);
        return bandData;
    }

    public static ArrayList<Double> getWidthHeightFromDimensionMeasureInCM(int ID) {
        ArrayList<Double> widthHeightFromDimensionMeasureInCM = MaterialMapper.getWidthHeightFromDimensionMeasureInCM(ID);
        return widthHeightFromDimensionMeasureInCM;
    }

    public static ArrayList<String> getPillarData(int ID, int pillarAmount, ArrayList<Double> pillarLengths) {
        ArrayList<String> pillarData = MaterialMapper.getPillarData(ID, pillarAmount, pillarLengths);
        return pillarData;
    }

    public static ArrayList<String> getTransomAndHeadInShedData(int ID, int[] transomsOrHeads) {
        ArrayList<String> transomAndHeadInShedData = MaterialMapper.getTransomAndHeadInShedData(ID, transomsOrHeads);
        return transomAndHeadInShedData;
    }

    public static ArrayList<String> getPlankData(int ID, int height, int plankAmount) {
        ArrayList<String> plankData = MaterialMapper.getPlankData(ID, height, plankAmount);
        return plankData;
    }

}
