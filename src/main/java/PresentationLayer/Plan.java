package PresentationLayer;

import FunctionLayer.CarportHelper;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Plan extends Command{
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));

        CarportHelper helper = LogicFacade.getHelper(orderID);
        String svgDrawing = helper.svgDrawing(helper.getCarportLengthCM(), helper.getCarportWidthCM(), helper.isHasShed());
        request.setAttribute("testDrawing", svgDrawing);
        return "admin";
    }
}
