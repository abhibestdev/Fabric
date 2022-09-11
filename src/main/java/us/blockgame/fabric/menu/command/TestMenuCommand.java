package us.blockgame.fabric.menu.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.blockgame.fabric.command.framework.Command;
import us.blockgame.fabric.menu.impl.ExampleMenu;

public class TestMenuCommand {

    @Command(name = "testmenu", permission = "op", inGameOnly = true)
    public void testMenu(CommandSender sender) {
        Player player = (Player) sender;

        ExampleMenu exampleMenu = new ExampleMenu();

        //Open menu
        exampleMenu.openMenu(player);
    }
}
