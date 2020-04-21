package PresentationLayer;

import FunctionLayer.Initialisation;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class FladtTag extends Command {
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
        Boolean hasShed = false;
        int price = 0;

        if (request.getParameter("checkboxShed") != null) {
            tmpShedLength = request.getParameter("shedLength");
            tmpShedWidth = request.getParameter("shedWidth");
            shedLength = Validation.getInteger(tmpShedLength);
            shedWidth = Validation.getInteger(tmpShedWidth);
            hasShed = true;
        }

        int carportLength = Validation.getInteger(tmpCarportLength);
        int carportWidth = Validation.getInteger(tmpCarportWidth);

        LogicFacade.addFlatCarportToCustOrder(carportLength, carportWidth, hasShed, shedWidth, shedLength, roofMaterial, price);

        request.setAttribute("carport_lengths", Initialisation.getCarportLengths());
        request.setAttribute("carport_widths", Initialisation.getCarportWidths());
        request.setAttribute("carport_roofs", Initialisation.getRoofs());
        request.setAttribute("shed_lengths", Initialisation.getShedLengths());
        request.setAttribute("shed_widths", Initialisation.getShedWidths());

        return "fladttag";
    }
}
