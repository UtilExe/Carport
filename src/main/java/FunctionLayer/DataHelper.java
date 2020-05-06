package FunctionLayer;

import DBAccess.MaterialMapper;
import PresentationLayer.MaterialCalculator;

import java.util.ArrayList;

public class DataHelper {
    private MaterialCalculator calcTest = new MaterialCalculator();

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


    private int shedLength = 0;
    private int shedWidth = 0;
    private boolean hasShed = false;
    private boolean hasPitch = false;
    private int price = 0;
    private int carportPitch = 0;
    private boolean invalidInput = false;

    /*
        String tmpCarportLength = request.getParameter("length");
        String tmpCarportWidth = request.getParameter("width");
        String tmpCarportHeight = request.getParameter("height");

        int carportLengthCM = Validation.getInteger(tmpCarportLength);
        int carportWidthCM = Validation.getInteger(tmpCarportWidth);
        int carportHeight = Validation.getInteger(tmpCarportHeight);

        TODO: Jeg har ikke kunne hente request.getParameter fra CarportDesign så jeg har bare nedenunder sat det til at være 0.
        -- Det ser ud til at virke, men er lidt usikker om validering så mister dens effekt.
        -- Eller om det så blot henter fra CarportDesign senere og derfor er i orden at gøre som nedenfor (carportLengthCM = 0;)
     */

    private int carportLengthCM = 0;
    private int carportWidthCM = 0;
    private int carportHeight = 0;
    private int shedHeight = (int) (carportHeight - MaterialMapper.getWidthHeightFromDimensionMeasureInCM(6).get(1));

    private int band = calcTest.calcBandAmount(carportLengthCM, carportWidthCM, hasShed, shedLength);
    private int rolesOfBand = calcTest.getRolesAmountBand(band);
    private int amountOfRafts = calcTest.calcRaftAmount(carportLengthCM, hasPitch);
    private int pillarAmount = calcTest.calcPillarAmount(carportLengthCM, hasShed, shedLength);
    private int amountOfTiles = calcTest.getRoofTileAmount(carportLengthCM, carportWidthCM);
    private int amountOfScrews = calcTest.getRoofScrewAmount(carportLengthCM, carportWidthCM, BOTTOMSCREW_ID);
    private int amountOfUniversalScrews = calcTest.getUniversalScrews(carportLengthCM, hasPitch);
    private int amountOfPlankWaterScrews = calcTest.getPlankAndWaterScrews();
    private int amountOfBracketScrews = calcTest.getBracketScrews(carportLengthCM, hasPitch);
    private int amountOfCarriageBolts = calcTest.getCarriageBolts(carportLengthCM, hasShed, shedLength);
    private int[] transomSides = calcTest.getTransomsLengthSides(shedLength);
    private int[] transomFrontAndBack = calcTest.getTransomsLengthFrontAndBack(shedWidth);
    private int[] headsInShed = calcTest.getHeadsInShed(shedLength);
    private int planksForShedAmount = calcTest.getPlanksForShed(shedLength, shedWidth);
    private int packageOfOuterScrews = calcTest.getOuterScrewsShed(shedLength, shedWidth, OUTERSCREW_ID);
    private int packageOfInnerScrews = calcTest.getInnerScrewsShed(shedLength, shedWidth, INNERSCREW_ID);
    private int amountOfAngleMount = calcTest.getAngleMount(shedLength, shedWidth);
    private int tilesForPitchedRoof = calcTest.getTilesForPitchedRoof();
    private int packagesOfTileBindersAndHooks = calcTest.getPackagesOfTileBindersAndHooks();
    private int amountOfRooftileStones = calcTest.getAmountOfRoofTileStones(carportLengthCM);
    private int amountOfRooftileStoneBrackets = calcTest.getAmountOfRoofTileStoneBrackets(amountOfRooftileStones);
    private int amountOfToplathHolders = calcTest.getAmountOfToplathHolders(amountOfRafts);
    private int gavlPlankLength = calcTest.getGavlPlanksLength(carportWidthCM, carportPitch);
    private int amountOfGavlPlank = calcTest.getAmountOfGavlPlanks();
    private int amountOfPlanksForGavlMount = calcTest.getAmountOfPlanksForGavlMount(carportWidthCM);
    private int planksForGavlMountLength = calcTest.getPlanksForGavlMountLength(carportWidthCM, carportPitch);
    private int amountOfRooflaths = calcTest.getAmountOfRooflaths(carportWidthCM);
    private int amountOfRooflathScrews = calcTest.getAmountOfToplathScrews(amountOfRooflaths, amountOfRafts, ROOFLATH_SCREWS_ID);

