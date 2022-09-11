package us.blockgame.fabric.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Logger {

    public static void success(JavaPlugin javaPlugin, String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[" + javaPlugin.getName() + "] " + message);
    }

    public static void info(JavaPlugin javaPlugin, String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[" + javaPlugin.getName() + "] " + message);
    }
    public static void error(JavaPlugin javaPlugin, String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[" + javaPlugin.getName() + "] " + message);
    }
}
