package us.blockgame.fabric.player.command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.blockgame.fabric.command.framework.Command;

public class MoreCommand {

    @Command(name = "more", permission = "op", inGameOnly = true)
    public void more(CommandSender sender) {
        Player player = (Player) sender;

        //Check if player is holding air
        if (player.getItemInHand().getType() == Material.AIR) {
            sender.sendMessage(ChatColor.RED + "You are holding air.");
            return;
        }
        //Check if player already has 64 of that item
        if (player.getItemInHand().getAmount() >= 64) {
            sender.sendMessage(ChatColor.RED + "You already have 64 of this item.");
            return;
        }
        //Give more
        player.getItemInHand().setAmount(64);
        player.updateInventory();

        sender.sendMessage(ChatColor.GREEN + "You have been given more.");
        return;
    }
}
