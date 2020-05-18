package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Daniel, Emil, Jannich, Jimmy
 * Plan klassen står for at vise skitserne på admin-siden ud fra Ordre ID.
 */

public class Plan extends Command{
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws UniversalSampleException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));

        CarportHelper helper = OrderFacade.getHelper(orderID);
        String svgDrawing = helper.svgDrawingTop(helper.getCarportLength(), helper.getCarportWidth(), helper.isHasShed());
        String svgDrawingFront = helper.svgDrawingSide(helper.getCarportLength(), helper.getCarportWidth(), helper.getCarportHeight(), helper.isHasShed());
        request.setAttribute("drawingAbove", svgDrawing);
        request.setAttribute("drawingSide", svgDrawingFront);
        request.setAttribute("showMaterials", false);

        Initialisation.initOrders();
        request.setAttribute("orders", Initialisation.getOrders());

        return "admin";
    }
}
