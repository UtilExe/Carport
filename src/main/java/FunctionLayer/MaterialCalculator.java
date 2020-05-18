package FunctionLayer;

import java.util.ArrayList;

/**
 * @author Daniel, Emil, Jannich, Jimmy
 * MaterialCalculator klassen står for at beregne Materiallisten ud fra forskellige Carport-mål.
 */

public class MaterialCalculator {

    /**
     * calcPillarAmount har til formålet, at udregne antallet af stolper på carporten ud fra kundens valgte mål.
     * @param carportLength længden på carporten, som kunden har valgt.
     * @param hasShed om kunden har valgt skur eller ej (true = har valgt, false = har ikke valgt).
     * @param shedLength længden på skuret, som kunden har valgt.
     * @param shedWidth bredden på skuret, som kunden har valgt.
     * @param carportWidth bredden på carporten, som kunden har valgt.
     * @return en int med antal stolper.
     */
    // calcStolpeAntal
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


    /**
     * calcRaftAmount har til formål at beregne antallet af spær på carporten ud fra kundens valgte mål.
     * @param carportLength er carportens længde, som kunden har valgt.
     * @param hasPitch om kunden har valgt rejsningstag eller fladt tag (true = rejsningstag, false = fladt tag).
     * @return en int med antal spær.
     */
    // calcSpærAntal
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


    /**
     * calcLengthOfBands har til formål at beregne længden af hulhånd, der skal bruges ud fra kundens valgte mål.
     * @param carportLength længden på carporten, som kunden har valgt.
     * @param carportWidth bredden på carporten, som kunden har valgt.
     * @param hasShed om kunden har valgt skur eller ej (true = har valgt, false = har ikke valgt).
     * @param shedLength længden på skuret, som kunden har valgt.
     * @return
     */
    // calcHulbåndLængde
    public int calcLengthOfBands(int carportLength, int carportWidth, boolean hasShed, int shedLength) {
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


    /**
     * calcRolesAmountBand har til formål at beregne antallet af ruller hulbånd ud fra hulbåndets længde samt antal
     * meter hulbånd i en pakke (der hentes fra databasen).
     * @param bandLength er længden på hulbåndet, der er beregnet i metoden: calcLengthOfBands.
     * @return en int med antal ruller hulbånd.
     * @throws UniversalSampleException kastes, hvis der er problemer med at hente fra databasen.
     */
    // calcHulbåndsRuller
    public int calcRolesAmountBand(int bandLength) throws UniversalSampleException {
        int result;
        final int BAND_ID = 10;
        final int BAND_PR_ROLES = MaterialFacade.getAmountPrUnit(BAND_ID);
        double bandLengthToM = bandLength / 100.0;
        result = (int) Math.ceil(bandLengthToM / BAND_PR_ROLES);
        return result;
    }

    /**
     * calcPillarHeight har til formål, at beregne højden på stolperne ud fra kundens valgte mål.
     * @param carportHeight højden på carporten, som kunden har valgt.
     * @param carportLength længden på carporten, som kunden har valgt.
     * @param hasShed om kunden har valgt skur eller ej (true = har valgt, false = har ikke valgt).
     * @param shedLength længden på skuret, som kunden har valgt.
     * @param hasPitch om kunden har valgt rejsningstag eller fladt tag (true = rejsningstag, false = fladt tag).
     * @param shedWidth bredden på skuret, som kunden har valgt.
     * @param carportWidth bredden på carporten, som kunde har valgt.
     * @return et ArrayList med doubles, der indeholder stolpernes højde i stolpepar (hvert index' længde, er længden for 2 stolper).
     * @throws UniversalSampleException kastes, hvis der er problemer med at hente fra databasen.
     */
    // calcStolpeHøjde
    public ArrayList<Double> calcPillarHeight(int carportHeight, double carportLength, boolean hasShed, int shedLength, boolean hasPitch, int shedWidth, int carportWidth) throws UniversalSampleException {
        ArrayList<Double> stolper = new ArrayList<Double>();
        final int HEAD_ID = 6;
        final int FACIA_ID = 1;
        final int GROUND_DEPTH = 90;
        final int DIST_BEHIND_CARPORT = 30;
        int pillars = 0;
        double firstPillar;
        double headHeight = MaterialFacade.getWidthHeightFromDimensionMeasureInCM(HEAD_ID).get(1);
        double fasciaBoardHeight = MaterialFacade.getWidthHeightFromDimensionMeasureInCM(FACIA_ID).get(1);
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
            firstPillar = Operations.roundToTwo(firstPillar);
            firstPillar += GROUND_DEPTH;
            stolper.add(firstPillar);


            double tmpStolpeHeight = pillarHeight;
            // Looper igennem carportens stolper (med varierende højde grundet hældning).
            for (int i = 1; i < pillars; i++) {
                tmpStolpeHeight = tmpStolpeHeight + Math.tan((2 * Math.PI) / 180) * 300;
                double roundedNum = Operations.roundToTwo(tmpStolpeHeight);
                roundedNum += GROUND_DEPTH;
                stolper.add(roundedNum);
            }
        }

        if (hasShed) {
            // Looper igennem skurets 8 stolper (med skurets højde).
            final int SHED_PILLARS = 8;
            for (int i = 1; i < SHED_PILLARS; i++) {
                stolper.add(firstPillar);
            }
        }
        return stolper;
    }

