package be.apo_tek.kings.players;


import be.apo_tek.kings.Main;
import be.apo_tek.kings.enums.States;
import com.google.common.base.Preconditions;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class King extends KingsPlayer{

    public King(Player player) {super(player);}

    public void regenerate(){
        getSourcePlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0,
                false, false, false));
        getSourcePlayer().addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE,
                2,false, false, false));
    }

    public void suddenState(){
        //Preconditions.checkArgument(Main.getPluginInstance().getGameState().equals(States.SUDDEN));
        getSourcePlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE,
                2,false, false, false));
        getSourcePlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE,
                2,false, false, false));
        getSourcePlayer().addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE,
                2,false, false, false));
    }
}
