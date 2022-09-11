package us.blockgame.fabric.economy;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EconomyType {

    DOLLARS("$", true),
    EUROS("€", true),
    POUNDS("£", true),
    POINTS("", false);

    private String symbol;
    private boolean transferable;
}
