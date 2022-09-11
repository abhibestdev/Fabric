package us.blockgame.fabric.player;

import us.blockgame.fabric.FabricPlugin;
import us.blockgame.fabric.command.FabricCommandHandler;
import us.blockgame.fabric.player.command.BuildCommand;
import us.blockgame.fabric.player.command.GameModeCommand;
import us.blockgame.fabric.player.command.MoreCommand;

public class FabricPlayerHandler {

    public FabricPlayerHandler() {
        //Register commands
        FabricCommandHandler fabricCommandHandler = FabricPlugin.getInstance().getFabricCommandHandler();
        fabricCommandHandler.registerCommand(new BuildCommand());
        fabricCommandHandler.registerCommand(new GameModeCommand());
        fabricCommandHandler.registerCommand(new MoreCommand());
    }
}
