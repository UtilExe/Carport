package FunctionLayer;

import DBAccess.MaterialMapper;

import java.util.ArrayList;

public class MaterialCalculator {

    public int calcPillarAmount(int carportLength, boolean hasShed, int shedLength, int shedWidth, int carportWidth) {
        int result;
        final double PILLAR_AT_METER = 3.0;
        final int REM_IN_DIST = 35 + 35;
        double tmpResult;
        if (hasShed) {
            double tmpLength = (carportLength - shedLength) / 100.0;
            tmpResult = (tmpLength / PILLAR_AT_METER);
            tmpResult = Math.ceil(tmpResult);
            result = (int) (tmpResult * 2);
            if (result < 4) {
                result = 4;
            }
            if(shedWidth == carportWidth - REM_IN_DIST) {
                result += 6; // Samme antagelse som nedenfor, men nu skal stolpen nederst til højre på skuret ikke på, da den stolpe støtter på remmen i forvejen.
            } else {
                result += 7; //Vi antager at et skur skal bruge 7 stolper (en i hvert hjørne (på nær hjørnet, hvor bagerste stolpe står) + 2 i midten) og 2 til hver side af døren.
            }
        } else {
            double tmpCarportLength = carportLength / 100.0;
            // Beregning af antal stolper (skal være et i hver side pr. 300 cm af carportens længde).
            tmpResult = (tmpCarportLength / PILLAR_AT_METER);
            tmpResult = Math.ceil(tmpResult);
            result = ((int) tmpResult) * 2;
            if (result < 4) {
                result = 4;
            }
        }
        // Vi antager, at der altid skal være et stolpe-par bagerst på carporten uanset om, der er skur eller ej.
        result += 2;

        return result;
    }

    //Vi antager at mængde af spær ikke ændre sig med skur eller ej
    public int calcRaftAmount(int carportLength, boolean hasPitch) {
        int result;
        double tmpResult;
        double tmpCarportLength = carportLength / 100.0;
        double raftAtMeter;
        if(hasPitch) {
            // Beregning af antal spær; skal være ét pr. 89 cm.
            raftAtMeter = 0.89;
        } else {
            // Beregning af antal spær; skal være ét pr. 55 cm.
            raftAtMeter = 0.55;
        }
        tmpResult = (tmpCarportLength / raftAtMeter);
        tmpResult = Math.ceil(tmpResult);
        result = ((int) tmpResult);
        return result;
    }

    public int calcBandAmount(int carportLength, int carportWidth, boolean hasShed, int shedLength) {
        double lengthOfSquare = 0.0;
        double widthOfSquare;
        double calcDiagonal;

        if (!hasShed) {
            // Beregner mål på firkanten hvor hulbåndet skal sidde (altid et spær inde i begge sider)
            lengthOfSquare = carportLength - 110;
        } else {
            //Hvis carporten har et skud, går hulbåndet fra et spær inde til første spær skuret rammer.
            lengthOfSquare = carportLength - (shedLength + 55);
        }
        // Beregner mål på firkanten hvor hulbåndet skal sidde (altid et spær inde i begge sider)
        widthOfSquare = carportWidth - 70;
        // Derefter beregner vi længden af hulbåndet vha. pytagoros.
        calcDiagonal = Math.sqrt((lengthOfSquare * lengthOfSquare) + (widthOfSquare * widthOfSquare));

        // Da vi nu har længden på et hulbånd ganger vi med 2 for at få totalen
        int diagonalToInt = (int) (calcDiagonal * 2.0);
        int result = diagonalToInt;

        return result;
    }

    public int getRolesAmountBand(int bandLength) {
        int result;
        final int BAND_ID = 10;
        final int BAND_PR_ROLES = MaterialMapper.getAmountPrUnit(BAND_ID);
        double bandLengthToM = bandLength / 100.0;
        result = (int) Math.ceil(bandLengthToM / BAND_PR_ROLES);
        return result;
    }

    // ANTAGELSE: Uanset carport-mål skal afstanden fra det yderste
    // af carport til rem være 35 cm på hver side (30 cm bagtil).


