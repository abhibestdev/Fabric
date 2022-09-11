package us.blockgame.fabric.economy.command;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import us.blockgame.fabric.FabricPlugin;
import us.blockgame.fabric.command.framework.Command;
import us.blockgame.fabric.command.framework.Param;
import us.blockgame.fabric.economy.FabricEconomyHandler;

public class EconomyCommand {

    @Command(name = "economy", aliases = {"eco"}, permission = "fabric.command.economy")
    public void economy(CommandSender sender) {
        sender.sendMessage(new String[]{
                ChatColor.RED + "Economy Help:",
                ChatColor.RED + " /economy set <player> <amount>",
                ChatColor.RED + " /economy give <player> <amount>",
                ChatColor.RED + " /economy take <player> <amount>",
        });
    }

    @Command(name = "economy.set", aliases = {"eco.set"}, permission = "fabric.command.economy")
    public void economySet(CommandSender sender, @Param(name = "player") OfflinePlayer offlinePlayer, @Param(name = "amount") Double amount) {
        FabricEconomyHandler fabricEconomyHandler = FabricPlugin.getInstance().getFabricEconomyHandler();

        if (amount < 0) {
            sender.sendMessage(ChatColor.RED + "Please enter an amount greater than 0.");
            return;
        }

        //Set balance
        fabricEconomyHandler.setBalance(offlinePlayer.getUniqueId(), amount);

        if (sender != offlinePlayer) {
            sender.sendMessage(ChatColor.GREEN + "You have updated " + offlinePlayer.getName() + "'s balance to " + fabricEconomyHandler.getEconomyType().getSymbol() + amount + ".");
        }
        if (offlinePlayer.isOnline()) {
            offlinePlayer.getPlayer().sendMessage(ChatColor.GREEN + "Your balance has been updated to " + fabricEconomyHandler.getEconomyType().getSymbol() + amount + ".");
        }
        return;
    }

    @Command(name = "economy.give", aliases = {"eco.give"}, permission = "fabric.command.economy")
    public void economyGive(CommandSender sender, @Param(name = "player") OfflinePlayer offlinePlayer, @Param(name = "amount") Double amount) {
        FabricEconomyHandler fabricEconomyHandler = FabricPlugin.getInstance().getFabricEconomyHandler();

        double targetBalance = fabricEconomyHandler.getBalance(offlinePlayer.getUniqueId()) + amount;

        if (amount <= 0) {
            sender.sendMessage(ChatColor.RED + "Please enter an amount greater than 0.");
            return;
        }
        //Set balance
        fabricEconomyHandler.setBalance(offlinePlayer.getUniqueId(), targetBalance);

        if (sender != offlinePlayer) {
            sender.sendMessage(ChatColor.GREEN + "You have updated " + offlinePlayer.getName() + "'s balance to " + fabricEconomyHandler.getEconomyType().getSymbol() + targetBalance + ".");
        }
        if (offlinePlayer.isOnline()) {
            offlinePlayer.getPlayer().sendMessage(ChatColor.GREEN + "Your balance has been updated to " + fabricEconomyHandler.getEconomyType().getSymbol() + targetBalance + ".");
        }
        return;
    }

    @Command(name = "economy.take", aliases = {"eco.take"}, permission = "fabric.command.economy")
    public void economyTake(CommandSender sender, @Param(name = "player") OfflinePlayer offlinePlayer, @Param(name = "amount") Double amount) {
        FabricEconomyHandler fabricEconomyHandler = FabricPlugin.getInstance().getFabricEconomyHandler();

        double targetBalance = fabricEconomyHandler.getBalance(offlinePlayer.getUniqueId()) - amount;

        if (targetBalance < 0) {
            sender.sendMessage(ChatColor.RED + "Balance can not be less than 0.");
            return;
        }
        //Set balance
        fabricEconomyHandler.setBalance(offlinePlayer.getUniqueId(), targetBalance);

        if (sender != offlinePlayer) {
            sender.sendMessage(ChatColor.GREEN + "You have updated " + offlinePlayer.getName() + "'s balance to " + fabricEconomyHandler.getEconomyType().getSymbol() + targetBalance + ".");
        }
        if (offlinePlayer.isOnline()) {
            offlinePlayer.getPlayer().sendMessage(ChatColor.GREEN + "Your balance has been updated to " + fabricEconomyHandler.getEconomyType().getSymbol() + targetBalance + ".");
        }
        return;
    }
}
