package PresentationLayer;

import FunctionLayer.UniversalSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnknownCommand extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws UniversalSampleException {
        String msg = "Der skete en Fejl. Kontakt IT-afdelingen.";
        throw new UniversalSampleException( msg );
    }

}
