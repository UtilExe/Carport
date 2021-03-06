package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Daniel, Emil, Jannich, Jimmy
 * Approve klassen som nedarver fra Command står for at funktionen godkend og afvis fungerer på admin-siden.
 */

public class Approve extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws UniversalSampleException {

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
