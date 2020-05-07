package PresentationLayer;

import DBAccess.MaterialMapper;
import FunctionLayer.*;
import FunctionLayer.Objects.Carport;
import FunctionLayer.Objects.CarportFlat;
import FunctionLayer.Objects.CarportPitch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;


public class CarportDesign extends Command {

    private final int AMOUNT_OF_HEADS = 2;
    private final int AMOUNT_OF_FRONT_BACK_UNDERPLANKS = 2;
    private final int AMOUNT_OF_SIDE_UNDERPLANKS = 2;
    private final int AMOUNT_OF_FRONT_OVERPLANKS = 1;
    private final int AMOUNT_OF_SIDE_OVERPLANKS = 2;
    private final int AMOUNT_OF_SIDE_WATERPLANKS = 2;
    private final int AMOUNT_OF_FRONT_WATERPLANKS = 1;
    private final int BATTERNLENGTH_DOOR = 420;
    private final int DOOR_GRIB = 1;
    private final int T_HINGE = 2;

    private final int RAFT_AND_HEAD_ID = 6;
    private final int BAND_ID = 10;
    private final int PILLAR_ID = 7;
    private final int PLANK_ID = 1;
    private final int WATERPLANK_AND_SHEDPLANK_ID = 3;
    private final int TILE_ID = 8;
    private final int BOTTOMSCREW_ID = 9;
    private final int UNIVERSALSCREW_ID = 11;
    private final int PLANK_WATERSCREW_ID = 12;
    private final int BRACKETSCEW_ID = 15;
    private final int CARRIAGEBOLT_ID = 16;
    private final int SQUAREWASHER_ID = 17;
    private final int BATTERN_ROOFLATH_ID = 4;
    private final int TRANSOM_ID = 5;
    private final int OUTERSCREW_ID = 13;
    private final int INNERSCREW_ID = 12;
    private final int DOORGRIB_ID = 18;
    private final int THINGE_ID = 19;
    private final int ANGLEMOUNT_ID = 20;
    private final int TILES_PITCHED_ID = 21;
    private final int TILES_BINDERS_HOOKS_ID = 25;
    private final int ROOFTILE_STONES_ID = 22;
    private final int ROOFTILE_STONE_BRACKETS_ID = 24;
    private final int TOPLATH_HOLDER_ID = 23;
    private final int ROOFLATH_SCREWS_ID = 30;

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        String tmpCarportLength = request.getParameter("length");
        String tmpCarportWidth = request.getParameter("width");
        String tmpCarportHeight = request.getParameter("height");
        int shedLength = 0;
        int shedWidth = 0;
        String roofMaterial = request.getParameter("roof");
        boolean hasShed = false;
        boolean hasPitch = false;
        int price = 0;
        int carportPitch = 0;
        boolean invalidInput = false;


        if (request.getParameter("checkboxShed") != null) {
            String tmpShedLength = request.getParameter("shedLength");
            String tmpShedWidth = request.getParameter("shedWidth");
            shedLength = Validation.getInteger(tmpShedLength);
            shedWidth = Validation.getInteger(tmpShedWidth);
            hasShed = true;
        }

        int carportLengthCM = Validation.getInteger(tmpCarportLength);
        int carportWidthCM = Validation.getInteger(tmpCarportWidth);
        int carportHeight = Validation.getInteger(tmpCarportHeight);
        int shedHeight = (int) (carportHeight - MaterialMapper.getWidthHeightFromDimensionMeasureInCM(6).get(1));
        if (request.getParameter("roofPitch") != null) {
            String tmpRoofPitch = request.getParameter("roofPitch");
            carportPitch = Validation.getInteger(tmpRoofPitch);
            request.setAttribute("roof_pitch", Initialisation.getRoofPitch());
        }

        if (carportPitch > 0) {
            hasPitch = true;
        }

