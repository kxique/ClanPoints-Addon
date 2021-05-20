package me.caique.plugins.clanpointsaddon.api;

import me.caique.plugins.clanpointsaddon.models.ClanPoint;
import net.sacredlabyrinth.phaed.simpleclans.Clan;

public class CpAPI {

    public static double getClanPoints(Clan clan) {
        return new ClanPoint(clan).getPoints();
    }

    public static void setClanPoints(Clan clan, double amount) {
        new ClanPoint(clan).setPoints(amount);
    }

    public static void addClanPoints(Clan clan, double amount) {
        new ClanPoint(clan).addPoints(amount);
    }

    public static void removeClanPoints(Clan clan, double amount) {
        new ClanPoint(clan).removePoints(amount);
    }

}
