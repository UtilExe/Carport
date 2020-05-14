package PresentationLayer;

import FunctionLayer.UniversalSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnknownCommand extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws UniversalSampleException {
        String msg = "Unknown command. Contact IT";
        throw new UniversalSampleException( msg );
    }

}
