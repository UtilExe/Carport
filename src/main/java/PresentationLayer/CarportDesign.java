package PresentationLayer;

import FunctionLayer.*;
import FunctionLayer.Entities.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CarportDesign extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws UniversalSampleException {

        String tmpCarportLength = request.getParameter("length");
        String tmpCarportWidth = request.getParameter("width");
        String tmpCarportHeight = request.getParameter("height");
        String roofMaterial = request.getParameter("roof");
        String tmpTlfNumber = request.getParameter("tlfNumber");

        if(tmpCarportHeight.equals("") || tmpCarportLength.equals("") || tmpCarportWidth.equals("") || roofMaterial.equals("") || tmpTlfNumber.equals("")){
            request.setAttribute("error", "Alle felter blev ikke udfyldt.");

            return "index";
        }

        int carportLength = ValidationValues.getInteger(tmpCarportLength);
        int carportWidth = ValidationValues.getInteger(tmpCarportWidth);
        int carportHeight = ValidationValues.getInteger(tmpCarportHeight);
        int tlfNumber = ValidationValues.getInteger(tmpTlfNumber);
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

            if(tmpShedLength.equals("") || tmpShedWidth.equals("")){
                request.setAttribute("error", "Et af skurmålene blev ikke udfyldt.");
                return "index";
            }

            shedLength = ValidationValues.getInteger(tmpShedLength);
            shedWidth = ValidationValues.getInteger(tmpShedWidth);
        }

        if (request.getParameter("roofPitch") != null) {
            tmpRoofPitch = request.getParameter("roofPitch");

            if(tmpRoofPitch.equals("")){
                request.setAttribute("error", "Taghældning blev ikke udfyldt.");
                return "index";
            }
            carportPitch = ValidationValues.getInteger(tmpRoofPitch);
            request.setAttribute("roof_pitch", Initialisation.getRoofPitch());
        }

        CarportHelper helper = new CarportHelper(carportLength, carportWidth, carportHeight, shedLength, shedWidth, carportPitch);

        //Vi minusser med 70 for at tage højde for at skuret ikke må gå ud over remmene og 30 for at gøre op for afstanden bagerst.
        if (helper.getShedWidth() > helper.getCarportWidth()-70 || helper.getShedLength() > helper.getCarportLength()-30) {
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

            Carport carport = null;
        if (!helper.isInvalidInput()) {
            OrderFacade.addCarportToCustOrder(helper.getCarportLength(), helper.getCarportWidth(), helper.getCarportHeight(), helper.isHasShed(), helper.getShedWidth(), helper.getShedLength(), helper.isHasPitch(), helper.getCarportPitch(), roofMaterial, finalPrice, tlfNumber);
            if (helper.isHasPitch()) {
                carport = new CarportPitch(helper.getCarportLength(), helper.getCarportWidth(), helper.getCarportHeight(), roofMaterial, helper.isHasShed(), helper.getShedWidth(), helper.getShedLength(), helper.isHasPitch(), helper.getCarportPitch());
            } else {
                carport = new CarportFlat(helper.getCarportLength(), helper.getCarportWidth(), helper.getCarportHeight(), roofMaterial, helper.isHasShed(), helper.getShedWidth(), helper.getShedLength());
            }
        }

        request.setAttribute("materialList", helper.createMaterialList(helper.isHasShed(), helper.isHasPitch()));
        request.setAttribute("finalPrice", finalPrice);

        String svgDrawing = helper.svgDrawingTop(carportLength, carportWidth, helper.isHasShed());
        String svgDrawingFront = helper.svgDrawingSide(carportLength, carportWidth, carportHeight, helper.isHasShed());

        request.setAttribute("svgdrawing", svgDrawing);
        request.setAttribute("svgdrawingfront", svgDrawingFront);

        return "carportOutput";
    }


}
