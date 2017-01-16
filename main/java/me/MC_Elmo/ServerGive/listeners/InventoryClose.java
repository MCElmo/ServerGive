package me.MC_Elmo.ServerGive.listeners;

import me.MC_Elmo.ServerGive.ServerGive;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Elom on 1/15/17.
 */
public class InventoryClose implements Listener
{
    private ServerGive plugin;
    private FileConfiguration config;
    private HashMap<UUID, ItemStack[]> giveInventories;
    private static HashMap<UUID, ItemStack[]> takeInventories;
    public InventoryClose(ServerGive plugin)
    {
        this.plugin = plugin;
        config = plugin.getPluginConifg();
        giveInventories = plugin.getGiveInventories();
        takeInventories = plugin.getTakeInventories();
    }
    @EventHandler
    public void onClose(InventoryCloseEvent e)
    {
        Player p = (Player)e.getPlayer();
        Inventory i = e.getInventory();
        if(i.getName().equals(ChatColor.GREEN + "[ServerGive] Give"))
        {
            giveInventories = plugin.getGiveInventories();
            giveInventories.put(p.getUniqueId(), i.getContents());
            plugin.saveGiveHash(giveInventories);
        }else if(i.getName().equals(ChatColor.GREEN + "[ServerGive] Take"))
        {
            takeInventories = plugin.getTakeInventories();
            takeInventories.put(p.getUniqueId(), i.getContents());
            plugin.saveTakeHash(takeInventories);
        }
    }


    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        Player p = (Player)e.getWhoClicked();
        Inventory i = e.getInventory();
        ClickType c = e.getClick();
        ItemStack stack = e.getCurrentItem();


        if(i.getName().equals(ChatColor.GREEN + "[ServerGive] Give"))
        {
            if(stack.hasItemMeta())
            {
                if (stack.getItemMeta().hasDisplayName())
                {
                    if (stack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Give"))
                    {
                        if (c.equals(ClickType.SHIFT_RIGHT))
                        {
                            giveInventories = plugin.getGiveInventories();
                            e.setCancelled(true);
                            for (Player player : plugin.getServer().getOnlinePlayers())
                            {
                                if (!(player.hasPermission("servergive.items.antigive")))
                                    for(ItemStack itemStack : i.getContents())
                                    {
                                        if(itemStack != null && !(itemStack.equals(stack)))
                                            player.getInventory().addItem(itemStack);
                                    }
                            }
                            if (config.getBoolean("ServerGive.Broadcast message"))
                                Bukkit.getServer().broadcastMessage(plugin.getPrefix() + ChatColor.GREEN + "Items were sent by a server administrator.");
                            p.closeInventory();
                            giveInventories.remove(p.getUniqueId());
                           plugin.saveGiveHash(giveInventories);

                            p.sendMessage(plugin.getPrefix() + ChatColor.GREEN + "Sent items to all online players.");
                        } else
                        {
                            e.setCancelled(true);
                        }

                    }
                }
            }
        } else if(i.getName().equals(ChatColor.GREEN + "[ServerGive] Take"))
        {
            if(stack.hasItemMeta())
            {
                if (stack.getItemMeta().hasDisplayName())
                {
                    if (stack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Take"))
                    {
                        if (c.equals(ClickType.SHIFT_RIGHT))
                        {
                            takeInventories = plugin.getTakeInventories();
                            e.setCancelled(true);
                            for (Player player : plugin.getServer().getOnlinePlayers())
                            {
                                if (!(player.hasPermission("servergive.items.antitake")))
                                    for(ItemStack itemStack : i.getContents())
                                    {
                                        if(itemStack != null && !(itemStack.equals(stack)))
                                            if(player.getInventory().contains(itemStack.getType()))
                                                player.getInventory().remove(itemStack.getType());
                                    }
                            }
                            if (config.getBoolean("ServerGive.Broadcast message"))
                                Bukkit.getServer().broadcastMessage(plugin.getPrefix() + ChatColor.GREEN + "Items were taken by a server administrator.");
                            p.closeInventory();
                            takeInventories.remove(p.getUniqueId());
                            plugin.saveTakeHash(takeInventories);

                            p.sendMessage(plugin.getPrefix() + ChatColor.GREEN + "Took items from all online players.");
                        } else
                        {
                            e.setCancelled(true);
                        }

                    }
                }
            }
        }
    }


}
