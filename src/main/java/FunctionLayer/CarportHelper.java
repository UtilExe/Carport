package FunctionLayer;

import DBAccess.MaterialMapper;
import FunctionLayer.Objects.MaterialList;
import PresentationLayer.MaterialCalculator;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CarportHelper {

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

    MaterialCalculator calcTest;
    private int shedHeight;
    private int band;
    private int rolesOfBand;
    private int amountOfRafts;
    private int pillarAmount;
    private int amountOfTiles;
    private int amountOfScrews;
    private int amountOfUniversalScrews;
    private int amountOfPlankWaterScrews;
    private int amountOfBracketScrews;
    private int amountOfCarriageBolts;
    private int[] transomSides;
    private int[] transomFrontAndBack;
    private int[] headsInShed;
    private int planksForShedAmount;
    private int packageOfOuterScrews;
    private int packageOfInnerScrews;
    private int amountOfAngleMount;
    private int tilesForPitchedRoof;
    private int packagesOfTileBindersAndHooks;
    private int amountOfRooftileStones;
    private int amountOfRooftileStoneBrackets;
    private int amountOfToplathHolders;
    private int gavlPlankLength;
    private int amountOfGavlPlank;
    private int amountOfPlanksForGavlMount;
    private int planksForGavlMountLength;
    private int amountOfRooflaths;
    private int amountOfRooflathScrews;

    private static ArrayList<Double> pillarLengths;
    private static ArrayList<String> heads;
    private static ArrayList<String> rafts;
    private static ArrayList<String> bands;
    private static ArrayList<String> pillars;
    private static ArrayList<String> frontbackunderplanks;
    private static ArrayList<String> sideunderplanks;
    private static ArrayList<String> frontoverplanks;
    private static ArrayList<String> sideoverplanks;
    private static ArrayList<String> sidewaterplanks;
    private static ArrayList<String> frontwaterplanks;
    private static ArrayList<String> tiles;
    private static ArrayList<String> roofBottomScrew;
    private static ArrayList<String> universalScrews;
    private static ArrayList<String> plankWaterScrews;
    private static ArrayList<String> bracketScrews;
    private static ArrayList<String> carriageBolts;
    private static ArrayList<String> squareWashers;
    private static ArrayList<String> battern;
    private static ArrayList<String> transomSidesInfo;
    private static ArrayList<String> transomFrontAndBackInfo;
    private static ArrayList<String> headsInShedInfo;
    private static ArrayList<String> planksInShedInfo;
    private static ArrayList<String> outerScrewsInfo;
    private static ArrayList<String> innerScrewsInfo;
    private static ArrayList<String> doorGribInfo;
    private static ArrayList<String> tHingeInfo;
    private static ArrayList<String> angleMountInfo;
    private static ArrayList<String> tilesPitchedRoof;
    private static ArrayList<String> tileBindersHooks;
    private static ArrayList<String> rooftileStones;
    private static ArrayList<String> rooftileStoneBracketsInfo;
    private static ArrayList<String> toplathHoldersInfo;
    private static ArrayList<String> gavlPlankInfo;
    private static ArrayList<String> gavlPlankMountInfo;
    private static ArrayList<String> rooflathsInfo;
    private static ArrayList<String> rooflathScrewsInfo;
    private static ArrayList<String> allPriceIndexes;

    private static ArrayList<ArrayList<String>> materials = new ArrayList<>();
    private static MaterialList allMaterials = new MaterialList(materials);

    private boolean hasShed = false;
    private boolean hasPitch = false;
    private boolean invalidInput = false;

    private int shedLength;
    private int shedWidth;
    private int carportPitch;
    private int carportLengthCM;
    private int carportWidthCM;
    private int carportHeight;

    public CarportHelper(int carportLengthCM, int carportWidthCM, int carportHeight, int shedLength, int shedWidth, int carportPitch) {
        if (shedLength > 0 && shedWidth > 0) {
            setHasShed(true);
        }
        if (carportPitch > 0) {
            setHasPitch(true);
        }

        this.carportLengthCM = carportLengthCM;
        this.carportWidthCM = carportWidthCM;
        this.carportHeight = carportHeight;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
        this.carportPitch = carportPitch;

        /// Initialize Variables
        this.calcTest = new MaterialCalculator();
        this.shedHeight = (int) (carportHeight - MaterialMapper.getWidthHeightFromDimensionMeasureInCM(6).get(1));
        this.band = calcTest.calcBandAmount(carportLengthCM, carportWidthCM, hasShed, shedLength);
        this.rolesOfBand = calcTest.getRolesAmountBand(band);
        this.amountOfRafts = calcTest.calcRaftAmount(carportLengthCM, hasPitch);
        this.pillarAmount = calcTest.calcPillarAmount(carportLengthCM, hasShed, shedLength);
        this.amountOfTiles = calcTest.getRoofTileAmount(carportLengthCM, carportWidthCM);
        this.amountOfScrews = calcTest.getRoofScrewAmount(carportLengthCM, carportWidthCM, BOTTOMSCREW_ID);
        this.amountOfUniversalScrews = calcTest.getUniversalScrews(carportLengthCM, hasPitch);
        this.amountOfPlankWaterScrews = calcTest.getPlankAndWaterScrews();
        this.amountOfBracketScrews = calcTest.getBracketScrews(carportLengthCM, hasPitch);
        this.amountOfCarriageBolts = calcTest.getCarriageBolts(carportLengthCM, hasShed, shedLength);
        this.transomSides = calcTest.getTransomsLengthSides(shedLength);
        this.transomFrontAndBack = calcTest.getTransomsLengthFrontAndBack(shedWidth);
        this.headsInShed = calcTest.getHeadsInShed(shedLength);
        this.planksForShedAmount = calcTest.getPlanksForShed(shedLength, shedWidth);
        this.packageOfOuterScrews = calcTest.getOuterScrewsShed(shedLength, shedWidth, OUTERSCREW_ID);
        this.packageOfInnerScrews = calcTest.getInnerScrewsShed(shedLength, shedWidth, INNERSCREW_ID);
        this.amountOfAngleMount = calcTest.getAngleMount(shedLength, shedWidth);
        this.tilesForPitchedRoof = calcTest.getTilesForPitchedRoof();
        this.packagesOfTileBindersAndHooks = calcTest.getPackagesOfTileBindersAndHooks();
        this.amountOfRooftileStones = calcTest.getAmountOfRoofTileStones(carportLengthCM);
        this.amountOfRooftileStoneBrackets = calcTest.getAmountOfRoofTileStoneBrackets(amountOfRooftileStones);
        this.amountOfToplathHolders = calcTest.getAmountOfToplathHolders(amountOfRafts);
        this.gavlPlankLength = calcTest.getGavlPlanksLength(carportWidthCM, carportPitch);
        this.amountOfGavlPlank = calcTest.getAmountOfGavlPlanks();
        this.amountOfPlanksForGavlMount = calcTest.getAmountOfPlanksForGavlMount(carportWidthCM);
        this.planksForGavlMountLength = calcTest.getPlanksForGavlMountLength(carportWidthCM, carportPitch);
        this.amountOfRooflaths = calcTest.getAmountOfRooflaths(carportWidthCM);
        this.amountOfRooflathScrews = calcTest.getAmountOfToplathScrews(amountOfRooflaths, amountOfRafts, ROOFLATH_SCREWS_ID);

        this.pillarLengths = calcTest.getPillarHeight(carportHeight, carportLengthCM, hasShed, shedLength, hasPitch);
        this.heads = MaterialMapper.getRoofData(RAFT_AND_HEAD_ID, carportLengthCM, AMOUNT_OF_HEADS);
        this.rafts = MaterialMapper.getRoofData(RAFT_AND_HEAD_ID, carportWidthCM, amountOfRafts);
        this.bands = MaterialMapper.getBandData(BAND_ID, band, rolesOfBand);
        this.pillars = MaterialMapper.getPillarData(PILLAR_ID, pillarAmount, pillarLengths);
        this.frontbackunderplanks = MaterialMapper.getRoofData(PLANK_ID, carportWidthCM, AMOUNT_OF_FRONT_BACK_UNDERPLANKS);
        this.sideunderplanks = MaterialMapper.getRoofData(PLANK_ID, carportLengthCM, AMOUNT_OF_SIDE_UNDERPLANKS);
        this.frontoverplanks = MaterialMapper.getRoofData(PLANK_ID, carportWidthCM, AMOUNT_OF_FRONT_OVERPLANKS);
        this.sideoverplanks = MaterialMapper.getRoofData(PLANK_ID, carportLengthCM, AMOUNT_OF_SIDE_OVERPLANKS);
        this.sidewaterplanks = MaterialMapper.getRoofData(WATERPLANK_AND_SHEDPLANK_ID, carportLengthCM, AMOUNT_OF_SIDE_WATERPLANKS);
        this.frontwaterplanks = MaterialMapper.getRoofData(WATERPLANK_AND_SHEDPLANK_ID, carportWidthCM, AMOUNT_OF_FRONT_WATERPLANKS);
        this.tiles = MaterialMapper.getScrewsAndTilesData(TILE_ID, amountOfTiles);
        this.roofBottomScrew = MaterialMapper.getScrewsAndTilesData(BOTTOMSCREW_ID, amountOfScrews);
        this.universalScrews = MaterialMapper.getScrewsAndTilesData(UNIVERSALSCREW_ID, amountOfUniversalScrews);
        this.plankWaterScrews = MaterialMapper.getScrewsAndTilesData(PLANK_WATERSCREW_ID, amountOfPlankWaterScrews);
        this.bracketScrews = MaterialMapper.getScrewsAndTilesData(BRACKETSCEW_ID, amountOfBracketScrews);
        this.carriageBolts = MaterialMapper.getScrewsAndTilesData(CARRIAGEBOLT_ID, amountOfCarriageBolts);
        this.squareWashers = MaterialMapper.getScrewsAndTilesData(SQUAREWASHER_ID, amountOfCarriageBolts);
        this.battern = MaterialMapper.getRoofData(BATTERN_ROOFLATH_ID, BATTERNLENGTH_DOOR, 1);
        this.transomSidesInfo = MaterialMapper.getTransomAndHeadInShedData(TRANSOM_ID, transomSides);
        this.transomFrontAndBackInfo = MaterialMapper.getTransomAndHeadInShedData(TRANSOM_ID, transomFrontAndBack);
        this.headsInShedInfo = MaterialMapper.getTransomAndHeadInShedData(RAFT_AND_HEAD_ID, headsInShed);
        this.planksInShedInfo = MaterialMapper.getPlankData(WATERPLANK_AND_SHEDPLANK_ID, shedHeight, planksForShedAmount);
        this.outerScrewsInfo = MaterialMapper.getScrewsAndTilesData(OUTERSCREW_ID, packageOfOuterScrews);
        this.innerScrewsInfo = MaterialMapper.getScrewsAndTilesData(INNERSCREW_ID, packageOfInnerScrews);
        this.doorGribInfo = MaterialMapper.getScrewsAndTilesData(DOORGRIB_ID, DOOR_GRIB);
        this.tHingeInfo = MaterialMapper.getScrewsAndTilesData(THINGE_ID, T_HINGE);
        this.angleMountInfo = MaterialMapper.getScrewsAndTilesData(ANGLEMOUNT_ID, amountOfAngleMount);
        this.tilesPitchedRoof = MaterialMapper.getScrewsAndTilesData(TILES_PITCHED_ID, tilesForPitchedRoof);
        this.tileBindersHooks = MaterialMapper.getScrewsAndTilesData(TILES_BINDERS_HOOKS_ID, packagesOfTileBindersAndHooks);
        this.rooftileStones = MaterialMapper.getScrewsAndTilesData(ROOFTILE_STONES_ID, amountOfRooftileStones);
        this.rooftileStoneBracketsInfo = MaterialMapper.getScrewsAndTilesData(ROOFTILE_STONE_BRACKETS_ID, amountOfRooftileStoneBrackets);
        this.toplathHoldersInfo = MaterialMapper.getScrewsAndTilesData(TOPLATH_HOLDER_ID, amountOfToplathHolders);
        this.gavlPlankInfo = MaterialMapper.getRoofData(PLANK_ID, gavlPlankLength, amountOfGavlPlank);
        this.gavlPlankMountInfo = MaterialMapper.getPlankData(WATERPLANK_AND_SHEDPLANK_ID, planksForGavlMountLength, amountOfPlanksForGavlMount);
        this.rooflathsInfo = MaterialMapper.getRoofData(BATTERN_ROOFLATH_ID, carportLengthCM, amountOfRooflaths);
        this.rooflathScrewsInfo = MaterialMapper.getScrewsAndTilesData(ROOFLATH_SCREWS_ID, amountOfRooflathScrews);
        this.allPriceIndexes = new ArrayList<>();
    }

    public boolean isHasShed() {
        return hasShed;
    }

    public boolean isHasPitch() {
        return hasPitch;
    }

    public int getCarportPitch() {
        return carportPitch;
    }

    public int getShedLength() {
        return shedLength;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public int getCarportWidthCM() {
        return carportWidthCM;
    }

    public int getCarportLengthCM() {
        return carportLengthCM;
    }

    public int getCarportHeight() {
        return carportHeight;
    }

    public boolean isInvalidInput() {
        return invalidInput;
    }

    public void setHasShed(boolean hasShed) {
        this.hasShed = hasShed;
    }

    public void setHasPitch(boolean hasPitch) {
        this.hasPitch = hasPitch;
    }

    public void setInvalidInput(boolean invalidInput) {
        this.invalidInput = invalidInput;
    }

    public ArrayList<String> getAllPriceIndexes() {
        return allPriceIndexes;
    }

    public void initArrayList() {

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

        if (isHasPitch()) {
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

        if (isHasShed()) {
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
    }

    public static MaterialList test() {
        allMaterials.addToList(heads);
        allMaterials.addToList(rafts);
        allMaterials.addToList(bands);
        allMaterials.addToList(pillars);
        allMaterials.addToList(frontbackunderplanks);
        allMaterials.addToList(sideunderplanks);
        allMaterials.addToList(frontoverplanks);
        allMaterials.addToList(sideoverplanks);
        allMaterials.addToList(sidewaterplanks);
        allMaterials.addToList(frontwaterplanks);
        allMaterials.addToList(tiles);
        allMaterials.addToList(roofBottomScrew);
        allMaterials.addToList(universalScrews);
        allMaterials.addToList(plankWaterScrews);
        allMaterials.addToList(bracketScrews);
        allMaterials.addToList(carriageBolts);
        allMaterials.addToList(squareWashers);
        // Med skur:
        allMaterials.addToList(battern);
        allMaterials.addToList(transomSidesInfo);
        allMaterials.addToList(transomFrontAndBackInfo);
        allMaterials.addToList(headsInShedInfo);
        allMaterials.addToList(planksInShedInfo);
        allMaterials.addToList(outerScrewsInfo);
        allMaterials.addToList(innerScrewsInfo);
        allMaterials.addToList(doorGribInfo);
        allMaterials.addToList(tHingeInfo);
        allMaterials.addToList(angleMountInfo);
        // Med rejsningstag:
        allMaterials.addToList(tilesPitchedRoof);
        allMaterials.addToList(tileBindersHooks);
        allMaterials.addToList(rooftileStones);
        allMaterials.addToList(rooftileStoneBracketsInfo);
        allMaterials.addToList(toplathHoldersInfo);
        allMaterials.addToList(gavlPlankInfo);
        allMaterials.addToList(gavlPlankMountInfo);
        allMaterials.addToList(rooflathsInfo);
        allMaterials.addToList(rooflathScrewsInfo);


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
        //System.out.println(finalPrice);

        return allMaterials;
    }
}