    public ArrayList<Double> getPillarHeight(int carportHeight, double carportLength, boolean hasShed, int shedLength, boolean hasPitch, int shedWidth, int carportWidth) {
        ArrayList<Double> stolper = new ArrayList<Double>();
        final int HEAD_ID = 6;
        final int FACIA_ID = 1;
        final int GROUND_DEPTH = 90;
        final int DIST_BEHIND_CARPORT = 30;
        int pillars = 0;
        double firstPillar;
        double headHeight = MaterialMapper.getWidthHeightFromDimensionMeasureInCM(HEAD_ID).get(1);
        double fasciaBoardHeight = MaterialMapper.getWidthHeightFromDimensionMeasureInCM(FACIA_ID).get(1);
        double pillarHeight = carportHeight - headHeight - fasciaBoardHeight;

        if (hasShed) {
            pillars = calcPillarAmount((int) carportLength, hasShed, shedLength, shedWidth, carportWidth) - 8;
            pillars = pillars / 2;
        } else {
            pillars = (calcPillarAmount((int) carportLength, hasShed, shedLength, shedWidth, carportWidth)) / 2;
        }

        if (hasPitch) {
            // Da rejsningstag ingen hældning har, er alle stolper samme længde som den første stolpe.
            firstPillar = pillarHeight;
            for (int i = 0; i < pillars; i++) {
                stolper.add(pillarHeight);
            }
        } else {
            firstPillar = pillarHeight + Math.tan((2 * Math.PI) / 180) * DIST_BEHIND_CARPORT;
            firstPillar = roundToTwo(firstPillar);
            firstPillar += GROUND_DEPTH;
            stolper.add(firstPillar);


            double tmpStolpeHeight = pillarHeight;
            // Looper igennem carportens stolper (med varierende højde grundet hældning).
            for (int i = 1; i < pillars; i++) {
                tmpStolpeHeight = tmpStolpeHeight + Math.tan((2 * Math.PI) / 180) * 300;
                double roundedNum = roundToTwo(tmpStolpeHeight);
                roundedNum += GROUND_DEPTH;
                stolper.add(roundedNum);
            }
        }
        // Looper igennem skurets 8 stolper (med skurets højde).
        final int SHED_PILLARS = 8;
        for (int i = 1; i < SHED_PILLARS; i++) {
            stolper.add(firstPillar);
        }
        return stolper;
    }

    public double roundToTwo(double number) {
        String rounder = String.format("%1.2f", number);
        rounder = rounder.replace(',', '.');
        number = Double.valueOf(rounder);
        return number;
    }

    public int getRoofTileAmount(int carportLength, int carportWidth) {
        final int ROOF_TILE_ID = 8;
        double roofTileLength = MaterialMapper.getWidthHeightFromDimensionMeasureInCM(ROOF_TILE_ID).get(1);
        double roofTileWidth = MaterialMapper.getWidthHeightFromDimensionMeasureInCM(ROOF_TILE_ID).get(0);
        final int TILE_OVERLAP = 20;
        roofTileWidth -= TILE_OVERLAP;
        double roofTileArea = roofTileWidth * roofTileLength;
        int roofArea = carportLength * carportWidth;
        int roofTileAmount = (int) Math.ceil(roofArea / roofTileArea);

        return roofTileAmount;
    }

    public int getRoofScrewAmount(int carportLength, int carportWidth, int ID) {
        int roofArea = carportLength * carportWidth;
        final int SCREW_PR_M2 = 12;
        final int CM_TO_M2 = 10000;
        final double AMOUNT_OF_SCREWS_PR_PACKAGE = MaterialMapper.getAmountPrUnit(ID);

        double tmpResult = ((roofArea / CM_TO_M2) * SCREW_PR_M2) / AMOUNT_OF_SCREWS_PR_PACKAGE;
        int result = (int) Math.ceil(tmpResult);

        return result;
    }

    public int getUniversalScrews(int carportLength, boolean hasPitch) {
        //Vi antager at der skrues en skrue i hver side af hvert spær
        return calcRaftAmount(carportLength, hasPitch) * 2;
    }

    public int getPlankAndWaterScrews() {
        /*
        OBS!! Der vil altid kun være 3 sternbrææder
        Vi antager at antallet af skruer aldrig bliver mere end 200stk(1 pakke) da der vil være 3 sternbrædder
        med en skrue i hver side.
        På vandbrædderne skal der bruges samme antal skruer. Dermed overstiger vi stadig ikke de 200stk.
        */
        return 1;
    }

