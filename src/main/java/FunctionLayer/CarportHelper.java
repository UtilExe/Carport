package FunctionLayer;


import FunctionLayer.Entities.MaterialList;
import FunctionLayer.Entities.Svg;

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

    private ArrayList<Double> pillarLengths;
    private ArrayList<String> heads;
    private ArrayList<String> rafts;
    private ArrayList<String> bands;
    private ArrayList<String> pillars;
    private ArrayList<String> frontbackunderplanks;
    private ArrayList<String> sideunderplanks;
    private ArrayList<String> frontoverplanks;
    private ArrayList<String> sideoverplanks;
    private ArrayList<String> sidewaterplanks;
    private ArrayList<String> frontwaterplanks;
    private ArrayList<String> tiles;
    private ArrayList<String> roofBottomScrew;
    private ArrayList<String> universalScrews;
    private ArrayList<String> plankWaterScrews;
    private ArrayList<String> bracketScrews;
    private ArrayList<String> carriageBolts;
    private ArrayList<String> squareWashers;
    private ArrayList<String> battern;
    private ArrayList<String> transomSidesInfo;
    private ArrayList<String> transomFrontAndBackInfo;
    private ArrayList<String> headsInShedInfo;
    private ArrayList<String> planksInShedInfo;
    private ArrayList<String> outerScrewsInfo;
    private ArrayList<String> innerScrewsInfo;
    private ArrayList<String> doorGribInfo;
    private ArrayList<String> tHingeInfo;
    private ArrayList<String> angleMountInfo;
    private ArrayList<String> tilesPitchedRoof;
    private ArrayList<String> tileBindersHooks;
    private ArrayList<String> rooftileStones;
    private ArrayList<String> rooftileStoneBracketsInfo;
    private ArrayList<String> toplathHoldersInfo;
    private ArrayList<String> gavlPlankInfo;
    private ArrayList<String> gavlPlankMountInfo;
    private ArrayList<String> rooflathsInfo;
    private ArrayList<String> rooflathScrewsInfo;
    private ArrayList<String> allPriceIndexes;

    private ArrayList<ArrayList<String>> materials = new ArrayList<>();
    private MaterialList allMaterials = new MaterialList(materials);

    private boolean hasShed = false;
    private boolean hasPitch = false;
    private boolean invalidInput = false;

    private int shedLength;
    private int shedWidth;
    private int carportPitch;
    private int carportLengthCM;
    private int carportWidthCM;
    private int carportHeight;

    public CarportHelper(int carportLengthCM, int carportWidthCM, int carportHeight, int shedLength, int shedWidth, int carportPitch) throws UniversalSampleException {
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
        this.shedHeight = (int) (carportHeight - MaterialFacade.getWidthHeightFromDimensionMeasureInCM(6).get(1));
        this.band = calcTest.calcLengthOfBands(carportLengthCM, carportWidthCM, hasShed, shedLength);
        this.rolesOfBand = calcTest.getRolesAmountBand(band);
        this.amountOfRafts = calcTest.calcRaftAmount(carportLengthCM, hasPitch);
        this.pillarAmount = calcTest.calcPillarAmount(carportLengthCM, hasShed, shedLength, shedWidth, carportWidthCM);
        this.amountOfTiles = calcTest.getRoofTileAmount(carportLengthCM, carportWidthCM);
        this.amountOfScrews = calcTest.getRoofScrewAmount(carportLengthCM, carportWidthCM, BOTTOMSCREW_ID);
        this.amountOfUniversalScrews = calcTest.getUniversalScrews(carportLengthCM, hasPitch);
        this.amountOfPlankWaterScrews = calcTest.getPlankAndWaterScrews();
        this.amountOfBracketScrews = calcTest.getBracketScrews(carportLengthCM, hasPitch);
        this.amountOfCarriageBolts = calcTest.getCarriageBolts(carportLengthCM, hasShed, shedLength, shedWidth, carportWidthCM);
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

        this.pillarLengths = calcTest.getPillarHeight(carportHeight, carportLengthCM, hasShed, shedLength, hasPitch, shedWidth, carportWidthCM);
        this.heads = MaterialFacade.getRoofData(RAFT_AND_HEAD_ID, carportLengthCM, AMOUNT_OF_HEADS);
        this.rafts = MaterialFacade.getRoofData(RAFT_AND_HEAD_ID, carportWidthCM, amountOfRafts);
        this.bands = MaterialFacade.getBandData(BAND_ID, band, rolesOfBand);
        this.pillars = MaterialFacade.getPillarData(PILLAR_ID, pillarAmount, pillarLengths);
        this.frontbackunderplanks = MaterialFacade.getRoofData(PLANK_ID, carportWidthCM, AMOUNT_OF_FRONT_BACK_UNDERPLANKS);
        this.sideunderplanks = MaterialFacade.getRoofData(PLANK_ID, carportLengthCM, AMOUNT_OF_SIDE_UNDERPLANKS);
        this.frontoverplanks = MaterialFacade.getRoofData(PLANK_ID, carportWidthCM, AMOUNT_OF_FRONT_OVERPLANKS);
        this.sideoverplanks = MaterialFacade.getRoofData(PLANK_ID, carportLengthCM, AMOUNT_OF_SIDE_OVERPLANKS);
        this.sidewaterplanks = MaterialFacade.getRoofData(WATERPLANK_AND_SHEDPLANK_ID, carportLengthCM, AMOUNT_OF_SIDE_WATERPLANKS);
        this.frontwaterplanks = MaterialFacade.getRoofData(WATERPLANK_AND_SHEDPLANK_ID, carportWidthCM, AMOUNT_OF_FRONT_WATERPLANKS);
        this.tiles = MaterialFacade.getScrewsAndTilesData(TILE_ID, amountOfTiles);
        this.roofBottomScrew = MaterialFacade.getScrewsAndTilesData(BOTTOMSCREW_ID, amountOfScrews);
        this.universalScrews = MaterialFacade.getScrewsAndTilesData(UNIVERSALSCREW_ID, amountOfUniversalScrews);
        this.plankWaterScrews = MaterialFacade.getScrewsAndTilesData(PLANK_WATERSCREW_ID, amountOfPlankWaterScrews);
        this.bracketScrews = MaterialFacade.getScrewsAndTilesData(BRACKETSCEW_ID, amountOfBracketScrews);
        this.carriageBolts = MaterialFacade.getScrewsAndTilesData(CARRIAGEBOLT_ID, amountOfCarriageBolts);
        this.squareWashers = MaterialFacade.getScrewsAndTilesData(SQUAREWASHER_ID, amountOfCarriageBolts);
        this.battern = MaterialFacade.getRoofData(BATTERN_ROOFLATH_ID, BATTERNLENGTH_DOOR, 1);
        this.transomSidesInfo = MaterialFacade.getTransomAndHeadInShedData(TRANSOM_ID, transomSides);
        this.transomFrontAndBackInfo = MaterialFacade.getTransomAndHeadInShedData(TRANSOM_ID, transomFrontAndBack);
        this.headsInShedInfo = MaterialFacade.getTransomAndHeadInShedData(RAFT_AND_HEAD_ID, headsInShed);
        this.planksInShedInfo = MaterialFacade.getPlankData(WATERPLANK_AND_SHEDPLANK_ID, shedHeight, planksForShedAmount);
        this.outerScrewsInfo = MaterialFacade.getScrewsAndTilesData(OUTERSCREW_ID, packageOfOuterScrews);
        this.innerScrewsInfo = MaterialFacade.getScrewsAndTilesData(INNERSCREW_ID, packageOfInnerScrews);
        this.doorGribInfo = MaterialFacade.getScrewsAndTilesData(DOORGRIB_ID, DOOR_GRIB);
        this.tHingeInfo = MaterialFacade.getScrewsAndTilesData(THINGE_ID, T_HINGE);
        this.angleMountInfo = MaterialFacade.getScrewsAndTilesData(ANGLEMOUNT_ID, amountOfAngleMount);
        this.tilesPitchedRoof = MaterialFacade.getScrewsAndTilesData(TILES_PITCHED_ID, tilesForPitchedRoof);
        this.tileBindersHooks = MaterialFacade.getScrewsAndTilesData(TILES_BINDERS_HOOKS_ID, packagesOfTileBindersAndHooks);
        this.rooftileStones = MaterialFacade.getScrewsAndTilesData(ROOFTILE_STONES_ID, amountOfRooftileStones);
        this.rooftileStoneBracketsInfo = MaterialFacade.getScrewsAndTilesData(ROOFTILE_STONE_BRACKETS_ID, amountOfRooftileStoneBrackets);
        this.toplathHoldersInfo = MaterialFacade.getScrewsAndTilesData(TOPLATH_HOLDER_ID, amountOfToplathHolders);
        this.gavlPlankInfo = MaterialFacade.getRoofData(PLANK_ID, gavlPlankLength, amountOfGavlPlank);
        this.gavlPlankMountInfo = MaterialFacade.getPlankData(WATERPLANK_AND_SHEDPLANK_ID, planksForGavlMountLength, amountOfPlanksForGavlMount);
        this.rooflathsInfo = MaterialFacade.getRoofData(BATTERN_ROOFLATH_ID, carportLengthCM, amountOfRooflaths);
        this.rooflathScrewsInfo = MaterialFacade.getScrewsAndTilesData(ROOFLATH_SCREWS_ID, amountOfRooflathScrews);
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

    public MaterialList createMaterialList(boolean hasShed, boolean hasPitch) {
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

        if (hasShed) {
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
        }

        if (hasPitch) {
            allMaterials.addToList(tilesPitchedRoof);
            allMaterials.addToList(tileBindersHooks);
            allMaterials.addToList(rooftileStones);
            allMaterials.addToList(rooftileStoneBracketsInfo);
            allMaterials.addToList(toplathHoldersInfo);
            allMaterials.addToList(gavlPlankInfo);
            allMaterials.addToList(gavlPlankMountInfo);
            allMaterials.addToList(rooflathsInfo);
            allMaterials.addToList(rooflathScrewsInfo);
        }

        allMaterials.sortListAlphabetically();

        return allMaterials;
    }

    ArrayList<Double> headRaftMeasure = MaterialFacade.getWidthHeightFromDimensionMeasureInCM(RAFT_AND_HEAD_ID);
    ArrayList<Double> pillarMeasure = MaterialFacade.getWidthHeightFromDimensionMeasureInCM(PILLAR_ID);
    ArrayList<Double> plankMeasure = MaterialFacade.getWidthHeightFromDimensionMeasureInCM(PLANK_ID);
    ArrayList<Double> roofLathsMeasure = MaterialFacade.getWidthHeightFromDimensionMeasureInCM(BATTERN_ROOFLATH_ID);



    public String svgDrawingTop(int carportLength, boolean hasShed) throws UniversalSampleException {
        final int MARKER_HEIGHT = 12;
        final int Y_DATA = 10;
        final int X_DATA = 75;
        final int PUSH_AWAY = 40;
        final int DRAW_END_VERTICAL = carportWidthCM - MARKER_HEIGHT + Y_DATA;
        final int DRAW_END_HORIZONTAL = carportLengthCM - MARKER_HEIGHT + X_DATA;
        final int PUSH_TEXT_DOWN = 70;
        // Vi lægger 20 til, så f.eks. den sidste rem kommer med på tegningen.
        String viewbox = "0,0," + (carportLength + 20) + "," + carportWidthCM;
        String viewboxInner = "0,0," + (carportLength + 100) + "," + (carportWidthCM + 100);
        // vi trækker 1 fra, da det 1. spær ikke skal med i beregningen.
        double lengthBetweenRafts = carportLength / (amountOfRafts - 1.0);

        Svg svg = new Svg(carportLength, carportWidthCM, viewbox, 75, 10);
        Svg svgInnerDrawing = new Svg(carportLength, carportWidthCM, viewboxInner, 75, 10);
        // Carport:
        svgInnerDrawing.addArrowLength(X_DATA, carportWidthCM + PUSH_AWAY, DRAW_END_HORIZONTAL, carportWidthCM + PUSH_AWAY);
        svgInnerDrawing.addArrowWidth(PUSH_AWAY, Y_DATA, PUSH_AWAY, DRAW_END_VERTICAL);
        svgInnerDrawing.addText((PUSH_AWAY / 2), ((Y_DATA + carportWidthCM) / 2), carportWidthCM, ((DRAW_END_HORIZONTAL / 2.0) + PUSH_AWAY), (carportWidthCM + PUSH_TEXT_DOWN), carportLength);
        svg.addRect(0, 0, carportLength, carportWidthCM);

        // Rem:
        // 35 er hvor mange cm rem sidder fra carportens sider.
        svg.addRect(0, 35, carportLength, headRaftMeasure.get(0));
        svg.addRect(0, carportWidthCM - 35, carportLength, headRaftMeasure.get(0));

        // Sternbrædder til siderne
        ArrayList<Double> overPlankMeasure = MaterialFacade.getWidthHeightFromDimensionMeasureInCM(PLANK_ID);
        svg.addRect(0, 0, carportLength, overPlankMeasure.get(0));
        svg.addRect(0, carportWidthCM, carportLength, overPlankMeasure.get(0));

        // Spær:
        double x = 0;
        for (int i = 0; i < amountOfRafts; i++) {
            svg.addRect(x, 0, headRaftMeasure.get(0), carportWidthCM);
            x += lengthBetweenRafts;
        }

        // Stolper:
        int stolpeX = 0;
        int methodPillarAmount = pillarAmount;
        double lengthBetweenPillars = carportLength / ((methodPillarAmount / 2.0) - 1.0);
        double pillarTransition = 0.0;
        final int CARPORT_END_DIST = 30;
        final int REM_IN_DIST = 35 + 35;

        if (hasShed) {
            if(shedWidth == carportWidthCM - REM_IN_DIST) {
                methodPillarAmount -= 6;
            } else {
                methodPillarAmount -= 7;
            }
            lengthBetweenPillars = (carportLength - shedLength - CARPORT_END_DIST) / ((methodPillarAmount / 2.0) - 1.0);

            //Skurstolper
            svg.addRect(carportLength - shedLength - CARPORT_END_DIST, 35, pillarMeasure.get(1), pillarMeasure.get(1)); //Top venstre stolpe
            svg.addRect(carportLength - shedLength - CARPORT_END_DIST, 35 + (shedWidth / 2), pillarMeasure.get(1), pillarMeasure.get(1)); //Midt venstre stolpe
            svg.addRect(carportLength - CARPORT_END_DIST - pillarMeasure.get(1), 35 + (shedWidth / 2), pillarMeasure.get(1), pillarMeasure.get(1)); //Midt højre stolpe
            svg.addRect(carportLength - shedLength - CARPORT_END_DIST, 35 + shedWidth - pillarMeasure.get(1), pillarMeasure.get(1), pillarMeasure.get(1)); //Venstre nederste stolpe

            if(!(shedWidth == carportWidthCM - REM_IN_DIST))
            svg.addRect(carportLength - CARPORT_END_DIST - pillarMeasure.get(1), 35 + shedWidth - pillarMeasure.get(1), pillarMeasure.get(1), pillarMeasure.get(1)); //Højre nederste stolpe

        }

        int halfOfPillarAmountCeil = (int) Math.ceil(methodPillarAmount / 2.0);
        for (int i = 0; i < halfOfPillarAmountCeil; i++) {
            int lastLoop = halfOfPillarAmountCeil - 1;

            if (i == lastLoop) {
                pillarTransition = (pillarMeasure.get(0) / 2);
                if (hasShed) {
                    svg.addRect(carportLength - pillarTransition - CARPORT_END_DIST, 35 - (headRaftMeasure.get(0) / 2), pillarMeasure.get(1), pillarMeasure.get(0));
                    svg.addRect(carportLength - pillarTransition - CARPORT_END_DIST, carportWidthCM - 35 - (headRaftMeasure.get(0) / 2), pillarMeasure.get(1), pillarMeasure.get(0));
                } else {
                    svg.addRect(stolpeX - pillarTransition - CARPORT_END_DIST, 35 - (headRaftMeasure.get(0) / 2), pillarMeasure.get(1), pillarMeasure.get(0));
                    svg.addRect(stolpeX - pillarTransition - CARPORT_END_DIST, carportWidthCM - 35 - (headRaftMeasure.get(0) / 2), pillarMeasure.get(1), pillarMeasure.get(0));
                }
            }

            if ((i > 0) && (i < lastLoop)) {
                svg.addRect(stolpeX, 35 - (headRaftMeasure.get(0) / 2), pillarMeasure.get(1), pillarMeasure.get(0));
                svg.addRect(stolpeX, carportWidthCM - 35 - (headRaftMeasure.get(0) / 2), pillarMeasure.get(1), pillarMeasure.get(0));
            }

            if (i == 0) {
                svg.addRect(stolpeX, 35 - (headRaftMeasure.get(0) / 2), pillarMeasure.get(1), pillarMeasure.get(0));
                svg.addRect(stolpeX, carportWidthCM - 35 - (headRaftMeasure.get(0) / 2), pillarMeasure.get(1), pillarMeasure.get(0));
            }

            stolpeX += lengthBetweenPillars;
        }

        // Hulbånd:
        if (!hasPitch) {
            if (!hasShed) {
                svg.addBand(lengthBetweenRafts, 35, carportLength - (lengthBetweenRafts - headRaftMeasure.get(0)), carportWidthCM - 35);
                svg.addBand(lengthBetweenRafts, carportWidthCM - 35, carportLength - (lengthBetweenRafts - headRaftMeasure.get(0)), 35);
            } else {
                svg.addBand(lengthBetweenRafts, 35, carportLength - shedLength - 35, carportWidthCM - 35);
                svg.addBand(lengthBetweenRafts, carportWidthCM - 35, carportLength - shedLength - 35, 35);
            }
        }
        // Skur:
        svg.addRect(carportLength - shedLength - 30, 35, shedLength, plankMeasure.get(0));
        svg.addRect(carportLength - shedLength - 30, shedWidth + 35, shedLength, plankMeasure.get(0));
        svg.addRect(carportLength - shedLength - 30, 35, plankMeasure.get(0), shedWidth);
        svg.addRect(carportLength - 30, 35, plankMeasure.get(0), shedWidth);

        // Rejsning:
        if (hasPitch) {

            //Taglægter:
            double lengthBetweenLaths = (carportWidthCM / 2.0) / (amountOfRooflaths / 2.0);
            double b = 45.0;
            double c = carportWidthCM - 45;

            for (int i = 0; i < (amountOfRooflaths / 2) - 1; i++) {
                svg.addRect(0, b, carportLength, roofLathsMeasure.get(0));
                svg.addRect(0, c, carportLength, roofLathsMeasure.get(0));
                b += lengthBetweenLaths;
                c -= lengthBetweenLaths;
            }
            svg.addRect(0, carportWidthCM / 2, carportLength, roofLathsMeasure.get(0));
            svg.addRect(0, (carportWidthCM / 2) - 10, carportLength, roofLathsMeasure.get(0));
            svg.addRect(0, (carportWidthCM / 2) + 10, carportLength, roofLathsMeasure.get(0));

            // Vindskeder (på gavlen):
            int gavlPlaceing = 0;
            for(int i = 0; i < amountOfGavlPlank / 2; i++) {
                svg.addRect(gavlPlaceing, 0, plankMeasure.get(0), carportWidthCM/2); // Venstre/højre oppe
                svg.addRect(gavlPlaceing, carportWidthCM/2, plankMeasure.get(0), carportWidthCM/2); // Venstre/højre, nede
                gavlPlaceing = carportLength;
            }

        }

        return svgInnerDrawing.toString() + svg.toString() + "</svg> </svg>";
    }

    public String svgDrawingSide(int carportLength, int carportHeight, boolean hasShed) throws UniversalSampleException {
        double viewboxX = 0.0;
        double viewboxY = 0.0;
        double cHeightViewY;
        double cLengthViewX;
        if(hasPitch) {
            viewboxX = plankMeasure.get(1);
            viewboxY = Math.tan((carportPitch * Math.PI) / 180) * (carportWidthCM/2);
            cHeightViewY = carportHeight + viewboxY;
            cLengthViewX = carportLength + viewboxX;
        } else {
            cHeightViewY = carportHeight;
            cLengthViewX = carportLength;
        }
        final int MARKER_HEIGHT = 12;
        final int Y_DATA = 10;
        final int X_DATA = 75;
        final int PUSH_AWAY = 40;
        final double DRAW_END_VERTICAL = cHeightViewY - MARKER_HEIGHT + Y_DATA;
        final double DRAW_END_HORIZONTAL = cLengthViewX - MARKER_HEIGHT + X_DATA;
        final int PUSH_TEXT_DOWN = 70;
        ArrayList<Double> plankMeasure = MaterialFacade.getWidthHeightFromDimensionMeasureInCM(WATERPLANK_AND_SHEDPLANK_ID);

        double x;
        double y;
        double length;
        double height;

        String viewboxInner = "0,0," + (cLengthViewX + 100) + "," + (cHeightViewY + 100);

        Svg svgInnerDrawing = new Svg(cLengthViewX, cHeightViewY, viewboxInner, 75, 10);
        // Carport:
        svgInnerDrawing.addArrowLength(X_DATA + viewboxX, cHeightViewY + PUSH_AWAY, DRAW_END_HORIZONTAL, cHeightViewY + PUSH_AWAY);
        svgInnerDrawing.addArrowWidth(PUSH_AWAY, Y_DATA, PUSH_AWAY, DRAW_END_VERTICAL);
        svgInnerDrawing.addText((PUSH_AWAY / 2), ((Y_DATA + cHeightViewY) / 2), carportHeight, ((DRAW_END_HORIZONTAL / 2.0) + PUSH_AWAY), (cHeightViewY + PUSH_TEXT_DOWN), carportLength);

        Svg svg = new Svg(cLengthViewX, cHeightViewY,  -viewboxX + "," + -viewboxY +  "," + (cLengthViewX+20) + "," + (cHeightViewY), 75, 10);

        //Top bræt
        svg.addRect(0, 0, carportLength, plankMeasure.get(1));

        //Head (Remme)
        if (hasShed) {
            svg.addRect(0, plankMeasure.get(1), carportLength - shedLength - 30, headRaftMeasure.get(1));
            svg.addRect(carportLength - 30, plankMeasure.get(1), 30, headRaftMeasure.get(1));
        } else {
            svg.addRect(0, plankMeasure.get(1), carportLength, headRaftMeasure.get(1));
        }

        //Stolper
        int methodPillarAmount = pillarAmount;
        double lengthBetweenPillars = carportLength / ((methodPillarAmount / 2.0) - 1.0);
        double pillarTransition = 0.0;
        final int CARPORT_END_DIST = 30;
        final int REM_IN_DIST = 35 + 35;
        if (hasShed) {
            if (shedWidth == carportWidthCM - REM_IN_DIST) {
                methodPillarAmount -= 6;
            } else {
                methodPillarAmount -= 7;
            }
            lengthBetweenPillars = (carportLength - shedLength - CARPORT_END_DIST) / ((methodPillarAmount / 2.0) - 1.0);
        }

        int halfOfPillarAmountCeil = (int) Math.ceil(methodPillarAmount / 2.0);
        y = plankMeasure.get(1) + headRaftMeasure.get(1);
        length = pillarMeasure.get(0);
        height = carportHeight - plankMeasure.get(1) - headRaftMeasure.get(1);
        int stolpeX = 0;

        for (int i = 0; i < halfOfPillarAmountCeil; i++) {
            int lastLoop = halfOfPillarAmountCeil - 1;

            if (i == lastLoop) {
                pillarTransition = (pillarMeasure.get(0) / 2);
                if (hasShed) {
                    svg.addRect(carportLength - pillarTransition - CARPORT_END_DIST, y, length, height);
                } else {
                    svg.addRect(stolpeX - pillarTransition - CARPORT_END_DIST, y, length, height);
                }
            }

            if ((i > 0) && (i < lastLoop)) {
                svg.addRect(stolpeX, y, length, height);
            }

            if (i == 0) {
                svg.addRect(stolpeX, y, length, height);
            }

            stolpeX += lengthBetweenPillars;

        }

        if (hasShed) {
            x = carportLength - shedLength - 30 - plankMeasure.get(1);
            y = plankMeasure.get(1);
            length = plankMeasure.get(1);
            height = carportHeight - plankMeasure.get(1);
            for (int i = 0; i < (shedLength / plankMeasure.get(1)); i++) {
                x += plankMeasure.get(1);
                svg.addRect(x, y, length, height);
            }
        }

        //Rejsning
        if (hasPitch) {
            double roofHeight = Math.tan((carportPitch * Math.PI) / 180) * (carportWidthCM/2);
            x = 0 - plankMeasure.get(1)/2;
            y = 0 - roofHeight;
            double width = plankMeasure.get(1);
            length = roofHeight;
            //Venstre vandbræt
            svg.addRect(x,y, width, length);

            x += plankMeasure.get(1);
            y += plankMeasure.get(0);
            width = carportLengthCM - plankMeasure.get(1)/2;
            length = roofLathsMeasure.get(1);

            //Taglægte
            svg.addRect(x,y,width,length);

            x += carportLength - plankMeasure.get(1);
            y -= plankMeasure.get(0);
            width = plankMeasure.get(1);
            length = roofHeight;
            //Højre vandbræt
            svg.addRect(x,y,width,length);

            //Tagsten
            x = 0 + plankMeasure.get(1) / 2;
            y = 0 - roofHeight + plankMeasure.get(1);
            width = 0 + plankMeasure.get(1);
            length = 0 + planksForGavlMountLength - plankMeasure.get(1);
            double loop = (carportLength / width) - 1;

            for(int i = 0; i < loop; i++) {
                svg.addRect(x,y,width,length);
                x += width;
            }
        }

        return svgInnerDrawing.toString() + svg.toString() + "</svg> </svg>";
    }
}