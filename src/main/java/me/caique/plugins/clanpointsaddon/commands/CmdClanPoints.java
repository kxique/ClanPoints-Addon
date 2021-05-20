package me.caique.plugins.clanpointsaddon.commands;

import me.caique.plugins.clanpointsaddon.apis.dependencies.SimpleClans;
import me.caique.plugins.clanpointsaddon.models.ClanPoint;
import me.caique.plugins.clanpointsaddon.utils.Utils;
import net.sacredlabyrinth.phaed.simpleclans.managers.ClanManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdClanPoints implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        ClanManager clanMng = SimpleClans.getCore().getClanManager();

        if (args.length == 0) {
            if (sender instanceof Player) {
                Player p = (Player) sender;

                if (clanMng.getClanByPlayerUniqueId(p.getUniqueId()) == null) {
                    p.sendMessage("§cYou aren't on a clan.");
                    return true;
                }

                ClanPoint cp = new ClanPoint(clanMng.getClanByPlayerUniqueId(p.getUniqueId()));
                p.sendMessage("§bYour clan balance: §f\u216D" + Math.round(cp.getPoints()));
            }else {
                sendHelp(sender);
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("set")) {
            if (args.length > 2) {

                if (clanMng.getClan(args[1]) == null) {
                    sender.sendMessage("§cInvalid clan.");
                    return true;
                }

                if (!Utils.isDouble(args[2])) {
                    sender.sendMessage("§cInvalid value.");
                    return true;
                }

                ClanPoint cp = new ClanPoint(clanMng.getClan(args[1]));
                cp.setPoints(Double.parseDouble(args[2]));
                sender.sendMessage("§aYou set " + args[2] + " clan points to the clan " + args[1].toUpperCase() + "'s balance.");
            }
        }

        if (args[0].equalsIgnoreCase("add")) {
            if (args.length > 2) {

                if (clanMng.getClan(args[1]) == null) {
                    sender.sendMessage("§cInvalid clan.");
                    return true;
                }

                if (!Utils.isDouble(args[2])) {
                    sender.sendMessage("§Invalid value.");
                    return true;
                }

                ClanPoint cp = new ClanPoint(clanMng.getClan(args[1]));
                cp.addPoints(Double.parseDouble(args[2]));
                sender.sendMessage("§aYou added" + args[2] + " clan points to the clan " + args[1].toUpperCase() + "'s balance.");
            }
        }

        if (args[0].equalsIgnoreCase("remove")) {
            if (args.length > 2) {

                if (clanMng.getClan(args[1]) == null) {
                    sender.sendMessage("§cInvalid clan.");
                    return true;
                }

                if (!Utils.isDouble(args[2])) {
                    sender.sendMessage("§cInvalid value.");
                    return true;
                }

                ClanPoint cp = new ClanPoint(clanMng.getClan(args[1]));
                cp.removePoints(Double.parseDouble(args[2]));

                sender.sendMessage("§aYou removed" + args[2] + " clan points from " + args[1].toUpperCase() + "'s balance.");
            }
        }

        if (args[0].equalsIgnoreCase("help")) sendHelp(sender);

        if (clanMng.getClan(args[0]) != null) {
            ClanPoint cp = new ClanPoint(clanMng.getClan(args[0]));
            sender.sendMessage("§bYour clan balance: §f\u216D" + Math.round(cp.getPoints()));
        }

        return false;
    }

    public void sendHelp(CommandSender sender) {
        sender.sendMessage("");
        sender.sendMessage("§bClanPoints - Help");
        sender.sendMessage("§b/cp §f(tag) §7- §fshows the clan balance");
        sender.sendMessage("§b/cp set §f(tag) (amount) §7- §fset the clan balance to the specified amount");
        sender.sendMessage("§b/cp add §f(tag) (amount) §7- §fadd the amount specified to the clan balance");
        sender.sendMessage("§b/cp remove §f(tag) (amount) §7- §fremove the specified amount from the clan balance");
        sender.sendMessage("");
    }

}
