package PresentationLayer;

import FunctionLayer.*;
import FunctionLayer.Objects.Carport;
import FunctionLayer.Objects.CarportFlat;
import FunctionLayer.Objects.CarportPitch;
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

        int test1 = Integer.parseInt(tmpCarportLength);
        int test2 = Integer.parseInt(tmpCarportWidth);
        int test3 = Integer.parseInt(tmpCarportHeight);

        MaterialCalculator calcTest = new MaterialCalculator();

        CarportHelper helper = new CarportHelper(test1, test2, test3);

        if (request.getParameter("checkboxShed") != null) {
            String tmpShedLength = request.getParameter("shedLength");
            String tmpShedWidth = request.getParameter("shedWidth");
            helper.setShedLength(Validation.getInteger(tmpShedLength));
            helper.setShedWidth(Validation.getInteger(tmpShedWidth));
            helper.setHasShed(true);
        }

        // Problem: ArrayListerne i DataHelper bliver initialiseret før, at disse bliver kaldt,
        // når DataHelper objektet bliver oprettet
        /*helper.setCarportLengthCM(Validation.getInteger(tmpCarportLength));
        helper.setCarportWidthCM(Validation.getInteger(tmpCarportWidth));
        helper.setCarportHeight(Validation.getInteger(tmpCarportHeight));

         */


        if (request.getParameter("roofPitch") != null) {
            String tmpRoofPitch = request.getParameter("roofPitch");
            helper.setCarportPitch(Validation.getInteger(tmpRoofPitch));
            request.setAttribute("roof_pitch", Initialisation.getRoofPitch());
        }

        if (helper.getCarportPitch() > 0) {
            helper.setHasPitch(true);
        }

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

        if (!helper.isInvalidInput()) {
            LogicFacade.addCarportToCustOrder(helper.getCarportLengthCM(), helper.getCarportWidthCM(), helper.getCarportHeight(), helper.isHasShed(), helper.getShedWidth(), helper.getShedLength(), helper.isHasPitch(), helper.getCarportPitch(), roofMaterial, finalPrice);
            ArrayList<Carport> tmpCart = new ArrayList<>();
            Cart cart = new Cart(tmpCart);
            Carport carport;
            if (helper.isHasPitch()) {
                carport = new CarportPitch(helper.getCarportLengthCM(), helper.getCarportWidthCM(), helper.getCarportHeight(), roofMaterial, helper.isHasShed(), helper.getShedWidth(), helper.getShedLength(), helper.isHasPitch(), helper.getCarportPitch());
            } else {
                carport = new CarportFlat(helper.getCarportLengthCM(), helper.getCarportWidthCM(), helper.getCarportHeight(), roofMaterial, helper.isHasShed(), helper.getShedWidth(), helper.getShedLength());
            }
            cart.addToCart(tmpCart, carport);

           // System.out.println(cart.toString());
        }

        System.out.println("DEEEEEEEBUG:" + finalPrice);

        helper.test();

        if (helper.isHasPitch()) {
            return "rejsningtag";
        } else {
            return "fladttag";
        }


    }

}
