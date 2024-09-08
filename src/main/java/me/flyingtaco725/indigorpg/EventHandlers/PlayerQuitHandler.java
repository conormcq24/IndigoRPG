package me.flyingtaco725.indigorpg.EventHandlers;

import me.flyingtaco725.indigorpg.IndigoRPG;
import me.flyingtaco725.indigorpg.GUI.CharacterSelector;
import me.flyingtaco725.indigorpg.PlayerInfo.ThePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitHandler implements Listener {
    private final IndigoRPG plugin;

    public PlayerQuitHandler(IndigoRPG plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        ThePlayer thePlayer = plugin.theList.getPlayer(player.getUniqueId());
        // 1: save active player position/inventory/quests etc to correct character for thePlayer, in theList
        // 2: wipe active player
        thePlayer.clearActiveCharacter();
        // 3: set Player location to spawn location
    }
}