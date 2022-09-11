package us.blockgame.fabric.redis.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import us.blockgame.fabric.event.BaseEvent;

import java.util.Map;

@AllArgsConstructor
public class RedisReceieveEvent extends BaseEvent {

    @Getter private String channel;
    @Getter private Map<String, Object> messageMap;

}
