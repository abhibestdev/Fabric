package us.blockgame.fabric.command.framework;

import org.bukkit.command.CommandSender;

public interface ParameterType<T> {

    T transform(CommandSender sender, String source);
}
