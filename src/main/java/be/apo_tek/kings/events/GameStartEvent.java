package be.apo_tek.kings.events;

import be.apo_tek.kings.game.KingsGame;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class GameStartEvent extends GameEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;


    public GameStartEvent(@NotNull KingsGame kingsGame, @NotNull Object starter) {
        super(kingsGame);
    }

    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public @NotNull static HandlerList getHandlersList(){
        return handlers;
    }
}
