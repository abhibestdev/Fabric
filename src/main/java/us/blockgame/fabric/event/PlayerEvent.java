package us.blockgame.fabric.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
@AllArgsConstructor
public class PlayerEvent extends BaseEvent {

    private Player player;
}
