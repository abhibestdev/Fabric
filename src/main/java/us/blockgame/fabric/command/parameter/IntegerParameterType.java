package us.blockgame.fabric.command.parameter;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import us.blockgame.fabric.command.framework.ParameterType;

public class IntegerParameterType implements ParameterType<Integer> {

    @Override
    public Integer transform(CommandSender sender, String source) {
        try {
            return Integer.parseInt(source);
        } catch (NumberFormatException ex) {
            sender.sendMessage(ChatColor.RED + source + " is not a valid number.");
            return null;
        }
    }
}
