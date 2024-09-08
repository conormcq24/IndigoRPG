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
        utils.unfreezePlayer(player);
        plugin.theList.addPlayer(thePlayer);
        plugin.characterSelector.openCharacterSelector(player);
    }
}