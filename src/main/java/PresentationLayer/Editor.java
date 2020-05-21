package PresentationLayer;

import DBAccess.OrderMapper;
import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Editor extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws UniversalSampleException {
        String measure = request.getParameter("measure");
        String tmpAmount = request.getParameter("amount");
        int orderID = ValidationValues.getInteger(request.getParameter("orderID"));

        if(!tmpAmount.equals("")) {
            int amount = ValidationValues.getInteger(tmpAmount);
            OrderFacade.editOrder(orderID, measure, amount);
        }

        Initialisation.initOrders();
        request.setAttribute("orders", Initialisation.getOrders());
        return "admin";
    }
}
