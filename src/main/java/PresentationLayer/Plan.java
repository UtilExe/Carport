package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Plan extends Command{
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws UniversalSampleException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));

        CarportHelper helper = OrderFacade.getHelper(orderID);
        String svgDrawing = helper.svgDrawing(helper.getCarportLengthCM(), helper.getCarportWidthCM(), helper.isHasShed());
        String svgDrawingFront = helper.svgDrawingFront(helper.getCarportLengthCM(), helper.getCarportHeight(), helper.isHasShed());
        request.setAttribute("drawingAbove", svgDrawing);
        request.setAttribute("drawingSide", svgDrawingFront);
        request.setAttribute("showMaterials", false);

        return "admin";
    }
}
