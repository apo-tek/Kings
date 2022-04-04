package be.apo_tek.kings;

import be.apo_tek.kings.enums.States;
import be.apo_tek.kings.manager.GameManager;
import be.apo_tek.kings.manager.PlayersManager;
import be.apo_tek.kings.io.Listener;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;


public class Main extends JavaPlugin {

    /*
      This class contains the root of the Plugin
     */
    private static Main pluginInstance;
    private boolean isDebug;
    private GameManager gameManager;
    private Listener eventsListener;
    private PlayersManager playersManager;
    private States gameState;


    @Override
    public void onEnable(){
        super.onEnable();
        isDebug = true;
        pluginInstance = this;
        sendLine(Constants.ENABLE_MESSAGE);
        eventsListener = new Listener();
        getServer().getPluginManager().registerEvents(eventsListener, this);
        playersManager = new PlayersManager();
        gameManager = new GameManager();
        Objects.requireNonNull(getCommand(Constants.PLAYERS_COMMAND_IDENTIFIER)).setExecutor(playersManager);
        getConfig().addDefault(Attribute.GENERIC_ATTACK_SPEED.name(), Constants.GENERIC_ATTACK_SPEED);
        getConfig().options().copyDefaults(Constants.COPY_DEFAULT);
        saveConfig();
    }

    @Override
    public void onDisable(){
        super.onDisable();
        sendLine(Constants.DISABLE_MESSAGE);
    }

    public static Main getPluginInstance() {return pluginInstance;}

    public Listener getEventsListener() {return eventsListener;}

    public PlayersManager getPlayersManager() {return playersManager;}

    public States getGameState() {return gameState;}

    public void setGameState(States state){gameState = state;}

    public boolean isDebug() {return isDebug;}

    public GameManager getGameManager() {return gameManager;}

    public void sendLine(String msg){Bukkit.getConsoleSender().sendMessage(msg);}

}
