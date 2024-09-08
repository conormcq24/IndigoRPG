package me.flyingtaco725.indigorpg.EventHandlers;

import me.flyingtaco725.indigorpg.IndigoRPG;
import me.flyingtaco725.indigorpg.GUI.CharacterSelector;
import me.flyingtaco725.indigorpg.PlayerInfo.ThePlayer;
import me.flyingtaco725.indigorpg.Utility.utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinHandler implements Listener {

    private final IndigoRPG plugin;

    public PlayerJoinHandler(IndigoRPG plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ThePlayer thePlayer = new ThePlayer(player);
        // clear any activeCharacterInformation left over from last play
        if (thePlayer.getActiveCharacter() != null)
        {
            thePlayer.clearActiveCharacter();
        }
        // unfreeze anybody who may have been frozen in their last play
        utils.unfreezePlayer(player);
        // if the creation location has been set teleport people there when selecting a character
        if (plugin.theList.getCreationLocation() != null){
            player.teleport(plugin.theList.getCreationLocation());
        }
        plugin.theList.addPlayer(thePlayer);
        plugin.characterSelector.openCharacterSelector(player);
    }
}