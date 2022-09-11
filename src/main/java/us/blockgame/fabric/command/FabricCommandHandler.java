package us.blockgame.fabric.command;

import com.google.common.collect.Maps;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import us.blockgame.fabric.FabricPlugin;
import us.blockgame.fabric.command.defaults.CommandInfoCommand;
import us.blockgame.fabric.command.defaults.TestCommand;
import us.blockgame.fabric.command.framework.Command;
import us.blockgame.fabric.command.framework.CommandFramework;
import us.blockgame.fabric.command.framework.ParameterType;
import us.blockgame.fabric.command.parameter.*;
import us.blockgame.fabric.util.StringUtil;

import java.lang.reflect.Method;
import java.util.*;

public class FabricCommandHandler {

    private CommandFramework commandFramework;
    private List<Object> commands = new ArrayList<>();
    private Map<Class<?>, ParameterType<?>> parameterTypeMap = Maps.newHashMap();

    public FabricCommandHandler() {
        setupCommandFramework();

        //Register parameter types
        addParameterType(Boolean.class, new BooleanParameterType());
        addParameterType(Double.class, new DoubleParameterType());
        addParameterType(Float.class, new FloatParameterType());
        addParameterType(Integer.class, new IntegerParameterType());
        addParameterType(OfflinePlayer.class, new OfflinePlayerParameterType());
        addParameterType(Player.class, new PlayerParameterType());
        addParameterType(String.class, new StringParameterType());
        addParameterType(GameMode.class, new GameModeParameterType());

        //Setup default commands
        registerCommand(new CommandInfoCommand());
        registerCommand(new TestCommand());
    }

    private void setupCommandFramework() {
        commandFramework = new CommandFramework(FabricPlugin.getInstance());
    }

    public void registerCommand(Object object) {
        commandFramework.registerCommands(object);
        commands.add(object);
    }

    public ParameterType<?> getParameterType(Class<?> clazz) {
        return parameterTypeMap.get(clazz);
    }

    public void addParameterType(Class<?> clazz, ParameterType<?> parameterType) {
        parameterTypeMap.put(clazz, parameterType);
    }

    public Object getCommand(String name) {
        for (Object o : commands) {
            for (Method method : o.getClass().getMethods()) {
                if (method.getAnnotation(Command.class) != null) {
                    Command command = method.getAnnotation(Command.class);
                    if (command.name().equalsIgnoreCase(name) || StringUtil.contains(command.aliases(), name)) {
                        return o;
                    }
                }
            }
        }
        return null;
    }

    public List<String> getAliases(Object o, String name) {
        List<String> aliases = new ArrayList<>();
        for (Method method : o.getClass().getMethods()) {
            if (method.getAnnotation(Command.class) != null) {
                Command command = method.getAnnotation(Command.class);

                if (command.name().equalsIgnoreCase(name) || StringUtil.contains(command.aliases(), name)) {

                    aliases.add(command.name().toLowerCase());
                    Arrays.stream(command.aliases()).forEach(a -> aliases.add(a.toLowerCase()));
                }
            }
        }
        return aliases;
    }

    public String getPermission(Object o, String name) {
        for (Method method : o.getClass().getMethods()) {
            if (method.getAnnotation(Command.class) != null) {
                Command command = method.getAnnotation(Command.class);

                if (command.name().equalsIgnoreCase(name) || StringUtil.contains(command.aliases(), name)) {
                    return command.permission();
                }
            }
        }
        return null;
    }
}
