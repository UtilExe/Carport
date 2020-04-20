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
            request.setAttribute("carport_lengths", Initialisation.getLengths());
            request.setAttribute("carport_widths", Initialisation.getWidths());
            request.setAttribute("carport_roofs", Initialisation.getRoofs());
        }

        return destination;
    }
}
