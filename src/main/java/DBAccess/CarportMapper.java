package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.User;

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
                int length = rs.getInt("measures");
                measure.add(length);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return measure;
    }
}