        if (shedWidth > carportWidthCM || shedLength > carportLengthCM) {
            request.setAttribute("fejl", "Skurrets mål er større end carporten! Prøv igen med korrekte værdier");
            invalidInput = true;
        }

        request.setAttribute("carport_lengths", Initialisation.getCarportLengths());
        request.setAttribute("carport_widths", Initialisation.getCarportWidths());
        request.setAttribute("carport_heights", Initialisation.getCarportHeights());
        request.setAttribute("carport_roofs", Initialisation.getRoofs());
        request.setAttribute("shed_lengths", Initialisation.getShedLengths());
        request.setAttribute("shed_widths", Initialisation.getShedWidths());

        MaterialCalculator calcTest = new MaterialCalculator();
        int band = calcTest.calcBandAmount(carportLengthCM, carportWidthCM, hasShed, shedLength);
        int rolesOfBand = calcTest.getRolesAmountBand(band);
        int amountOfRafts = calcTest.calcRaftAmount(carportLengthCM, hasPitch);
        int pillarAmount = calcTest.calcPillarAmount(carportLengthCM, hasShed, shedLength);
        ArrayList<Double> pillarLengths = calcTest.getPillarHeight(carportHeight, carportLengthCM, hasShed, shedLength, hasPitch);
        int amountOfTiles = calcTest.getRoofTileAmount(carportLengthCM, carportWidthCM);
        int amountOfScrews = calcTest.getRoofScrewAmount(carportLengthCM, carportWidthCM, BOTTOMSCREW_ID);
        int amountOfUniversalScrews = calcTest.getUniversalScrews(carportLengthCM, hasPitch);
        int amountOfPlankWaterScrews = calcTest.getPlankAndWaterScrews();
        int amountOfBracketScrews = calcTest.getBracketScrews(carportLengthCM, hasPitch);
        int amountOfCarriageBolts = calcTest.getCarriageBolts(carportLengthCM, hasShed, shedLength);
        int[] transomSides = calcTest.getTransomsLengthSides(shedLength);
        int[] transomFrontAndBack = calcTest.getTransomsLengthFrontAndBack(shedWidth);
        int[] headsInShed = calcTest.getHeadsInShed(shedLength);
        int planksForShedAmount = calcTest.getPlanksForShed(shedLength, shedWidth);
        int packageOfOuterScrews = calcTest.getOuterScrewsShed(shedLength, shedWidth, OUTERSCREW_ID);
        int packageOfInnerScrews = calcTest.getInnerScrewsShed(shedLength, shedWidth, INNERSCREW_ID);
        int amountOfAngleMount = calcTest.getAngleMount(shedLength, shedWidth);
        int tilesForPitchedRoof = calcTest.getTilesForPitchedRoof();
        int packagesOfTileBindersAndHooks = calcTest.getPackagesOfTileBindersAndHooks();
        int amountOfRooftileStones = calcTest.getAmountOfRoofTileStones(carportLengthCM);
        int amountOfRooftileStoneBrackets = calcTest.getAmountOfRoofTileStoneBrackets(amountOfRooftileStones);
        int amountOfToplathHolders = calcTest.getAmountOfToplathHolders(amountOfRafts);
        int gavlPlankLength = calcTest.getGavlPlanksLength(carportWidthCM, carportPitch);
        int amountOfGavlPlank = calcTest.getAmountOfGavlPlanks();
        int amountOfPlanksForGavlMount = calcTest.getAmountOfPlanksForGavlMount(carportWidthCM);
        int planksForGavlMountLength = calcTest.getPlanksForGavlMountLength(carportWidthCM, carportPitch);
        int amountOfRooflaths = calcTest.getAmountOfRooflaths(carportWidthCM);
        int amountOfRooflathScrews = calcTest.getAmountOfToplathScrews(amountOfRooflaths, amountOfRafts, ROOFLATH_SCREWS_ID);

