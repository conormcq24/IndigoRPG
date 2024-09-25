package me.flyingtaco725.indigorpg.EventHandlers;

import me.flyingtaco725.indigorpg.IndigoRPG;
import me.flyingtaco725.indigorpg.PlayerInfo.ThePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class PlayerChatHandler implements Listener {
    private final IndigoRPG plugin;

    public PlayerChatHandler(IndigoRPG plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        // get player sending the chat
        Player player = event.getPlayer();
        // get there player object from "the list"
        ThePlayer thePlayer = plugin.theList.getPlayer(player.getUniqueId());
        // get character info
        String charName = thePlayer.getActiveCharacter().getName();
        String charClass = thePlayer.getActiveCharacter().getCharacterClass();
        int charLvl = thePlayer.getActiveCharacter().getLevel();

        // get the original message
        String message = event.getMessage();

        // deny messaging if character is in creation mode, send if not
        if (!charName.equals("")){
            switch(charClass){
                case "cleric":
                    message = ("§e§l[§6§lLVL " + charLvl + "§e§l|§a§l"+ charClass.toUpperCase() + "§e§l|§f§l" + charName.toUpperCase() + "§e§l]:§f " + message);
                    event.setCancelled(true);
                    plugin.getServer().broadcastMessage(message);
                    break;
                case "knight":
                    message = ("§e§l[§6§lLVL " + charLvl + "§e§l|§c§l"+ charClass.toUpperCase() + "§e§l|§f§l" + charName.toUpperCase() + "§e§l]:§f " + message);
                    event.setCancelled(true);
                    plugin.getServer().broadcastMessage(message);
                    break;
                case "ranger":
                    message = ("§e§l[§6§lLVL " + charLvl + "§e§l|§2§l"+ charClass.toUpperCase() + "§e§l|§f§l" + charName.toUpperCase() + "§e§l]:§f " + message);
                    event.setCancelled(true);
                    plugin.getServer().broadcastMessage(message);
                    break;
                case "rogue":
                    message = ("§e§l[§6§lLVL " + charLvl + "§e§l|§8§l"+ charClass.toUpperCase() + "§e§l|§f§l" + charName.toUpperCase() + "§e§l]:§f " + message);
                    event.setCancelled(true);
                    plugin.getServer().broadcastMessage(message);
                    break;
                case "sorcerer":
                    message = ("§e§l[§6§lLVL " + charLvl + "§e§l|§3§l"+ charClass.toUpperCase() + "§e§l|§f§l" + charName.toUpperCase() + "§e§l]:§f " + message);
                    event.setCancelled(true);
                    plugin.getServer().broadcastMessage(message);
                    break;
                default:
                    break;
            }
        } else {
            event.setCancelled(true);
            player.sendMessage("§e§l[IRPG] §l§cYou cannot send messages until you've set a name for your character");
        }

    }
}
