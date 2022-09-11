package us.blockgame.fabric.event;

import org.bukkit.Bukkit;
import us.blockgame.fabric.FabricPlugin;

public class FabricEventHandler {

    public FabricEventHandler() {
        //Regsiter listener
        Bukkit.getPluginManager().registerEvents(new FabricEventListener(), FabricPlugin.getInstance());
    }
}
