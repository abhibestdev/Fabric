package us.blockgame.fabric.board;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import us.blockgame.fabric.FabricPlugin;

public class FabricBoardListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        //Create scoreboard if enabled
        FabricBoardHandler fabricBoardHandler = FabricPlugin.getInstance().getFabricBoardHandler();
        if (fabricBoardHandler.isScoreboardEnabled()) {
            ScoreHelper.createScore(player);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        //Remove scoreboard for player
        if (ScoreHelper.hasScore(player)) {
            ScoreHelper.removeScore(player);
        }
    }
}
