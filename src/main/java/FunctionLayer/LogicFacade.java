package FunctionLayer;

import DBAccess.CarportMapper;
import DBAccess.UserMapper;

import java.util.ArrayList;

public class LogicFacade {

    public static User login( String email, String password ) throws LoginSampleException {
        return UserMapper.login( email, password );
    } 

    public static User createUser() throws LoginSampleException {
        tmpUser user = (username, email, password, mobilNr);
        UserMapper.createUser(user);
        return user;
    }

    public static ArrayList<Integer> getCarportLength() {
        ArrayList<Integer> carportLength = CarportMapper.getCarportMeasure("længde");
        return carportLength;
    }

    public static ArrayList<Integer> getCarportWidth() {
        ArrayList<Integer> carportWidth = CarportMapper.getCarportMeasure("bredde");
        return carportWidth;
    }

    public static ArrayList<String> getCarportRoof() {
        ArrayList<String> carportRoof = CarportMapper.getCarportRoof();
        return carportRoof;
    }

    public static ArrayList<Integer> getShedLength() {
        ArrayList<Integer> shedLength = CarportMapper.getShedMeasure("længde");
        return shedLength;
    }

    public static ArrayList<Integer> getShedWidth() {
        ArrayList<Integer> shedWidth = CarportMapper.getShedMeasure("bredde");
        return shedWidth;
    }

    public static ArrayList<Integer> getRoofPitch() {
        ArrayList<Integer> roofPitch = CarportMapper.getRoofPitch();
        return roofPitch;
    }

}
