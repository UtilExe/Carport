package DBAccess;

import FunctionLayer.LoginSampleException;

import java.sql.*;
import java.util.ArrayList;

public class CarportMapper {

    public static ArrayList<Integer> getCarportMeasure(String description) {
        ArrayList<Integer> measure = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT measures FROM carport.carport_measures WHERE description=? ORDER BY measures ASC;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,description);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int unit = rs.getInt("measures");
                measure.add(unit);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return measure;
    }

    public static ArrayList<String> getCarportRoof() {
        ArrayList<String> types = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT roof_material FROM carport.roof_data WHERE roof_material IS NOT NULL;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String roof = rs.getString("roof_material");
                types.add(roof);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return types;
    }

    public static ArrayList<Integer> getShedMeasure(String description) {
        ArrayList<Integer> measure = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT measures FROM carport.shed_measures WHERE description=? ORDER BY measures ASC;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,description);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int unit = rs.getInt("measures");
                measure.add(unit);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return measure;
    }

    public static ArrayList<Integer> getRoofPitch() {
        ArrayList<Integer> pitch = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT roof_pitch FROM carport.roof_data WHERE roof_pitch IS NOT NULL;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int roofPitch = rs.getInt("roof_pitch");
                pitch.add(roofPitch);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return pitch;
    }

    public static void addCarportToCustOrder(int carportLength, int carportWidth, int carportHeight, boolean hasShed, int shedWidth, int shedLength, boolean hasPitch, int roofPitch, String roofMaterial, int price) {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO carport.cust_order (carport_length, carport_width, carport_height, hasShed, shedWidth, " +
                    "shedLength, hasPitch, roof_pitch, roof_material, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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

            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
        } catch ( SQLException | ClassNotFoundException ex ) {
            ex.printStackTrace();
        }
    }
}
