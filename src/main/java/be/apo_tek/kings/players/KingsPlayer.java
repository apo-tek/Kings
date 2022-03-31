package be.apo_tek.kings.players;

import org.bukkit.entity.Player;

public class KingsPlayer {

    private final Player kingsPlayerSource;
    private boolean kingsPlayerLiving;
    private int kingsPlayerLevel;


    protected KingsPlayer(Player sourcePlayer){
        kingsPlayerSource = sourcePlayer;
    }

    protected Player getSourcePlayer() {
        return kingsPlayerSource;
    }

    protected boolean isKingsPlayerDead() {
        return kingsPlayerLiving;
    }

    protected void setKingsPlayerDead(boolean living) {
        kingsPlayerLiving = living;
    }

    protected int getKingsPlayerLevel() {
        return kingsPlayerLevel;
    }

    protected void setKingsPlayerLevel(int level) {
        kingsPlayerLevel = level;
    }

}
