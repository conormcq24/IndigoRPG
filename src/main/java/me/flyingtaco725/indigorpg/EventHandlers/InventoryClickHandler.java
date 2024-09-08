package me.flyingtaco725.indigorpg.EventHandlers;

import me.flyingtaco725.indigorpg.IndigoRPG;
import me.flyingtaco725.indigorpg.GUI.ClassSelector;
import me.flyingtaco725.indigorpg.GUI.CharacterSelector;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickHandler implements Listener {

    private final IndigoRPG plugin;

    public InventoryClickHandler(IndigoRPG plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        String inventoryTitle = event.getView().getTitle();
        switch (inventoryTitle) {
            case "Character Selection":
                plugin.characterSelector.handleCharacterClick(event);
                break;
            case "Select a Class":
                plugin.classSelector.handleClassClick(event);
                break;
            default:
                break;
        }
    }
}