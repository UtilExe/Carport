package PresentationLayer;

import FunctionLayer.Initialisation;
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

        return destination;
    }
}
