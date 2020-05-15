package PresentationLayer;

import FunctionLayer.UniversalSampleException;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put( "login",              new Login()              );
        commands.put( "register",           new Register()           );
        commands.put( "redirect",           new Redirect()           );
        commands.put( "carportDesign",      new CarportDesign()      );
        commands.put( "approve",            new Approve()            );
        commands.put( "showplan",           new Plan()               );
        commands.put( "materialView",       new MaterialView()       );
    }

    static Command from( HttpServletRequest request ) {
        String targetName = request.getParameter( "target" );
        if ( commands == null ) {
            initCommands();
        }
        return commands.getOrDefault(targetName, new UnknownCommand() );   // unknowncommand er default.
    }


    abstract String execute( HttpServletRequest request, HttpServletResponse response ) 
            throws UniversalSampleException;

}