    public int getBracketScrews(int carportLength, boolean hasPitch) {
        /*
        Vi antager at der skrues 2 beslag på hver side af et spær i begge ender. Derved ganger vi spær med 4 og derefter
        med 6 da der går 6 skruer til 1 beslag.

        Vi antager at beskrivelse om montering af hulbånd allerede er dækket af vores beregning.
        */
        int amountOfRafts = calcRaftAmount(carportLength, hasPitch);
        int amountOfScrews = amountOfRafts * 4 * 6;
        double amountOfPacks = Math.ceil(amountOfScrews / 250.0);
        return (int) amountOfPacks;
    }

    public int getCarriageBolts(int carportLength, boolean hasShed, int shedLength, int shedWidth, int carportWidth) {
        return calcPillarAmount(carportLength, hasShed, shedLength, shedWidth, carportWidth) * 2;
    }

    public int[] getTransomsLengthFrontAndBack(int shedWidth) {
        /* Vi antager, at der er 3 løsholte fra bunden til toppen på skurets for- og bagside samt 2 løsholte
         fra bunden til toppen på skurets sider. */

        // HashMappet består af int = antal løsholter samt double = længde på de løsholter:
        int[] result = new int[4];
        final int TRANSOMS_ON_SIDES = 3;
        // Vi antager at dørens bredde er 80 cm, da det virker som en dør-standard bredde:
        final int DOOR_WIDTH = 80;
        int transomLength = shedWidth / 2;
        int transomLengthWithDoor = (shedWidth / 2) - DOOR_WIDTH;
        // Her ganges hver side, der ikke har en dør, med antal løsholter på en side:
        int regularTransomAmount = TRANSOMS_ON_SIDES * 3;
        int transomAmountWithDoor = TRANSOMS_ON_SIDES * 1;

        result[0] = regularTransomAmount;
        result[1] = transomLength;
        result[2] = transomAmountWithDoor;
        result[3] = transomLengthWithDoor;

        return result;
    }

    public int[] getTransomsLengthSides(int shedLength) {
        /* Vi antager, at der er 3 løsholte fra bunden til toppen på skurets for- og bagside samt 2 løsholte
         fra bunden til toppen på skurets sider. */

        // HashMappet består af int = antal løsholter samt double = længde på de løsholter:
        int[] result = new int[2];
        final int TRANSOMS_ON_SIDES = 2;
        int transomLength = shedLength;
        // Her ganges hver side, der ikke har en dør, med antal løsholter på en side:
        int transomAmount = TRANSOMS_ON_SIDES * 2;

        result[0] = transomAmount;
        result[1] = transomLength;

        return result;
    }

    public int[] getHeadsInShed(int shedLength) {
        int headsLength = shedLength;
        // Der er 1 rem i hver side (derfor, 2 i alt):
        final int HEADS_AMOUNT = 2;

        int[] result = new int[2];
        result[0] = HEADS_AMOUNT;
        result[1] = headsLength;

        return result;
    }

    public int getPlanksForShed(int shedLength, int shedWidth) {
        int result;
        // ID til brædtet, der skal bruges:
        final int PLANK_ID = 3;
        // Vi antager, at der er et overlap på beklædningen af 2,5 cm i hver side af brædtet.
        final double OVERLAP = 2.5;
        ArrayList<Double> dimensionsOfPlank = MaterialMapper.getWidthHeightFromDimensionMeasureInCM(PLANK_ID);
        double tmpPlankWidth = dimensionsOfPlank.get(1);
        double plankWidthAfterOverlap = tmpPlankWidth - OVERLAP;
        int plankWidth = (int) Math.ceil(plankWidthAfterOverlap);
        double sides = (shedLength / plankWidthAfterOverlap) * 2;
        double frontBack = (shedWidth / plankWidthAfterOverlap) * 2;
        double tmpResult = sides + frontBack;
        result = (int) Math.ceil(tmpResult);

        return result;
    }

