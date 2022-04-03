package be.apo_tek.kings.manager;


import be.apo_tek.kings.game.KingsGame;

import java.util.ArrayList;
import java.util.Collection;

public class GameManager {

    private static final ArrayList<KingsGame> gamesList = new ArrayList<>();

    public GameManager(){

    }

    public static ArrayList<KingsGame> getGamesList() {
        return gamesList;
    }

    public static void addGameToList(Collection<? extends KingsGame> kingsGames) {
        gamesList.addAll(kingsGames);
    }

    public static void addGameToList(KingsGame kingsGame){
        gamesList.add(kingsGame);
    }
}


