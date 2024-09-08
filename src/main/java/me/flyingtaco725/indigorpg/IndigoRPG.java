package me.flyingtaco725.indigorpg;

import me.flyingtaco725.indigorpg.Commands.CharCommand.CharCommand;
import me.flyingtaco725.indigorpg.Commands.DMCommand.DMCommand;
import me.flyingtaco725.indigorpg.EventHandlers.InventoryClickHandler;
import me.flyingtaco725.indigorpg.EventHandlers.InventoryCloseHandler;
import me.flyingtaco725.indigorpg.EventHandlers.PlayerJoinHandler;
import me.flyingtaco725.indigorpg.EventHandlers.PlayerQuitHandler;
import me.flyingtaco725.indigorpg.GUI.CharacterSelector;
import me.flyingtaco725.indigorpg.GUI.ClassSelector;
import me.flyingtaco725.indigorpg.PlayerInfo.TheList;
import org.bukkit.plugin.java.JavaPlugin;

public final class IndigoRPG extends JavaPlugin {

    // GUI class instances
    public ClassSelector classSelector;
    public CharacterSelector characterSelector;
    public TheList theList;

    @Override
    public void onEnable() {
        // create config
        saveDefaultConfig();

        // GUI ClASSES
        classSelector = new ClassSelector(this);
        characterSelector = new CharacterSelector(this);

        // Declaring "The List" to track player/character information
        theList = new TheList();

        // we need to include any EventHandler we create here
        getServer().getPluginManager().registerEvents(new PlayerJoinHandler(this), this);
        getServer().getPluginManager().registerEvents(new InventoryClickHandler(this), this);
        getServer().getPluginManager().registerEvents(new InventoryCloseHandler(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitHandler(this),this);

        // include any command handler we create here
        getCommand("char").setExecutor(new CharCommand(this));
        getCommand("dm").setExecutor(new DMCommand(this));
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}