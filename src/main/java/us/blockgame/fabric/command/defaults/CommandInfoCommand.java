package us.blockgame.fabric.command.defaults;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import us.blockgame.fabric.FabricPlugin;
import us.blockgame.fabric.command.FabricCommandHandler;
import us.blockgame.fabric.command.framework.Command;
import us.blockgame.fabric.command.framework.Param;
import us.blockgame.fabric.util.StringUtil;

import java.util.List;

public class CommandInfoCommand {

    @Command(name = "commandinfo", aliases = {"cmdinfo"}, permission = "op")
    public void commandInfo(CommandSender sender, @Param(name = "command") String command) {
        FabricCommandHandler fabricCommandHandler = FabricPlugin.getInstance().getFabricCommandHandler();

        Object commandNode = fabricCommandHandler.getCommand(command);

        //Check if command exists
        if (commandNode == null) {
            sender.sendMessage(ChatColor.RED + "No command with the name \"" + command + "\" found.");
            return;
        }

        String permission = fabricCommandHandler.getPermission(commandNode, command.toLowerCase());
        List<String> aliases = fabricCommandHandler.getAliases(commandNode, command.toLowerCase());

        sender.sendMessage(ChatColor.GREEN + "The command " + command.toLowerCase() + " belongs to the plugin " + JavaPlugin.getProvidingPlugin(commandNode.getClass()).getName() + ".");
        if (aliases.size() > 0) {
            sender.sendMessage(ChatColor.GRAY + "Aliases: " + StringUtil.join(aliases, ", "));
        }
        if (!permission.isEmpty()) {
            sender.sendMessage(ChatColor.GRAY + "Permission: " + permission);
        }
        return;
    }
}
