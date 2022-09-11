package us.blockgame.fabric.menu;

import org.bukkit.Bukkit;
import us.blockgame.fabric.FabricPlugin;
import us.blockgame.fabric.command.FabricCommandHandler;
import us.blockgame.fabric.menu.command.TestMenuCommand;

public class FabricMenuHandler {

    public FabricMenuHandler() {
        //If in example mode, register the test menu
        if (FabricPlugin.isExampleMode()) {

            FabricCommandHandler fabricCommandHandler = FabricPlugin.getInstance().getFabricCommandHandler();
            fabricCommandHandler.registerCommand(new TestMenuCommand());

        }

        //Register listener
        Bukkit.getPluginManager().registerEvents(new FabricMenuListener(), FabricPlugin.getInstance());
    }
}
