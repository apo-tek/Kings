package be.apo_tek.kings.io;

import be.apo_tek.kings.Constants;
import be.apo_tek.kings.Main;
import be.apo_tek.kings.enums.States;
import be.apo_tek.kings.manager.BlockManager;
import be.apo_tek.kings.manager.GuiManager;
import be.apo_tek.kings.manager.ItemManager;
import be.apo_tek.kings.players.KingsPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Listener implements org.bukkit.event.Listener {


    private final Main pluginInstance = Main.getPluginInstance();
    private final boolean isDebug = pluginInstance.isDebug();
    private final List<Player> kingsList = new ArrayList<>();

    private final HashMap<ItemStack, Integer> technologyTree = new HashMap<>();
    private final HashMap<ItemStack, Integer> militaryTree = new HashMap<>();
    private final HashMap<ItemStack, Integer> agricultureTree = new HashMap<>();


    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event){
        if(isDebug) {
            Player p = event.getPlayer();
            KingsPlayer kp = new KingsPlayer(p);
            addLivePlayersHere(p);
            sendTestLine(Constants.LOGIN_PASSED_MESSAGE);
            disableCooldown(p);
            giveMenuItem(p);
            kp.removePotionEffects();
        }
    }

    @EventHandler
    public void onDropEvent(PlayerDropItemEvent event) {
        if (isDebug) {
                sendTestLine(event.getEventName());
                boolean cancel =
                        ItemManager.getDisplayName(event.getItemDrop()).toString().
                        equalsIgnoreCase(Constants.MAIN_MENU_NAME);
                event.setCancelled(cancel);
            }
        }

    @EventHandler
    public void onDigEvent(BlockBreakEvent event) {
        if (isDebug) {
            sendTestLine(event.getEventName());
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteractBlockEvent(PlayerInteractEvent event){
        if(event.getClickedBlock() == null) return;
        if(pluginInstance.getGameState() == States.PLAYING
                || pluginInstance.getGameState() == States.SUDDEN
                && event.getAction().isRightClick()
                && BlockManager.getItemType(event.getClickedBlock()) == Material.LECTERN){


            HashMap<ItemStack, Integer> items = new HashMap<>();
            items.put(Constants.TECHNOLOGICAL_GUI, 0);
            items.put(Constants.MILITARY_GUI, 2);
            items.put(Constants.FARMING_GUI, 4);

            GuiManager.sendInventory(null,
                    InventoryType.HOPPER, Component.text(Constants.ROYAL_MENU_NAME), items, event.getPlayer());
        }
    }

    @EventHandler
    public void onRetrieveFromInventoryEvent(InventoryClickEvent event){
        if(event.getClickedInventory() == null
                || event.getCurrentItem() == null
                || event.getCurrentItem().getItemMeta() == null) return;
        if (Objects.equals
                (ItemManager.getDisplayName(event.getCurrentItem()),
                        Constants.GET_TECHNOLOGICAL_GUI_NAME)
                || Objects.equals(ItemManager.getDisplayName(event.getCurrentItem()),
                Constants.GET_FARMING_GUI_NAME)
                || Objects.equals(ItemManager.getDisplayName(event.getCurrentItem()),
                Constants.GET_MILITARY_GUI_NAME)){
            event.setCancelled(true);
            firstLayerKingGui(event);
        }
        if(Objects.equals
                (ItemManager.getDisplayName(event.getCurrentItem()),
                        Constants.GET_TECHNOLOGICAL_GUI_NAME)
                || Objects.equals(ItemManager.getDisplayName(event.getCurrentItem()),
                Constants.GET_FARMING_GUI_NAME)
                || Objects.equals(ItemManager.getDisplayName(event.getCurrentItem()),
                Constants.GET_MILITARY_GUI_NAME)){
            event.setCancelled(true);
        }
    }




    @EventHandler
    public void onLecternTakeEvent(PlayerTakeLecternBookEvent event){

    }

    @EventHandler
    public void onBlockHarvestEvent(PlayerHarvestBlockEvent event){
        if (!isDebug) {
            sendTestLine(event.getEventName());
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onWindowClickEvent(InventoryClickEvent event){
        if (!isDebug) {
            sendTestLine(event.getEventName());
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onWindowPickUpEvent(InventoryPickupItemEvent event){
        if (!isDebug) {
            sendTestLine(event.getEventName());
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onWindowDragEvent(InventoryDragEvent event){
        if (!isDebug) {
            sendTestLine(event.getEventName());
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onWindowMoveItemEvent(InventoryMoveItemEvent event){
        if (!isDebug) {
            sendTestLine(event.getEventName());
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent event){
    }

    public void sendTestLineServer(@NotNull String message){
        Bukkit.getConsoleSender().sendMessage(Constants.FORMAT_GREEN + message);
    }

    public void sendTestLineClient(@NotNull String message){
        Bukkit.getConsoleSender().sendMessage(Constants.FORMAT_BLUE + message);
    }

    public void sendTestLine(@NotNull String message){
        Bukkit.getConsoleSender().sendMessage(Constants.FORMAT_RED + message);
    }


    public void addLivePlayersHere(@NotNull Player player){
        pluginInstance.getPlayersManager().addLivePlayers(player);
    }

    public void disableCooldown(@NotNull Player player){
        for (String key : pluginInstance.getConfig().getKeys(false)) {
            AttributeInstance attributeInstance = player.getAttribute(Attribute.valueOf(key));
            if (attributeInstance != null)
                attributeInstance.setBaseValue(pluginInstance.getConfig().getDouble(key, attributeInstance.getBaseValue()));
        }
    }

    public void giveMenuItem(@NotNull Player player){
        ItemStack menu = Constants.GET_TECHNOLOGICAL_GUI.clone();
        ItemMeta menuMeta = menu.getItemMeta();
        menuMeta.displayName(Component.text(Constants.MAIN_MENU_NAME));
        menu.setItemMeta(menuMeta);
        player.getInventory().clear();
        player.getInventory().setItem(4, menu);
    }

    private void firstLayerKingGui(InventoryClickEvent event){
        ArrayList<Player> players = new ArrayList<>();
        players.add((Player) event.getWhoClicked());

        technologyTree.put(Constants.GET_TECHNOLOGICAL_GUI, 4);
        militaryTree.put(Constants.GET_MILITARY_GUI, 4);
        agricultureTree.put(Constants.GET_FARMING_GUI, 4);




        switch (Objects.requireNonNull(event.getCurrentItem()).getType().toString().toUpperCase()){
            case "BREWING_STAND" ->
                    GuiManager.sendInventory(null, InventoryType.BREWING,
                             Component.text(Constants.TECHNOLOGICAL_NAME), technologyTree, players);
            case "IRON_SWORD" ->
                    GuiManager.sendInventory(null, InventoryType.BREWING,
                            Component.text(Constants.MILITARY_NAME), militaryTree, players);
            case "WHEAT" ->
                    GuiManager.sendInventory(null, InventoryType.BREWING,
                            Component.text(Constants.FARMING_NAME), agricultureTree, players);
        }
    }

    private void secondLayerKingGui(InventoryClickEvent event) {
        ArrayList<Player> players = new ArrayList<>();
        players.add((Player) event.getWhoClicked());
    }



}


