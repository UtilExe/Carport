package PresentationLayer;

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
        System.out.println(tmpCarportLength + "    " + tmpCarportWidth);
        String tmpShedLength = "";
        int shedLength = 0;
        String tmpShedWidth = "";
        int shedWidth = 0;
        String roofMaterial = request.getParameter("roof");
        Boolean hasShed = false;
        int price = 0;

        /*if(request.getParameter("checkboxShed").equals("on")) {
            tmpShedLength = request.getParameter("shedLength");
            tmpShedWidth = request.getParameter("shedWidth");
            shedLength = Validation.getInteger(tmpShedLength);
            shedWidth = Validation.getInteger(tmpShedWidth);
            hasShed = true;
        }*/

        int carportLength = Validation.getInteger(tmpCarportLength);
        int carportWidth = Validation.getInteger(tmpCarportWidth);

        LogicFacade.addFlatCarportToCustOrder(carportLength, carportWidth, hasShed, shedWidth, shedLength, roofMaterial, price);

        return "fladttag";
    }
}
