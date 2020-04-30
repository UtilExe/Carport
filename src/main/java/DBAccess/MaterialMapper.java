package DBAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialMapper {
    public static ArrayList<String> getRoofData(int ID, int carportMeasure, int tmpAmount) {
        ArrayList<String> data = new ArrayList<>();
        String amount = String.valueOf(tmpAmount);
        String carpMeasure = String.valueOf(carportMeasure);

        try {
            Connection con = Connector.connection();
            String SQL = "SELECT `material_list`.`category`, `material_type`.`type_name`, `material_list`.`description` " +
                    "FROM material_list INNER JOIN `material_type` ON `material_type`.`typeID` = `material_list`.`type_id` " +
                    "WHERE `material_list`.`productID`=?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String category = rs.getString("category");
                String type = rs.getString("material_type.type_name");
                String description = rs.getString("description");
                data.add(category);
                data.add(type);
                data.add(description);
                data.add(amount + " stk.");
                data.add(carpMeasure + " cm.");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public static ArrayList<String> getRoofTileData(int ID, int tmpAmount) {
        ArrayList<String> data = new ArrayList<>();
        String amount = String.valueOf(tmpAmount);

        try {
            Connection con = Connector.connection();
            String SQL = "SELECT `material_list`.`category`, `material_type`.`type_name`, `material_list`.`description`, `material_list`.`unit` " +
                    "FROM material_list INNER JOIN `material_type` ON `material_type`.`typeID` = `material_list`.`type_id` " +
                    "WHERE `material_list`.`productID`=?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String category = rs.getString("category");
                String type = rs.getString("material_type.type_name");
                String description = rs.getString("description");
                String unit = rs.getString("unit");
                data.add(category);
                data.add(type);
                data.add(description);
                data.add(amount);
                data.add(unit);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return data;
    }


    public static int getAmountPrUnit(int ID) {
        String amountPrUnit = "";
        int resultAmount = 0;
        String[] splittedArr = new String[2];

        try {
            Connection con = Connector.connection();
            String SQL = "SELECT `amount_pr_unit` FROM carport.material_list WHERE `productID`=?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                amountPrUnit = rs.getString("amount_pr_unit");
                splittedArr = splitter(amountPrUnit, ", ");
                resultAmount = Integer.parseInt(splittedArr[0]);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return resultAmount;
    }

    public static ArrayList<String> getBandData(int ID, int bandLength, int rolesOfBand) {
        ArrayList<String> data = new ArrayList<>();
        double bandLengthToMeters = bandLength / 100.0;
        String[] amountPrUnitSplitted = new String[2];

        try {
            Connection con = Connector.connection();
            String SQL =
                    "SELECT `material_list`.`category`, `material_list`.`unit`, `material_list`.`amount_pr_unit`, " +
                    "`material_type`.`type_name`, `material_list`.`description` FROM material_list INNER JOIN `material_type` ON " +
                    "`material_type`.`typeID` = `material_list`.`type_id`" +
                    " WHERE `material_list`.`productID`=?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String category = rs.getString("category");
                String type = rs.getString("material_type.type_name");
                String description = rs.getString("description");
                String unit = rs.getString("unit");
                String amountPrUnit = rs.getString("amount_pr_unit");
                amountPrUnitSplitted = splitter(amountPrUnit, ", ");
                int amountPrUnitNumber = Integer.parseInt(amountPrUnitSplitted[0]);
                String amountPrUnitType = amountPrUnitSplitted[1];
                data.add(category);
                data.add(type);
                data.add(description);
                data.add(unit + "-antal: " + rolesOfBand);
                data.add(amountPrUnitNumber + " " + amountPrUnitType + " pr. " + unit);
                data.add(bandLengthToMeters + " " + amountPrUnitType);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public static String[] splitter(String strFromDB, String regex) {
        String[] splittedString = new String[2];
        splittedString = strFromDB.split(regex);
        return splittedString;
    }

    public static ArrayList<Double> getWidthHeightFromDimensionMeasureInCM(int ID) {
        ArrayList<Double> widthHeightMeasure = new ArrayList<>();
        String description = "";
        String[] descriptionSplitted = new String[2];

        try {
            Connection con = Connector.connection();
            String SQL = "SELECT `description` FROM carport.material_list WHERE `productID`=?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                description = rs.getString("description");
                descriptionSplitted = splitter(description, "x");
                double width = Double.parseDouble(descriptionSplitted[0]);
                width /= 10;
                widthHeightMeasure.add(width);
                descriptionSplitted = splitter(descriptionSplitted[1], " ");
                double height = Double.parseDouble(descriptionSplitted[0]);
                height /= 10;
                widthHeightMeasure.add(height);

            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return widthHeightMeasure;
    }

    public static ArrayList<String> getPillarData(int ID, int pillarAmount, ArrayList<Double> pillarLengths) {
        ArrayList<String> data = new ArrayList<>();
        String[] descriptionSplitted = new String[2];

        try {
            Connection con = Connector.connection();
            String SQL = "SELECT `material_list`.`category`, `material_list`.`unit`, `material_type`.`type_name`, " +
                    "`material_list`.`description` FROM carport.material_list INNER JOIN `material_type` ON " +
                    "`material_type`.`typeID` = `material_list`.`type_id` WHERE `material_list`.`productID`=?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String category = rs.getString("category");
                String unit = rs.getString("unit");
                String type = rs.getString("material_type.type_name");
                String description = rs.getString("description");

                data.add(category);
                data.add(type);
                data.add(description);
                data.add("Antal stolper: " + pillarAmount);
                for(int i = 1; i <= pillarAmount/2; i++) {
                    data.add("Højde på stolpe-par " + i + ": " + pillarLengths.get(i-1) + unit);
                    // GØR LIGESOM I PDF MED ANTAL I FORHOLD TIL MÅL (2 STOLPER AF 271.55 OG 2 STOLPER AF 280.98)
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public static ArrayList<String> getPlankData(int ID, int carportMeasure, int tmpAmount) {
        ArrayList<String> data = new ArrayList<>();
        String amount = String.valueOf(tmpAmount);
        String carpMeasure = String.valueOf(carportMeasure);

        try {
            Connection con = Connector.connection();
            String SQL = "SELECT `material_list`.`category`, `material_type`.`type_name`, `material_list`.`description` " +
                    "FROM material_list INNER JOIN `material_type` ON `material_type`.`typeID` = `material_list`.`type_id` " +
                    "WHERE `material_list`.`productID`=?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String category = rs.getString("category");
                String type = rs.getString("material_type.type_name");
                String description = rs.getString("description");
                data.add(category);
                data.add(type);
                data.add(description);
                data.add(amount + " stk.");
                data.add(carpMeasure + " cm.");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return data;
    }

}


