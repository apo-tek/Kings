package be.apo_tek.kings;

/*
This class, which I'm not sure of the real utility, consists of static methods returning code constants. Those are
public and so accessible everywhere (apart from non-static context which may cause conflicts).
 */

import be.apo_tek.kings.manager.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Constants {

    public static int GENERIC_ATTACK_SPEED() {return 24;}
    public static String PLAYERS_COMMAND_IDENTIFIER(){return "players";}
    public static String TECHNOLOGICAL_NAME(){return "Technology";}
    public static String MILITARY_NAME(){return "Military";}
    public static String FARMING_NAME(){return "Agriculture";}
    public static boolean COPY_DEFAULT(){return true;}
    public static String ENABLE_MESSAGE(){return "§cEnabled";}
    public static String DISABLE_MESSAGE(){return "§cDisabled";}
    public static ChatColor FORMAT_RED() {return ChatColor.RED;}
    public static ChatColor FORMAT_BLUE() {return ChatColor.BLUE;}
    public static ChatColor FORMAT_GREEN() {return ChatColor.GREEN;}
    public static Material BREWING_STAND(){return Material.BREWING_STAND;}


    public static ItemStack TECHNOLOGICAL_GUI() {return ItemManager.createItem(BREWING_STAND(),
            Constants.FORMAT_BLUE() + TECHNOLOGICAL_NAME());}
    public static ItemStack MILITARY_GUI() {return ItemManager.createItem(Material.IRON_SWORD,
            Constants.FORMAT_RED() + MILITARY_NAME());}
    public static ItemStack FARM_GUI() {return ItemManager.createItem(Material.WHEAT,
            Constants.FORMAT_GREEN() + FARMING_NAME());}
    public static ItemStack GET_TECHNOLOGICAL_GUI() {return new ItemStack(Material.COMPARATOR);}
    public static ItemStack GET_FARM_GUI() {return new ItemStack(Material.IRON_HOE);}
    public static ItemStack GET_MILITARY_GUI() {return new ItemStack(Material.CHAINMAIL_CHESTPLATE);}
    public static Component GET_TECHNOLOGICAL_GUI_NAME(){return ItemManager.getDisplayName(GET_TECHNOLOGICAL_GUI());}
    public static Component GET_FARM_GUI_NAME(){return ItemManager.getDisplayName(GET_FARM_GUI());}
    public static Component GET_MILITARY_GUI_NAME(){return ItemManager.getDisplayName(GET_MILITARY_GUI());}
}
