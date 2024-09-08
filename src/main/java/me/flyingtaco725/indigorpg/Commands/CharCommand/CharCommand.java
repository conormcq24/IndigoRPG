package me.flyingtaco725.indigorpg.Commands.CharCommand;

import me.flyingtaco725.indigorpg.IndigoRPG;
import me.flyingtaco725.indigorpg.PlayerInfo.TheCharacter;
import me.flyingtaco725.indigorpg.PlayerInfo.ThePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CharCommand implements CommandExecutor {

    private final IndigoRPG plugin;

    public CharCommand(IndigoRPG plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Ensure the sender is a player
        if (!(sender instanceof Player)) {
            sender.sendMessage("§e§l[IRPG] §l§cOnly players can use this command");
            return false;
        }
        // Get Player
        Player player = (Player) sender;
        // Ensure the player has permission to use the command
        if (!player.hasPermission("char.use")) {
            player.sendMessage("§e§l[IRPG] §l§cYou do not have permission for this command");
            return false;
        }
        // Handle Set Name Command
        if (args.length > 0 && args[0].equalsIgnoreCase("setname")) {
            if(!player.hasPermission("char.setname")){
                player.sendMessage("§e§l[IRPG] §l§cYou do not have permission to set a player name");
                return false;
            } else {
                if (args.length == 2) {
                    String charName = args[1];
                    ThePlayer thePlayer = plugin.theList.getPlayer(player.getUniqueId());

                    if (thePlayer != null) {
                        CharCommandHelpers.handleSetName(plugin, charName, player);
                        return true;
                    }
                } else {
                    player.sendMessage("§e§l[IRPG] §l§cIncorrect usage try: /char setname <name>");
                    return false;
                }
            }
        }
        return false;
    }
}
