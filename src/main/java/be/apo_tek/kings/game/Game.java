package be.apo_tek.kings.game;

import be.apo_tek.kings.enums.States;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.UUID;

public interface Game {

    String gameID = "";

    States state = States.WAITING;

    HashMap<Player, UUID> players = new HashMap<>();

    abstract void setState(States state);

    abstract void setGameID(String ID);

    abstract void addPlayer(Player player);

    abstract void deletePlayer(Player player);

    abstract States getState();

    abstract String getID();


}
