package us.blockgame.fabric.menu;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import us.blockgame.fabric.FabricPlugin;
import us.blockgame.fabric.util.ThreadUtil;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public abstract class Menu {

    public static Map<String, Menu> currentlyOpenedMenus = new HashMap<>();

    private Map<Integer, Button> buttons = new HashMap<>();
    private boolean autoUpdate = false;
    private boolean updateAfterClick = true;
    private boolean closedByMenu = false;
    private boolean placeholder = false;
    private Button placeholderButton = Button.placeholder(Material.GRAY_STAINED_GLASS_PANE, (byte) 15, " ");
    public static Map<String, BukkitRunnable> checkTasks = Maps.newHashMap();
    private boolean async = false;
    private Inventory inventory = null;

    private ItemStack createItemStack(Player player, Button button) {
        ItemStack item = button.getButtonItem(player);

        if (item.getType() != Material.PLAYER_HEAD) {
            ItemMeta meta = item.getItemMeta();

            if (meta != null && meta.hasDisplayName()) {

                meta.setDisplayName(meta.getDisplayName() + " ");
            }

            item.setItemMeta(meta);
        }

        return item;
    }

    private Inventory createInventory(final Player player) {
        final Map<Integer, Button> invButtons = this.getButtons(player);
        inventory = Bukkit.createInventory((InventoryHolder) player, this.size(invButtons), this.getTitle(player));

        for (final Map.Entry<Integer, Button> buttonEntry : invButtons.entrySet()) {
            this.buttons.put(buttonEntry.getKey(), buttonEntry.getValue());
            inventory.setItem((int) buttonEntry.getKey(), buttonEntry.getValue().getButtonItem(player));

        }
        if (this.isPlaceholder()) {
            final Button placeholder = Button.placeholder(Material.GRAY_STAINED_GLASS_PANE, (byte) 15, new String[0]);
            for (int index = 0; index < this.size(invButtons); ++index) {
                if (invButtons.get(index) == null) {
                    this.buttons.put(index, placeholder);
                    inventory.setItem(index, placeholder.getButtonItem(player));
                }
            }
        }
        return inventory;
    }

    public void openMenu(final Player player) {
        Inventory inventory = createInventory(player);

        ThreadUtil.runSync(() -> {
            player.openInventory(inventory);
            update(player);
        });
    }

    private void update(final Player player) {
        cancelCheck(player);
        Menu.currentlyOpenedMenus.put(player.getName(), this);
        this.onOpen(player);
        final BukkitRunnable runnable = new BukkitRunnable() {
            public void run() {
                if (!player.isOnline()) {
                    Menu.cancelCheck(player);
                    Menu.currentlyOpenedMenus.remove(player.getName());
                }
                if (Menu.this.isAutoUpdate()) {
                    player.getOpenInventory().getTopInventory().setContents(Menu.this.createInventory(player).getContents());
                }
            }
        };
        runnable.runTaskTimerAsynchronously(FabricPlugin.getInstance(), 0L, 0L);
        Menu.checkTasks.put(player.getName(), runnable);
    }

    public static void cancelCheck(final Player player) {
        if (Menu.checkTasks.containsKey(player.getName())) {
            Menu.checkTasks.get(player.getName()).cancel();
            Menu.checkTasks.remove(player.getName()).cancel();
        }
    }

    public int size(Map<Integer, Button> buttons) {
        int highest = 0;

        for (int buttonValue : buttons.keySet()) {
            if (buttonValue > highest) {
                highest = buttonValue;
            }
        }

        return (int) (Math.ceil((highest + 1) / 9D) * 9D);
    }

    public int getSize() {
        return -1;
    }

    public int getSlot(int x, int y) {
        return ((9 * y) + x);
    }

    public abstract String getTitle(Player player);

    public abstract Map<Integer, Button> getButtons(Player player);

    public void onOpen(Player player) {
    }

    public void onClose(Player player) {
    }

    public void updateLore(Button button, Player player) {
        try {
            ItemStack itemStack = button.getButtonItem(player);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setLore(button.getLore());
            itemStack.setItemMeta(itemMeta);

            if (player.isOnline() && getSlot(button) != 100) {
                inventory.setItem(getSlot(button), itemStack);
            }
        } catch (Exception ex) {
            //get rid of my errors pls :(
        }
    }

    private int getSlot(Button button) {
        for (int entry : buttons.keySet()) {
            if (buttons.get(entry).equals(button)) {
                return entry;
            }
        }
        return 100;
    }
}