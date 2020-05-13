package PresentationLayer;

import FunctionLayer.*;
import FunctionLayer.Entities.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CarportDesign extends Command {


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        String tmpCarportLength = request.getParameter("length");
        String tmpCarportWidth = request.getParameter("width");
        String tmpCarportHeight = request.getParameter("height");
        String roofMaterial = request.getParameter("roof");

        int carportLength = ValidationValues.getInteger(tmpCarportLength);
        int carportWidthCM = ValidationValues.getInteger(tmpCarportWidth);
        int carportHeight = ValidationValues.getInteger(tmpCarportHeight);
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
            shedLength = ValidationValues.getInteger(tmpShedLength);
            shedWidth = ValidationValues.getInteger(tmpShedWidth);
        }

        if (request.getParameter("roofPitch") != null) {
            tmpRoofPitch = request.getParameter("roofPitch");
            carportPitch = ValidationValues.getInteger(tmpRoofPitch);
            request.setAttribute("roof_pitch", Initialisation.getRoofPitch());
        }

        CarportHelper helper = new CarportHelper(carportLength, carportWidthCM, carportHeight, shedLength, shedWidth, carportPitch);

        //TODO  Vi minusser med 70 for at tage højde for at skuret ikke må gå ud over remmene
        if (helper.getShedWidth() > helper.getCarportWidthCM()-70 || helper.getShedLength() > helper.getCarportLengthCM()-30) {
            request.setAttribute("fejl", "Skurrets mål er for store i forhold til carporten! Prøv igen med korrekte værdier");
            helper.setInvalidInput(true);

            request.setAttribute("carport_lengths", Initialisation.getCarportLengths());
            request.setAttribute("carport_widths", Initialisation.getCarportWidths());
            request.setAttribute("carport_heights", Initialisation.getCarportHeights());
            request.setAttribute("carport_roofs", Initialisation.getRoofs());
            request.setAttribute("shed_lengths", Initialisation.getShedLengths());
            request.setAttribute("shed_widths", Initialisation.getShedWidths());

            if(helper.isHasPitch()) {
                return "rejsningstag";
            } else {
                return "fladttag";
            }
        }

        helper.initArrayList();

        int finalPrice = calcTest.fullPrice(helper.getAllPriceIndexes());

        // hvad skal carport bruges til, bliver ikke brugt lige nu.
            Carport carport = null;
        if (!helper.isInvalidInput()) {
            LogicFacade.addCarportToCustOrder(helper.getCarportLengthCM(), helper.getCarportWidthCM(), helper.getCarportHeight(), helper.isHasShed(), helper.getShedWidth(), helper.getShedLength(), helper.isHasPitch(), helper.getCarportPitch(), roofMaterial, finalPrice);
            if (helper.isHasPitch()) {
                carport = new CarportPitch(helper.getCarportLengthCM(), helper.getCarportWidthCM(), helper.getCarportHeight(), roofMaterial, helper.isHasShed(), helper.getShedWidth(), helper.getShedLength(), helper.isHasPitch(), helper.getCarportPitch());
            } else {
                carport = new CarportFlat(helper.getCarportLengthCM(), helper.getCarportWidthCM(), helper.getCarportHeight(), roofMaterial, helper.isHasShed(), helper.getShedWidth(), helper.getShedLength());
            }
        }

        request.setAttribute("materialList", helper.test(helper.isHasShed(), helper.isHasPitch()));
        request.setAttribute("finalPrice", finalPrice);


        String svgDrawing = helper.svgDrawing(carportLength, carportWidthCM, helper.isHasShed());
        String svgDrawingFront = helper.svgDrawingFront(carportLength, carportHeight, helper.isHasShed());

        request.setAttribute("svgdrawing", svgDrawing);
        request.setAttribute("svgdrawingfront", svgDrawingFront);


        return "tmpList";
    }

}