        ArrayList<String> heads = MaterialMapper.getRoofData(RAFT_AND_HEAD_ID, carportLengthCM, AMOUNT_OF_HEADS);
        ArrayList<String> rafts = MaterialMapper.getRoofData(RAFT_AND_HEAD_ID, carportWidthCM, amountOfRafts);
        ArrayList<String> bands = MaterialMapper.getBandData(BAND_ID, band, rolesOfBand);
        ArrayList<String> pillars = MaterialMapper.getPillarData(PILLAR_ID, pillarAmount, pillarLengths);
        ArrayList<String> frontbackunderplanks = MaterialMapper.getRoofData(PLANK_ID, carportWidthCM, AMOUNT_OF_FRONT_BACK_UNDERPLANKS);
        ArrayList<String> sideunderplanks = MaterialMapper.getRoofData(PLANK_ID, carportLengthCM, AMOUNT_OF_SIDE_UNDERPLANKS);
        ArrayList<String> frontoverplanks = MaterialMapper.getRoofData(PLANK_ID, carportWidthCM, AMOUNT_OF_FRONT_OVERPLANKS);
        ArrayList<String> sideoverplanks = MaterialMapper.getRoofData(PLANK_ID, carportLengthCM, AMOUNT_OF_SIDE_OVERPLANKS);
        ArrayList<String> sidewaterplanks = MaterialMapper.getRoofData(WATERPLANK_AND_SHEDPLANK_ID, carportLengthCM, AMOUNT_OF_SIDE_WATERPLANKS);
        ArrayList<String> frontwaterplanks = MaterialMapper.getRoofData(WATERPLANK_AND_SHEDPLANK_ID, carportWidthCM, AMOUNT_OF_FRONT_WATERPLANKS);
        ArrayList<String> tiles = MaterialMapper.getScrewsAndTilesData(TILE_ID, amountOfTiles);
        ArrayList<String> roofBottomScrew = MaterialMapper.getScrewsAndTilesData(BOTTOMSCREW_ID, amountOfScrews);
        ArrayList<String> universalScrews = MaterialMapper.getScrewsAndTilesData(UNIVERSALSCREW_ID, amountOfUniversalScrews);
        ArrayList<String> plankWaterScrews = MaterialMapper.getScrewsAndTilesData(PLANK_WATERSCREW_ID, amountOfPlankWaterScrews);
        ArrayList<String> bracketScrews = MaterialMapper.getScrewsAndTilesData(BRACKETSCEW_ID, amountOfBracketScrews);
        ArrayList<String> carriageBolts = MaterialMapper.getScrewsAndTilesData(CARRIAGEBOLT_ID, amountOfCarriageBolts);
        ArrayList<String> squareWashers = MaterialMapper.getScrewsAndTilesData(SQUAREWASHER_ID, amountOfCarriageBolts);
        ArrayList<String> battern = MaterialMapper.getRoofData(BATTERN_ROOFLATH_ID, BATTERNLENGTH_DOOR, 1);
        ArrayList<String> transomSidesInfo = MaterialMapper.getTransomAndHeadInShedData(TRANSOM_ID, transomSides);
        ArrayList<String> transomFrontAndBackInfo = MaterialMapper.getTransomAndHeadInShedData(TRANSOM_ID, transomFrontAndBack);
        ArrayList<String> headsInShedInfo = MaterialMapper.getTransomAndHeadInShedData(RAFT_AND_HEAD_ID, headsInShed);
        ArrayList<String> planksInShedInfo = MaterialMapper.getPlankData(WATERPLANK_AND_SHEDPLANK_ID, shedHeight, planksForShedAmount);
        ArrayList<String> outerScrewsInfo = MaterialMapper.getScrewsAndTilesData(OUTERSCREW_ID, packageOfOuterScrews);
        ArrayList<String> innerScrewsInfo = MaterialMapper.getScrewsAndTilesData(INNERSCREW_ID, packageOfInnerScrews);
        ArrayList<String> doorGribInfo = MaterialMapper.getScrewsAndTilesData(DOORGRIB_ID, DOOR_GRIB);
        ArrayList<String> tHingeInfo = MaterialMapper.getScrewsAndTilesData(THINGE_ID, T_HINGE);
        ArrayList<String> angleMountInfo = MaterialMapper.getScrewsAndTilesData(ANGLEMOUNT_ID, amountOfAngleMount);
        ArrayList<String> tilesPitchedRoof = MaterialMapper.getScrewsAndTilesData(TILES_PITCHED_ID, tilesForPitchedRoof);
        ArrayList<String> tileBindersHooks = MaterialMapper.getScrewsAndTilesData(TILES_BINDERS_HOOKS_ID, packagesOfTileBindersAndHooks);
        ArrayList<String> rooftileStones = MaterialMapper.getScrewsAndTilesData(ROOFTILE_STONES_ID, amountOfRooftileStones);
        ArrayList<String> rooftileStoneBracketsInfo = MaterialMapper.getScrewsAndTilesData(ROOFTILE_STONE_BRACKETS_ID, amountOfRooftileStoneBrackets);
        ArrayList<String> toplathHoldersInfo = MaterialMapper.getScrewsAndTilesData(TOPLATH_HOLDER_ID, amountOfToplathHolders);
        ArrayList<String> gavlPlankInfo = MaterialMapper.getRoofData(PLANK_ID, gavlPlankLength, amountOfGavlPlank);
        ArrayList<String> gavlPlankMountInfo = MaterialMapper.getPlankData(WATERPLANK_AND_SHEDPLANK_ID, planksForGavlMountLength, amountOfPlanksForGavlMount);
        ArrayList<String> rooflathsInfo = MaterialMapper.getRoofData(BATTERN_ROOFLATH_ID, carportLengthCM, amountOfRooflaths);
        ArrayList<String> rooflathScrewsInfo = MaterialMapper.getScrewsAndTilesData(ROOFLATH_SCREWS_ID, amountOfRooflathScrews);

