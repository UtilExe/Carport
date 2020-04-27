package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.User;

public class main {
    public static void main(String[] args) throws LoginSampleException {
        //User user = LogicFacade.createUser("test", "test@gmail.com", "test", 12345678);
        //System.out.println(user.getName());

        MaterialCalculator calcTest = new MaterialCalculator();
        double carportHeight = 180;
        int carportLengthCM = 780;
        int carportWidthCM = 600;
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
