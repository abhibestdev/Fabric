package us.blockgame.fabric.menu.button;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import us.blockgame.fabric.menu.Button;
import us.blockgame.fabric.menu.Menu;

public class JumpToMenuButton extends Button {

    private Menu menu;
    private ItemStack itemStack;

    public JumpToMenuButton(Menu menu, ItemStack itemStack) {
        this.menu = menu;
        this.itemStack = itemStack;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return itemStack;
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        menu.openMenu(player);
    }

}
