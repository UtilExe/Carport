package FunctionLayer;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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

    @After
    public void setBooleans() {
        hasShed = false;
        hasPitch = false;
    }

    @Test
    public void testCalcPillarAmount() {
        int expected = 6;
        int result = calculator.calcPillarAmount(carportLength, hasShed, shedLength, shedWidth, carportWidth);
        assertEquals(expected, result);

        hasShed = true;
        expected = 13;
        result = calculator.calcPillarAmount(carportLength, hasShed, shedLength, shedWidth, carportWidth);
        assertEquals(expected, result);
    }
    @Test (expected = AssertionError.class)
    public void testCalcPillarAmountNegative() {
        int expected = 7;
        int result = calculator.calcPillarAmount(carportLength, hasShed, shedLength, shedWidth, carportWidth);
        assertEquals(expected, result);

        hasShed = true;
        expected = 14;
        result = calculator.calcPillarAmount(carportLength, hasShed, shedLength, shedWidth, carportWidth);
        assertEquals(expected, result);
    }

    @Test
    public void calcRaftAmount() {
        int expected = 11;
        int result = calculator.calcRaftAmount(carportLength, hasPitch);
        assertEquals(expected, result);

        hasPitch = true;

        expected = 7;
        result = calculator.calcRaftAmount(carportLength, hasPitch);
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void calcRaftAmountNegativ() {
        int expected = 12;
        int result = calculator.calcRaftAmount(carportLength, hasPitch);
        assertEquals(expected, result);

        hasPitch = true;

        expected = 9;
        result = calculator.calcRaftAmount(carportLength, hasPitch);
        assertEquals(expected, result);
    }

    @Test
    public void calcLengthOfBands() {
        int expected = 1181;
        int result = calculator.calcLengthOfBands(carportLength, carportWidth, hasShed, shedLength);
        assertEquals(expected, result);

        hasShed = true;
        expected = 954;
        result = calculator.calcLengthOfBands(carportLength, carportWidth, hasShed, shedLength);
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void calcLengthOfBandsNegative() {
        int expected = 5000;
        int result = calculator.calcLengthOfBands(carportLength, carportWidth, hasShed, shedLength);
        assertEquals(expected, result);

        hasShed = true;
        expected = 1000;
        result = calculator.calcLengthOfBands(carportLength, carportWidth, hasShed, shedLength);
        assertEquals(expected, result);
    }

    @Test
    public void getRolesAmountBand() throws UniversalSampleException {
        int bandLength = 500;
        int expected = 1;
        int result = calculator.getRolesAmountBand(bandLength);
        assertEquals(expected, result);

        bandLength = 1500;
        expected = 2;
        result = calculator.getRolesAmountBand(bandLength);
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void getRolesAmountBandNegative() throws UniversalSampleException {
        int bandLength = 500;
        int expected = 5;
        int result = calculator.getRolesAmountBand(bandLength);
        assertEquals(expected, result);

        bandLength = 1500;
        expected = 5;
        result = calculator.getRolesAmountBand(bandLength);
        assertEquals(expected, result);
    }
}
