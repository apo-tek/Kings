package be.apo_tek.kings.manager;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class ItemManager {

    public static ItemStack createItem(Material material, String displayName){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.text(displayName));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static Component getDisplayName(ItemStack itemStack){
        return itemStack.displayName();
    }

    public static Component getDisplayName(Item item){
        return item.getItemStack().displayName();
    }

}
