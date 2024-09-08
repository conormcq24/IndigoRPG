package me.flyingtaco725.indigorpg.EventHandlers;

import me.flyingtaco725.indigorpg.IndigoRPG;
import me.flyingtaco725.indigorpg.PlayerInfo.ThePlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.entity.Player;
public class InventoryCloseHandler implements Listener {
    private final IndigoRPG plugin;

    public InventoryCloseHandler(IndigoRPG plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        // Get player that closed inventory
        Player player = (Player) event.getPlayer();
        // find the player in the list if exists
        ThePlayer thePlayer = plugin.theList.getPlayer(player.getUniqueId());
        if (event.getView().getTitle().equals("Character Selection") && !thePlayer.isCharacterSelected()) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> player.openInventory(event.getInventory()), 1L);
        }
        if (event.getView().getTitle().equals("Select a Class") && thePlayer.isCharacterSelected() && !thePlayer.isClassSelected()) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> player.openInventory(event.getInventory()), 1L);
        }
    }
}
