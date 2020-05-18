package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.Entities.User;
import FunctionLayer.UniversalSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Daniel, Emil, Jannich, Jimmy
 * Register klassen står for at den besøgende kan oprette en bruger.
 */

public class Register extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws UniversalSampleException {
        String name = request.getParameter( "name" );
        String email = request.getParameter( "email" );
        String password = request.getParameter( "password" );
        String tmpMobilNr = (request.getParameter("mobilNr")).replace(" ", "");
        int mobilNr = 0;

        if(!(tmpMobilNr.equals(""))) {
            mobilNr = Integer.parseInt(tmpMobilNr);
        } else {
            request.setAttribute("createAccountBesked1", "Du har ikke udfyldt alle felter");
            return "createAccount";
        }

        if(name.equals("") | email.equals("") | password.equals("") | mobilNr == 0) {
            request.setAttribute("createAccountBesked1", "Du har ikke udfyldt alle felter");
            return "createAccount";
        }

        try {
            User user = LogicFacade.createUser(name, email, password, mobilNr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        request.setAttribute("createAccountBesked2", "Du er nu oprettet!");

        return "createAccount";
    }
}
