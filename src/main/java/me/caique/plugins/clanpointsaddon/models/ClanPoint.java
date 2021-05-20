package me.caique.plugins.clanpointsaddon.models;

import me.caique.plugins.clanpointsaddon.storage.PointsSQL;
import net.sacredlabyrinth.phaed.simpleclans.Clan;

public class ClanPoint {

    Clan clan;
    String clanTag;

    public ClanPoint(Clan clan) {
        this.clan = clan;
        this.clanTag = clan.getTag();
    }

    public double getPoints() {
        return PointsSQL.getPoints(clanTag) == null ? 0 : Double.parseDouble(PointsSQL.getPoints(clanTag));
    }

    public void setPoints(double amount) {
        PointsSQL.setPoints(clanTag, String.valueOf(amount));
    }

    public void addPoints(double amount) {
        PointsSQL.setPoints(clanTag, String.valueOf(getPoints() + amount));
    }

    public void removePoints(double amount) {
        PointsSQL.setPoints(clanTag, String.valueOf(getPoints() - amount));
    }

}
