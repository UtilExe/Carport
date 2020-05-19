package PresentationLayer;

import FunctionLayer.Entities.Order;
import FunctionLayer.Initialisation;
import FunctionLayer.OrderFacade;
import FunctionLayer.UniversalSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Searcher extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws UniversalSampleException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        Order order = OrderFacade.getOrder(orderID);
        request.setAttribute("order", order);
        Initialisation.initOrders();
        request.setAttribute("orders", Initialisation.getOrders());
        return "searchOrder";
    }
}
