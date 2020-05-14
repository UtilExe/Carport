package FunctionLayer;


import DBAccess.UserMapper;
import FunctionLayer.Entities.User;

import java.util.ArrayList;

public class LogicFacade {

    public static User login(String email, String password ) throws LoginSampleException {
        return UserMapper.login(email, password);
    } 

    public static User createUser(String name, String email, String password, int mobilNr) throws RegisterSampleException {
        User user = new User(name, email, password, mobilNr);
        UserMapper.createUser(user);
        return user;
    }



}
