package me.caique.plugins.clanpointsaddon.listeners;

import me.caique.plugins.clanpointsaddon.apis.dependencies.SimpleClans;
import me.caique.plugins.clanpointsaddon.models.ClanPoint;
import net.sacredlabyrinth.phaed.simpleclans.managers.ClanManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ClanPointsListener implements Listener {

    @EventHandler
    public void createClan(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        ClanManager mng = SimpleClans.getCore().getClanManager();
        if (e.getMessage().startsWith("/clan create")) {
            if (mng.getClanPlayer(p) != null && mng.getClanPlayer(p).isLeader()) {
                ClanPoint clanPoint = new ClanPoint(mng.getClanPlayer(p).getClan());

                if (clanPoint.getPoints() > 0) {
                    clanPoint.setPoints(0);
                }
            }
        }
    }

}
