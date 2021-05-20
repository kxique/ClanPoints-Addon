package me.caique.plugins.clanpointsaddon.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PointsSQL {

    public static String table = "c_clanpoints";

    public void createTable() {
        PreparedStatement stm = null;

        try {
            stm = Database.con.prepareStatement("CREATE TABLE IF NOT EXISTS " + table + "(clan VARCHAR(508), points TEXT, PRIMARY KEY (clan))");
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getPoints(String clan) {
        PreparedStatement stm = null;

        try {
            stm = Database.con.prepareStatement("SELECT points FROM " + table + " WHERE clan = ?");
            stm.setString(1, clan);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString("points");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setPoints(String clan, String points) {
        PreparedStatement stm = null;

        try {
            if (Database.useMySQL()) {
                stm = Database.con.prepareStatement("INSERT INTO " + table + "(clan, points) VALUES (?,?) ON DUPLICATE KEY UPDATE points = VALUES(points)");
                stm.setString(1, clan);
                stm.setString(2, points);
            }else {
                if (getPoints(clan) == null) {
                    stm = Database.con.prepareStatement("INSERT INTO " + table + "(clan, points) VALUES (?,?)");
                    stm.setString(1, clan);
                    stm.setString(2, points);
                }else {
                    stm = Database.con.prepareStatement("UPDATE " + table + " SET points = ? WHERE clan = ?");
                    stm.setString(2, clan);
                    stm.setString(1, points);
                }
            }
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void remove(String clan) {
        PreparedStatement stm = null;

        try {
            stm = Database.con.prepareStatement("DELETE FROM " + table + " WHERE clan = ?");
            stm.setString(1 , clan);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
