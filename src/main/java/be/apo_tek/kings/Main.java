package be.apo_tek.kings;

import be.apo_tek.kings.manager.BlockManager;
import be.apo_tek.kings.manager.GameManager;
import be.apo_tek.kings.manager.ItemManager;
import be.apo_tek.kings.manager.PlayersManager;
import be.apo_tek.kings.io.Listener;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public class Main extends JavaPlugin {

    /*
      This class contains the root of the Plugin
     */
    private static Main pluginInstance;
    private boolean isDebug;
    private Listener eventsListener;
    private PlayersManager playersManager;
    private GameManager gameManager;
    private BlockManager blockManager;
    private ItemManager itemManager;


    @Override
    public void onEnable(){
        super.onEnable();
        isDebug = true;
        pluginInstance = this;
        sendLine(Constants.ENABLE_MESSAGE);
        eventsListener = new Listener();
        playersManager = new PlayersManager();
        gameManager = new GameManager();
        blockManager = new BlockManager();
        itemManager = new ItemManager();
        setEventsListener(eventsListener);
        removeAttackCooldown();
        registerCommandExecutor(Constants.PLAYERS_COMMAND_IDENTIFIER, playersManager);
        registerCommandExecutor(Constants.GAME_COMMAND_IDENTIFIER, gameManager);
    }

    @Override
    public void onDisable(){
        super.onDisable();
        sendLine(Constants.DISABLE_MESSAGE);
    }

    public static Main getPluginInstance() {return pluginInstance;}

    public boolean isDebug() {return isDebug;}

    public void sendLine(String msg){Bukkit.getConsoleSender().sendMessage(msg);}

    private void setEventsListener(Listener listener){
        getServer().getPluginManager().registerEvents(listener, this);
    }

    private void removeAttackCooldown(){
        getConfig().addDefault(Attribute.GENERIC_ATTACK_SPEED.name(), Constants.GENERIC_ATTACK_SPEED);
        getConfig().options().copyDefaults(Constants.COPY_DEFAULT);
        saveConfig();
    }

    private void registerCommandExecutor(@NotNull String commandName, @NotNull CommandExecutor commandExecutor){
        Objects.requireNonNull(getCommand(commandName)).setExecutor(commandExecutor);
    }

}
