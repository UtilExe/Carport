package PresentationLayer;

import FunctionLayer.Initialisation;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Redirect extends Command{


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        String destination = request.getParameter("destination");

        if(destination.equals("fladttag")) {
            request.setAttribute("carport_lengths", Initialisation.getCarportLengths());
            request.setAttribute("carport_widths", Initialisation.getCarportWidths());
            request.setAttribute("carport_roofs", Initialisation.getRoofs());
            request.setAttribute("shed_lengths", Initialisation.getShedLengths());
            request.setAttribute("shed_widths", Initialisation.getShedWidths());
        }

        return destination;
    }
}
