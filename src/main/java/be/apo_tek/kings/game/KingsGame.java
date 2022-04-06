package be.apo_tek.kings.game;

import be.apo_tek.kings.Constants;
import be.apo_tek.kings.enums.States;
import be.apo_tek.kings.interfaces.Joinable;
import be.apo_tek.kings.manager.GameManager;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class KingsGame implements Joinable {

    protected String gameID;
    protected States gameState;
    protected HashMap<Player, UUID> players = new HashMap<>();
    protected Boolean openState;


    private KingsGame(){
        setGameID("KINGS-" + UUID.randomUUID());
        initGame();
        openOrCloseGame(Constants.CLOSE_NAME);
    }

    public KingsGame createGame(){
        KingsGame kingsGame = new KingsGame();
        GameManager.addGameToList(kingsGame);
        return kingsGame;
    }
    public void initGame(){setState(States.WAITING);}

    public void startGame(){setState(States.PLAYING);}

    public void setState(States state) {gameState = state;}

    public void setGameID(String ID) {gameID = ID;}

    public void addPlayer(Player player) {if(!players.containsKey(player))players.put(player, player.getUniqueId());}

    public void removePlayer(Player player) {players.remove(player);}

    public States getState() {return gameState;}

    public String getID() {return gameID;}

    @Override
    public void openOrCloseGame(String openOrClose) {openState = openOrClose.equalsIgnoreCase(Constants.OPEN_NAME);}
}
