package me.MC_Elmo.ServerGive.commands;

import me.MC_Elmo.ServerGive.ServerGive;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Elom on 1/15/17.
 */
public class SGExp
{
    private ServerGive plugin;
    public SGExp(ServerGive plugin)
    {
        this.plugin = plugin;
    }
    /*public boolean give(CommandSender sender, int exp)
    {
        for(Player player: Bukkit.getOnlinePlayers())
        {
            if(!(player.hasPermission("servergive.exp.antigive")))
                player.setTotalExperience(player.getTotalExperience() + exp);
        }
        if (plugin.getConfig().getBoolean("ServerGive.Broadcast message"))
            Bukkit.getServer().broadcastMessage(plugin.getPrefix() + ChatColor.GREEN + "Exp was sent by a server administrator.");
        sender.sendMessage(plugin.getPrefix() + ChatColor.GREEN + "Sent Exp to Server.");
        return true;
    }*/

    public boolean giveLevel(CommandSender sender, int level)
    {
        for(Player player: Bukkit.getOnlinePlayers())
        {
            if(!(player.hasPermission("servergive.exp.antigive")))
                player.setLevel(player.getLevel() + level);
        }
        if (plugin.getConfig().getBoolean("ServerGive.Broadcast message"))
            Bukkit.getServer().broadcastMessage(plugin.getPrefix() + ChatColor.GREEN + "Exp was sent by a server administrator.");
        sender.sendMessage(plugin.getPrefix() + ChatColor.GREEN + "Sent Exp to Server.");
        return true;
    }


   /* public boolean take(CommandSender sender, int exp)
    {
        for(Player player: Bukkit.getOnlinePlayers())
        {
            if(!(player.hasPermission("servergive.exp.antitake")))
                player.setTotalExperience(player.getTotalExperience() - exp);
        }
        if (plugin.getConfig().getBoolean("ServerGive.Broadcast message"))
            Bukkit.getServer().broadcastMessage(plugin.getPrefix() + ChatColor.GREEN + "Exp was taken by a server administrator.");
        sender.sendMessage(plugin.getPrefix() + ChatColor.GREEN + "Took exp from server.");
        return true;
    }*/

    public boolean takeLevel(CommandSender sender, int level)
    {
        for(Player player: Bukkit.getOnlinePlayers())
        {
            if(!(player.hasPermission("servergive.exp.antitake")))
            {
                if (player.getLevel() - level >= 0)
                    player.setLevel(player.getLevel() - level);
                else
                    player.setLevel(0);
            }
        }
        if (plugin.getConfig().getBoolean("ServerGive.Broadcast message"))
            Bukkit.getServer().broadcastMessage(plugin.getPrefix() + ChatColor.GREEN + "Exp was taken by a server administrator.");
        sender.sendMessage(plugin.getPrefix() + ChatColor.GREEN + "Took exp from server.");
        return true;
    }
}
