package PresentationLayer;

import DBAccess.MaterialMapper;
import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class MaterialCalculator extends Command {


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        HttpSession session = request.getSession();
        /*String tmpCarportLength = request.getParameter("length");
        String tmpCarportWidth = request.getParameter("width");
        String tmpCarportHeight = request.getParameter("height");*/

        int carportLength = Validation.getInteger(tmpCarportLength);
        int carportWidth = Validation.getInteger(tmpCarportWidth);
        int carportHeight = Validation.getInteger(tmpCarportHeight);


        boolean hasShed = true;


        return "tmpList";
    }

    public int calcPillarAmount(int carportLength, boolean hasShed, int shedLenght) {
        int result;
        final double PILLAR_AT_METER = 3.0;
        double tmpResult;
        if (hasShed) {
            double tmpLength = carportLength - shedLenght;
            double tmpCarportLength = carportLength / 100.0;
            tmpResult = (tmpCarportLength / PILLAR_AT_METER);
            tmpResult = Math.floor(tmpResult);
            result = (int) (tmpResult * 2);
            result += 8; //Vi antager at et skur skal bruge 6 stolper (en i hvert hjørne + 2 i midten) og 2 til hver side af døren.
        } else {
            double tmpCarportLength = carportLength / 100.0;
            // Beregning af antal stolper (skal være et i hver side pr. 300 cm af carportens længde).
            tmpResult = (tmpCarportLength / PILLAR_AT_METER);
            tmpResult = Math.floor(tmpResult);
            result = ((int) tmpResult) * 2;
            if (result <= 4) {
                result = 4;
            }
        }
        return result;
    }

    //Vi antager at mængde af spær ikke ændre sig med skur eller ej
    public int calcRaftAmount(int carportLength) {
        int result;
        double tmpResult;
        double tmpCarportLength = carportLength / 100.0;
        // Beregning af antal spær; skal være et pr. 55 cm.
        final double RAFT_AT_METER = 0.55;
        tmpResult = (tmpCarportLength / RAFT_AT_METER);
        tmpResult = Math.ceil(tmpResult);
        result = ((int) tmpResult);
        return result;
    }

    //Antal spær -2 i stedet for 70% ind? TODO
    public int[] calcBandAmount(int carportLength, int carportWidth) {
        int[] result = new int[4];

        // Beregning af første placering af hulbånd (skal side på det første spær)
        // Det første spær er altid 55 cm fra carportens længdes start.
        final double BAND1_START_METER = 0.55;
        double tmpResultBand1Double = 0.55 * 100.0;
        int tmpResultBand1Int = (int) tmpResultBand1Double;
        result[0] = tmpResultBand1Int;

        // Beregning af andet hulbåndsplacering
        // (skal være på det spær, der rundes op til at være tættest på 73% af carportens længde).
        final double BAND2_END_PERCENT = 70.51 / 100.0;
        double tmpResultBand2Double = carportLength * BAND2_END_PERCENT;
        // står i cm:
        final double DIST_BET_RAFTS = 55.0;
        // Vi plusser 1, da udregningen ikke tager det 1. spær med i bektræktning.
        double theRaftThatHasThe2ndBand = Math.ceil(tmpResultBand2Double / DIST_BET_RAFTS);
        double theRaftThatHasThe2ndBandWithFirstRaft = theRaftThatHasThe2ndBand + 1.0;
        int tmpIntResultBand2Length = (int) (theRaftThatHasThe2ndBand * DIST_BET_RAFTS);
        // Tilføj længde til andet punkt af hulbåndet af den samlede carport længde.
        result[1] = tmpIntResultBand2Length;

        // Beregn og tilføj hvilket spær andet punkt af hulbåndet skal monteres på.
        int tmpIntResultBand2Raft = (int) theRaftThatHasThe2ndBand;
        result[2] = tmpIntResultBand2Raft;

        // Beregn hulbåndets længde fra punkt 1 (top venstre til bund højre) til punkt 2 (top venstre til bund højre).
        double lengthOfSquare = tmpIntResultBand2Length - DIST_BET_RAFTS;
        final double HEAD_TO_HEAD_PERCENT = 88.3333 / 100.0;
        double widthOfSquare = (carportWidth * HEAD_TO_HEAD_PERCENT);
        final double CALC_DIAGONAL = Math.sqrt((lengthOfSquare * lengthOfSquare) + (widthOfSquare * widthOfSquare));
        int diagonalToInt = (int) (CALC_DIAGONAL * 2.0);
        result[3] = diagonalToInt;

        return result;
    }
    //TODO efter calcBandAmount
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


    public ArrayList<Double> getPillarHeight(int carportHeight, double længde) {
        ArrayList<Double> stolper = new ArrayList<Double>();
        final int HEAD_ID = 6;
        final int FACIA_ID = 1;
        final int GROUND_DEPTH = 90;
        final int DIST_BEHIND_CARPORT = 30;
        double headHeight = MaterialMapper.getWidthHeightFromDimensionMeasureInCM(HEAD_ID).get(1);
        double fasciaBoardHeight = MaterialMapper.getWidthHeightFromDimensionMeasureInCM(FACIA_ID).get(1);
        double pillarHeight = carportHeight - headHeight - fasciaBoardHeight;


        //TODO if(skur)

        //if(ikke skur)

        int pillars = (calcPillarAmount((int) længde)) / 2;
        double første = pillarHeight + Math.tan((2 * Math.PI) / 180) * DIST_BEHIND_CARPORT;
        første = roundToTwo(første);
        første += GROUND_DEPTH;
        stolper.add(første);

        double tmpStolpeHeight = pillarHeight;
        for (int i = 1; i < pillars; i++) {
            tmpStolpeHeight = tmpStolpeHeight + Math.tan((2 * Math.PI) / 180) * 300;
            double roundedNum = roundToTwo(tmpStolpeHeight);
            roundedNum += GROUND_DEPTH; //TODO Tjek om den tilføjer 180 til tredje stolpe, ellers tilføj de 90 cm senere hen.
            stolper.add(roundedNum);
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

    public int getRoofScrewAmount(int carportLength, int carportWidth) {
        int roofArea = carportLength * carportWidth;
        final int SCREW_PR_M2 = 12;
        final int CM_TO_M2 = 10000;
        double AMOUNT_OF_SCREWS_PR_PACKAGE = 200;

        double tmpResult = ((roofArea / CM_TO_M2) * SCREW_PR_M2) / AMOUNT_OF_SCREWS_PR_PACKAGE;
        int result = (int) Math.ceil(tmpResult);

        return result;
    }

    public int getUniversalScrews(int carportLength) {
        //Vi antager at der skrues en skrue i hver side af hvert spær
        return calcRaftAmount(carportLength) * 2;
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

    public int getBracketScrews(int carportLength) {
        /*
        Vi antager at der skrues 2 beslag på hver side af et spær i begge ender. Derved ganger vi spær med 4 og derefter
        med 6 da der går 6 skruer til 1 beslag.

        Vi antager at beskrivelse om montering af hulbånd allerede er dækket af vores beregning.
        */
        int amountOfRafts = calcRaftAmount(carportLength);
        int amountOfScrews = amountOfRafts * 4 * 6;
        double amountOfPacks = Math.ceil(amountOfScrews / 250.0);
        return (int) amountOfPacks;
    }

    public int getCarriageBolts(int carportLength) {
        return calcPillarAmount(carportLength) * 2;
    }
}
