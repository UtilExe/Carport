package FunctionLayer;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
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
    public void testCalcRaftAmount() {
        int expected = 11;
        int result = calculator.calcRaftAmount(carportLength, hasPitch);
        assertEquals(expected, result);

        hasPitch = true;

        expected = 7;
        result = calculator.calcRaftAmount(carportLength, hasPitch);
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testCalcRaftAmountNegativ() {
        int expected = 12;
        int result = calculator.calcRaftAmount(carportLength, hasPitch);
        assertEquals(expected, result);

        hasPitch = true;

        expected = 9;
        result = calculator.calcRaftAmount(carportLength, hasPitch);
        assertEquals(expected, result);
    }

    @Test
    public void testCalcLengthOfBands() {
        int expected = 1181;
        int result = calculator.calcLengthOfBands(carportLength, carportWidth, hasShed, shedLength);
        assertEquals(expected, result);

        hasShed = true;
        expected = 954;
        result = calculator.calcLengthOfBands(carportLength, carportWidth, hasShed, shedLength);
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testCalcLengthOfBandsNegative() {
        int expected = 5000;
        int result = calculator.calcLengthOfBands(carportLength, carportWidth, hasShed, shedLength);
        assertEquals(expected, result);

        hasShed = true;
        expected = 1000;
        result = calculator.calcLengthOfBands(carportLength, carportWidth, hasShed, shedLength);
        assertEquals(expected, result);
    }

    @Test
    public void testGetRolesAmountBand() throws UniversalSampleException {
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
    public void testGetRolesAmountBandNegative() throws UniversalSampleException {
        int bandLength = 500;
        int expected = 5;
        int result = calculator.getRolesAmountBand(bandLength);
        assertEquals(expected, result);

        bandLength = 1500;
        expected = 5;
        result = calculator.getRolesAmountBand(bandLength);
        assertEquals(expected, result);
    }

    @Test
    public void testGetPillarHeight() throws UniversalSampleException {
        int expected = 3;
        ArrayList<Double> result = calculator.getPillarHeight(carportHeight, carportLength, hasShed, shedLength, hasPitch, shedWidth, carportWidth);
        assertThat(result, hasSize(expected));

        hasShed = true;
        expected = 10;
        result = calculator.getPillarHeight(carportHeight, carportLength, hasShed, shedLength, hasPitch, shedWidth, carportWidth);
    }

    @Test (expected = AssertionError.class)
    public void testGetPillarHeightNegative() throws UniversalSampleException {
        int expected = 10;
        ArrayList<Double> result = calculator.getPillarHeight(carportHeight, carportLength, hasShed, shedLength, hasPitch, shedWidth, carportWidth);
        assertThat(result, hasSize(expected));

        hasShed = true;
        expected = 3;
        result = calculator.getPillarHeight(carportHeight, carportLength, hasShed, shedLength, hasPitch, shedWidth, carportWidth);
    }

    @Test
    public void testGetRoofTileAmount() throws UniversalSampleException {
        int expected = 8;
        int result = calculator.getRoofTileAmount(carportLength, carportWidth);

        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetRoofTileAmountNegative() throws UniversalSampleException {
        int expected = 15;
        int result = calculator.getRoofTileAmount(carportLength, carportWidth);

        assertEquals(expected, result);
    }

    @Test
    public void testGetRoofScrewAmount() throws UniversalSampleException {
        int expected = 2;
        int ID = 9;
        int result = calculator.getRoofScrewAmount(carportLength, carportWidth, ID);
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetRoofScrewAmountNegative() throws UniversalSampleException {
        int expected = 7;
        int ID = 9;
        int result = calculator.getRoofScrewAmount(carportLength, carportWidth, ID);
        assertEquals(expected, result);
    }

    @Test
    public void testGetUniversalScrews() {
        int expected = 22;
        int result = calculator.getUniversalScrews(carportLength, hasPitch);
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetUniversalScrewsNegative()  {
        int expected = 18;
        int result = calculator.getUniversalScrews(carportLength, hasPitch);
        assertEquals(expected, result);
    }

    @Test
    public void testGetPlankAndWaterScrews() {
        int expected = 1;
        int result = calculator.getPlankAndWaterScrews();
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetPlankAndWaterScrewsNegative() {
        int expected = 4;
        int result = calculator.getPlankAndWaterScrews();
        assertEquals(expected, result);
    }

    @Test
    public void testGetBracketScrews() {
        int expected = 2;
        int result = calculator.getBracketScrews(carportLength, hasPitch);

        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetBracketScrewsNegative() {
        int expected = 1;
        int result = calculator.getBracketScrews(carportLength, hasPitch);
        assertEquals(expected, result);
    }

    @Test
    public void testGetCarriageBolts() {
        int expected = 12;
        int result = calculator.getCarriageBolts(carportLength, hasShed, shedLength, shedWidth, carportWidth);
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetCarriageBoltsNegative() {
        int expected = 6;
        int result = calculator.getCarriageBolts(carportLength, hasShed, shedLength, shedWidth, carportWidth);
        assertEquals(expected, result);
    }

    @Test
    public void testGetTransomsLengthFrontAndBack() {
        int[] expected = {9, 100, 3, 20}; // udregnet ud fra variablerne i getTransomsLengthFrontAndBack metoden
        int[] result = calculator.getTransomsLengthFrontAndBack(shedWidth);
        assertArrayEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetTransomsLengthFrontAndBackNegative() {
        int[] expected = {5, 100, 3, 20};
        int[] result = calculator.getTransomsLengthFrontAndBack(shedWidth);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testGetTransomsLengthSides() {
        int[] expected = {4, 200};
        int[] result = calculator.getTransomsLengthSides(shedLength);
        assertArrayEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetTransomsLengthSidesNegative() {
        int[] expected = {4, 150};
        int[] result = calculator.getTransomsLengthSides(shedLength);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testGetHeadsInShed() {
        int[] expected = {2, 200};
        int[] result = calculator.getHeadsInShed(shedLength);
        assertArrayEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetHeadsInShedNegative() {
        int[] expected = {5, 150};
        int[] result = calculator.getHeadsInShed(shedLength);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testGetPlanksForShed() throws UniversalSampleException {
        int expected = 107;
        int result = calculator.getPlanksForShed(shedLength, shedWidth);
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetPlanksForShedNegative() throws UniversalSampleException {
        int expected = 60;
        int result = calculator.getPlanksForShed(shedLength, shedWidth);
        assertEquals(expected, result);
    }

    @Test
    public void testGetOuterScrewsShed() throws UniversalSampleException {
        int expected = 2;
        int ID = 13;
        int result = calculator.getOuterScrewsShed(shedLength, shedWidth, ID);
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetOuterScrewsShedNegative() throws UniversalSampleException {
        int expected = 1;
        int ID = 13;
        int result = calculator.getOuterScrewsShed(shedLength, shedWidth, ID);
        assertEquals(expected, result);
    }

    @Test
    public void testGetInnerScrewsShed() throws UniversalSampleException {
        int expected = 2;
        int ID = 12;
        int result = calculator.getInnerScrewsShed(shedLength, shedWidth, ID);
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetInnerScrewsShedNegative() throws UniversalSampleException {
        int expected = 3;
        int ID = 12;
        int result = calculator.getInnerScrewsShed(shedLength, shedWidth, ID);
        assertEquals(expected, result);
    }

    @Test
    public void testGetAngleMount() {
        int expected = 32;
        int result = calculator.getAngleMount(shedLength, shedWidth);
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetAngleMountNegative() throws UniversalSampleException {
        int expected = 5;
        int ID = 12;
        int result = calculator.getInnerScrewsShed(shedLength, shedWidth, ID);
        assertEquals(expected, result);
    }

    @Test
    public void getTilesForPitchedRoof() {
        int expected = 288;
        int result = calculator.getTilesForPitchedRoof();
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void getTilesForPitchedRoofNegative() {
        int expected = 188;
        int result = calculator.getTilesForPitchedRoof();
        assertEquals(expected, result);
    }

    @Test
    public void testGetAmountOfRooflaths() {
        int expected = 9;
        int result = calculator.getAmountOfRooflaths(carportWidth);
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetAmountOfRooflathsNegative() {
        int expected = 10;
        int result = calculator.getAmountOfRooflaths(carportWidth);
        assertEquals(expected, result);
    }

    @Test
    public void testGetAmountOfToplathScrews() throws UniversalSampleException {
        int expected = 1;
        int amountOfRooflaths = 9;
        int raftAmount = 11;
        int ID = 30;
        int result = calculator.getAmountOfToplathScrews(amountOfRooflaths, raftAmount, ID);
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetAmountOfToplathScrewsNegative() throws UniversalSampleException {
        int expected = 2;
        int amountOfRooflaths = 9;
        int raftAmount = 11;
        int ID = 30;
        int result = calculator.getAmountOfToplathScrews(amountOfRooflaths, raftAmount, ID);
        assertEquals(expected, result);
    }

    @Test
    public void testGetPackagesOfTileBindersAndHooks() {
        int expected = 2;
        int result = calculator.getPackagesOfTileBindersAndHooks();
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetPackagesOfTileBindersAndHooksNegative() {
        int expected = 1;
        int result = calculator.getPackagesOfTileBindersAndHooks();
        assertEquals(expected, result);
    }

    @Test
    public void testGetAmountOfRoofTileStones() {
        int expected = 15;
        int result = calculator.getAmountOfRoofTileStones(carportLength);
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetAmountOfRoofTileStonesNegative() {
        int expected = 10;
        int result = calculator.getAmountOfRoofTileStones(carportLength);
        assertEquals(expected, result);
    }


    @Test
    public void testGetAmountOfRoofTileStoneBrackets() {
        int expected = 600;
        int result = calculator.getAmountOfRoofTileStoneBrackets(carportLength);
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetAmountOfRoofTileStoneBracketsNegative() {
        int expected = 500;
        int result = calculator.getAmountOfRoofTileStoneBrackets(carportLength);
        assertEquals(expected, result);
    }

    @Test
    public void testGetAmountOfToplathHolders() {
        int expected = 600;
        int result = calculator.getAmountOfToplathHolders(carportLength);
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetAmountOfToplathHoldersNegative() {
        int expected = 2;
        int result = calculator.getAmountOfToplathHolders(carportLength);
        assertEquals(expected, result);
    }

    @Test
    public void testGetGavlPlanksLength() {
        int expected = 231;
        int result = calculator.getGavlPlanksLength(carportWidth, roofPitch);
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetGavlPlanksLengthNegative() {
        int expected = 130;
        int result = calculator.getGavlPlanksLength(carportWidth, roofPitch);
        assertEquals(expected, result);
    }

    @Test
    public void testGetAmountOfGavlPlanks() {
        int expected = 4;
        int result = calculator.getAmountOfGavlPlanks();
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetAmountOfGavlPlanksNegative() {
        int expected = 2;
        int result = calculator.getAmountOfGavlPlanks();
        assertEquals(expected, result);
    }

    @Test
    public void testGetAmountOfPlanksForGavlMount() throws UniversalSampleException {
        int expected = 107;
        int result = calculator.getAmountOfPlanksForGavlMount(carportWidth);
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetAmountOfPlanksForGavlMountNegative() throws UniversalSampleException {
        int expected = 50;
        int result = calculator.getAmountOfPlanksForGavlMount(carportWidth);
        assertEquals(expected, result);
    }

    @Test
    public void testGetPlanksForGavlMountLength() {
        int expected = 116;
        int result = calculator.getPlanksForGavlMountLength(carportWidth, roofPitch);
        assertEquals(expected, result);
    }

    @Test (expected = AssertionError.class)
    public void testGetPlanksForGavlMountLengthNegative() {
        int expected = 75;
        int result = calculator.getPlanksForGavlMountLength(carportWidth, roofPitch);
        assertEquals(expected, result);
    }
    

}
