package DBAccess;

import FunctionLayer.CarportHelper;
import FunctionLayer.Entities.Carport;
import FunctionLayer.Entities.Order;
import FunctionLayer.UniversalSampleException;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Daniel, Emil, Jannich, Jimmy
 * OrderMapper klassen bruges til at håndtere bestilling/ordre af en Carport fra brugeren på database-niveau.
 */

public class OrderMapper {

    public static ArrayList<Order> getOrdersThatAreNotApproved() throws UniversalSampleException {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM cust_order WHERE approved != 1;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
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
                int tlfNumber = rs.getInt("tlf_number");
                Carport carport = new Carport(carportLength, carportWidth, carportHeight, roofMaterial);
                Order order = new Order(orderID, carport, hasShed, shedWidth, shedLength, hasPitch, roofPitch, price, approved, tlfNumber);
                orders.add(order);
            }
        } catch(SQLException | ClassNotFoundException ex) {

            String methodName = "getOrders";
            UniversalSampleException.exceptionIfsDB(ex.getMessage(), methodName);
            UniversalSampleException.exceptionIfLast(ex.getMessage(), methodName);

        }
        return orders;
    }

    public static void approve(int orderID) throws UniversalSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE cust_order SET approved=1 WHERE orderID=?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orderID);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            String methodName = "approve";
            UniversalSampleException.exceptionIfsDB(ex.getMessage(), methodName);
            UniversalSampleException.exceptionIfLast(ex.getMessage(), methodName);

        }
    }

    public static void removeOrder(int orderID) throws UniversalSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "DELETE FROM cust_order WHERE orderID=?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orderID);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {

            String methodName = "removeOrder";
            UniversalSampleException.exceptionIfsDB(ex.getMessage(), methodName);
            UniversalSampleException.exceptionIfLast(ex.getMessage(), methodName);

        }
    }

    public static void addCarportToCustOrder(int carportLength, int carportWidth, int carportHeight, boolean hasShed, int shedWidth, int shedLength, boolean hasPitch, int roofPitch, String roofMaterial, int price, int tlfNumber) throws UniversalSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO cust_order (carport_length, carport_width, carport_height, hasShed, shedWidth, " +
                    "shedLength, hasPitch, roof_pitch, roof_material, price, tlf_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );
            ps.setInt(1, carportLength);
            ps.setInt( 2, carportWidth);
            ps.setInt( 3, carportHeight);
            ps.setBoolean( 4, hasShed);
            ps.setInt(5, shedWidth);
            ps.setInt(6, shedLength);
            ps.setBoolean(7, hasPitch);
            ps.setInt(8, roofPitch);
            ps.setString(9, roofMaterial);
            ps.setInt(10, price);
            ps.setInt(11, tlfNumber);

            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
        } catch ( SQLException | ClassNotFoundException ex ) {

            String methodName = "addCarportToCustOrder";
            UniversalSampleException.exceptionIfsDB(ex.getMessage(), methodName);
            UniversalSampleException.exceptionIfLast(ex.getMessage(), methodName);

        }
    }

    public static CarportHelper getHelper(int orderID) throws UniversalSampleException {
        CarportHelper helper = null;
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT carport_length, carport_width, carport_height, shedWidth, shedLength, roof_pitch FROM cust_order WHERE orderID=?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int carportLength = rs.getInt("carport_length");
                int carportWidth = rs.getInt("carport_width");
                int carportHeight = rs.getInt("carport_height");
                int shedWidth = rs.getInt("shedWidth");
                int shedLength = rs.getInt("shedLength");
                int carportPitch = rs.getInt("roof_pitch");
                helper = new CarportHelper(carportLength, carportWidth, carportHeight, shedLength, shedWidth, carportPitch);
            }
        } catch (SQLException | ClassNotFoundException ex) {

            String methodName = "getHelper";
            UniversalSampleException.exceptionIfsDB(ex.getMessage(), methodName);
            UniversalSampleException.exceptionIfLast(ex.getMessage(), methodName);

        }
        return helper;
    }

    public static void editOrder(int orderID, String measure, int amount) {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE carport.cust_order SET ?=? WHERE orderID=?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, measure);
            ps.setInt(2, amount);
            ps.setInt(3, orderID);
            ps.executeUpdate();
            System.out.println("Test");
        } catch (SQLException | ClassNotFoundException ex) {
            ex.getMessage();
        }
    }


}
