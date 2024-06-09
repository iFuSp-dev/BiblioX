package org.aifusp.listeners;

import de.tr7zw.nbtapi.NBTBlock;
import de.tr7zw.nbtapi.NBTItem;
import me.aifusp.bibliox.gui.menu.MAIN_MENU;
import org.aifusp.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ButtonInteraction implements Listener {

    private final Main main;
    public ButtonInteraction(Main plugin){this.main = plugin;}






    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();
        NBTBlock nbtBlock = new NBTBlock(clickedBlock);
        Bukkit.getConsoleSender().sendMessage("hay");
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK ){
            Bukkit.getConsoleSender().sendMessage("hay2");

            // Verifica si el bloque es un botón de piedra negra pulida y si tiene el NBTTag
            if (clickedBlock.getType() == Material.POLISHED_BLACKSTONE_BUTTON) {
                Bukkit.getConsoleSender().sendMessage("hay3");

                if (nbtBlock.getData().getBoolean("inmobiliaria")) {
                    MAIN_MENU mainMenu = new MAIN_MENU(player);
                    player.openInventory(mainMenu.getInventory());
                }
            }
        }
    }



    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent e){
        Player player = e.getPlayer();
        ItemStack item = e.getItemInHand();
        NBTItem nbtItem = new NBTItem(item);


        if (nbtItem.getBoolean("inmobiliaria")){
            Block block = e.getBlock();
            NBTBlock nbtBlock = new NBTBlock(block);

            nbtBlock.getData().setBoolean("inmobiliaria",true);
            player.sendMessage("se ha colocado con NBT");
        }


    }



}
