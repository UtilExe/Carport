package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.User;

import java.sql.*;
import java.util.ArrayList;

public class CarportMapper {

    public static ArrayList<Integer> getCarportLength() {
        ArrayList<Integer> lengths = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT measures FROM carport.carport_measures WHERE description='længde' ORDER BY measures ASC;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int length = rs.getInt("measures");
                lengths.add(length);
            }
            return lengths;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return lengths;
    }
}
