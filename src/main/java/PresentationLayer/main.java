package PresentationLayer;

import DBAccess.MaterialMapper;
import FunctionLayer.LoginSampleException;

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
        int amountOfTiles;
        final int RAFT_ID = 6;
        final int BAND_ID = 10;
        final int PILLAR_ID = 7;
        final int PLANK_ID = 1;
        final int WATERPLANK_ID = 3;
        int pillarAmount;
        final int AMOUNT_OF_FRONT_BACK_UNDERPLANKS = 2;
        final int AMOUNT_OF_SIDE_UNDERPLANKS = 2;
        final int AMOUNT_OF_FRONT_OVERPLANKS = 1;
        final int AMOUNT_OF_SIDE_OVERPLANKS = 2;
        final int AMOUNT_OF_SIDE_WATERPLANKS = 2;
        final int AMOUNT_OF_FRONT_WATERPLANKS = 1;
        final int TILE_ID = 8;

        ArrayList<Double> pillarLengths;




        MaterialCalculator calcTest = new MaterialCalculator();
        band = calcTest.calcBandAmount(carportLengthCM, carportWidthCM);
        bandLength = band[3];
        rolesOfBand = calcTest.getRolesAmountBand(bandLength);
        amountOfRafts = calcTest.calcRaftAmount(carportLengthCM);
        pillarAmount = calcTest.calcPillarAmount(carportLengthCM);
        pillarLengths = calcTest.getPillarHeight(carportHeight, carportLengthCM);
        amountOfTiles = calcTest.getRoofTileAmount(carportLengthCM, carportWidthCM);

        ArrayList<String> heads = MaterialMapper.getRoofData(RAFT_ID, carportLengthCM, AMOUNT_OF_HEADS );
        ArrayList<String> rafts = MaterialMapper.getRoofData(RAFT_ID, carportWidthCM, amountOfRafts);
        ArrayList<String> bands = MaterialMapper.getBandData(BAND_ID, bandLength, rolesOfBand);
        ArrayList<String> pillars = MaterialMapper.getPillarData(PILLAR_ID, pillarAmount, pillarLengths);
        ArrayList<String> frontbackunderplanks = MaterialMapper.getRoofData(PLANK_ID, carportWidthCM, AMOUNT_OF_FRONT_BACK_UNDERPLANKS);
        ArrayList<String> sideunderplanks = MaterialMapper.getRoofData(PLANK_ID, carportLengthCM, AMOUNT_OF_SIDE_UNDERPLANKS);
        ArrayList<String> frontoverplanks = MaterialMapper.getRoofData(PLANK_ID, carportWidthCM, AMOUNT_OF_FRONT_OVERPLANKS);
        ArrayList<String> sideoverplanks = MaterialMapper.getRoofData(PLANK_ID, carportLengthCM, AMOUNT_OF_SIDE_OVERPLANKS);
        ArrayList<String> sidewaterplanks = MaterialMapper.getRoofData(WATERPLANK_ID, carportLengthCM, AMOUNT_OF_SIDE_WATERPLANKS);
        ArrayList<String> frontwaterplanks = MaterialMapper.getRoofData(WATERPLANK_ID, carportWidthCM, AMOUNT_OF_FRONT_WATERPLANKS);
        ArrayList<String> tiles = MaterialMapper.getRoofTileData(TILE_ID, amountOfTiles);

        System.out.println("Info om rem:" + heads);
        System.out.println("Info om spær:" + rafts);
        System.out.println("Info om hulbånd:" + bands);
        System.out.println("Info om stolper: " + pillars);
        System.out.println("Info om understernsbrædder til for- og bagende: " + frontbackunderplanks);
        System.out.println("Info om understernsbrædder til siderne: " + sideunderplanks);
        System.out.println("Info om oversternsbræt til forenden: " + frontoverplanks);
        System.out.println("Info om oversternsbrædder til siderne: " + sideoverplanks);
        System.out.println("Info om vandbræt til siderne: " + sidewaterplanks);
        System.out.println("Info om vandbræt til forenden: " + frontwaterplanks);
        System.out.println("Info om tagplader: " + tiles);




        //System.out.println(calcTest.calcPillarAmount(carportLengthCM));
        //System.out.println(calcTest.calcRaftAmount(carportLengthCM));
        /*System.out.println();
        int[] result = calcTest.calcBandAmount(carportLengthCM, carportWidthCM);
        for (int i : result) {
            System.out.println(i);
        }*/


    }
}
