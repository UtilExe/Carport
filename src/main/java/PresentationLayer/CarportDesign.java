package PresentationLayer;

import FunctionLayer.Initialisation;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class CarportDesign extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        HttpSession session = request.getSession();
        String tmpCarportLength = request.getParameter("length");
        String tmpCarportWidth = request.getParameter("width");
        String tmpShedLength = "";
        int shedLength = 0;
        String tmpShedWidth = "";
        int shedWidth = 0;
        String roofMaterial = request.getParameter("roof");
        boolean hasShed = false;
        boolean roofIsFlat = false;
        int price = 0;
        int roofPitch = 0;

        if (request.getParameter("checkboxShed") != null) {
            tmpShedLength = request.getParameter("shedLength");
            tmpShedWidth = request.getParameter("shedWidth");
            shedLength = Validation.getInteger(tmpShedLength);
            shedWidth = Validation.getInteger(tmpShedWidth);
            hasShed = true;
        }

        int carportLength = Validation.getInteger(tmpCarportLength);
        int carportWidth = Validation.getInteger(tmpCarportWidth);
        if (request.getParameter("roofPitch") != null) {
            String tmpRoofPitch = request.getParameter("roofPitch");
            roofPitch = Validation.getInteger(tmpRoofPitch);
            request.setAttribute("roof_pitch", Initialisation.getRoofPitch());
        }

        if (roofPitch <= 0) {
            roofIsFlat = true;
        }

        LogicFacade.addCarportToCustOrder(carportLength, carportWidth, hasShed, shedWidth, shedLength, roofIsFlat, roofPitch, roofMaterial, price);

        request.setAttribute("carport_lengths", Initialisation.getCarportLengths());
        request.setAttribute("carport_widths", Initialisation.getCarportWidths());
        request.setAttribute("carport_roofs", Initialisation.getRoofs());
        request.setAttribute("shed_lengths", Initialisation.getShedLengths());
        request.setAttribute("shed_widths", Initialisation.getShedWidths());

        if (roofIsFlat) {
            return "fladttag";
        }
        return "rejsningtag";
    }
}
