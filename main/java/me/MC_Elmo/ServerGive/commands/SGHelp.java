package me.MC_Elmo.ServerGive.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Created by Elom on 1/15/17.
 */
public class SGHelp
{
    public boolean help(CommandSender sender, String title)
    {
        sender.sendMessage(title);
        sender.sendMessage(ChatColor.GREEN +  "/servergive help - " + ChatColor.DARK_GREEN + "Display plugin help.");
        if(sender.hasPermission("servergive.items.give"))
            sender.sendMessage(ChatColor.GREEN + "/servergive items give - " + ChatColor.DARK_GREEN + "Give items to all players.");
        if(sender.hasPermission("servergive.items.take"))
            sender.sendMessage(ChatColor.GREEN + "/servergive items take - " + ChatColor.DARK_GREEN + "Take items from all players.");
        if(sender.hasPermission("servergive.exp.give"))
            sender.sendMessage(ChatColor.GREEN + "/servergive exp give <level> - " + ChatColor.DARK_GREEN + "Give levels to all players.");
        if(sender.hasPermission("servergive.exp.take"))
            sender.sendMessage(ChatColor.GREEN + "/servergive exp take <level> - " + ChatColor.DARK_GREEN + "Take levels from all players.");
        if(sender.hasPermission("servergive.reload"))
            sender.sendMessage(ChatColor.GREEN + "/servergive reload - " + ChatColor.DARK_GREEN + "Reload plugin.");
        return true;
    }
}
