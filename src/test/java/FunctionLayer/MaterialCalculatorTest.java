package FunctionLayer;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class MaterialCalculatorTest {

    private static MaterialCalculator calculator = new MaterialCalculator();
    private static int carportLength = 600;
    private static int carportWidth = 400;
    private static int carportHeight = 300;
    private static boolean hasShed = false;
    private static boolean hasPitch = false;
    private static int shedLength = 200;
    private static int shedWidth = 200;
    private static int roofPitch = 30;


    @Test
    public void testCalcPillarAmount() {
        //int expected =
        int result = calculator.calcPillarAmount(carportLength, hasShed, shedLength, shedWidth, carportWidth);
    }




}
