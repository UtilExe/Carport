package PresentationLayer;

import FunctionLayer.CarportHelper;
import FunctionLayer.OrderFacade;
import FunctionLayer.UniversalSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MaterialView extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws UniversalSampleException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));

        CarportHelper helper = OrderFacade.getHelper(orderID);
        request.setAttribute("materialView", helper.createMaterialList(helper.isHasShed(), helper.isHasPitch()));
        request.setAttribute("showMaterials", true);

        return "admin";
    }
}
