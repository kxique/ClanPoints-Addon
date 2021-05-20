package me.caique.plugins.clanpointsaddon.apis.config;

import me.caique.plugins.clanpointsaddon.Core;

public class Config {

    private static ConfigAPI global;

    public static void setup() {
        global = new ConfigAPI("config.yml", Core.getInstance());

        global.saveDefaultConfig();
        global.getConfig().options().copyDefaults(false);
    }

    public static ConfigAPI getGlobal() {
        return global;
    }

}