        ArrayList<String> allPriceIndexes = new ArrayList<>();
        allPriceIndexes.add(heads.get(heads.size() - 1));
        allPriceIndexes.add(rafts.get(rafts.size() - 1));
        allPriceIndexes.add(bands.get(bands.size() - 1));
        allPriceIndexes.add(pillars.get(pillars.size() - 1));
        allPriceIndexes.add(frontbackunderplanks.get(frontbackunderplanks.size() - 1));
        allPriceIndexes.add(sideunderplanks.get(sideunderplanks.size() - 1));
        allPriceIndexes.add(frontoverplanks.get(frontoverplanks.size() - 1));
        allPriceIndexes.add(sideoverplanks.get(sideoverplanks.size() - 1));
        allPriceIndexes.add(roofBottomScrew.get(roofBottomScrew.size() - 1));
        allPriceIndexes.add(sidewaterplanks.get(sidewaterplanks.size() - 1));
        allPriceIndexes.add(frontwaterplanks.get(frontwaterplanks.size() - 1));
        allPriceIndexes.add(universalScrews.get(universalScrews.size() - 1));
        allPriceIndexes.add(plankWaterScrews.get(plankWaterScrews.size() - 1));
        allPriceIndexes.add(bracketScrews.get(bracketScrews.size() - 1));
        allPriceIndexes.add(carriageBolts.get(carriageBolts.size() - 1));
        allPriceIndexes.add(squareWashers.get(squareWashers.size() - 1));

