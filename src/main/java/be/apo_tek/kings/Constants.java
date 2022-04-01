package be.apo_tek.kings;

/*
This class, which I'm not sure of the real utility, consists of static final variables returning code constants. Those are
public and so accessible everywhere (apart from non-static context which may cause conflicts).
 */

import be.apo_tek.kings.manager.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Constants {

    public static final @NotNull Integer GENERIC_ATTACK_SPEED = 24;
    public static final @NotNull String PLAYERS_COMMAND_IDENTIFIER = "players";
    public static final @NotNull String TECHNOLOGICAL_NAME = "Technology";
    public static final @NotNull String MILITARY_NAME = "Military";
    public static final @NotNull String FARMING_NAME = "Agriculture";
    public static final @NotNull Boolean COPY_DEFAULT = true;
    public static final @NotNull String ENABLE_MESSAGE = "§cEnabled";
    public static final @NotNull String DISABLE_MESSAGE = "§cDisabled";
    public static final @NotNull String LOGIN_PASSED_MESSAGE = "LOGIN PASSED";
    public static final @NotNull ChatColor FORMAT_RED = ChatColor.RED;
    public static final @NotNull ChatColor FORMAT_BLUE = ChatColor.BLUE;
    public static final @NotNull ChatColor FORMAT_GREEN = ChatColor.GREEN;
    public static final @NotNull Material BREWING_STAND = Material.BREWING_STAND;
    public static final @NotNull String MAIN_MENU_NAME = FORMAT_RED + "Main Menu";
    public static final @NotNull String ROYAL_MENU_NAME = "Royal Terminal";
    public static final @NotNull ItemStack TECHNOLOGICAL_GUI = ItemManager.createItem(BREWING_STAND,
            FORMAT_BLUE + TECHNOLOGICAL_NAME);
    public static final @NotNull ItemStack MILITARY_GUI = ItemManager.createItem(Material.IRON_SWORD,
            FORMAT_RED + MILITARY_NAME);
    public static final @NotNull ItemStack FARMING_GUI = ItemManager.createItem(Material.WHEAT,
            FORMAT_GREEN + FARMING_NAME);
    public static final @NotNull ItemStack GET_TECHNOLOGICAL_GUI = new ItemStack(Material.COMPARATOR);
    public static final @NotNull ItemStack GET_FARMING_GUI = new ItemStack(Material.IRON_HOE);
    public static final @NotNull ItemStack GET_MILITARY_GUI = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
    public static final @NotNull Component GET_TECHNOLOGICAL_GUI_NAME = ItemManager.getDisplayName(GET_TECHNOLOGICAL_GUI);
    public static final @NotNull Component GET_FARMING_GUI_NAME = ItemManager.getDisplayName(GET_FARMING_GUI);
    public static final @NotNull Component GET_MILITARY_GUI_NAME = ItemManager.getDisplayName(GET_MILITARY_GUI);
}
