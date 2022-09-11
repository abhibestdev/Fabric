package us.blockgame.fabric.command.parameter;

import org.bukkit.command.CommandSender;
import us.blockgame.fabric.command.framework.ParameterType;

public class StringParameterType implements ParameterType<String> {

    @Override
    public String transform(CommandSender sender, String source) {
        return source;
    }
}
