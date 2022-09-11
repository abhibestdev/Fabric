package us.blockgame.fabric.player.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import us.blockgame.fabric.FabricPlugin;
import us.blockgame.fabric.command.framework.Command;

public class BuildCommand {

    @Command(name = "build", aliases = {"builder", "building"}, permission = "fabric.command.build", inGameOnly = true)
    public void build(CommandSender sender) {
        Player player = (Player) sender;

        //Add or remove build metadeta depending on whether the user already possesses it
        if (player.hasMetadata("build")) {
            player.removeMetadata("build", FabricPlugin.getInstance());
        } else {
            player.setMetadata("build", new FixedMetadataValue(FabricPlugin.getInstance(), true));
        }
        player.sendMessage(ChatColor.GREEN + "You are " + (player.hasMetadata("build") ? "now" : "no longer") + " in builder mode.");
        return;
    }
}
