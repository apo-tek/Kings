package be.apo_tek.kings.events;

import be.apo_tek.kings.game.KingsGame;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public abstract class GameEvent extends Event {

    protected KingsGame kingsGame;

    public GameEvent(@NotNull KingsGame kingsGame) {this.kingsGame = kingsGame;}

    public GameEvent(@NotNull KingsGame kingsGame, boolean async) {super(async);
        this.kingsGame = kingsGame;
    }

    public final @NotNull KingsGame getPlayer() {return this.kingsGame;}


}
