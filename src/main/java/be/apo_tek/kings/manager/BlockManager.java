package be.apo_tek.kings.manager;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public class BlockManager {

    public static Material getItemType(@NotNull Block block){return block.getType();}


}
