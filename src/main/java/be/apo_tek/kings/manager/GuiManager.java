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
import java.util.ArrayList;
import java.util.Map;


public class GuiManager {

    private final Main instance = Main.getPluginInstance();
    //private final PlayersManager playersManager = instance.getPlayersManager();


    public static void sendInventory(@Nullable InventoryHolder inventoryHolder,
                                     InventoryType inventoryType,
                                     TextComponent titleComponent,
                                     Map<ItemStack, Integer> itemsHeldInInventory,
                                     ArrayList<Player> playersOpeningInventory){
        Inventory sentInventory = Bukkit.createInventory(inventoryHolder, inventoryType, titleComponent);
        itemsHeldInInventory.forEach((itemStack, integer) -> sentInventory.setItem(integer, itemStack));
        playersOpeningInventory.forEach(player -> player.openInventory(sentInventory));
    }

    public static void sendInventory(@Nullable InventoryHolder inventoryHolder,
                                     InventoryType inventoryType,
                                     TextComponent titleComponent,
                                     Map<ItemStack, Integer> itemsHeldInInventory,
                                     Player playerOpeningInventory) {
        Inventory sentInventory = Bukkit.createInventory(inventoryHolder, inventoryType, titleComponent);
        itemsHeldInInventory.forEach((itemStack, integer) -> sentInventory.setItem(integer, itemStack));
        playerOpeningInventory.openInventory(sentInventory);
    }

}
