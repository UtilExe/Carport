package PresentationLayer;

import FunctionLayer.Entities.Order;
import FunctionLayer.Initialisation;
import FunctionLayer.OrderFacade;
import FunctionLayer.UniversalSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Daniel, Emil, Jannich, Jimmy
 * Redirect klassen står for at håndtere initialisering når man tilgår en bestemt destination.
 */

public class Redirect extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws UniversalSampleException {
        String destination = request.getParameter("destination");

        if(destination.equals("fladttag")) {
            request.setAttribute("carport_lengths", Initialisation.getCarportLengths());
            request.setAttribute("carport_widths", Initialisation.getCarportWidths());
            request.setAttribute("carport_heights", Initialisation.getCarportHeights());
            request.setAttribute("carport_roofs", Initialisation.getRoofs());
            request.setAttribute("shed_lengths", Initialisation.getShedLengths());
            request.setAttribute("shed_widths", Initialisation.getShedWidths());
        }
        if(destination.equals("rejsningtag")) {
            request.setAttribute("carport_lengths", Initialisation.getCarportLengths());
            request.setAttribute("carport_widths", Initialisation.getCarportWidths());
            request.setAttribute("carport_heights", Initialisation.getCarportHeights());
            request.setAttribute("carport_roofs", Initialisation.getRoofs());
            request.setAttribute("shed_lengths", Initialisation.getShedLengths());
            request.setAttribute("shed_widths", Initialisation.getShedWidths());
            request.setAttribute("roof_pitch", Initialisation.getRoofPitch());
        }

        if(destination.equals("searchOrder") && request.getParameter("orderID") != null) {
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            request.setAttribute("orderID", orderID);
        }

        if(destination.equals("searchOrder")) {
            //TODO Lav egen extender eller fix det på en smartere måde uden at lave flere klasser
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            Order order = OrderFacade.getOrder(orderID);
            request.setAttribute("order", order);
        }

        return destination;
    }
}
