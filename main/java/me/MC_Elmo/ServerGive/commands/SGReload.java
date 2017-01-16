package me.MC_Elmo.ServerGive.commands;

import me.MC_Elmo.ServerGive.ServerGive;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Created by Elom on 1/15/17.
 */
public class SGReload
{
    private ServerGive plugin;
    private String prefix;

    public SGReload(ServerGive plugin)
    {
        this.plugin = plugin;
        this.prefix = plugin.getPrefix();
    }

    public boolean reload(CommandSender sender)
    {
        plugin.reload();
        sender.sendMessage(prefix + ChatColor.GREEN + " Plugin successfully reloaded!");
        return true;
    }
}
