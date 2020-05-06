package PresentationLayer;

import DBAccess.MaterialMapper;
import FunctionLayer.*;
import FunctionLayer.Objects.Carport;
import FunctionLayer.Objects.CarportFlat;
import FunctionLayer.Objects.CarportPitch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


public class CarportDesign extends Command {
    private DataHelper helper = new DataHelper();
    private MaterialCalculator calcTest = new MaterialCalculator();


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        String tmpCarportLength = request.getParameter("length");
        String tmpCarportWidth = request.getParameter("width");
        String tmpCarportHeight = request.getParameter("height");
        int shedLength = 0;
        int shedWidth = 0;
        String roofMaterial = request.getParameter("roof");

        if (request.getParameter("checkboxShed") != null) {
            String tmpShedLength = request.getParameter("shedLength");
            String tmpShedWidth = request.getParameter("shedWidth");
            shedLength = Validation.getInteger(tmpShedLength);
            shedWidth = Validation.getInteger(tmpShedWidth);
            helper.setHasShed(true);
        }

        int carportLengthCM = Validation.getInteger(tmpCarportLength);
        int carportWidthCM = Validation.getInteger(tmpCarportWidth);
        int carportHeight = Validation.getInteger(tmpCarportHeight);


        if (request.getParameter("roofPitch") != null) {
            String tmpRoofPitch = request.getParameter("roofPitch");
            helper.setCarportPitch(Validation.getInteger(tmpRoofPitch));
            request.setAttribute("roof_pitch", Initialisation.getRoofPitch());
        }

        if (helper.getCarportPitch() > 0) {
            helper.setHasPitch(true);
        }

        if (shedWidth > carportWidthCM || shedLength > carportLengthCM) {
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
        helper.hasPitchAndShedInitArray();

        int finalPrice = calcTest.fullPrice(helper.getAllPriceIndexes());

        if (!helper.isInvalidInput()) {
            LogicFacade.addCarportToCustOrder(carportLengthCM, carportWidthCM, carportHeight, helper.isHasShed(), shedWidth, shedLength, helper.isHasPitch(), helper.getCarportPitch(), roofMaterial, finalPrice);
            ArrayList<Carport> tmpCart = new ArrayList<>();
            Cart cart = new Cart(tmpCart);
            Carport carport;
            if (helper.isHasPitch()) {
                carport = new CarportPitch(carportLengthCM, carportWidthCM, carportHeight, roofMaterial, helper.isHasShed(), shedWidth, shedLength, helper.isHasPitch(), helper.getCarportPitch());
            } else {
                carport = new CarportFlat(carportLengthCM, carportWidthCM, carportHeight, roofMaterial, helper.isHasShed(), shedWidth, shedLength);
            }
            cart.addToCart(tmpCart, carport);

           // System.out.println(cart.toString());
        }

        /*
        System.out.println("Info om rem:" + heads);
        System.out.println("Info om spær:" + rafts);
        System.out.println("Info om hulbånd:" + bands);
        System.out.println("Info om stolper: " + pillars);
        System.out.println("Info om understernsbrædder til for- og bagende: " + frontbackunderplanks);
        System.out.println("Info om understernsbrædder til siderne: " + sideunderplanks);
        System.out.println("Info om oversternsbræt til forenden: " + frontoverplanks);
        System.out.println("Info om oversternsbrædder til siderne: " + sideoverplanks);
        System.out.println("Info om vandbræt til siderne: " + sidewaterplanks);
        System.out.println("Info om vandbræt til forenden: " + frontwaterplanks);
        System.out.println("Info om tagplader: " + tiles);
        System.out.println("Info om bundskruer til tagplader: " + roofBottomScrew);
        System.out.println("Info om universal skruer til remmen: " + universalScrews);
        System.out.println("Info om skruer til stern og vandbrædt: " + plankWaterScrews);
        System.out.println("Info om skruer til beslag for spærg: " + bracketScrews);
        System.out.println("Info om skruer til montering af rem på stolper: " + carriageBolts);
        //Dette her er antal firkantskiver, men da det altid vil være samme antal som carriageBolts er det mere logisk og bruge den metode.
        System.out.println("Info om firkantskirver til montering af rem på stolper: " + squareWashers);
        System.out.println("Ekstra med skur: ");
        System.out.println("Info om lægte til bagside af døren: " + battern);
        System.out.println("Info om løsholte til siderne af skuret: " + transomSidesInfo);
        System.out.println("Info om løsholte til skurets gavle: " + transomFrontAndBackInfo);
        System.out.println("Info om rem i skuret: " + headsInShedInfo);
        System.out.println("Info om brædt til skurbeklædning: " + planksInShedInfo);
        System.out.println("Info om skruer til ydre beklædning på skuret: " + outerScrewsInfo);
        System.out.println("Info om skruer til indre beklædning på skuret: " + innerScrewsInfo);
        System.out.println("Info om stalddørsgreb: " + doorGribInfo);
        System.out.println("Info om t-hængsel til dør: " + tHingeInfo);
        System.out.println("Info om vinkelbeslag til løsholte: " + angleMountInfo);
        System.out.println("Ekstra med rejsninstag: ");
        System.out.println("Info om tagsten til rejsningstag: " + tilesPitchedRoof);
        System.out.println("Info om tagstens bindere og nakkekroge: " + tileBindersHooks);
        System.out.println("Info om rygsten: " + rooftileStones);
        System.out.println("Info om rygstens beslag: " + rooftileStoneBracketsInfo);
        System.out.println("Info om toplægte holdere: " + toplathHoldersInfo);
        System.out.println("Info om gavl (vindskeder): " + gavlPlankInfo);
        System.out.println("Info om gavl beklædning på tag: " + gavlPlankMountInfo);
        System.out.println("Info om taglægter: " + rooflathsInfo);
        System.out.println("Info om skruer til taglægter: " + rooflathScrewsInfo);
        System.out.println(finalPrice);
        */

        helper.test();

        if (helper.isHasPitch()) {
            return "rejsningtag";
        } else {
            return "fladttag";
        }


    }

}
