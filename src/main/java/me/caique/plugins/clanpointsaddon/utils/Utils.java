package me.caique.plugins.clanpointsaddon.utils;

public class Utils {

    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
        }catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
