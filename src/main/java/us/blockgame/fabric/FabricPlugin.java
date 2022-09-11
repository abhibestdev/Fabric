package us.blockgame.fabric;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import okhttp3.OkHttpClient;
import org.bukkit.plugin.java.JavaPlugin;
import us.blockgame.fabric.board.FabricBoardHandler;
import us.blockgame.fabric.command.FabricCommandHandler;
import us.blockgame.fabric.economy.FabricEconomyHandler;
import us.blockgame.fabric.event.FabricEventHandler;
import us.blockgame.fabric.menu.FabricMenuHandler;
import us.blockgame.fabric.mongo.FabricMongoHandler;
import us.blockgame.fabric.nametag.FabricNametagHandler;
import us.blockgame.fabric.player.FabricPlayerHandler;
import us.blockgame.fabric.redis.FabricRedisHandler;
import us.blockgame.fabric.redis.RedisListener;
import us.blockgame.fabric.request.FabricRequestHandler;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FabricPlugin extends JavaPlugin {

    @Getter private static FabricPlugin instance;
    @Getter private static Gson gson;
    @Getter private static OkHttpClient okHttpClient;
    @Getter private static Random random;
    @Getter private static boolean exampleMode = false;

    @Getter private FabricRequestHandler fabricRequestHandler;
    @Getter private FabricMongoHandler fabricMongoHandler;
    @Getter private FabricRedisHandler fabricRedisHandler;
    @Getter private FabricCommandHandler fabricCommandHandler;
    @Getter private FabricEconomyHandler fabricEconomyHandler;
    @Getter private FabricMenuHandler fabricMenuHandler;
    @Getter private FabricBoardHandler fabricBoardHandler;
    @Getter private FabricPlayerHandler fabricPlayerHandler;
    @Getter private FabricNametagHandler fabricNametagHandler;
    @Getter private FabricEventHandler fabricEventHandler;

    @Override
    public void onEnable() {
        instance = this;
        gson = new GsonBuilder().setPrettyPrinting().create();
        okHttpClient = (new OkHttpClient.Builder()).connectTimeout(2L, TimeUnit.SECONDS).writeTimeout(2L, TimeUnit.SECONDS).readTimeout(2L, TimeUnit.SECONDS).build();
        random = new Random();

        //Setup config
        getConfig().options().copyDefaults(true);
        saveConfig();

        //Setup handlers
        registerHandlers();
    }

    @Override
    public void onDisable() {
        //Close Mongo connection
   //     fabricMongoHandler.getMongoClient().close();

        //Close Redis connection
        fabricRedisHandler.getJedisPool().close();
    }

    private void registerHandlers() {
        fabricRequestHandler = new FabricRequestHandler();
       // fabricMongoHandler = new FabricMongoHandler();
        fabricRedisHandler = new FabricRedisHandler(
                getConfig().getString("redis.ip"),
                getConfig().getInt("redis.port"),
                30_000,
                getConfig().getString("redis.password"));

        fabricRedisHandler.registerListeners(
                new RedisListener()
        );
        fabricRedisHandler.initialize();
        fabricCommandHandler = new FabricCommandHandler();
       // fabricEconomyHandler = new FabricEconomyHandler();
        fabricMenuHandler = new FabricMenuHandler();
        fabricBoardHandler = new FabricBoardHandler();
        fabricPlayerHandler = new FabricPlayerHandler();
        fabricNametagHandler = new FabricNametagHandler();
        fabricEventHandler = new FabricEventHandler();
    }


}
