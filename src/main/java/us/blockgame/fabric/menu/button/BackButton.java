package us.blockgame.fabric.menu.button;

import lombok.AllArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import us.blockgame.fabric.menu.Button;
import us.blockgame.fabric.menu.Menu;
import us.blockgame.fabric.util.ItemBuilder;

@AllArgsConstructor
public class BackButton extends Button {

    private Menu back;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.REDSTONE)
                .setName(ChatColor.RED.toString() + ChatColor.BOLD + "Back")
                .setLore(
                        ChatColor.RED + "Click here to return to",
                        ChatColor.RED + "the previous menu.")
                .toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        //Button.playNeutral(player);
        back.openMenu(player);
    }

}
