package us.blockgame.fabric.command.defaults;

import org.bukkit.command.CommandSender;
import us.blockgame.fabric.command.framework.Command;
import us.blockgame.fabric.command.framework.Flag;
import us.blockgame.fabric.command.framework.Param;

public class TestCommand {

    @Command(name = "test")
    public void test(CommandSender sender, @Flag(value = {"s", "silent"}) boolean silent, @Param(name = "reason", wildcard = true) String reason) {
        sender.sendMessage(silent + " - " + reason);
    }
}
