package us.blockgame.fabric.board.task;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import us.blockgame.fabric.FabricPlugin;
import us.blockgame.fabric.board.FabricBoard;
import us.blockgame.fabric.board.FabricBoardHandler;
import us.blockgame.fabric.board.ScoreHelper;

public class FabricBoardUpdateTask extends BukkitRunnable {

    public void run() {
        FabricBoardHandler fabricBoardHandler = FabricPlugin.getInstance().getFabricBoardHandler();

        FabricBoard fabricBoard = fabricBoardHandler.getFabricBoard();

        Bukkit.getOnlinePlayers().stream().filter(ScoreHelper::hasScore).forEach(p -> {
            ScoreHelper scoreHelper = ScoreHelper.getByPlayer(p);

            scoreHelper.setTitle(fabricBoard.getTitle(p));
            scoreHelper.setSlotsFromList(fabricBoard.getSlots(p));
        });
    }
}
