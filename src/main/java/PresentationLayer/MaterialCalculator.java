package PresentationLayer;

import FunctionLayer.*;
import com.sun.prism.Material;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MaterialCalculator extends Command {


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        HttpSession session = request.getSession();
        String tmpCarportLength = request.getParameter("length");
        String tmpCarportWidth = request.getParameter("width");
        String tmpCarportHeight = request.getParameter("height");

        int carportLength = Validation.getInteger(tmpCarportLength);
        int carportWidth = Validation.getInteger(tmpCarportWidth);
        int carportHeight = Validation.getInteger(tmpCarportHeight);



        return "tmpList";
    }

    public int calcPillarAmount(int carportLength) {
        int result;
        double tmpResult;
        double tmpCarportLength = carportLength / 100.0;
        // Beregning af antal stolper (skal være et i hver side pr. 300 cm af carportens længde).
        final double PILLAR_AT_METER = 3.0;
        tmpResult = (tmpCarportLength / PILLAR_AT_METER);
        tmpResult = Math.floor(tmpResult);
        result = ((int) tmpResult) * 2;
        if(result <= 4) {
            result = 4;
        }
        return result;
    }

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
        final double CALC_DIAGONAL = Math.sqrt((lengthOfSquare*lengthOfSquare) + (widthOfSquare*widthOfSquare));
        int diagonalToInt = (int) (CALC_DIAGONAL * 2.0);
        result[3] = diagonalToInt;

        return result;
    }

    // ANTAGELSE: Uanset carport-mål skal afstanden fra det yderste
    // af carport til rem være 35 cm på hver side (30 cm bagtil).


    public void getStolper(double stolpeHøjde, double længde) {
        ArrayList<Double> stolper = new ArrayList<Double>();
        //if(skur)

        //if(ikke skur)

        int pillars = (calcPillarAmount((int)længde))/2;
        double første = stolpeHøjde + Math.tan((2*Math.PI)/180)*30;
        stolper.add(første);

        double tmpStolpeHeight = stolpeHøjde;
        for(int i=1; i < pillars; i++) {
            tmpStolpeHeight = tmpStolpeHeight + Math.tan((2*Math.PI)/180)*300;
            stolper.add(tmpStolpeHeight);
        }

        for(double d: stolper) {
            System.out.println(d);
        }

    }

}
