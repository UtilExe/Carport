package DBAccess;

import FunctionLayer.Objects.Carport;
import FunctionLayer.Objects.Order;

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

}
