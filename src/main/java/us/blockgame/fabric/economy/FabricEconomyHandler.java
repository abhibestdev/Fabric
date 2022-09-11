package us.blockgame.fabric.economy;

import com.google.common.collect.Maps;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import us.blockgame.fabric.FabricPlugin;
import us.blockgame.fabric.command.FabricCommandHandler;
import us.blockgame.fabric.economy.command.BalanceCommand;
import us.blockgame.fabric.economy.command.EconomyCommand;
import us.blockgame.fabric.economy.command.PayCommand;
import us.blockgame.fabric.mongo.FabricMongoHandler;
import us.blockgame.fabric.util.MapUtil;
import us.blockgame.fabric.util.ThreadUtil;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

public class FabricEconomyHandler {

    @Setter @Getter private EconomyType economyType;
    private Map<UUID, Map<EconomyType, Double>> economyMap = Maps.newHashMap();

    public FabricEconomyHandler() {
        //Setup economy in example mode
        if (FabricPlugin.isExampleMode()) {
            initializeEconomy(EconomyType.DOLLARS);
        }
    }

    public void initializeEconomy(EconomyType economyType) {
        //Set economy type
        setEconomyType(economyType);

        FabricCommandHandler fabricCommandHandler = new FabricCommandHandler();

        //Register commands
        fabricCommandHandler.registerCommand(new BalanceCommand());
        fabricCommandHandler.registerCommand(new EconomyCommand());

        //If currency can be exchanged then enable certain functions
        if (economyType.isTransferable()) {
            //Register pay command
            fabricCommandHandler.registerCommand(new PayCommand());
        }
        //Register listeners
        Bukkit.getPluginManager().registerEvents(new FabricEconomyListener(), FabricPlugin.getInstance());
    }

    public double getBalance(UUID uuid, EconomyType economyType) {
        Map<EconomyType, Double> moneyMap = economyMap.getOrDefault(uuid, Maps.newHashMap());
        return moneyMap.getOrDefault(economyType, 0D);
    }

    public double getBalance(UUID uuid) {
        return getBalance(uuid, economyType);
    }

    public void setBalance(UUID uuid, EconomyType economyType, double balance) {
        Map<EconomyType, Double> moneyMap = economyMap.getOrDefault(uuid, Maps.newHashMap());
        moneyMap.put(economyType, balance);

        economyMap.put(uuid, moneyMap);

        ThreadUtil.runAsync(() -> {
            setBalanceMongo(uuid, balance);
        });
        return;
    }

    public void setBalance(UUID uuid, double balance) {
        setBalance(uuid, economyType, balance);
    }

    //Method to get player's balance regardless of if they are online
    public double getOfflineBalance(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);

        //Return online balance
        if (player != null) return getBalance(player.getUniqueId());

        FabricMongoHandler mongoHandler = FabricPlugin.getInstance().getFabricMongoHandler();
        MongoCollection mongoCollection = mongoHandler.getCollection("economy");

        Document document = (Document) mongoCollection.find(Filters.eq("_id", uuid)).first();
        if (document != null) {
            return document.getDouble(economyType.toString());
        }
        return 0.0;
    }

    //Method to get player's balance from mongo
    public double getBalanceFromMongo(UUID uuid) {
        FabricMongoHandler mongoHandler = FabricPlugin.getInstance().getFabricMongoHandler();
        MongoCollection mongoCollection = mongoHandler.getCollection("economy");

        Document document = (Document) mongoCollection.find(Filters.eq("_id", uuid)).first();
        if (document != null) {
            return document.getDouble(economyType.toString());
        }
        return 0.0;
    }

    //Method to set player's balance regardless of if they are online
    public void setOfflineBalance(UUID uuid, double balance) {
        Player player = Bukkit.getPlayer(uuid);

        //Set online balance
        if (player != null) {
            setBalance(player.getUniqueId(), balance);
            return;
        }

        FabricMongoHandler mongoHandler = FabricPlugin.getInstance().getFabricMongoHandler();
        MongoCollection mongoCollection = mongoHandler.getCollection("economy");

        Document document = (Document) mongoCollection.find(Filters.eq("_id", uuid)).first();

        if (document != null) {
            Map<String, Object> documentMap = MapUtil.cloneDocument(document);

            documentMap.put(economyType.toString(), balance);

            //Delete old document
            mongoCollection.deleteOne(document);

            mongoCollection.insertOne(new Document(documentMap));
        } else {
            Map<String, Object> documentMap = MapUtil.createMap("_id", uuid);

            Arrays.stream(EconomyType.values()).filter(economyType -> economyType != this.economyType).forEach(economyType -> {
                documentMap.put(economyType.toString(), 0.0);
            });
            documentMap.put(economyType.toString(), balance);

            //Insert document
            mongoCollection.insertOne(new Document(documentMap));
            return;
        }
    }

    //Method to set player's balance directly in mongo
    public void setBalanceMongo(UUID uuid, double balance) {
        FabricMongoHandler mongoHandler = FabricPlugin.getInstance().getFabricMongoHandler();
        MongoCollection mongoCollection = mongoHandler.getCollection("economy");

        Document document = (Document) mongoCollection.find(Filters.eq("_id", uuid)).first();

        if (document != null) {
            Map<String, Object> documentMap = MapUtil.cloneDocument(document);

            documentMap.put(economyType.toString(), balance);

            //Delete old document
            mongoCollection.deleteOne(document);

            mongoCollection.insertOne(new Document(documentMap));
        } else {
            Map<String, Object> documentMap = MapUtil.createMap("_id", uuid);

            Arrays.stream(EconomyType.values()).filter(economyType -> economyType != this.economyType).forEach(economyType -> {
                documentMap.put(economyType.toString(), 0.0);
            });
            documentMap.put(economyType.toString(), balance);

            //Insert document
            mongoCollection.insertOne(new Document(documentMap));
            return;
        }
    }

}
