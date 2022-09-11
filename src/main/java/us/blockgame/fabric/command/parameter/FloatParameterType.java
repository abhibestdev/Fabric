package us.blockgame.fabric.command.parameter;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import us.blockgame.fabric.command.framework.ParameterType;

public class FloatParameterType implements ParameterType<Float> {

    @Override
    public Float transform(CommandSender sender, String source) {
        if (source.toLowerCase().contains("e")) {
            sender.sendMessage(ChatColor.RED + source + " is not a valid number.");
            return null;
        }
        try {
            float parsed = Float.parseFloat(source);
            if (Float.isNaN(parsed) || !Float.isFinite(parsed)) {
                sender.sendMessage(ChatColor.RED + source + " is not a valid number.");
                return null;
            }
            return parsed;
        } catch (NumberFormatException ex) {
            sender.sendMessage(ChatColor.RED + source + " is not a valid number.");
            return null;
        }
    }
}