    public int getOuterScrewsShed(int shedLength, int shedWidth, int ID) {
        int result;
        int planksForShed = getPlanksForShed(shedLength, shedWidth);
        // Vi antager, at der skal bruges 2 skruer pr. ydre bræt.
        final int AMOUNT_OF_OUTERSCREW_PR_PLANK = 2;
        double amountOfScrewPrPackage = MaterialMapper.getAmountPrUnit(ID);
        double tmpResult = (planksForShed * AMOUNT_OF_OUTERSCREW_PR_PLANK) / amountOfScrewPrPackage;
        result = (int) Math.ceil(tmpResult);
        return result;

    }

    public int getInnerScrewsShed(int shedLength, int shedWidth, int ID) {
        int result;
        // Vi antager, at der bruges 4 skruer pr. løsholte.
        final int AMOUNT_OF_SCREWS_PR_TRANSOM = 4;
        final int AMOUNT_OF_SCREWS_ON_DOOR = 50;
        double amountOfScewPrPackage = MaterialMapper.getAmountPrUnit(ID);
        int transomAmountFrontBack = getTransomsLengthFrontAndBack(shedWidth)[0] + getTransomsLengthFrontAndBack(shedWidth)[2];
        int transomAmountSides = getTransomsLengthSides(shedLength)[0];
        double tmpResult = ((transomAmountFrontBack * AMOUNT_OF_SCREWS_PR_TRANSOM) + (transomAmountSides * AMOUNT_OF_SCREWS_ON_DOOR) + AMOUNT_OF_SCREWS_ON_DOOR) / amountOfScewPrPackage;
        result = (int) Math.ceil(tmpResult);


        return result;
    }

    public int getAngleMount(int shedLength, int shedWidth) {
        int result;
        // Vi antager, at der bruges 2 vinkelbeslag pr. løsholt(én i hver ende)
        final int ANGLE_MOUNT_PR_TRANSOM = 2;
        int transomAmountFrontBack = getTransomsLengthFrontAndBack(shedWidth)[0] + getTransomsLengthFrontAndBack(shedWidth)[2];
        int transomAmountSides = getTransomsLengthSides(shedLength)[0];
        result = (transomAmountFrontBack + transomAmountSides) * 2;
        return result;
    }

    public int getTilesForPitchedRoof() {
        // 6 rækker af 24 sten på hver side af taget:
        int totalTiles = (6*24) * 2;
        //return (int) (Math.ceil(totalTiles/300));
        return totalTiles;
    }

    public int getAmountOfRooflaths(int carportWidth) {
        // Vi antager, at der er 35 cm mellem hver toplægte.
        final double SPACE_BETWEEN = 35.0;
        int result;
        double  tmpResult = Math.ceil((carportWidth - 90.0) / SPACE_BETWEEN);
        result = (int)tmpResult;
        return result;
    }

    public int getAmountOfToplathScrews(int amountOfRooflaths, int raftAmount, int ID) {
        // Vi antager, at der skal én skrue til én taglægte pr. spær:
        final int AMOUNT_OF_SCREWS_PR_ROOFLATH = raftAmount;

        double screwsPrUnit = MaterialMapper.getAmountPrUnit(ID);
        int result = (amountOfRooflaths * AMOUNT_OF_SCREWS_PR_ROOFLATH);
        result = (int) (Math.ceil(result / screwsPrUnit));

        return result;
    }

    public int getPackagesOfTileBindersAndHooks() {
        // Vi antager, at der uanset tagstørrelse skal bruges 2 pakker tagstens bindere og nakkekroge
        final int AMOUNT_OF_TILE_BINDERS_HOOKS = 2;

        return AMOUNT_OF_TILE_BINDERS_HOOKS;
    }

    public int getAmountOfRoofTileStones(int carportLength) {
        // Vi antager, at en rygsten har en længde på 50 cm.
        final int ROOFTILE_STONE_LENGTH_CM = 50;
        final int OVERLAP_CM = 10;

        int result = carportLength / (ROOFTILE_STONE_LENGTH_CM - OVERLAP_CM);

        return result;
    }

    public int getAmountOfRoofTileStoneBrackets(int amountOfRoofTileStones) {
        // Der skal 1 rygstensbeslag til 1 rygsten.
        int result = amountOfRoofTileStones;

        return result;
    }

