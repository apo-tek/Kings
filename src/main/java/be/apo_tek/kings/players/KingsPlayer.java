package be.apo_tek.kings.players;

import org.bukkit.entity.Player;

public class KingsPlayer {

    private final Player kingsPlayerSource;
    private boolean kingsPlayerLiving;
    private int kingsPlayerLevel;


    public KingsPlayer(Player sourcePlayer){
        kingsPlayerSource = sourcePlayer;
    }

    public Player getSourcePlayer() {return kingsPlayerSource;}

    public boolean isKingsPlayerDead() {return kingsPlayerLiving;}

    public void setKingsPlayerDead(boolean living) {kingsPlayerLiving = living;}

    public int getKingsPlayerLevel() {return kingsPlayerLevel;}

    public void setKingsPlayerLevel(int level) {kingsPlayerLevel = level;}

    public void removePotionEffects(){
        getSourcePlayer().getActivePotionEffects().forEach(potionEffect ->
                getSourcePlayer().removePotionEffect(potionEffect.getType()));
    }
}
