package be.apo_tek.kings.manager;

import be.apo_tek.kings.Constants;
import be.apo_tek.kings.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class PlayersManager implements CommandExecutor {

    private final Main pluginInstance = Main.getPluginInstance();

    private final ArrayList<Player> livePlayers = new ArrayList<>();


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s,
                             @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) return false;
        if (!"players".equals(s)) return false;
        retrieveLivePlayers().forEach(player -> Bukkit.getServer().sendMessage(player.name()));
        return true;
    }

    public void addLivePlayers(Player player){
        if(livePlayers.contains(player)) return;
        livePlayers.add(player);
    }

    public ArrayList<Player> retrieveLivePlayers(){
        return livePlayers;
    }

}
