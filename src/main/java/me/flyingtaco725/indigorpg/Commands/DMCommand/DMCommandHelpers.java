package me.flyingtaco725.indigorpg.Commands.DMCommand;

import me.flyingtaco725.indigorpg.IndigoRPG;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DMCommandHelpers {
    public static void handleCharacterCreateLocation(IndigoRPG plugin, Player player){
        // 1. get location of player sending the message
        Location creationLocation = player.getLocation();
        // 2. use setCreationLocation on the list
        plugin.theList.setCreationLocation(creationLocation);
        // 3. TODO: save theList immediately
    }
}
