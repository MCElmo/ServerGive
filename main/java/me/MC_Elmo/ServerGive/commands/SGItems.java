package me.MC_Elmo.ServerGive.commands;

import me.MC_Elmo.ServerGive.ServerGive;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Elom on 1/15/17.
 */
public class SGItems
{
    private ServerGive plugin;
    private String prefix;
    //private FileConfiguration config;
    private HashMap<UUID, ItemStack[]> giveInventories;
    private HashMap<UUID, ItemStack[]> takeInventories;
    public SGItems(ServerGive plugin)
    {
        this.plugin = plugin;
        prefix = plugin.getPrefix();
        //this.config = plugin.getPluginConifg();
        giveInventories = plugin.getGiveInventories();
        takeInventories = plugin.getTakeInventories();
    }

    public boolean give(Player p)
    {

        int size = 36;
        p.sendMessage(prefix + ChatColor.GREEN + " Place items in the GUI. Click the Send button to send to all players.");
        giveInventories = plugin.getGiveInventories();giveInventories = plugin.getGiveInventories();
        if(giveInventories.keySet().contains(p.getUniqueId()))
        {
            Inventory give = Bukkit.createInventory(null, size,ChatColor.GREEN + "[ServerGive] Give");
            give.setContents(giveInventories.get(p.getUniqueId()));
            p.openInventory(give);
        }else
        {

            List<String> lore = new ArrayList<String>();
            lore.add(ChatColor.GREEN + "Shift Right-Click to send items to all online players.");
            Inventory give = Bukkit.createInventory(null, size,ChatColor.GREEN + "[ServerGive] Give");
            ItemStack send = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
            ItemMeta sendMeta = send.getItemMeta();
            sendMeta.setDisplayName(ChatColor.GREEN + "Give");
            sendMeta.setLore(lore);
            send.setItemMeta(sendMeta);
            give.setItem(size - 1, send);
            p.openInventory(give);
        }

        return true;
    }

    public boolean take(Player p)
    {
        int size = 36;
        takeInventories = plugin.getTakeInventories();
        p.sendMessage(prefix + ChatColor.GREEN + " Place items in the GUI. Click the Take button to take from all players.");

        if(takeInventories.keySet().contains(p.getUniqueId()))
        {
            Inventory take = Bukkit.createInventory(null, size, ChatColor.GREEN + "[ServerGive] Take");
            take.setContents(takeInventories.get(p.getUniqueId()));
            p.openInventory(take);
        }else
        {

            List<String> lore = new ArrayList<String>();
            lore.add(ChatColor.GREEN + "Shift Right-Click to take items from all online players.");
            Inventory takeInv = Bukkit.createInventory(null, size, ChatColor.GREEN + "[ServerGive] Take");
            ItemStack take = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
            ItemMeta takeMeta = take.getItemMeta();
            takeMeta.setDisplayName(ChatColor.GREEN + "Take");
            takeMeta.setLore(lore);
            take.setItemMeta(takeMeta);
            takeInv.setItem(size - 1, take);
            p.openInventory(takeInv);
        }

        return true;
    }


}