    public int getAmountOfToplathHolders(int amountOfRafts) {
        // Der er 1 toplægteholder på hvert spær.
        int result = amountOfRafts;

        return result;
    }

    public int getGavlPlanksLength(int carportWidth, int carportPitch) {
        double halfWidth = carportWidth / 2;
        double middleHeight = Math.tan((carportPitch * Math.PI) / 180) * halfWidth;
        double tmpResult = Math.sqrt((halfWidth * halfWidth) + (middleHeight * middleHeight));
        int result = (int) (Math.ceil(tmpResult));

        return result;
    }

    public int getAmountOfGavlPlanks() {
        // Vi antager, at der altid er 4 gavl-bræt (2 foran og 2 bagved):
        final int AMOUNT_OF_GAVL_PLANKS = 4;

        return AMOUNT_OF_GAVL_PLANKS;
    }

    public int getAmountOfPlanksForGavlMount(int carportWidth) {
        int result;
        // ID til brættet, der skal bruges:
        final int PLANK_ID = 3;
        // Vi antager, at der er et overlap på beklædningen af 2,5 cm i hver side af brædtet.
        final double OVERLAP = 2.5;
        ArrayList<Double> dimensionsOfPlank = MaterialMapper.getWidthHeightFromDimensionMeasureInCM(PLANK_ID);
        double tmpPlankWidth = dimensionsOfPlank.get(1);
        double plankWidthAfterOverlap = tmpPlankWidth - OVERLAP;
        int plankWidth = (int) Math.ceil(plankWidthAfterOverlap);
        double amountOfPlanks = (carportWidth / plankWidthAfterOverlap) * 2;
        result = (int) Math.ceil(amountOfPlanks);

        return result;
    }

    public int getPlanksForGavlMountLength(int carportWidth, int carportPitch) {
        double halfWidth = carportWidth / 2;
        double middleHeight = Math.tan((carportPitch * Math.PI) / 180) * halfWidth;
        int result = (int) (Math.ceil(middleHeight));

        return result;
    }

    public int calcPricePrUnit(int unit, double pricePrUnit) {
        int result = (int) (Math.ceil(unit * pricePrUnit));
        return result;
    }

    public int calcPricePrUnitWithLength(int unit, double pricePrUnit, int amount) {
        int result = (int) (Math.ceil((unit * pricePrUnit) * amount));
        return result;
    }

    public int fullPrice(ArrayList<String> materialInfo) {
        // Vi antager, at Fog tager 5000 kr. for carport-ydelsen ekls. materiale priser.
        final int PRICE_FOR_SERVICE = 5000;
        int prices;
        int result = 0;
        String priceIndex = "";
        for (String s : materialInfo) {
            String[] tmpPriceIndex = new String[2];
            tmpPriceIndex = s.split(" ");
            priceIndex = tmpPriceIndex[0];
            prices = Integer.parseInt(priceIndex);
            result += prices;
        }

        result += PRICE_FOR_SERVICE;

        return result;
    }

    public ArrayList<Integer> getWoodForMeasure(int materialMeasure, ArrayList<Integer> lengths, int amountOnCarport) {
        ArrayList<Integer> result = new ArrayList<>();
        int tmpResult = 0;
        int amountOfWood = 0;

        if(lengths.get(lengths.size()-1) < materialMeasure) {
            materialMeasure /= 2;
            for (int i = 0; i < lengths.size(); i++) {
                if(lengths.get(i) >= materialMeasure) {
                    tmpResult = lengths.get(i);
                    amountOfWood = 2;
                    break;
                }
            }
        } else {
            for (int i = 0; i < lengths.size(); i++) {
                if(lengths.get(i) >= materialMeasure) {
                    tmpResult = lengths.get(i);
                    amountOfWood = 1;
                    break;
                }
                if(lengths.get(i)/2 >= materialMeasure) {
                    tmpResult = lengths.get(i);
                    amountOfWood = 2;
                    break;
                }
            }
        }
        // Vi ganger antal brædder på carporten med antal brædder til én længde.
        amountOfWood *= amountOnCarport;

        result.add(amountOfWood);
        result.add(tmpResult);

        return result;
    }



 }
