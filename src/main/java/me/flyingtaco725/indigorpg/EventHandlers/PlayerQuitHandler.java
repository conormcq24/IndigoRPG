package me.flyingtaco725.indigorpg.EventHandlers;

import me.flyingtaco725.indigorpg.IndigoRPG;
import me.flyingtaco725.indigorpg.GUI.CharacterSelector;
import me.flyingtaco725.indigorpg.PlayerInfo.TheCharacter;
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
        // save activeCharacter info (inventory/location/quests etc)
        if (thePlayer.getActiveCharacter() != null){
            TheCharacter theCharacter = thePlayer.getActiveCharacter();
            // save location
            theCharacter.setCurrentLocation(player.getLocation());
            thePlayer.saveActiveCharacter();
            // set selected values to false (reset guis)
            thePlayer.setClassSelected(false);
            thePlayer.setCharacterSelected(false);
            // TODO: save inventory
            // TODO: save quests

            // save the theList.thePlayer.activeCharacter to theList.thePlayer.theCharacter
            // overwrite if character name does exist
            // addCharacter if character name does not exist
        }
        // save active character to correct character

        // 3: set Player location to spawn location
    }
}