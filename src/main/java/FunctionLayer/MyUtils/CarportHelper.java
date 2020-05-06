package FunctionLayer.MyUtils;

import DBAccess.MaterialMapper;
import PresentationLayer.MaterialCalculator;

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
}
