package us.blockgame.fabric.economy.command;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import us.blockgame.fabric.FabricPlugin;
import us.blockgame.fabric.command.framework.Command;
import us.blockgame.fabric.command.framework.Param;
import us.blockgame.fabric.economy.FabricEconomyHandler;

public class BalanceCommand {

    @Command(name = "balance", aliases = {"bal", "money", "$"}, inGameOnly = true, runAsync = true)
    public void balance(CommandSender sender, @Param(name = "Player", optional = true) OfflinePlayer offlinePlayer) {
        FabricEconomyHandler fabricEconomyHandler = FabricPlugin.getInstance().getFabricEconomyHandler();

        if (!sender.hasPermission("fabric.admin") || sender == offlinePlayer) {
            sender.sendMessage(ChatColor.GREEN + "Your balance is " + fabricEconomyHandler.getEconomyType().getSymbol() + fabricEconomyHandler.getBalance(offlinePlayer.getUniqueId()) + ".");
            return;
        }
        sender.sendMessage(ChatColor.GREEN + offlinePlayer.getName() + "'s balance is " + fabricEconomyHandler.getEconomyType().getSymbol() + fabricEconomyHandler.getBalance(offlinePlayer.getUniqueId()) + ".");
        return;
    }
}
