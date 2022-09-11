package us.blockgame.fabric.economy.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.blockgame.fabric.FabricPlugin;
import us.blockgame.fabric.command.framework.Command;
import us.blockgame.fabric.command.framework.Param;
import us.blockgame.fabric.economy.FabricEconomyHandler;

public class PayCommand {

    @Command(name = "pay", inGameOnly = true)
    public void pay(CommandSender sender, @Param(name = "player") Player target, @Param(name = "amount") Double amount) {
        Player player = (Player) sender;

        FabricEconomyHandler fabricEconomyHandler = FabricPlugin.getInstance().getFabricEconomyHandler();
        double balance = fabricEconomyHandler.getBalance(player.getUniqueId());

        if (amount == 0) {
            sender.sendMessage(ChatColor.RED + "Please enter an amount greater than 0.");
            return;
        }

        //Check if user has enough money
        if (balance < amount) {
            sender.sendMessage(ChatColor.RED + "You have insufficient funds.");
            return;
        }

        double targetBalance = fabricEconomyHandler.getBalance(target.getUniqueId()) + amount;
        double playerBalance = fabricEconomyHandler.getBalance(player.getUniqueId()) - amount;

        //Update target's balance
        fabricEconomyHandler.setBalance(target.getUniqueId(), targetBalance);
        target.sendMessage(ChatColor.GREEN + player.getName() + " has sent you " + fabricEconomyHandler.getEconomyType().getSymbol() + amount + ". Your new balance is " + fabricEconomyHandler.getEconomyType().getSymbol() + targetBalance + ".");

        //Update player's balance
        fabricEconomyHandler.setBalance(player.getUniqueId(), playerBalance);
        player.sendMessage("You have sent " + target.getName() + " " + fabricEconomyHandler.getEconomyType().getSymbol() + amount + ". Your new balance is " + fabricEconomyHandler.getEconomyType().getSymbol() + playerBalance + ".");
        return;
    }
}
