package be.apo_tek.kings.manager;

import be.apo_tek.kings.Main;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;


public class GuiManager {

    private final Main instance = Main.getInstance();
    private PlayersManager playersManager = instance.getPlayersManager();


    public static void sendInventory(@Nullable InventoryHolder owner, InventoryType inventoryType, TextComponent title,
                                     Map<ItemStack, Integer> items, ArrayList<Player> players){
        Inventory inventory = Bukkit.createInventory(owner, inventoryType, title);
        items.forEach((itemStack, integer) -> inventory.setItem(integer, itemStack));
        players.forEach(player -> player.openInventory(inventory));
    }


}
