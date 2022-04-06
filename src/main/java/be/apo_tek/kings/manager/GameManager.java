package be.apo_tek.kings.manager;


import be.apo_tek.kings.game.KingsGame;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GameManager implements CommandExecutor {

    private static final ArrayList<KingsGame> gamesList = new ArrayList<>();

    public static ArrayList<KingsGame> getGamesList() {
        return gamesList;
    }

    public static void addGameToList(KingsGame kingsGame){
        gamesList.add(kingsGame);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return false;
    }
}


