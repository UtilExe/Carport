package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.User;

public class main {
    public static void main(String[] args) throws LoginSampleException {
        User user = LogicFacade.createUser("test", "test@gmail.com", "test", 12345678);
        System.out.println(user.getName());

    }
}
