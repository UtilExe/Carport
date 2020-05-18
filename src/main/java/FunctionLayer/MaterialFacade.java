package FunctionLayer;

import DBAccess.MaterialMapper;

import java.util.ArrayList;

/**
 * @author Daniel, Emil, Jannich, Jimmy
 * MaterialFacade håndterer processen mellem Præsentationslaget, Funktionslaget og Database-niveau for Materialer.
 */

public class MaterialFacade {

    public static ArrayList<Integer> getLengthsFromStorage(int ID) throws UniversalSampleException {
        ArrayList<Integer> lengthsFromStorage = MaterialMapper.getLengthsFromStorage(ID);
        return lengthsFromStorage;
    }

    public static ArrayList<String> getRoofData(int ID, int measure, int tmpAmount) throws UniversalSampleException {
        ArrayList<String> roofData = MaterialMapper.getRoofData(ID, measure, tmpAmount);
        return roofData;
    }

    public static ArrayList<String> getScrewsAndTilesData(int ID, int tmpAmount) throws UniversalSampleException {
        ArrayList<String> screwsAndTilesData = MaterialMapper.getScrewsAndTilesData(ID, tmpAmount);
        return screwsAndTilesData;
    }

    public static int getAmountPrUnit(int ID) throws UniversalSampleException {
        int amountPrUnit = MaterialMapper.getAmountPrUnit(ID);
        return amountPrUnit;
    }

    public static ArrayList<String> getBandData(int ID, int bandLength, int rolesOfBand) throws UniversalSampleException {
        ArrayList<String> bandData = MaterialMapper.getBandData(ID, bandLength, rolesOfBand);
        return bandData;
    }

    public static ArrayList<Double> getWidthHeightFromDimensionMeasureInCM(int ID) throws UniversalSampleException {
        ArrayList<Double> widthHeightFromDimensionMeasureInCM = MaterialMapper.getWidthHeightFromDimensionMeasureInCM(ID);
        return widthHeightFromDimensionMeasureInCM;
    }

    public static ArrayList<String> getPillarData(int ID, int pillarAmount, ArrayList<Double> pillarLengths) throws UniversalSampleException {
        ArrayList<String> pillarData = MaterialMapper.getPillarData(ID, pillarAmount, pillarLengths);
        return pillarData;
    }

    public static ArrayList<String> getTransomAndHeadInShedData(int ID, int[] transomsOrHeads) throws UniversalSampleException {
        ArrayList<String> transomAndHeadInShedData = MaterialMapper.getTransomAndHeadInShedData(ID, transomsOrHeads);
        return transomAndHeadInShedData;
    }

    public static ArrayList<String> getPlankData(int ID, int height, int plankAmount) throws UniversalSampleException {
        ArrayList<String> plankData = MaterialMapper.getPlankData(ID, height, plankAmount);
        return plankData;
    }

}
