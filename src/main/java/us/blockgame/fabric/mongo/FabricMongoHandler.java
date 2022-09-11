package us.blockgame.fabric.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import us.blockgame.fabric.FabricPlugin;
import us.blockgame.fabric.util.Logger;

public class FabricMongoHandler {

    @Getter
    private MongoClient mongoClient;
    @Getter
    private MongoDatabase mongoDatabase;

    public FabricMongoHandler() {
        String ip = FabricPlugin.getInstance().getConfig().getString("mongo.ip");
        int port = FabricPlugin.getInstance().getConfig().getInt("mongo.port");
        String database = FabricPlugin.getInstance().getConfig().getString("mongo.database");
        boolean usePassword = FabricPlugin.getInstance().getConfig().getBoolean("mongo.use-password");
        String username = FabricPlugin.getInstance().getConfig().getString("mongo.username");
        String password = FabricPlugin.getInstance().getConfig().getString("mongo.password");

        try {
            String mongoUri = "mongodb://" + (usePassword ? username + ":" + password + "@" : "") + ip + ":" + port;
            MongoClientURI uri = new MongoClientURI(mongoUri, MongoClientOptions.builder().maxWaitTime(30000).maxConnectionIdleTime(5000).threadsAllowedToBlockForConnectionMultiplier(500));

            mongoClient = new MongoClient(uri);
            mongoDatabase = mongoClient.getDatabase(database);
            mongoClient.getAddress();
            Logger.success(FabricPlugin.getInstance(), "Successfully established Mongo connection.");
        } catch (Exception ex) {
            Logger.error(FabricPlugin.getInstance(), "Could not establish Mongo connection.");
        }
    }

    public MongoCollection getCollection(String collection) {
        return mongoDatabase.getCollection(collection);
    }

    public boolean collectionExists(String collection) {
        boolean exists = false;
        for (String collections : mongoDatabase.listCollectionNames()) {
            if (collection.equalsIgnoreCase(collections)) exists = true;
        }
        return exists;
    }

}
