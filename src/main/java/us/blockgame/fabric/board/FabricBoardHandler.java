package us.blockgame.fabric.board;

import lombok.Getter;
import org.bukkit.Bukkit;
import us.blockgame.fabric.FabricPlugin;
import us.blockgame.fabric.board.impl.FabricExampleBoard;
import us.blockgame.fabric.board.task.FabricBoardUpdateTask;

public class FabricBoardHandler {

    @Getter private FabricBoard fabricBoard;

    public FabricBoardHandler() {
        //Register example board
        if (FabricPlugin.isExampleMode()) {
            setScoreboard(new FabricExampleBoard());
        }

        //Register listeners
        Bukkit.getPluginManager().registerEvents(new FabricBoardListener(), FabricPlugin.getInstance());
    }

    public void setScoreboard(FabricBoard fabricBoard) {
        this.fabricBoard = fabricBoard;

        //Initialize scoreboard task
        new FabricBoardUpdateTask().runTaskTimerAsynchronously(FabricPlugin.getInstance(), 0L, fabricBoard.getUpdateInterval());
    }

    public boolean isScoreboardEnabled() {
        return fabricBoard != null;
    }
}
