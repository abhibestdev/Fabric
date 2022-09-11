package us.blockgame.fabric.util;

import org.bukkit.Bukkit;
import us.blockgame.fabric.FabricPlugin;

public class ThreadUtil {

    public static void runSync(Runnable runnable) {
        Bukkit.getScheduler().runTask(FabricPlugin.getInstance(), runnable);
    }

    public static void runAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(FabricPlugin.getInstance(), runnable);
    }
}
