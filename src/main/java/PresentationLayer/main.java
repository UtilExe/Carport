package PresentationLayer;

import DBAccess.MaterialMapper;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.User;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws LoginSampleException {
        //User user = LogicFacade.createUser("test", "test@gmail.com", "test", 12345678);
        //System.out.println(user.getName());
        double carportHeight = 180;
        int carportLengthCM = 780;
        int carportWidthCM = 600;
        final int AMOUNT_OF_HEADS = 2;
        int amountOfRafts;
        int bandLength;
        int[] band = new int[4];
        int packagesOfBand;



        MaterialCalculator calcTest = new MaterialCalculator();
        band = calcTest.calcBandAmount(carportLengthCM, carportWidthCM);
        bandLength = band[3];
        packagesOfBand = calcTest.getPackagesAmountBand(bandLength);



        amountOfRafts = calcTest.calcRaftAmount(carportLengthCM);

        ArrayList<String> a = MaterialMapper.getRemOrRaftData("spærtræ", carportLengthCM, AMOUNT_OF_HEADS );
        ArrayList<String> b = MaterialMapper.getRemOrRaftData("spærtræ", carportWidthCM, amountOfRafts);
        System.out.println(a);
        System.out.println(b);
        System.out.println(packagesOfBand);



        //System.out.println(calcTest.calcPillarAmount(carportLengthCM));
        //System.out.println(calcTest.calcRaftAmount(carportLengthCM));
        /*System.out.println();
        int[] result = calcTest.calcBandAmount(carportLengthCM, carportWidthCM);
        for (int i : result) {
            System.out.println(i);
        }*/

        calcTest.getStolper(carportHeight, carportLengthCM);
    }
}
