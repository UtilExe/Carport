package PresentationLayer;

import FunctionLayer.Entities.Order;
import FunctionLayer.Initialisation;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Approve extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        String str = request.getParameter("godkend");
        String str2 = request.getParameter("afvis");

        int orderID = Integer.parseInt(request.getParameter("orderID"));

        if (str != null) {
            OrderFacade.approve(orderID);
        } else if (str2 != null) {
            OrderFacade.removeOrder(orderID);
        }

        Initialisation.initOrders();
        request.setAttribute("orders", Initialisation.getOrders());

        return "admin";
    }
}
