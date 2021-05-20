package me.caique.plugins.clanpointsaddon.apis.dependencies;

import me.caique.plugins.clanpointsaddon.Core;
import org.bukkit.plugin.Plugin;

public class SimpleClans {

    private static net.sacredlabyrinth.phaed.simpleclans.SimpleClans core;

    public static boolean hookSimpleClans() {
        try {
            for (Plugin plugin : Core.getInstance().getServer().getPluginManager().getPlugins()) {
                if (plugin instanceof net.sacredlabyrinth.phaed.simpleclans.SimpleClans) {
                    core = (net.sacredlabyrinth.phaed.simpleclans.SimpleClans) plugin;
                    return true;
                }
            }
        } catch (NoClassDefFoundError e) {
            return false;
        }

        return false;
    }

    public static net.sacredlabyrinth.phaed.simpleclans.SimpleClans getCore() {
        return core;
    }
}