    private ArrayList<Double> pillarLengths = calcTest.getPillarHeight(carportHeight, carportLengthCM, isHasShed(), shedLength, isHasShed());
    private ArrayList<String> heads = MaterialMapper.getRoofData(RAFT_AND_HEAD_ID, carportLengthCM, AMOUNT_OF_HEADS);
    private ArrayList<String> rafts = MaterialMapper.getRoofData(RAFT_AND_HEAD_ID, carportWidthCM, amountOfRafts);
    private ArrayList<String> bands = MaterialMapper.getBandData(BAND_ID, band, rolesOfBand);
    private ArrayList<String> pillars = MaterialMapper.getPillarData(PILLAR_ID, pillarAmount, pillarLengths);
    private ArrayList<String> frontbackunderplanks = MaterialMapper.getRoofData(PLANK_ID, carportWidthCM, AMOUNT_OF_FRONT_BACK_UNDERPLANKS);
    private ArrayList<String> sideunderplanks = MaterialMapper.getRoofData(PLANK_ID, carportLengthCM, AMOUNT_OF_SIDE_UNDERPLANKS);
    private ArrayList<String> frontoverplanks = MaterialMapper.getRoofData(PLANK_ID, carportWidthCM, AMOUNT_OF_FRONT_OVERPLANKS);
    private ArrayList<String> sideoverplanks = MaterialMapper.getRoofData(PLANK_ID, carportLengthCM, AMOUNT_OF_SIDE_OVERPLANKS);
    private ArrayList<String> sidewaterplanks = MaterialMapper.getRoofData(WATERPLANK_AND_SHEDPLANK_ID, carportLengthCM, AMOUNT_OF_SIDE_WATERPLANKS);
    private ArrayList<String> frontwaterplanks = MaterialMapper.getRoofData(WATERPLANK_AND_SHEDPLANK_ID, carportWidthCM, AMOUNT_OF_FRONT_WATERPLANKS);
    private ArrayList<String> tiles = MaterialMapper.getScrewsAndTilesData(TILE_ID, amountOfTiles);
    private ArrayList<String> roofBottomScrew = MaterialMapper.getScrewsAndTilesData(BOTTOMSCREW_ID, amountOfScrews);
    private ArrayList<String> universalScrews = MaterialMapper.getScrewsAndTilesData(UNIVERSALSCREW_ID, amountOfUniversalScrews);
    private ArrayList<String> plankWaterScrews = MaterialMapper.getScrewsAndTilesData(PLANK_WATERSCREW_ID, amountOfPlankWaterScrews);
    private ArrayList<String> bracketScrews = MaterialMapper.getScrewsAndTilesData(BRACKETSCEW_ID, amountOfBracketScrews);
    private ArrayList<String> carriageBolts = MaterialMapper.getScrewsAndTilesData(CARRIAGEBOLT_ID, amountOfCarriageBolts);
    private ArrayList<String> squareWashers = MaterialMapper.getScrewsAndTilesData(SQUAREWASHER_ID, amountOfCarriageBolts);
    private ArrayList<String> battern = MaterialMapper.getRoofData(BATTERN_ROOFLATH_ID, BATTERNLENGTH_DOOR, 1);
    private ArrayList<String> transomSidesInfo = MaterialMapper.getTransomAndHeadInShedData(TRANSOM_ID, transomSides);
    private ArrayList<String> transomFrontAndBackInfo = MaterialMapper.getTransomAndHeadInShedData(TRANSOM_ID, transomFrontAndBack);
    private ArrayList<String> headsInShedInfo = MaterialMapper.getTransomAndHeadInShedData(RAFT_AND_HEAD_ID, headsInShed);
    private ArrayList<String> planksInShedInfo = MaterialMapper.getPlankData(WATERPLANK_AND_SHEDPLANK_ID, shedHeight, planksForShedAmount);
    private ArrayList<String> outerScrewsInfo = MaterialMapper.getScrewsAndTilesData(OUTERSCREW_ID, packageOfOuterScrews);
    private ArrayList<String> innerScrewsInfo = MaterialMapper.getScrewsAndTilesData(INNERSCREW_ID, packageOfInnerScrews);
    private ArrayList<String> doorGribInfo = MaterialMapper.getScrewsAndTilesData(DOORGRIB_ID, DOOR_GRIB);
    private ArrayList<String> tHingeInfo = MaterialMapper.getScrewsAndTilesData(THINGE_ID, T_HINGE);
    private ArrayList<String> angleMountInfo = MaterialMapper.getScrewsAndTilesData(ANGLEMOUNT_ID, amountOfAngleMount);
    private ArrayList<String> tilesPitchedRoof = MaterialMapper.getScrewsAndTilesData(TILES_PITCHED_ID, tilesForPitchedRoof);
    private ArrayList<String> tileBindersHooks = MaterialMapper.getScrewsAndTilesData(TILES_BINDERS_HOOKS_ID, packagesOfTileBindersAndHooks);
    private ArrayList<String> rooftileStones = MaterialMapper.getScrewsAndTilesData(ROOFTILE_STONES_ID, amountOfRooftileStones);
    private ArrayList<String> rooftileStoneBracketsInfo = MaterialMapper.getScrewsAndTilesData(ROOFTILE_STONE_BRACKETS_ID, amountOfRooftileStoneBrackets);
    private ArrayList<String> toplathHoldersInfo = MaterialMapper.getScrewsAndTilesData(TOPLATH_HOLDER_ID, amountOfToplathHolders);
    private ArrayList<String> gavlPlankInfo = MaterialMapper.getRoofData(PLANK_ID, gavlPlankLength, amountOfGavlPlank);
    private ArrayList<String> gavlPlankMountInfo = MaterialMapper.getPlankData(WATERPLANK_AND_SHEDPLANK_ID, planksForGavlMountLength, amountOfPlanksForGavlMount);
    private ArrayList<String> rooflathsInfo = MaterialMapper.getRoofData(BATTERN_ROOFLATH_ID, carportLengthCM, amountOfRooflaths);
    private ArrayList<String> rooflathScrewsInfo = MaterialMapper.getScrewsAndTilesData(ROOFLATH_SCREWS_ID, amountOfRooflathScrews);
    private ArrayList<String> allPriceIndexes = new ArrayList<>();

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

