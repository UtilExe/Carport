package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;


public class CarportDesign extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        HttpSession session = request.getSession();
        String tmpCarportLength = request.getParameter("length");
        String tmpCarportWidth = request.getParameter("width");
        String tmpCarportHeight = request.getParameter("height");
        String tmpShedLength = "";
        int shedLength = 0;
        String tmpShedWidth = "";
        int shedWidth = 0;
        String roofMaterial = request.getParameter("roof");
        boolean hasShed = false;
        boolean roofIsFlat = false;
        int price = 0;
        int roofPitch = 0;
        boolean invalidInput = false;

        if (request.getParameter("checkboxShed") != null) {
            tmpShedLength = request.getParameter("shedLength");
            tmpShedWidth = request.getParameter("shedWidth");
            shedLength = Validation.getInteger(tmpShedLength);
            shedWidth = Validation.getInteger(tmpShedWidth);
            hasShed = true;
        }

        int carportLength = Validation.getInteger(tmpCarportLength);
        int carportWidth = Validation.getInteger(tmpCarportWidth);
        int carportHeight = Validation.getInteger(tmpCarportHeight);
        if (request.getParameter("roofPitch") != null) {
            String tmpRoofPitch = request.getParameter("roofPitch");
            roofPitch = Validation.getInteger(tmpRoofPitch);
            request.setAttribute("roof_pitch", Initialisation.getRoofPitch());
        }

        if (roofPitch <= 0) {
            roofIsFlat = true;
        }

        if (shedWidth > carportWidth || shedLength > carportLength) {
            request.setAttribute("fejl", "Skurrets bredde er større end carporten! Prøv igen med korrekte værdier");
            invalidInput = true;
        }

        if (invalidInput != true) {
            LogicFacade.addCarportToCustOrder(carportLength, carportWidth, carportHeight, hasShed, shedWidth, shedLength, roofIsFlat, roofPitch, roofMaterial, price);
            ArrayList<Carport> tmpCart = new ArrayList<>();
            Cart cart = new Cart(tmpCart);
            Carport carport = new Carport(carportLength, carportWidth, carportHeight, hasShed, shedWidth, shedLength, roofIsFlat, roofPitch, roofMaterial);
            cart.addToCart(tmpCart, carport);
            System.out.println(cart.toString());
        }

        request.setAttribute("carport_lengths", Initialisation.getCarportLengths());
        request.setAttribute("carport_widths", Initialisation.getCarportWidths());
        request.setAttribute("carport_heights", Initialisation.getCarportHeights());
        request.setAttribute("carport_roofs", Initialisation.getRoofs());
        request.setAttribute("shed_lengths", Initialisation.getShedLengths());
        request.setAttribute("shed_widths", Initialisation.getShedWidths());

        if (roofIsFlat) {
            return "fladttag";
        } else {
            return "rejsningtag";
        }
    }
}
