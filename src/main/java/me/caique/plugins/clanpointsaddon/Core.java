package me.caique.plugins.clanpointsaddon;

import me.caique.plugins.clanpointsaddon.apis.config.Config;
import me.caique.plugins.clanpointsaddon.apis.dependencies.SimpleClans;
import me.caique.plugins.clanpointsaddon.commands.CmdClanPoints;
import me.caique.plugins.clanpointsaddon.listeners.ClanPointsListener;
import me.caique.plugins.clanpointsaddon.storage.Database;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    private static Core instance;

    @Override
    public void onEnable() {
        instance = this;

        setupConfig();

        Database.connect();

        setupCommands();
        setupListeners();

        setupClan();

        sendMessage(ChatColor.GREEN + "Plugin enabled!");
    }

    @Override
    public void onDisable() {
        sendMessage(ChatColor.RED + "Plugin disabled!");

        Database.closeDatabase();
    }

    public void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage("[" + getName() + "] " + message);
    }

    public static Core getInstance() {
        return instance;
    }

    public void setupListeners() {
        Bukkit.getPluginManager().registerEvents(new ClanPointsListener(), this);
    }

    public void setupCommands() {
        getCommand("clanpoints").setExecutor(new CmdClanPoints());
    }

    public void setupConfig() {
        Config.setup();
    }

    public void setupClan() {
        SimpleClans.hookSimpleClans();
    }

}
