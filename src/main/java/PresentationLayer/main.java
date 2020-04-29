package PresentationLayer;

import DBAccess.MaterialMapper;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.User;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws LoginSampleException {
        //User user = LogicFacade.createUser("test", "test@gmail.com", "test", 12345678);
        //System.out.println(user.getName());
        int carportHeight = 220;
        int carportLengthCM = 780;
        int carportWidthCM = 600;
        final int AMOUNT_OF_HEADS = 2;
        int amountOfRafts;
        int bandLength;
        int[] band = new int[4];
        int rolesOfBand;
        final int RAFT_ID = 6;
        final int BAND_ID = 10;
        final int FASCIA_ID = 1;
        final int PILLAR_ID = 7;
        int pillarAmount;
        ArrayList<Double> pillarLengths;




        MaterialCalculator calcTest = new MaterialCalculator();
        band = calcTest.calcBandAmount(carportLengthCM, carportWidthCM);
        bandLength = band[3];
        rolesOfBand = calcTest.getRolesAmountBand(bandLength);
        amountOfRafts = calcTest.calcRaftAmount(carportLengthCM);
        pillarAmount = calcTest.calcPillarAmount(carportLengthCM);
        pillarLengths = calcTest.getPillarHeight(carportHeight, carportLengthCM);

        ArrayList<String> heads = MaterialMapper.getRemOrRaftData(RAFT_ID, carportLengthCM, AMOUNT_OF_HEADS );
        ArrayList<String> rafts = MaterialMapper.getRemOrRaftData(RAFT_ID, carportWidthCM, amountOfRafts);
        ArrayList<String> bands = MaterialMapper.getBandData(BAND_ID, bandLength, rolesOfBand);
        ArrayList<String> pillars = MaterialMapper.getPillarData(PILLAR_ID, pillarAmount, pillarLengths);

        System.out.println("Info om rem:" + heads);
        System.out.println("Info om spær:" + rafts);
        System.out.println("Info om hulbånd:" + bands);
        System.out.println("Info om stolper: " + pillars);



        //System.out.println(calcTest.calcPillarAmount(carportLengthCM));
        //System.out.println(calcTest.calcRaftAmount(carportLengthCM));
        /*System.out.println();
        int[] result = calcTest.calcBandAmount(carportLengthCM, carportWidthCM);
        for (int i : result) {
            System.out.println(i);
        }*/


    }
}
