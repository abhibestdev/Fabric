package us.blockgame.fabric.redis;

import us.blockgame.fabric.redis.event.RedisReceieveEvent;
import us.blockgame.fabric.util.ThreadUtil;

import java.util.Map;

public class RedisListener {

    @RedisSubscriber("fabric")
    public void onFabric(Map<String, Object> messageMap) {
        //Call RedisReceiveEvent
        ThreadUtil.runSync(() -> {
            new RedisReceieveEvent("fabric", messageMap).call();
        });
    }
}
