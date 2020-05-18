package PresentationLayer;

import FunctionLayer.CarportHelper;
import FunctionLayer.Initialisation;
import FunctionLayer.OrderFacade;
import FunctionLayer.UniversalSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Daniel, Emil, Jannich, Jimmy
 * MaterialView klassen håndterer at materiale-listen kan blive vist på admin-siden.
 */

public class MaterialView extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws UniversalSampleException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));

        CarportHelper helper = OrderFacade.getHelper(orderID);
        request.setAttribute("materialView", helper.createMaterialList(helper.isHasShed(), helper.isHasPitch()));
        request.setAttribute("showMaterials", true);

        Initialisation.initOrders();
        request.setAttribute("orders", Initialisation.getOrders());

        return "admin";
    }
}
