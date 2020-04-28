package DBAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialMapper {
        public static ArrayList<String> getRemOrRaftData(String type, int carportMeasure, int tmpAmount) {
            ArrayList<String> remInfo = new ArrayList<>();
            String amount = String.valueOf(tmpAmount);
            String carpMeasure = String.valueOf(carportMeasure);

            try {
                Connection con = Connector.connection();
                String SQL = "SELECT `category`, `type`, `description` FROM carport.material_list WHERE `type`=?;";
                PreparedStatement ps = con.prepareStatement(SQL);
                ps.setString(1,type);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    String category = rs.getString("category");
                    type = rs.getString("type");
                    String description = rs.getString("description");
                    remInfo.add(category);
                    remInfo.add(type);
                    remInfo.add(description);
                    remInfo.add(amount + " stk.");
                    remInfo.add(carpMeasure + " cm.");
                }
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        return remInfo;
        }


    }


