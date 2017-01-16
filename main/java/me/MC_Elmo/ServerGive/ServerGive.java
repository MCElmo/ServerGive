package me.MC_Elmo.ServerGive;

import me.MC_Elmo.ServerGive.commands.SGCommand;
import me.MC_Elmo.ServerGive.listeners.InventoryClose;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

/**
 * Created by Elom on 1/15/17.
 */
public class ServerGive extends JavaPlugin
{
    private FileConfiguration config;
    private String prefix;
    private HashMap<UUID, ItemStack[]> giveInventories = new HashMap<UUID, ItemStack[]>();
    private HashMap<UUID, ItemStack[]> takeInventories = new HashMap<UUID, ItemStack[]>();
    private PluginDescriptionFile pdfFile;
    private SGCommand SGC = new SGCommand(this);
    private PluginManager pm;

    @Override
    public void onEnable()
    {
        super.onEnable();
        loadConfig();
        if(config.getBoolean("ServerGive.enabled"))
        {
            this.pdfFile = super.getDescription();
            this.pm = this.getServer().getPluginManager();
            registerCommands();
            registerEvents();
        }
        else
        {
            getLogger().log(Level.INFO,"[ServerGive] Plugin not enabled in config.yml");
            getServer().getPluginManager().disablePlugin(this);
        }
    }


    public void registerEvents() {
        pm.registerEvents(new InventoryClose(this), this);
    }
    private void registerCommands() {
        this.getCommand("servergive").setExecutor(SGC);
    }
    private void loadConfig()
    {
        config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
    }
    public FileConfiguration getPluginConifg()
    {
       if(this.config == null)
        loadConfig();
        return this.config;
    }

    public PluginDescriptionFile getPdfFile() {
        return this.pdfFile;
    }
    public void reload() {
        saveConfig();
        pm.disablePlugin(this);
        pm.enablePlugin(this);
        loadConfig();
    }
    public String getPrefix()
    {
        prefix = "&aServer&2Give";
        prefix = ChatColor.WHITE + "[" + prefix + ChatColor.WHITE + "]";
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);
        return this.prefix;
    }
    public void saveGiveHash(HashMap<UUID, ItemStack[]> h)
    {
        giveInventories = h;
    }
    public void saveTakeHash(HashMap<UUID, ItemStack[]> h)
    {
        takeInventories = h;
    }
    public HashMap getGiveInventories() {
        return giveInventories;
    }
    public HashMap getTakeInventories() {
        return takeInventories;
    }
}
