package FunctionLayer;


import DBAccess.OrderMapper;
import FunctionLayer.Entities.Order;

import java.util.ArrayList;

public class OrderFacade {

    public static void addCarportToCustOrder(int carportLength, int carportWidth, int carportHeight, boolean hasShed, int shedWidth, int shedLength, boolean hasPitch, int roofPitch, String roofMaterial, int price) {
        OrderMapper.addCarportToCustOrder(carportLength, carportWidth, carportHeight, hasShed, shedWidth, shedLength, hasPitch, roofPitch, roofMaterial, price);
    }

    public static ArrayList<Order> getOrders() {
        return OrderMapper.getOrders();
    }

    public static void approve(int orderID) {
        OrderMapper.approve(orderID);
    }

    public static void removeOrder(int orderID) { OrderMapper.removeOrder(orderID);}

    public static CarportHelper getHelper(int orderID) { return OrderMapper.getHelper(orderID); }

}
