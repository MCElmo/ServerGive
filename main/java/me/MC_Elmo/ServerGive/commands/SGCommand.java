package me.MC_Elmo.ServerGive.commands;

import me.MC_Elmo.ServerGive.ServerGive;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Elom on 1/15/17.
 */
public class SGCommand implements CommandExecutor
{
    private String title;
    private ServerGive plugin;
    private String prefix = "&aServer&2Give";
    private String noPerms;
    private SGHelp helpCommand;
    private SGReload reloadCommand;
    private SGItems itemsCommand;
    private SGExp expCommand;
    public SGCommand(ServerGive plugin)
    {
        this.plugin = plugin;
        prefix = ChatColor.WHITE + "[" + prefix + ChatColor.WHITE + "]";
        prefix = ChatColor.translateAlternateColorCodes('&',prefix);
        this.title =  ChatColor.STRIKETHROUGH + "-----" + ChatColor.RESET + prefix + ChatColor.RESET + ChatColor.STRIKETHROUGH + "-----";
        noPerms = prefix + ChatColor.RED + " You do not have permission to perform this command!";
        helpCommand = new SGHelp();
        reloadCommand = new SGReload(plugin);
        itemsCommand = new SGItems(plugin);
        expCommand = new SGExp(plugin);
    }
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if(args.length == 0)
        {
            sender.sendMessage(title);
            sender.sendMessage(ChatColor.GREEN + "Author: " + ChatColor.GOLD +  "MC_Elmo");
            sender.sendMessage(ChatColor.GREEN + "Alias: " + ChatColor.GREEN +  " [/sg] ");
            sender.sendMessage(ChatColor.GREEN + "Version: " + ChatColor.GREEN +  plugin.getPdfFile().getVersion());
            sender.sendMessage(ChatColor.GREEN + "Description: " + ChatColor.DARK_GREEN + "Give items/xp to all players on Server.");
            sender.sendMessage(ChatColor.GREEN + "Try " + ChatColor.DARK_GREEN + "/servergive help " + ChatColor.GREEN + " for help.");
            sender.sendMessage(ChatColor.STRIKETHROUGH + "--------------------");
            return true;
        }
        else if(args.length == 1)
        {
            if(args[0].equalsIgnoreCase("help"))
            {
                return helpCommand.help(sender, title);
            }
            else if(args[0].equalsIgnoreCase("reload"))
            {
                if(sender.hasPermission("servergive.reload"))
                {
                    return reloadCommand.reload(sender);
                }
                else
                {
                    sender.sendMessage(noPerms);
                    return true;
                }
            }
        }
        else if (args.length == 2)
        {
            if(args[0].equalsIgnoreCase("items"))
            {
                if(args[1].equalsIgnoreCase("give"))
                {
                    if(sender.hasPermission("servergive.items.give"))
                    {
                        if (!(sender instanceof Player))
                        {
                            sender.sendMessage(ChatColor.RED + "This command can only be performed by players");
                            return false;
                        }
                        Player p = (Player) sender;
                        return itemsCommand.give(p);
                    }
                    else
                    {
                        sender.sendMessage(noPerms);
                        return true;
                    }
                }
                else if(args[1].equalsIgnoreCase("take"))
                {
                    if(sender.hasPermission("servergive.items.take"))
                    {
                        if (!(sender instanceof Player))
                        {
                            sender.sendMessage(ChatColor.RED + "This command can only be performed by players");
                            return false;
                        }
                        Player p = (Player) sender;
                        return itemsCommand.take(p);
                    }
                    else
                    {
                        sender.sendMessage(noPerms);
                        return true;
                    }
                }
            }
        }else if (args.length == 3)
        {
            if(args[0].equalsIgnoreCase("exp"))
            {
                if(args[1].equalsIgnoreCase("give"))
                {
                    if(sender.hasPermission("servergive.exp.give"))
                    {
                            int level;
                            try
                            {
                                level = Integer.valueOf(args[2]);
                                if (level <= 0)
                                {
                                    sender.sendMessage(prefix + ChatColor.RED + "Invalid level");
                                    return false;
                                }
                                return expCommand.giveLevel(sender, level);
                            } catch (NumberFormatException e)
                            {
                                sender.sendMessage(prefix + ChatColor.RED + "Invalid level");
                                return false;
                            }
                    }else
                    {
                        sender.sendMessage(noPerms);
                    }
                }
                else if(args[1].equalsIgnoreCase("take"))
                {
                    if(sender.hasPermission("servergive.exp.take"))
                    {
                            int level;
                            try
                            {
                                level = Integer.valueOf(args[2]);
                                if (level <= 0)
                                {
                                    sender.sendMessage(prefix + ChatColor.RED + "Invalid level");
                                    return false;
                                }
                                return expCommand.takeLevel(sender, level);
                            } catch (NumberFormatException e)
                            {
                                sender.sendMessage(prefix + ChatColor.RED + "Invalid level");
                                return false;
                            }
                    }else
                    {
                        sender.sendMessage(noPerms);
                    }
                }
            }
        }
        sender.sendMessage(prefix + ChatColor.RED + " Unknown command! Try /servergive help");
        return false;
    }

}
