package be.apo_tek.kings.game;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private List<Game> gameList = new ArrayList<>();
    private static boolean isGame;


    public GameManager(){

    }

    public boolean isGame() {
        isGame = !gameList.isEmpty();
        return isGame;
    }
}
