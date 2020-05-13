package DBAccess;

import FunctionLayer.CarportHelper;
import FunctionLayer.Entities.Carport;
import FunctionLayer.Entities.Order;

import java.sql.*;
import java.util.ArrayList;

public class OrderMapper {

    public static ArrayList<Order> getOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM carport.cust_order WHERE approved != 1;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                //orderID, carport_length, carport_width, carport_height, hasShed, shedWidth, shedLength, hasPitch, roof_pitch, roof_material, price, approved
                int orderID = rs.getInt("orderID");
                int carportLength = rs.getInt("carport_length");
                int carportWidth = rs.getInt("carport_width");
                int carportHeight = rs.getInt("carport_height");
                boolean hasShed = rs.getBoolean("hasShed");
                int shedWidth = rs.getInt("shedWidth");
                int shedLength = rs.getInt("shedLength");
                boolean hasPitch = rs.getBoolean("hasPitch");
                int roofPitch = rs.getInt("roof_pitch");
                String roofMaterial = rs.getString("roof_material");
                int price = rs.getInt("price");
                boolean approved = rs.getBoolean("approved");
                Carport carport = new Carport(carportLength, carportWidth, carportHeight, roofMaterial);
                Order order = new Order(orderID, carport, hasShed, shedWidth, shedLength, hasPitch, roofPitch, price, approved);
                orders.add(order);
            }
        } catch(SQLException | ClassNotFoundException ex) {
            ex.getMessage();
        }
        return orders;
    }

    public static void approve(int orderID) {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE carport.cust_order SET approved=1 WHERE orderID=?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orderID);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.getMessage();
        }
    }

    public static void removeOrder(int orderID) {
        try {
            Connection con = Connector.connection();
            String SQL = "DELETE FROM carport.cust_order WHERE orderID=?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orderID);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.getMessage();
        }
    }

    public static CarportHelper getHelper(int orderID) {
        CarportHelper helper = null;
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT carport_length, carport_width, carport_height, shedWidth, shedLength, roof_pitch FROM carport.cust_order WHERE orderID=?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int carportLengthCM = rs.getInt("carport_length");
                int carportWidthCM = rs.getInt("carport_width");
                int carportHeight = rs.getInt("carport_height");
                int shedWidth = rs.getInt("shedWidth");
                int shedLength = rs.getInt("shedLength");
                int carportPitch = rs.getInt("roof_pitch");
                helper = new CarportHelper(carportLengthCM, carportWidthCM, carportHeight, shedLength, shedWidth, carportPitch);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.getMessage();
        }
        return helper;
    }

}
