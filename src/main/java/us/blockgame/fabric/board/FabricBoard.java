package us.blockgame.fabric.board;

import org.bukkit.entity.Player;

import java.util.List;

public interface FabricBoard {

    String getTitle(Player player);

    List<String> getSlots(Player player);

    long getUpdateInterval();
}
