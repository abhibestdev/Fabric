package us.blockgame.fabric.time.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import us.blockgame.fabric.event.BaseEvent;
import us.blockgame.fabric.time.Timer;

@AllArgsConstructor
public class TimerTickEvent extends BaseEvent {

    @Getter private Timer timer;

}
