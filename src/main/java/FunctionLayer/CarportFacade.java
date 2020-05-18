package FunctionLayer;

import DBAccess.CarportMapper;

import java.util.ArrayList;

/**
 * @author Daniel, Emil, Jannich, Jimmy
 * CarportFacaden håndterer processen mellem Præsentationslaget, Funktionslaget og Database-niveau for Carport.
 */

public class CarportFacade {

    public static ArrayList<Integer> getCarportLength() throws UniversalSampleException {
        ArrayList<Integer> carportLength = CarportMapper.getCarportMeasure("længde");
        return carportLength;
    }

    public static ArrayList<Integer> getCarportWidth() throws UniversalSampleException {
        ArrayList<Integer> carportWidth = CarportMapper.getCarportMeasure("bredde");
        return carportWidth;
    }

    public static ArrayList<Integer> getCarportHeight() throws UniversalSampleException {
        ArrayList<Integer> carportHeight = CarportMapper.getCarportMeasure("højde");
        return carportHeight;
    }

    public static ArrayList<String> getCarportRoof() throws UniversalSampleException {
        ArrayList<String> carportRoof = CarportMapper.getCarportRoof();
        return carportRoof;
    }

    public static ArrayList<Integer> getShedLength() throws UniversalSampleException {
        ArrayList<Integer> shedLength = CarportMapper.getShedMeasure("længde");
        return shedLength;
    }

    public static ArrayList<Integer> getShedWidth() throws UniversalSampleException {
        ArrayList<Integer> shedWidth = CarportMapper.getShedMeasure("bredde");
        return shedWidth;
    }

    public static ArrayList<Integer> getRoofPitch() throws UniversalSampleException {
        ArrayList<Integer> roofPitch = CarportMapper.getRoofPitch();
        return roofPitch;
    }

}
