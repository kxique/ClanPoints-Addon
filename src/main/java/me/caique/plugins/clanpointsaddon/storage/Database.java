package me.caique.plugins.clanpointsaddon.storage;

import me.caique.plugins.clanpointsaddon.Core;
import me.caique.plugins.clanpointsaddon.apis.config.Config;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.sql.*;

public class Database {

    public static Connection con = null;

    public static void connect() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (Config.getGlobal().getBoolean("MySQL.usar")) {
            openMySQL();
        }else {
            openSQLite();
        }

        if (con != null) {
            new PointsSQL().createTable();
        }
    }

    public static void openMySQL() {
        String host = Config.getGlobal().getString("MySQL.host");
        int port = Config.getGlobal().getInt("MySQL.porta");
        String db = Config.getGlobal().getString("MySQL.database");

        String url = "jdbc:mysql://" + host + ":" + port + "/" + db;

        String user = Config.getGlobal().getString("MySQL.user");
        String password = Config.getGlobal().getString("MySQL.senha");

        try {
            con = DriverManager.getConnection(url, user, password);
            //Core.getInstance().sendMessage("§aConexão com o MySQL estabelecida.");
        } catch (SQLException e) {
            //Core.getInstance().sendMessage("§4Ocorreu um erro ao conectar-se com o MySQL, desativando plugin.");
            Core.getInstance().getPluginLoader().disablePlugin(Core.getInstance());
            e.printStackTrace();
        }
    }

    public static void openSQLite() {
        File db = new File(Core.getInstance().getDataFolder(), "database.db");
        String URL = "jdbc:sqlite:" + db;

        try {
            con = DriverManager.getConnection(URL);
            //Core.getInstance().sendMessage(ChatColor.GREEN + "Conexão com o SQLite estabelecida.");
        } catch (SQLException e) {
            e.printStackTrace();
            //Core.getInstance().sendMessage("§4Ocorreu um erro ao conectar-se com o SQLite, desativando plugin.");
            Core.getInstance().getPluginLoader().disablePlugin(Core.getInstance());
        }
    }

    public static void closeDatabase() {
        if (con != null) {
            try {
                con.close();
                //Core.getInstance().sendMessage("§aConexão ao banco de dados fechada.");
            } catch (SQLException e) {
                //Core.getInstance().sendMessage("§4A conexão não pôde ser fechada.");
            }
        }
    }

    public static boolean useMySQL() {
        return Config.getGlobal().getBoolean("MySQL.usar");
    }

    private void keepOpen() {
        new BukkitRunnable() {

            @Override
            public void run() {
                PreparedStatement stm = null;

                try {
                    stm = Database.con.prepareStatement("SELECT json FROM cbases_resources WHERE chave = keepSQL");
                    ResultSet rs = stm.executeQuery();
                    rs.getString("json");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }.runTaskTimer(Core.getInstance(), 20 * 60 * 30, 20 * 60 * 30);
    }

}
