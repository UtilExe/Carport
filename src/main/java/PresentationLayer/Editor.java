package PresentationLayer;

import DBAccess.OrderMapper;
import FunctionLayer.Initialisation;
import FunctionLayer.LogicFacade;
import FunctionLayer.OrderFacade;
import FunctionLayer.UniversalSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Editor extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws UniversalSampleException {
        String measure = request.getParameter("measure");
        int amount = Integer.parseInt(request.getParameter("amount"));
        int orderID = Integer.parseInt(request.getParameter("orderID"));

        OrderFacade.editOrder(orderID, measure, amount);

        Initialisation.initOrders();
        request.setAttribute("orders", Initialisation.getOrders());
        return "admin";
    }
}
