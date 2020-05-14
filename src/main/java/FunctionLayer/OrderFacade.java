package FunctionLayer;


import DBAccess.OrderMapper;
import FunctionLayer.Entities.Order;

import java.util.ArrayList;

public class OrderFacade {

    public static void addCarportToCustOrder(int carportLength, int carportWidth, int carportHeight, boolean hasShed, int shedWidth, int shedLength, boolean hasPitch, int roofPitch, String roofMaterial, int price) throws OrderSampleException {
        OrderMapper.addCarportToCustOrder(carportLength, carportWidth, carportHeight, hasShed, shedWidth, shedLength, hasPitch, roofPitch, roofMaterial, price);
    }

    public static ArrayList<Order> getOrders() throws OrderSampleException {
        return OrderMapper.getOrders();
    }

    public static void approve(int orderID) throws OrderSampleException {
        OrderMapper.approve(orderID);
    }

    public static void removeOrder(int orderID) throws OrderSampleException { OrderMapper.removeOrder(orderID);}

    public static CarportHelper getHelper(int orderID) throws OrderSampleException { return OrderMapper.getHelper(orderID); }

}
