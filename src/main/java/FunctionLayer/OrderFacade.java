package FunctionLayer;

import DBAccess.OrderMapper;
import FunctionLayer.Entities.Order;

import java.util.ArrayList;

/**
 * @author Daniel, Emil, Jannich, Jimmy
 * OrderFacade håndterer processen mellem Præsentationslaget, Funktionslaget og Database-niveau for Ordre ift. tilføjelse, godkendelse mv.
 */

public class OrderFacade {

    public static void addCarportToCustOrder(int carportLength, int carportWidth, int carportHeight, boolean hasShed, int shedWidth, int shedLength, boolean hasPitch, int roofPitch, String roofMaterial, int price, int tlfNumber) throws UniversalSampleException {
        OrderMapper.addCarportToCustOrder(carportLength, carportWidth, carportHeight, hasShed, shedWidth, shedLength, hasPitch, roofPitch, roofMaterial, price, tlfNumber);
    }

    public static ArrayList<Order> getOrders() throws UniversalSampleException {
        return OrderMapper.getOrdersThatAreNotApproved();
    }

    public static void approve(int orderID) throws UniversalSampleException {
        OrderMapper.approve(orderID);
    }

    public static void removeOrder(int orderID) throws UniversalSampleException {
        OrderMapper.removeOrder(orderID);
    }

    public static CarportHelper getHelper(int orderID) throws UniversalSampleException {
        return OrderMapper.getHelper(orderID);
    }

    public static void editOrder(int orderID, String measure, int amount) {
        OrderMapper.editOrder(orderID, measure, amount);
    }

}
