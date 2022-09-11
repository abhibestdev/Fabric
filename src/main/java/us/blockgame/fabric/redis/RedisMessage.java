package us.blockgame.fabric.redis;

import com.google.gson.reflect.TypeToken;
import us.blockgame.fabric.FabricPlugin;

import java.lang.reflect.Type;
import java.util.Map;

public class RedisMessage {

    public static Map<String, Object> deserialize(String string) {
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        return FabricPlugin.getGson().fromJson(string, type);
    }

    public static String serialize(Map<String, Object> map) {
        return FabricPlugin.getGson().toJson(map);
    }

}