    public void setCarportLengthCM(int carportLengthCM) {
        this.carportLengthCM = carportLengthCM;
    }

    public void setCarportWidthCM(int carportWidthCM) {
        this.carportWidthCM = carportWidthCM;
    }

    public void setCarportHeight(int carportHeight) {
        this.carportHeight = carportHeight;
    }

    public void setShedLength(int shedLength) {
        this.shedLength = shedLength;
    }

    public void setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
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

    public void setCarportPitch(int carportPitch) {
        this.carportPitch = carportPitch;
    }

    public void setAllPriceIndexes(ArrayList<String> allPriceIndexes) {
        this.allPriceIndexes = allPriceIndexes;
    }

    public ArrayList<String> getAllPriceIndexes() {
        return allPriceIndexes;
    }

    public void initArrayList() {
        getAllPriceIndexes().add(heads.get(heads.size() - 1));
        getAllPriceIndexes().add(rafts.get(rafts.size() - 1));
        getAllPriceIndexes().add(bands.get(bands.size() - 1));
        getAllPriceIndexes().add(pillars.get(pillars.size() - 1));
        getAllPriceIndexes().add(frontbackunderplanks.get(frontbackunderplanks.size() - 1));
        getAllPriceIndexes().add(sideunderplanks.get(sideunderplanks.size() - 1));
        getAllPriceIndexes().add(frontoverplanks.get(frontoverplanks.size() - 1));
        getAllPriceIndexes().add(sideoverplanks.get(sideoverplanks.size() - 1));
        getAllPriceIndexes().add(roofBottomScrew.get(roofBottomScrew.size() - 1));
        getAllPriceIndexes().add(sidewaterplanks.get(sidewaterplanks.size() - 1));
        getAllPriceIndexes().add(frontwaterplanks.get(frontwaterplanks.size() - 1));
        getAllPriceIndexes().add(universalScrews.get(universalScrews.size() - 1));
        getAllPriceIndexes().add(plankWaterScrews.get(plankWaterScrews.size() - 1));
        getAllPriceIndexes().add(bracketScrews.get(bracketScrews.size() - 1));
        getAllPriceIndexes().add(carriageBolts.get(carriageBolts.size() - 1));
        getAllPriceIndexes().add(squareWashers.get(squareWashers.size() - 1));

        if (isHasPitch()) {
            getAllPriceIndexes().add(tilesPitchedRoof.get(tilesPitchedRoof.size() - 1));
            getAllPriceIndexes().add(tileBindersHooks.get(tileBindersHooks.size() - 1));
            getAllPriceIndexes().add(rooftileStones.get(rooftileStones.size() - 1));
            getAllPriceIndexes().add(rooftileStoneBracketsInfo.get(rooftileStoneBracketsInfo.size() - 1));
            getAllPriceIndexes().add(toplathHoldersInfo.get(toplathHoldersInfo.size() - 1));
            getAllPriceIndexes().add(gavlPlankInfo.get(gavlPlankInfo.size() - 1));
            getAllPriceIndexes().add(gavlPlankMountInfo.get(gavlPlankMountInfo.size() - 1));
            getAllPriceIndexes().add(rooflathsInfo.get(rooflathsInfo.size() - 1));
            getAllPriceIndexes().add(rooflathScrewsInfo.get(rooflathScrewsInfo.size() - 1));
        } else {
            getAllPriceIndexes().add(tiles.get(tiles.size() - 1));
        }

        if (isHasShed()) {
            getAllPriceIndexes().add(battern.get(battern.size() - 1));
            getAllPriceIndexes().add(transomSidesInfo.get(transomSidesInfo.size() - 1));
            getAllPriceIndexes().add(transomFrontAndBackInfo.get(transomFrontAndBackInfo.size() - 1));
            getAllPriceIndexes().add(headsInShedInfo.get(headsInShedInfo.size() - 1));
            getAllPriceIndexes().add(planksInShedInfo.get(planksInShedInfo.size() - 1));
            getAllPriceIndexes().add(outerScrewsInfo.get(outerScrewsInfo.size() - 1));
            getAllPriceIndexes().add(innerScrewsInfo.get(innerScrewsInfo.size() - 1));
            getAllPriceIndexes().add(doorGribInfo.get(doorGribInfo.size() - 1));
            getAllPriceIndexes().add(tHingeInfo.get(tHingeInfo.size() - 1));
            getAllPriceIndexes().add(angleMountInfo.get(angleMountInfo.size() - 1));
        }
    }

    public void test() {
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
    }
}
