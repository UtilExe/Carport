package DBAccess;

import FunctionLayer.UniversalSampleException;

import java.sql.*;
import java.util.ArrayList;

public class CarportMapper {

    public static ArrayList<Integer> getCarportMeasure(String description) throws UniversalSampleException {
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
            String methodName = "getCarportMeasure";
            UniversalSampleException.exceptionIfsDB(ex.getMessage(), methodName);
            UniversalSampleException.exceptionIfLast(ex.getMessage(), methodName);
        }
        return measure;
    }

    public static ArrayList<String> getCarportRoof() throws UniversalSampleException {
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
            String methodName = "getCarportRoof";
            UniversalSampleException.exceptionIfsDB(ex.getMessage(), methodName);
            UniversalSampleException.exceptionIfLast(ex.getMessage(), methodName);
        }
        return types;
    }

    public static ArrayList<Integer> getShedMeasure(String description) throws UniversalSampleException {
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
            String methodName = "getShedMeasure";
            UniversalSampleException.exceptionIfsDB(ex.getMessage(), methodName);
            UniversalSampleException.exceptionIfLast(ex.getMessage(), methodName);
        }
        return measure;
    }

    public static ArrayList<Integer> getRoofPitch() throws UniversalSampleException {
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
            String methodName = "getRoofPitch";
            UniversalSampleException.exceptionIfsDB(ex.getMessage(), methodName);
            UniversalSampleException.exceptionIfLast(ex.getMessage(), methodName);
        }
        return pitch;
    }

}
