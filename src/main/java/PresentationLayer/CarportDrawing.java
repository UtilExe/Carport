package PresentationLayer;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Objects.Carport;
import FunctionLayer.Svg;
import FunctionLayer.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CarportDrawing extends Command {


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        Carport carport = (Carport) request.getAttribute("carportLength");

        int carportLength = carport.getCarportLength();
        int carportWidth = carport.getCarportWidth();
        int carportHeight = carport.getCarportHeight();


        Svg svg = new Svg(carportLength, carportWidth, "0,0,800,600",0,0);
        Svg svgInnerDrawing = new Svg(900,800,"0,0,900,800",0,0);
        svg.addRect(0,0,600,780);
        svg.addRect(0,35,4,780);
        svg.addRect(0,565,4,780);
        request.setAttribute("svgdrawing", svg.toString());
        System.out.println(svg.toString());
        return "tmpList";

    }
}