    /**
     * calcRoofTileAmount har til formål at beregnes plasttrapezpladerne for en carport med fladt tag, ud fra kundens
     * valgte mål.
     * @param carportLength længden på carporten, som kunden har valgt.
     * @param carportWidth bredden på carporten, som kunden har valgt.
     * @return en int der indeholder antal plasttrapezplader.
     * @throws UniversalSampleException kastes, hvis der er problemer med at hente fra databasen.
     */
    // calcPlasttrapezpladerAntal
    public int calcRoofTileAmount(int carportLength, int carportWidth) throws UniversalSampleException {
        final int ROOF_TILE_ID = 8;
        double roofTileLength = MaterialFacade.getWidthHeightFromDimensionMeasureInCM(ROOF_TILE_ID).get(1);
        double roofTileWidth = MaterialFacade.getWidthHeightFromDimensionMeasureInCM(ROOF_TILE_ID).get(0);
        final int TILE_OVERLAP = 20;
        roofTileWidth -= TILE_OVERLAP;
        double roofTileArea = roofTileWidth * roofTileLength;
        int roofArea = carportLength * carportWidth;
        int roofTileAmount = (int) Math.ceil(roofArea / roofTileArea);

        return roofTileAmount;
    }

    /**
     * calcRoofScrewAmount har til formål at beregne antal tagskruer ud fra kundens valgte mål.
     * @param carportLength længden på carporten, som kunden har valgt.
     * @param carportWidth bredden på carporten, som kunden har valgt.
     * @param ID product_id'et på tagskruer i databasen.
     * @return en int der indeholder antal tagskruer.
     * @throws UniversalSampleException kastes, hvis der er problemer med at hente fra databasen.
     */
    // calcTagskrueAntal
    public int calcRoofScrewAmount(int carportLength, int carportWidth, int ID) throws UniversalSampleException {
        int roofArea = carportLength * carportWidth;
        final int SCREW_PR_M2 = 12;
        final int CM_TO_M2 = 10000;
        final double AMOUNT_OF_SCREWS_PR_PACKAGE = MaterialFacade.getAmountPrUnit(ID);

        double tmpResult = ((roofArea / CM_TO_M2) * SCREW_PR_M2) / AMOUNT_OF_SCREWS_PR_PACKAGE;
        int result = (int) Math.ceil(tmpResult);

        return result;
    }

    /**
     * calcUniversalScrews har til formål at beregne antal universalskruer ud fra kundens valgte mål.
     * @param carportLength længden på carporten, som kunden har valgt.
     * @param hasPitch om kunden har valgt rejsningstag eller fladt tag (true = rejsningstag, false = fladt tag).
     * @return en int der indeholder antal universalskruer.
     */
    // calcUniversalSkruer
    public int calcUniversalScrews(int carportLength, boolean hasPitch) {
        //Vi antager at der skrues en skrue i hver side af hvert spær
        return calcRaftAmount(carportLength, hasPitch) * 2;
    }

