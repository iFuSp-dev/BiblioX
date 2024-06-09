package me.aifusp.bibliox.gui.menu;

import de.tr7zw.nbtapi.NBTItem;
import me.aifusp.bibliox.InventoryName;
import org.aifusp.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MAIN_MENU {

    private Inventory inventory;
    private Player player;

    public MAIN_MENU(Player player){
        this.player = player;

        createInventory();
    }


    public void createInventory(){
        inventory = Bukkit.createInventory(player, InventoryName.MAIN_MENU.getSize(),InventoryName.MAIN_MENU.getName());

        inventory.setItem(1,upload());
        inventory.setItem(3,filter());
        inventory.setItem(5,library());

    }


    private ItemStack upload(){
        ItemStack item = new ItemStack(Material.WRITABLE_BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessageUtils.getColoredMessage("Upload"));

        List<String> lore = new ArrayList<>();
        lore.add("Click to open the");
        lore.add("Upload interface");

        meta.setLore(lore);

        item.setItemMeta(meta);
        NBTItem nbtitem = new NBTItem(item);
        nbtitem.setBoolean("UploadBtn",true);

        nbtitem.applyNBT(item);

        return  nbtitem.getItem();
    }


    private ItemStack filter(){
        ItemStack item = new ItemStack(Material.COMPASS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessageUtils.getColoredMessage("Filter"));

        List<String> lore = new ArrayList<>();
        lore.add("Click to open the");
        lore.add("Filter interface");

        meta.setLore(lore);

        item.setItemMeta(meta);
        NBTItem nbtitem = new NBTItem(item);
        nbtitem.setBoolean("FilterBtn",true);

        nbtitem.applyNBT(item);

        return  nbtitem.getItem();
    }

    private ItemStack library(){
        ItemStack item = new ItemStack(Material.BOOKSHELF);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessageUtils.getColoredMessage("Library"));

        List<String> lore = new ArrayList<>();
        lore.add("Click to open the");
        lore.add("Library with all");
        lore.add("the stored Books");

        meta.setLore(lore);

        item.setItemMeta(meta);
        NBTItem nbtitem = new NBTItem(item);
        nbtitem.setBoolean("LibraryBtn",true);

        nbtitem.applyNBT(item);

        return  nbtitem.getItem();
    }










    public Inventory getInventory(){
        return this.inventory;
    }

}
