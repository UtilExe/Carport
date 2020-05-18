package PresentationLayer;

import FunctionLayer.Initialisation;
import FunctionLayer.LogicFacade;
import FunctionLayer.Entities.User;
import FunctionLayer.UniversalSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Daniel, Emil, Jannich, Jimmy
 * Login klassen håndterer et brugerlogin/admin-login, med brugerens angive email og password, der bliver hentet.
 */

public class Login extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws UniversalSampleException {
        String email = request.getParameter( "email" );
        String password = request.getParameter( "password" );
        User user = LogicFacade.login(email, password);

        HttpSession session = request.getSession();

        session.setAttribute("email", email);
        if(email.equals("admin@admin.com")) {
            Initialisation.initOrders();
            request.setAttribute("orders", Initialisation.getOrders());
            return "admin";
        }

        return "index";
    }

}
