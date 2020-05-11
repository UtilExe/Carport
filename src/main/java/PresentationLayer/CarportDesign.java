package PresentationLayer;

import FunctionLayer.*;
import FunctionLayer.Objects.Carport;
import FunctionLayer.Objects.CarportFlat;
import FunctionLayer.Objects.CarportPitch;
import FunctionLayer.Objects.MaterialList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


public class CarportDesign extends Command {


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        String tmpCarportLength = request.getParameter("length");
        String tmpCarportWidth = request.getParameter("width");
        String tmpCarportHeight = request.getParameter("height");
        String roofMaterial = request.getParameter("roof");

        int carportLength = Validation.getInteger(tmpCarportLength);
        int carportWidthCM = Validation.getInteger(tmpCarportWidth);
        int carportHeight = Validation.getInteger(tmpCarportHeight);
        int shedLength = 0;
        int shedWidth = 0;
        int carportPitch = 0;
        String tmpShedLength;
        String tmpShedWidth;
        String tmpRoofPitch;

        MaterialCalculator calcTest = new MaterialCalculator();

        if (request.getParameter("checkboxShed") != null) {
            tmpShedLength = request.getParameter("shedLength");
            tmpShedWidth = request.getParameter("shedWidth");
            shedLength = Validation.getInteger(tmpShedLength);
            shedWidth = Validation.getInteger(tmpShedWidth);
        }

        if (request.getParameter("roofPitch") != null) {
            tmpRoofPitch = request.getParameter("roofPitch");
            carportPitch = Validation.getInteger(tmpRoofPitch);
            request.setAttribute("roof_pitch", Initialisation.getRoofPitch());
        }

        CarportHelper helper = new CarportHelper(carportLength, carportWidthCM, carportHeight, shedLength, shedWidth, carportPitch);

        if (helper.getShedWidth() > helper.getCarportWidthCM() || helper.getShedLength() > helper.getCarportLengthCM()) {
            request.setAttribute("fejl", "Skurrets mål er større end carporten! Prøv igen med korrekte værdier");
            helper.setInvalidInput(true);
        }

        request.setAttribute("carport_lengths", Initialisation.getCarportLengths());
        request.setAttribute("carport_widths", Initialisation.getCarportWidths());
        request.setAttribute("carport_heights", Initialisation.getCarportHeights());
        request.setAttribute("carport_roofs", Initialisation.getRoofs());
        request.setAttribute("shed_lengths", Initialisation.getShedLengths());
        request.setAttribute("shed_widths", Initialisation.getShedWidths());

        helper.initArrayList();

        int finalPrice = calcTest.fullPrice(helper.getAllPriceIndexes());

            Carport carport = null;
        if (!helper.isInvalidInput()) {
            LogicFacade.addCarportToCustOrder(helper.getCarportLengthCM(), helper.getCarportWidthCM(), helper.getCarportHeight(), helper.isHasShed(), helper.getShedWidth(), helper.getShedLength(), helper.isHasPitch(), helper.getCarportPitch(), roofMaterial, finalPrice);
            ArrayList<Carport> tmpCart = new ArrayList<>();
            Cart cart = new Cart(tmpCart);
            if (helper.isHasPitch()) {
                carport = new CarportPitch(helper.getCarportLengthCM(), helper.getCarportWidthCM(), helper.getCarportHeight(), roofMaterial, helper.isHasShed(), helper.getShedWidth(), helper.getShedLength(), helper.isHasPitch(), helper.getCarportPitch());
            } else {
                carport = new CarportFlat(helper.getCarportLengthCM(), helper.getCarportWidthCM(), helper.getCarportHeight(), roofMaterial, helper.isHasShed(), helper.getShedWidth(), helper.getShedLength());
            }
            cart.addToCart(tmpCart, carport);
        }

        request.setAttribute("materialList", helper.test(helper.isHasShed(), helper.isHasPitch()));
        request.setAttribute("finalPrice", finalPrice);

        Svg svg = new Svg(800, 600, "0,0,800,600",0,0);
        Svg svgInnerDrawing = new Svg(900,800,"0,0,900,800",0,0);
        svg.addRect(0,0,600,780);
        svg.addRect(0,35,4,780);
        svg.addRect(0,565,4,780);
        request.setAttribute("svgdrawing", svg.toString());
        System.out.println(svg.toString());


        return "tmpList";
    }

}
