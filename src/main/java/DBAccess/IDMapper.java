package DBAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class IDMapper {
    public static HashMap<String, Integer> getIDs() {
        HashMap<String, Integer> productIDs = new HashMap<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT description, productID FROM carport.material_list ORDER BY productID ASC;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String description = rs.getString("description");
                int id = rs.getInt("productID");
                productIDs.put(description, id);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.getMessage();
        }
        return productIDs;
    }
}
