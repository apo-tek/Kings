package be.apo_tek.kings.packet;

import be.apo_tek.kings.Main;
import be.apo_tek.kings.enums.States;
import be.apo_tek.kings.manager.GuiManager;
import be.apo_tek.kings.manager.ItemManager;
import be.apo_tek.kings.players.King;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Listener implements org.bukkit.event.Listener {

    private Main instance = Main.getInstance();
    private final ChatColor formatRed = ChatColor.RED;
    private final ChatColor formatBlue = ChatColor.BLUE;
    private final ChatColor formatGreen = ChatColor.GREEN;
    private boolean debug = instance.isDebug();
    private List<Player> Kings = new ArrayList<>();

    private final ItemStack technological_gui = ItemManager.createItem(Material.BREWING_STAND, formatBlue + "Technology");
    private final ItemStack military_gui = ItemManager.createItem(Material.IRON_SWORD, formatRed + "Military");
    private final ItemStack farm_gui = ItemManager.createItem(Material.WHEAT, formatGreen + "Agriculture");
    private final ItemStack getTechnological_gui = new ItemStack(Material.COMPARATOR);
    private final ItemStack getFarm_gui = new ItemStack(Material.IRON_HOE);
    private final ItemStack getMilitary_gui = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
    private HashMap<ItemStack, Integer> technologyTree = new HashMap<>();
    private HashMap<ItemStack, Integer> militaryTree = new HashMap<>();
    private HashMap<ItemStack, Integer> agricultureTree = new HashMap<>();
    private HashMap<ItemStack, Integer> items = new HashMap<>();


    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event){
        if(debug) {
            addLivePlayersHere(event.getPlayer());
            sendTestLine("LOGIN PASSED");
            disableCooldown(event.getPlayer());
            giveMenuItem(event.getPlayer());
            new King(event.getPlayer()).regenerate();
            if(!Kings.contains(event.getPlayer()))Kings.add(event.getPlayer());
        }
    }

    @EventHandler
    public void onDropEvent(PlayerDropItemEvent event) {
        if (debug) {
                sendTestLine(event.getEventName());
                boolean cancel = Objects.requireNonNull(event.getItemDrop().
                                getItemStack().getItemMeta().displayName()).toString().
                        equalsIgnoreCase(ChatColor.RED + "Menu principal");
                event.setCancelled(cancel);
            }
        }

    @EventHandler
    public void onDigEvent(BlockBreakEvent event) {
        if (debug) {
            sendTestLine(event.getEventName());
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteractBlockEvent(PlayerInteractEvent event){
        if(event.getClickedBlock() == null) return;
        if(instance.getGameState() == States.PLAYING || instance.getGameState() == States.SUDDEN
                && event.getAction().isRightClick()
                && Objects.requireNonNull(event.getClickedBlock()).getType().equals(Material.LECTERN)){


            items.put(technological_gui, 0);
            items.put(military_gui, 2);
            items.put(farm_gui, 4);
            ArrayList<Player> players = new ArrayList<>();
            players.add(event.getPlayer());

            GuiManager.sendInventory(null, InventoryType.HOPPER, Component.text("Terminal Royal"), items, players);
        }
    }

    @EventHandler
    public void onRetrieveFromInventoryEvent(InventoryClickEvent event){
        if(event.getClickedInventory() == null
                || event.getCurrentItem() == null
                || event.getCurrentItem().getItemMeta() == null) return;
        if (Objects.equals
                (event.getCurrentItem().getItemMeta().displayName(), technological_gui.getItemMeta().displayName())
                || Objects.equals(event.getCurrentItem().getItemMeta().displayName(), farm_gui.getItemMeta().displayName())
                || Objects.equals(event.getCurrentItem().getItemMeta().displayName(), military_gui.getItemMeta().displayName())){
            event.setCancelled(true);
            firstLayerKingGui(event);
        }
        if(Objects.equals
                (event.getCurrentItem().getItemMeta().displayName(), getTechnological_gui.getItemMeta().displayName())
                || Objects.equals(event.getCurrentItem().getItemMeta().displayName(), getFarm_gui.getItemMeta().displayName())
                || Objects.equals(event.getCurrentItem().getItemMeta().displayName(), getMilitary_gui.getItemMeta().displayName())){
            event.setCancelled(true);
        }
    }




    @EventHandler
    public void onLecternTakeEvent(PlayerTakeLecternBookEvent event){

    }

    @EventHandler
    public void onBlockHarvestEvent(PlayerHarvestBlockEvent event){
        if (!debug) {
            sendTestLine(event.getEventName());
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onWindowClickEvent(InventoryClickEvent event){
        if (!debug) {
            sendTestLine(event.getEventName());
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onWindowPickUpEvent(InventoryPickupItemEvent event){
        if (!debug) {
            sendTestLine(event.getEventName());
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onWindowDragEvent(InventoryDragEvent event){
        if (!debug) {
            sendTestLine(event.getEventName());
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onWindowMoveItemEvent(InventoryMoveItemEvent event){
        if (!debug) {
            sendTestLine(event.getEventName());
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent event){
    }

    public void sendTestLineServer(@NotNull String message){
        Bukkit.getConsoleSender().sendMessage(formatGreen + message);
    }

    public void sendTestLineClient(@NotNull String message){
        Bukkit.getConsoleSender().sendMessage(formatBlue + message);
    }

    public void sendTestLine(@NotNull String message){
        Bukkit.getConsoleSender().sendMessage(formatRed + message);
    }


    public void addLivePlayersHere(@NotNull Player player){
        instance.getPlayersManager().addLivePlayers(player);
    }

    public void disableCooldown(@NotNull Player player){
        for (String key : instance.getConfig().getKeys(false)) {
            AttributeInstance attributeInstance = player.getAttribute(Attribute.valueOf(key));
            if (attributeInstance != null)
                attributeInstance.setBaseValue(instance.getConfig().getDouble(key, attributeInstance.getBaseValue()));
        }
    }

    public void giveMenuItem(@NotNull Player player){
        ItemStack menu = new ItemStack(Material.COMPARATOR);
        ItemMeta menuMeta = menu.getItemMeta();
        menuMeta.displayName(Component.text(ChatColor.RED + "Menu principal"));
        menu.setItemMeta(menuMeta);
        player.getInventory().clear();
        player.getInventory().setItem(4, menu);
    }

    private void firstLayerKingGui(InventoryClickEvent event){
        ArrayList<Player> players = new ArrayList<>();
        players.add((Player) event.getWhoClicked());


        militaryTree.put(getMilitary_gui, 4);
        agricultureTree.put(getFarm_gui, 4);
        technologyTree.put(getTechnological_gui, 4);



        switch (Objects.requireNonNull(event.getCurrentItem()).getType().toString().toUpperCase()){
            case "BREWING_STAND" ->
                    GuiManager.sendInventory(null, InventoryType.BREWING,
                             Component.text("Technology"), technologyTree, players);
            case "IRON_SWORD" ->
                    GuiManager.sendInventory(null, InventoryType.BREWING,
                            Component.text("Military"), militaryTree, players);
            case "WHEAT" ->
                    GuiManager.sendInventory(null, InventoryType.BREWING,
                            Component.text("Agriculture"), agricultureTree, players);
        }
    }

    private void secondLayerKingGui(InventoryClickEvent event) {
        ArrayList<Player> players = new ArrayList<>();
        players.add((Player) event.getWhoClicked());
    }



}