        if (hasPitch) {
            allPriceIndexes.add(tilesPitchedRoof.get(tilesPitchedRoof.size() - 1));
            allPriceIndexes.add(tileBindersHooks.get(tileBindersHooks.size() - 1));
            allPriceIndexes.add(rooftileStones.get(rooftileStones.size() - 1));
            allPriceIndexes.add(rooftileStoneBracketsInfo.get(rooftileStoneBracketsInfo.size() - 1));
            allPriceIndexes.add(toplathHoldersInfo.get(toplathHoldersInfo.size() - 1));
            allPriceIndexes.add(gavlPlankInfo.get(gavlPlankInfo.size() - 1));
            allPriceIndexes.add(gavlPlankMountInfo.get(gavlPlankMountInfo.size() - 1));
            allPriceIndexes.add(rooflathsInfo.get(rooflathsInfo.size() - 1));
            allPriceIndexes.add(rooflathScrewsInfo.get(rooflathScrewsInfo.size() - 1));
        } else {
            allPriceIndexes.add(tiles.get(tiles.size() - 1));
        }

        if (hasShed) {
            allPriceIndexes.add(battern.get(battern.size() - 1));
            allPriceIndexes.add(transomSidesInfo.get(transomSidesInfo.size() - 1));
            allPriceIndexes.add(transomFrontAndBackInfo.get(transomFrontAndBackInfo.size() - 1));
            allPriceIndexes.add(headsInShedInfo.get(headsInShedInfo.size() - 1));
            allPriceIndexes.add(planksInShedInfo.get(planksInShedInfo.size() - 1));
            allPriceIndexes.add(outerScrewsInfo.get(outerScrewsInfo.size() - 1));
            allPriceIndexes.add(innerScrewsInfo.get(innerScrewsInfo.size() - 1));
            allPriceIndexes.add(doorGribInfo.get(doorGribInfo.size() - 1));
            allPriceIndexes.add(tHingeInfo.get(tHingeInfo.size() - 1));
            allPriceIndexes.add(angleMountInfo.get(angleMountInfo.size() - 1));
        }


        int finalPrice = calcTest.fullPrice(allPriceIndexes);

        if (invalidInput != true) {
            LogicFacade.addCarportToCustOrder(carportLengthCM, carportWidthCM, carportHeight, hasShed, shedWidth, shedLength, hasPitch, carportPitch, roofMaterial, finalPrice);
            ArrayList<Carport> tmpCart = new ArrayList<>();
            Cart cart = new Cart(tmpCart);
            Carport carport;
            if (hasPitch) {
                carport = new CarportPitch (carportLengthCM, carportWidthCM, carportHeight, roofMaterial, hasShed, shedWidth, shedLength, hasPitch, carportPitch);
            }
            else {
                carport = new CarportFlat(carportLengthCM, carportWidthCM, carportHeight, roofMaterial, hasShed, shedWidth, shedLength);
            }
            cart.addToCart(tmpCart, carport);
        }

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
        System.out.println("Info om brædt til skurbeklædning: " + planksInShedInfo);
        System.out.println("Info om skruer til ydre beklædning på skuret: " + outerScrewsInfo);
        System.out.println("Info om skruer til indre beklædning på skuret: " + innerScrewsInfo);
        System.out.println("Info om stalddørsgreb: " + doorGribInfo);
        System.out.println("Info om t-hængsel til dør: " + tHingeInfo);
        System.out.println("Info om vinkelbeslag til løsholte: " + angleMountInfo);
        System.out.println("Ekstra med rejsninstag: ");
        System.out.println("Info om tagsten til rejsningstag: " + tilesPitchedRoof);
        System.out.println("Info om tagstens bindere og nakkekroge: " + tileBindersHooks);
        System.out.println("Info om rygsten: " + rooftileStones);
        System.out.println("Info om rygstens beslag: " + rooftileStoneBracketsInfo);
        System.out.println("Info om toplægte holdere: " + toplathHoldersInfo);
        System.out.println("Info om gavl (vindskeder): " + gavlPlankInfo);
        System.out.println("Info om gavl beklædning på tag: " + gavlPlankMountInfo);
        System.out.println("Info om taglægter: " + rooflathsInfo);
        System.out.println("Info om skruer til taglægter: " + rooflathScrewsInfo);
        System.out.println(finalPrice);

        if (hasPitch) {
            return "rejsningtag";
        } else {
            return "fladttag";
        }


    }
}
