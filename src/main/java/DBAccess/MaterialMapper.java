package DBAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialMapper {
    public static ArrayList<String> getRemOrRaftData(int ID, int carportMeasure, int tmpAmount) {
        ArrayList<String> data = new ArrayList<>();
        String amount = String.valueOf(tmpAmount);
        String carpMeasure = String.valueOf(carportMeasure);

        try {
            Connection con = Connector.connection();
            String SQL = "SELECT `category`, `type`, `description` FROM carport.material_list WHERE `ID`=?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String category = rs.getString("category");
                String type = rs.getString("type");
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


    public static int getAmountPrUnit(int ID) {
        String amountPrUnit = "";
        int resultAmount = 0;
        String[] splittedArr = new String[2];

        try {
            Connection con = Connector.connection();
            String SQL = "SELECT `amount_pr_unit` FROM carport.material_list WHERE `ID`=?;";
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
            String SQL = "SELECT `category`, `unit`, `amount_pr_unit`, `type`, `description` FROM carport.material_list WHERE `ID`=?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String category = rs.getString("category");
                String type = rs.getString("type");
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

    public static double getHeadHeightFromDimensionMeasureInCM(int ID) {
        String description = "";
        double result = 0;
        String isolatingHeight = "";
        String writingHeight = "";
        String[] descriptionSplitted = new String[2];

        try {
            Connection con = Connector.connection();
            String SQL = "SELECT `description` FROM carport.material_list WHERE `ID`=?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                description = rs.getString("description");
                descriptionSplitted = splitter(description, "x");
                isolatingHeight = descriptionSplitted[1];
                descriptionSplitted = splitter(isolatingHeight, " ");
                isolatingHeight = descriptionSplitted[0];
                result = Double.parseDouble(isolatingHeight);
                result /= 10;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return result;
    }


}