    /**
     * calcPlankAndWaterScrews har til formål at give antal pakker med skruer til vandbrædt og sternbrædder.
     * @return en int indeholdende antal pakker med skruer.
     */
    // calcVandbrætOgSternSkruer
    public int calcPlankAndWaterScrews() {
        /*
        OBS: Der vil altid kun være 3 sternbrææder
        Vi antager at antallet af skruer aldrig bliver mere end 200stk(1 pakke) da der vil være 3 sternbrædder
        med en skrue i hver side.
        På vandbrædderne skal der bruges samme antal skruer. Dermed overstiger vi stadig ikke de 200stk.
        */
        return 1;
    }


    /**
     * calcBracketScrews har til formål at beregne antallet af pakker med skruer til beslag ud fra kundens valgte mål.
     * @param carportLength længden på carporten, som kunden har valgt.
     * @param hasPitch om kunden har valgt rejsningstag eller fladt tag (true = rejsningstag, false = fladt tag).
     * @return en int der indeholder antal pakker med skruer til beslag.
     */
    // calcBeslagSkruer
    public int calcBracketScrews(int carportLength, boolean hasPitch) {
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


    /**
     * calcCarriageBolts har til formål at beregne antal bræddebolte ud fra kundens valgte mål.
     * @param carportLength længden på carporten, som kunden har valgt.
     * @param hasShed om kunden har valgt skur eller ej (true = har valgt, false = har ikke valgt).
     * @param shedLength længden på skuret, som har kunden har valgt.
     * @param shedWidth bredden på skuret, som kunden har valgt.
     * @param carportWidth bredden på carporten, som kunden har valgt.
     * @return en int der indeholder antal bræddebolte.
     */
    // calcBræddeBolte
    public int calcCarriageBolts(int carportLength, boolean hasShed, int shedLength, int shedWidth, int carportWidth) {
        return calcPillarAmount(carportLength, hasShed, shedLength, shedWidth, carportWidth) * 2;
    }


    /**
     * calcTransomsLengthFrontAndBack har til formål at beregne antal løsholter foran og bagpå skuret ud fra kundens
     * valgte mål.
     * @param shedWidth bredden på skuret, som kunden har valgt.
     * @return et Array med en størrelse på 4, hvor index 0: antal normale løsholte, index 1: længden på de normale løsholte,
     * index 2: antal løsholter, hvor døren er trukket fra, index 3: længden på løsholter, hvor døren er trukket fra.
     */
    // calcLøsholteLængdeForanOgBagved
    public int[] calcTransomsLengthFrontAndBack(int shedWidth) {
        /* Vi antager, at der er 3 løsholte fra bunden til toppen på skurets for- og bagside samt 2 løsholte
         fra bunden til toppen på skurets sider. */

        // Array'et består af int = antal løsholter samt double = længde på de løsholter:
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

    /**
     * calcTransomsLengthSides har til formål at beregne antallet af løsholter i siderne af skuret ud fra kundens
     * valgte mål.
     * @param shedLength længden på skuret, som kunden har valgt.
     * @return et Array med en størrelse på 2, hvor index 0: antal løsholte og index 1: længen på løsholterne.
     */
    // calcLøsholteLængdeISiderne
    public int[] calcTransomsLengthSides(int shedLength) {
         /* Vi antager, at der er 3 løsholte fra bunden til toppen på skurets for- og bagside samt 2 løsholte
         fra bunden til toppen på skurets sider. */

        // Array'et består af int = antal løsholter samt double = længde på de løsholter:
        int[] result = new int[2];
        final int TRANSOMS_ON_SIDES = 2;
        int transomLength = shedLength;
        // Her ganges hver side, der ikke har en dør, med antal løsholter på en side:
        int transomAmount = TRANSOMS_ON_SIDES * 2;

        result[0] = transomAmount;
        result[1] = transomLength;

        return result;
    }

    /**
     * calcHeadsInShed er til formål at beregne antal rem i skuret ud fra kundens valgte mål.
     * @param shedLength længden på skuret, som kunden har valgt.
     * @return et Array med en størrelse på 2, hvor index 0: antal remme, index 1: længden på remmene.
     */
    // calcRemPåSkur
    public int[] calcHeadsInShed(int shedLength) {
        int headsLength = shedLength;
        // Der er 1 rem i hver side (derfor, 2 i alt):
        final int HEADS_AMOUNT = 2;

        int[] result = new int[2];
        result[0] = HEADS_AMOUNT;
        result[1] = headsLength;

        return result;
    }

    /**
     * calcPlanksForShed har til formål at beregne antal brædt til beklædningen på skuret ud fra kundens valgte mål.
     * @param shedLength længden på skuret, som kunden har valgt.
     * @param shedWidth bredden på skuret, som kunden har valgt.
     * @return en int der indeholder antal brædt til beklædningen på skuret.
     * @throws UniversalSampleException kastes, hvis der er problemer med at hente fra databasen.
     */
    // calcBeklædningForSkur
    public int calcPlanksForShed(int shedLength, int shedWidth) throws UniversalSampleException {
        int result;
        // ID til brædtet, der skal bruges:
        final int PLANK_ID = 3;
        // Vi antager, at der er et overlap på beklædningen af 2,5 cm i hver side af brædtet.
        final double OVERLAP = 2.5;
        ArrayList<Double> dimensionsOfPlank = MaterialFacade.getWidthHeightFromDimensionMeasureInCM(PLANK_ID);
        double tmpPlankWidth = dimensionsOfPlank.get(1);
        double plankWidthAfterOverlap = tmpPlankWidth - OVERLAP;
        double sides = (shedLength / plankWidthAfterOverlap) * 2;
        double frontBack = (shedWidth / plankWidthAfterOverlap) * 2;
        double tmpResult = sides + frontBack;
        result = (int) Math.ceil(tmpResult);

        return result;
    }

    /**
     * calcOuterScrewsShed har til formål at beregne antal pakker med skruer til ydersiden af skuret ud fra kundens
     * valgte mål.
     * @param shedLength længden på skuret, som kunden har valgt.
     * @param shedWidth bredden på skuret, som kunden har valgt.
     * @param ID product_id'et på ydreskruer hentet fra databasen.
     * @return en int der indeholder antal pakker med skruer til ydersiden af skuret.
     * @throws UniversalSampleException kastes, hvis der er problemer med at hente fra databasen.
     */
    // calcYdreSkruerPåSkur
    public int calcOuterScrewsShed(int shedLength, int shedWidth, int ID) throws UniversalSampleException {
        int result;
        int planksForShed = calcPlanksForShed(shedLength, shedWidth);
        // Vi antager, at der skal bruges 2 skruer pr. ydre bræt.
        final int AMOUNT_OF_OUTERSCREW_PR_PLANK = 2;
        double amountOfScrewPrPackage = MaterialFacade.getAmountPrUnit(ID);
        double tmpResult = (planksForShed * AMOUNT_OF_OUTERSCREW_PR_PLANK) / amountOfScrewPrPackage;
        result = (int) Math.ceil(tmpResult);
        return result;

    }

    /**
     * calcInnerScrewsShed har til formål at beregne antal pakker med skruer til indersiden af skuret ud fra kundens
     * valgte mål.
     * @param shedLength længden på skuret, som kunden har valgt.
     * @param shedWidth bredden på skuret, som kunden har valgt.
     * @param ID product_id'et på indreskruer hentet fra databasen.
     * @return en int der indeholder antal pakker med skruer til indersiden af skuret.
     * @throws UniversalSampleException kastes, hvis der er problemer med at hente fra databasen.
     */
    // calcIndreSkruerPåSkur
    public int calcInnerScrewsShed(int shedLength, int shedWidth, int ID) throws UniversalSampleException {
        int result;
        // Vi antager, at der bruges 4 skruer pr. løsholte.
        final int AMOUNT_OF_SCREWS_PR_TRANSOM = 4;
        final int AMOUNT_OF_SCREWS_ON_DOOR = 50;
        double amountOfScewPrPackage = MaterialFacade.getAmountPrUnit(ID);
        int transomAmountFrontBack = calcTransomsLengthFrontAndBack(shedWidth)[0] + calcTransomsLengthFrontAndBack(shedWidth)[2];
        int transomAmountSides = calcTransomsLengthSides(shedLength)[0];
        double tmpResult = ((transomAmountFrontBack * AMOUNT_OF_SCREWS_PR_TRANSOM) + (transomAmountSides * AMOUNT_OF_SCREWS_ON_DOOR) + AMOUNT_OF_SCREWS_ON_DOOR) / amountOfScewPrPackage;
        result = (int) Math.ceil(tmpResult);

        return result;
    }


    /**
     * calcAngleMount har til formål at beregne antal vinkelbeslag ud fra kundens valgte mål.
     * @param shedLength længden på skuret, som kunden har valgt.
     * @param shedWidth bredden på skuret, som kunden har valgt.
     * @return en int der indeholder antal vinkelbeslag.
     */
    // calcVinkelBeslag
    public int calcAngleMount(int shedLength, int shedWidth) {
        int result;
        // Vi antager, at der bruges 2 vinkelbeslag pr. løsholt(én i hver ende)
        int transomAmountFrontBack = calcTransomsLengthFrontAndBack(shedWidth)[0] + calcTransomsLengthFrontAndBack(shedWidth)[2];
        int transomAmountSides = calcTransomsLengthSides(shedLength)[0];
        result = (transomAmountFrontBack + transomAmountSides) * 2;
        return result;
    }

    /**
     * calcTilesForPitchedRoof har til formål at beregne antal tagsten til en carport med rejsningstag.
     * @return en int der indeholder antal tagsten til en carport med rejsningstag.
     */
    // calcTagstenForRejsningtag
    public int calcTilesForPitchedRoof() {
        // Vi antager at der er 6 rækker af 24 sten på hver side af taget:
        final int ROWS = 6;
        final int AMOUNT_STONES = 24;
        int totalTiles = (ROWS*AMOUNT_STONES) * 2;

        return totalTiles;
    }

    /**
     * calcAmountOfRooflaths har til formål at beregne antal taglægter ud fra kundens valgte mål.
     * @param carportWidth bredden på carporten, som kunden har valgt.
     * @return en int der indeholder antal taglægter.
     */
    // calcAntalTaglægter
    public int calcAmountOfRooflaths(int carportWidth) {
        // Vi antager, at der er 35 cm mellem hver taglægte.
        final double SPACE_BETWEEN = 35.0;
        int result;
        double  tmpResult = Math.ceil((carportWidth - 90.0) / SPACE_BETWEEN);
        result = (int)tmpResult;
        return result;
    }

    /**
     * calcAmountOfRooflathScrews har til formål at beregne antal pakker med skruer til taglægterne ud fra antal
     * taglægter og antal spær.
     * @param amountOfRooflaths antallet af taglægter.
     * @param raftAmount antallet af spær.
     * @param ID product_id'et på skruer til taglægter der hentes fra databasen.
     * @return en int der indeholder antal pakker med skruer til taglægterne.
     * @throws UniversalSampleException kastes, hvis der er problemer med at hente fra databasen.
     */
    // calcAntalTaglægteSkruer
    public int calcAmountOfRooflathScrews(int amountOfRooflaths, int raftAmount, int ID) throws UniversalSampleException {
        // Vi antager, at der skal én skrue til én taglægte pr. spær:
        final int AMOUNT_OF_SCREWS_PR_ROOFLATH = raftAmount;

        double screwsPrUnit = MaterialFacade.getAmountPrUnit(ID);
        int result = (amountOfRooflaths * AMOUNT_OF_SCREWS_PR_ROOFLATH);
        result = (int) (Math.ceil(result / screwsPrUnit));

        return result;
    }

    /**
     * calcPackagesOfTileBindersAndHooks har til formål at give antal pakker med tagstens bindere og nakkekroge
     * til en carport med rejsningstag.
     * @return en int der indeholder antal pakker med tagstens bindere og nakkekroge.
     */
    // calcTagstensbindereOgNakkekroge
    public int calcPackagesOfTileBindersAndHooks() {
        // Vi antager, at der uanset tagstørrelse skal bruges 2 pakker tagstens bindere og nakkekroge
        final int AMOUNT_OF_TILE_BINDERS_HOOKS = 2;

        return AMOUNT_OF_TILE_BINDERS_HOOKS;
    }

    /**
     * calcAmountOfRoofTileStones har til formål at beregne antal rygsten ud fra kundens valgte mål.
     * @param carportLength længden på carporten, som kunden har valgt.
     * @return en int der indeholder antal rygsten.
     */
    // calcAntalRygsten
    public int calcAmountOfRoofTileStones(int carportLength) {
        // Vi antager, at en rygsten har en længde på 50 cm.
        final int ROOFTILE_STONE_LENGTH_CM = 50;
        final int OVERLAP_CM = 10;

        int result = carportLength / (ROOFTILE_STONE_LENGTH_CM - OVERLAP_CM);

        return result;
    }

    /**
     * calcAmountOfRoofTileStoneBrackets har til formål at beregne antal rugstensbeslag ud fra antal rygsten.
     * @param amountOfRoofTileStones antal rygsten.
     * @return en int der indeholder antal rygstensbeslag.
     */
    // calcAntalRygstensBeslag
    public int calcAmountOfRoofTileStoneBrackets(int amountOfRoofTileStones) {
        // Der skal 1 rygstensbeslag til 1 rygsten.
        int result = amountOfRoofTileStones;

        return result;
    }


    /**
     * calcAmountOfToplathHolders har til formål at beregne antal toplægteholdere ud fra antallet af spær.
     * @param amountOfRafts antallet af spær.
     * @return en int der indeholder antallet af toplægte holdere.
     */
    // calcAntalToplægteHoldere
    public int calcAmountOfToplathHolders(int amountOfRafts) {
        // Der er 1 toplægteholder på hvert spær.
        int result = amountOfRafts;

        return result;
    }

    /**
     * calcGavlPlanksLength har til formål at beregne længden på vindskederne på en carport med rejsningstag ud fra
     * kundens valgte mål.
     * @param carportWidth bredden på carporten, som kunden har valgt.
     * @param carportPitch hældningen på taget, som kunden har valgt.
     * @return
     */
    // calcVindskederLængde
    public int calcGavlPlanksLength(int carportWidth, int carportPitch) {
        double halfWidth = carportWidth / 2;
        double middleHeight = Math.tan((carportPitch * Math.PI) / 180) * halfWidth;
        double tmpResult = Math.sqrt((halfWidth * halfWidth) + (middleHeight * middleHeight));
        int result = (int) (Math.ceil(tmpResult));

        return result;
    }

    /**
     * calcAmountOfGavlPlanks har til formål at give antal vindskeder til en carport med rejsningstag.
     * @return en int der indeholder antal vindskeder.
     */
    // calcAntalVindskeder
     public int calcAmountOfGavlPlanks() {
        // Vi antager, at der altid er 4 gavl-bræt (2 foran og 2 bagved):
        final int AMOUNT_OF_GAVL_PLANKS = 4;

        return AMOUNT_OF_GAVL_PLANKS;
    }

    /**
     * calcAmountOfPlanksForGavlMount har til formål at beregne antallet af brædt til beklædningen på gavlen ud fra
     * kundens valgte mål.
     * @param carportWidth bredden på carporten, som kunden har valgt.
     * @return en int der indeholder antallet af brædt til gavlens beklædning.
     * @throws UniversalSampleException
     */
    // calcAntalBrætIGavlen
    public int calcAmountOfPlanksForGavlMount(int carportWidth) throws UniversalSampleException {
        int result;
        // ID til brættet, der skal bruges:
        final int PLANK_ID = 3;
        // Vi antager, at der er et overlap på beklædningen af 2,5 cm i hver side af brædtet.
        final double OVERLAP = 2.5;
        ArrayList<Double> dimensionsOfPlank = MaterialFacade.getWidthHeightFromDimensionMeasureInCM(PLANK_ID);
        double tmpPlankWidth = dimensionsOfPlank.get(1);
        double plankWidthAfterOverlap = tmpPlankWidth - OVERLAP;
        double amountOfPlanks = (carportWidth / plankWidthAfterOverlap) * 2;
        result = (int) Math.ceil(amountOfPlanks);

        return result;
    }

    /**
     * calcPlanksForGavlMountLength har til formål at beregne den største længden på brædderne til beklædningen på gavlen
     * ud fra kundens valgte mål. Brædderne tilskæres, når carporten bygges.
     * @param carportWidth bredden på carporten, som kunden har valgt.
     * @param carportPitch hældningen på taget, som kunden har valgt.
     * @return en int der indeholder den største længden på brædderne til beklædningen på gavlen.
     */
    // calcLængdePåBrætIGavlen
    public int calcPlanksForGavlMountLength(int carportWidth, int carportPitch) {
        double halfWidth = carportWidth / 2;
        double middleHeight = Math.tan((carportPitch * Math.PI) / 180) * halfWidth;
        int result = (int) (Math.ceil(middleHeight));

        return result;
    }


    /**
     * calcPricePrUnit har til formål at beregne prisen for et materiale pr. enhed, f.eks. prisen for en pakke skruer,
     * en rulle hulbånd, en cm spærtræ mm.
     * @param unit er antallet pr. enhed (f.eks. 200 for en pakke skruer, hvor der er 200 stk. pr. pakke).
     * @param pricePrUnit er prisen pr. enhed.
     * @return en int der indeholder prisen pr. enhed.
     */
    // calcPrisPrEnhed
    public int calcPricePrUnit(int unit, double pricePrUnit) {
        int result = (int) (Math.ceil(unit * pricePrUnit));
        return result;
    }

    /**
     * calcPricePrUnitWithLength har til formål at beregne prisen pr. enhed af materialer, hvor antallet afhænger
     * af længden, f.eks. skal der bruges ét brædt på 1000 cm, men grundet lagerets længder, bruges der to brædt af 500 cm.
     * @param unit antal pr. enhed.
     * @param pricePrUnit prisen pr. enhed.
     * @param amount antal.
     * @return
     */
    // calcPrisPrEnhedMedLængde
    public int calcPricePrUnitWithLength(int unit, double pricePrUnit, int amount) {
        int result = (int) (Math.ceil((unit * pricePrUnit) * amount));
        return result;
    }

    /**
     * fullPrice har til formål at beregne den samlede pris for caporten, hvor prisen på materialer samt Fogs
     * service begge udgør en samlet pris.
     * @param materialInfo en ArrayList der indeholder alle priserne på alle de beregnede materialer til carporten.
     *                     ArrayListen er skabt ud fra info om hvert materiales sidste index.
     * @return en int der indeholder den fulde pris for carport-servicen.
     */
    public int fullPrice(ArrayList<String> materialInfo) {
        // Vi antager, at Fog tager 5000 kr. for carport-ydelsen ekls. materiale priser.
        final int PRICE_FOR_SERVICE = 5000;
        int prices;
        int result = 0;
        String priceIndex;
        for (String s : materialInfo) {
            String[] tmpPriceIndex;
            tmpPriceIndex = s.split(" ");
            priceIndex = tmpPriceIndex[0];
            prices = Integer.parseInt(priceIndex);
            result += prices;
        }

        result += PRICE_FOR_SERVICE;

        return result;
    }


    /**
     * calcWoodForMeasure-metoden har til formål at beregne, hvor mange stykker træ der skal til for
     * at udfylde en given længde ud fra de længde, der står i databasen.
     * F.eks.: Der skal bruges en rem på 1472 cm, men den maksimale længde i databasen er 900 cm, så der udregnes måske,
     * at der skal bruges to stykker træ af 750 cm, da der er tættest på halvdelen af 1472 cm.
     *
     * @param materialMeasure er den utopiske længde, der skal bruges på carporten.
     * @param lengths er længderne fra storage-tabellen (Fogs lager), der er blevet lagt i et ArrayList.
     * @param amountOnCarport er antallet af brædt med den utopiske længde, der skal bruges på carporten (f.eks. 2 rem,
     *                        én i hver side).
     * @return index 0: antal brædt (fra lageret), index 1: længden på brædt (fra lageret).
     */
    // calcAntalTræTilLængde
    public ArrayList<Integer> calcWoodForMeasure(int materialMeasure, ArrayList<Integer> lengths, int amountOnCarport) {
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
