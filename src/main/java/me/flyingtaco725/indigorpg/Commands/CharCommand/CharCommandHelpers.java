package me.flyingtaco725.indigorpg.Commands.CharCommand;

import me.flyingtaco725.indigorpg.IndigoRPG;
import me.flyingtaco725.indigorpg.PlayerInfo.TheCharacter;
import me.flyingtaco725.indigorpg.PlayerInfo.TheList;
import me.flyingtaco725.indigorpg.PlayerInfo.ThePlayer;
import me.flyingtaco725.indigorpg.Utility.utils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class CharCommandHelpers {
    public static void handleSetName(IndigoRPG plugin, String charName, Player player){
        TheList theList = plugin.theList;
        // verify that they are currently frozen
        Collection<PotionEffect> playerEffects = player.getActivePotionEffects();

        // Flags to check the presence of specific potion effects
        boolean hasSlowness = false;
        boolean hasBlindness = false;
        boolean hasJumpBoost = false;
        boolean hasResistance = false;

        // Iterate over all active potion effects
        for (PotionEffect effect : playerEffects) {
            PotionEffectType type = effect.getType();

            if (type.equals(PotionEffectType.SLOWNESS)) {
                hasSlowness = true;
            } else if (type.equals(PotionEffectType.BLINDNESS)) {
                hasBlindness = true;
            } else if (type.equals(PotionEffectType.JUMP_BOOST)) {
                hasJumpBoost = true;
            } else if (type.equals(PotionEffectType.RESISTANCE)) {
                hasResistance = true;
            }
        }

        // Check if any of the required effects are missing
        if (!hasSlowness || !hasBlindness || !hasJumpBoost || !hasResistance) {
            player.sendMessage("§e§l[IRPG] §l§cYou are not currently creating a character");
            return;
        }
        // find out if player name is in use
        boolean doesNameExist = theList.doesCharacterExistInAnyPlayer(charName);

        if (doesNameExist){
            // player name already in use
            player.sendMessage("§e§l[IRPG] §l§cCharacter name is already in use");
            player.sendMessage("§e§lUse: §l§c/char setname <name>");
            return;
        } else {
            // gets the player who sent the commands player info object
            ThePlayer thePlayer = theList.getPlayer(player.getUniqueId());
            // gets the pending character from current player
            TheCharacter theCharacter = thePlayer.getActiveCharacter();
            // sets character name
            theCharacter.setName(charName);
            // binds the character to the player in the server player list
            thePlayer.addCharacter(theCharacter);
            // unfreeze player
            utils.unfreezePlayer(player);
        }
    }
}
