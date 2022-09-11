package us.blockgame.fabric.player.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.blockgame.fabric.command.framework.Command;
import us.blockgame.fabric.command.framework.Param;
import us.blockgame.fabric.util.StringUtil;

public class GameModeCommand {

    @Command(name = "gamemode", aliases = {"gm"}, permission = "fabric.command.gamemode", inGameOnly = true)
    public void gamemode(CommandSender sender, @Param(name = "gamemode") GameMode gameMode, @Param(name = "player", optional = true) Player player) {

        //Set gamemode
        player.setGameMode(gameMode);

        if (player != sender) {
            sender.sendMessage(ChatColor.GREEN + "You have updated " + player.getName() + "'s gamemode to " + StringUtil.convertToTitleCaseIteratingChars(gameMode.toString()) + ".");
        }
        player.sendMessage(ChatColor.GREEN + "Your gamemode has been updated to " + StringUtil.convertToTitleCaseIteratingChars(gameMode.toString()) + ".");
        return;
    }

    @Command(name = "gms", aliases = {"gm0"}, permission = "fabric.command.gamemode", inGameOnly = true)
    public void gms(CommandSender sender, @Param(name = "player", optional = true) Player player) {
        Bukkit.getServer().dispatchCommand(sender, "gm s " + player.getName());
    }

    @Command(name = "gmc", aliases = {"gm1"}, permission = "fabric.command.gamemode", inGameOnly = true)
    public void gmc(CommandSender sender, @Param(name = "player", optional = true) Player player) {
        Bukkit.getServer().dispatchCommand(sender, "gm c " + player.getName());
    }

    @Command(name = "gma", aliases = {"gm2"}, permission = "fabric.command.gamemode", inGameOnly = true)
    public void gma(CommandSender sender, @Param(name = "player", optional = true) Player player) {
        Bukkit.getServer().dispatchCommand(sender, "gm a " + player.getName());
    }

    @Command(name = "gmsp", aliases = {"gm3"}, permission = "fabric.command.gamemode", inGameOnly = true)
    public void gmsp(CommandSender sender, @Param(name = "player", optional = true) Player player) {
        Bukkit.getServer().dispatchCommand(sender, "gm sp " + player.getName());
    }
}
