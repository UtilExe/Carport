package PresentationLayer;

import DBAccess.IDMapper;
import DBAccess.MaterialMapper;
import FunctionLayer.LoginSampleException;

import java.util.ArrayList;
import java.util.HashMap;

public class main {
    public static void main(String[] args) throws LoginSampleException {
        //User user = LogicFacade.createUser("test", "test@gmail.com", "test", 12345678);
        //System.out.println(user.getName());
        int carportHeight = 220;
        int carportLengthCM = 1200;
        int carportWidthCM = 600;
        int shedLength = 220;
        int shedWidth = 530;
        int shedHeight = (int) (carportHeight - MaterialMapper.getWidthHeightFromDimensionMeasureInCM(6).get(1));
        boolean hasShed = true;

        final int AMOUNT_OF_HEADS = 2;
        int amountOfRafts;
        int bandLength;
        int pillarAmount;
        int band;
        int rolesOfBand;
        int amountOfTiles;
        int amountOfScrews;
        int[] transomSides;
        int[] transomFrontAndBack;
        int[] headsInShed;

        final int AMOUNT_OF_FRONT_BACK_UNDERPLANKS = 2;
        final int AMOUNT_OF_SIDE_UNDERPLANKS = 2;
        final int AMOUNT_OF_FRONT_OVERPLANKS = 1;
        final int AMOUNT_OF_SIDE_OVERPLANKS = 2;
        final int AMOUNT_OF_SIDE_WATERPLANKS = 2;
        final int AMOUNT_OF_FRONT_WATERPLANKS = 1;
        final int BATTERNLENGTH_DOOR = 420;

        HashMap<String, Integer> productIDs = IDMapper.getIDs();

        final int RAFT_AND_HEAD_ID = 6;
        final int BAND_ID = 10;
        final int PILLAR_ID = 7;
        final int PLANK_ID = 1;
        final int WATERPLANK_ID = 3;
        final int TILE_ID = 8;
        final int BOTTOMSCREW_ID = 9;
        final int UNIVERSALSCREW_ID = 11;
        final int PLANK_WATERSCREW_ID = 12;
        final int BRACKETSCEW_ID = 15;
        final int CARRIAGEBOLT_ID = 16;
        final int SQUAREWASHER_ID = 17;
        final int BATTERN_ID = 4;
        final int TRANSOM_ID = 5;


        ArrayList<Double> pillarLengths;




        MaterialCalculator calcTest = new MaterialCalculator();
        band = calcTest.calcBandAmount(carportLengthCM, carportWidthCM, hasShed, shedLength);
        rolesOfBand = calcTest.getRolesAmountBand(band);
        amountOfRafts = calcTest.calcRaftAmount(carportLengthCM);
        pillarAmount = calcTest.calcPillarAmount(carportLengthCM, hasShed, shedLength);
        pillarLengths = calcTest.getPillarHeight(carportHeight, carportLengthCM, hasShed, shedLength);
        amountOfTiles = calcTest.getRoofTileAmount(carportLengthCM, carportWidthCM);
        amountOfScrews = calcTest.getRoofScrewAmount(carportLengthCM, carportWidthCM);
        int amountOfUniversalScrews = calcTest.getUniversalScrews(carportLengthCM);
        int amountOfPlankWaterScrews = calcTest.getPlankAndWaterScrews();
        int amountOfBracketScrews = calcTest.getBracketScrews(carportLengthCM);
        int amountOfCarriageBolts = calcTest.getCarriageBolts(carportLengthCM, hasShed, shedLength);
        transomSides = calcTest.getTransomsLengthSides(shedLength);
        transomFrontAndBack = calcTest.getTransomsLengthFrontAndBack(shedWidth);
        headsInShed = calcTest.getHeadsInShed(shedLength);




        ArrayList<String> heads = MaterialMapper.getRoofData(RAFT_AND_HEAD_ID, carportLengthCM, AMOUNT_OF_HEADS );
        ArrayList<String> rafts = MaterialMapper.getRoofData(RAFT_AND_HEAD_ID, carportWidthCM, amountOfRafts);
        ArrayList<String> bands = MaterialMapper.getBandData(BAND_ID, band, rolesOfBand);
        ArrayList<String> pillars = MaterialMapper.getPillarData(PILLAR_ID, pillarAmount, pillarLengths);
        ArrayList<String> frontbackunderplanks = MaterialMapper.getRoofData(PLANK_ID, carportWidthCM, AMOUNT_OF_FRONT_BACK_UNDERPLANKS);
        ArrayList<String> sideunderplanks = MaterialMapper.getRoofData(PLANK_ID, carportLengthCM, AMOUNT_OF_SIDE_UNDERPLANKS);
        ArrayList<String> frontoverplanks = MaterialMapper.getRoofData(PLANK_ID, carportWidthCM, AMOUNT_OF_FRONT_OVERPLANKS);
        ArrayList<String> sideoverplanks = MaterialMapper.getRoofData(PLANK_ID, carportLengthCM, AMOUNT_OF_SIDE_OVERPLANKS);
        ArrayList<String> sidewaterplanks = MaterialMapper.getRoofData(WATERPLANK_ID, carportLengthCM, AMOUNT_OF_SIDE_WATERPLANKS);
        ArrayList<String> frontwaterplanks = MaterialMapper.getRoofData(WATERPLANK_ID, carportWidthCM, AMOUNT_OF_FRONT_WATERPLANKS);
        ArrayList<String> tiles = MaterialMapper.getScrewsAndTilesData(TILE_ID, amountOfTiles);
        ArrayList<String> roofBottomScrew = MaterialMapper.getScrewsAndTilesData(BOTTOMSCREW_ID, amountOfScrews);
        ArrayList<String> universalScrews = MaterialMapper.getScrewsAndTilesData(UNIVERSALSCREW_ID, amountOfUniversalScrews);
        ArrayList<String> plankWaterScrews = MaterialMapper.getScrewsAndTilesData(PLANK_WATERSCREW_ID, amountOfPlankWaterScrews);
        ArrayList<String> bracketScrews = MaterialMapper.getScrewsAndTilesData(BRACKETSCEW_ID, amountOfBracketScrews);
        ArrayList<String> carriageBolts = MaterialMapper.getScrewsAndTilesData(CARRIAGEBOLT_ID, amountOfCarriageBolts);
        ArrayList<String> squareWashers = MaterialMapper.getScrewsAndTilesData(SQUAREWASHER_ID, amountOfCarriageBolts);
        ArrayList<String> battern = MaterialMapper.getRoofData(BATTERN_ID, BATTERNLENGTH_DOOR, 1);
        ArrayList<String> transomSidesInfo = MaterialMapper.getTransomAndHeadInShedData(TRANSOM_ID, transomSides);
        ArrayList<String> transomFrontAndBackInfo = MaterialMapper.getTransomAndHeadInShedData(TRANSOM_ID, transomFrontAndBack);
        ArrayList<String> headsInShedInfo = MaterialMapper.getTransomAndHeadInShedData(RAFT_AND_HEAD_ID, headsInShed);


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
        System.out.println("Info om bundskruer til tagplader: " + roofBottomScrew);
        System.out.println("Info om universal skruer til remmen: " + universalScrews);
        System.out.println("Info om skruer til stern og vandbrædt: " + plankWaterScrews);
        System.out.println("Info om skruer til beslag for spærg: " + bracketScrews);
        System.out.println("Info om skruer til montering af rem på stolper: " + carriageBolts);
        //Dette her er antal firkantskiver, men da det altid vil være samme antal som carriageBolts er det mere logisk og bruge den metode.
        System.out.println("Info om firkantskirver til montering af rem på stolper: " + squareWashers);
        System.out.println("Ekstra med skur: ");
        System.out.println("Info om lægte til bagside af døren: " + battern);
        System.out.println("Info om løsholte til siderne af skuret: " + transomSidesInfo);
        System.out.println("Info om løsholte til skurets gavle: " + transomFrontAndBackInfo);
        System.out.println("Info om rem i skuret: " + headsInShedInfo);




        //System.out.println(calcTest.calcPillarAmount(carportLengthCM));
        //System.out.println(calcTest.calcRaftAmount(carportLengthCM));
        /*System.out.println();
        int[] result = calcTest.calcBandAmount(carportLengthCM, carportWidthCM);
        for (int i : result) {
            System.out.println(i);
        }*/


    }
}
