package us.blockgame.fabric.economy;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import us.blockgame.fabric.FabricPlugin;

import java.util.concurrent.CompletableFuture;

public class FabricEconomyListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        FabricEconomyHandler fabricEconomyHandler = FabricPlugin.getInstance().getFabricEconomyHandler();

        //Load player's balance
        CompletableFuture.runAsync(() -> {
           fabricEconomyHandler.setBalance(player.getUniqueId(), fabricEconomyHandler.getBalanceFromMongo(player.getUniqueId()));
        });
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        FabricEconomyHandler fabricEconomyHandler = FabricPlugin.getInstance().getFabricEconomyHandler();

        //Load player's balance
        CompletableFuture.runAsync(() -> {
            fabricEconomyHandler.setBalanceMongo(player.getUniqueId(), fabricEconomyHandler.getBalanceFromMongo(player.getUniqueId()));
        });
    }
}
