package us.blockgame.fabric.board.impl;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import us.blockgame.fabric.FabricPlugin;
import us.blockgame.fabric.board.FabricBoard;
import us.blockgame.fabric.economy.EconomyType;
import us.blockgame.fabric.economy.FabricEconomyHandler;

import java.util.ArrayList;
import java.util.List;

public class FabricExampleBoard implements FabricBoard {

    @Override
    public String getTitle(Player player) {
        return ChatColor.AQUA + "Fabric Board";
    }

    @Override
    public List<String> getSlots(Player player) {
        List<String> slots = new ArrayList<>();
        slots.add("&7&m" + StringUtils.repeat('-', 20));
        slots.add("&bYou:");
        slots.add(" &7» &f" + player.getName());
        slots.add(" ");

        FabricEconomyHandler fabricEconomyHandler = FabricPlugin.getInstance().getFabricEconomyHandler();
        slots.add("&bBalance:");
        slots.add(" &7» &f" + fabricEconomyHandler.getEconomyType().getSymbol() + fabricEconomyHandler.getBalance(player.getUniqueId()) + (fabricEconomyHandler.getEconomyType() == EconomyType.POINTS ? " Points" : ""));

        slots.add(" ");
        slots.add("&7www.fabric.com");
        slots.add("&7&m" + StringUtils.repeat('-', 20));
        return slots;
    }

    @Override
    public long getUpdateInterval() {
        return 1L;
    }
}
