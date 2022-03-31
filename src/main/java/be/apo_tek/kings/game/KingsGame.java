package be.apo_tek.kings.game;

import be.apo_tek.kings.enums.States;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class KingsGame implements Game{

    protected String gameID;
    protected States gameState;
    protected HashMap<Player, UUID> players;


    public KingsGame(){
        gameID = "KINGS-" + new Random().longs().toString();
        gameState = States.WAITING;
        players = new HashMap<>();
    }

    @Override
    public void setState(States state) {gameState = state;}

    @Override
    public void setGameID(String ID) {gameID = ID;}

    @Override
    public void addPlayer(Player player) {if(!players.containsKey(player))players.put(player, player.getUniqueId());}

    @Override
    public void deletePlayer(Player player) {players.remove(player);}

    @Override
    public States getState() {return gameState;}

    @Override
    public String getID() {return gameID;}
}
