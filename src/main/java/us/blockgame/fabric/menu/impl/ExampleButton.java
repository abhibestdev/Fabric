package us.blockgame.fabric.menu.impl;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import us.blockgame.fabric.menu.Button;
import us.blockgame.fabric.util.ItemBuilder;

public class ExampleButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.LIME_WOOL).setName(ChatColor.GREEN + "Push Me!").setLore(ChatColor.GRAY + "This is such a cool button!").toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        player.closeInventory();

        player.sendMessage(ChatColor.GREEN + "You have clicked the magic button!");
    }
}
