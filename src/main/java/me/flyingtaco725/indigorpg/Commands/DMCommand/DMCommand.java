package me.flyingtaco725.indigorpg.Commands.DMCommand;

import me.flyingtaco725.indigorpg.IndigoRPG;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DMCommand implements CommandExecutor {
    private final IndigoRPG plugin;

    public DMCommand(IndigoRPG plugin) {
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
        if (!player.hasPermission("dm.use")) {
            player.sendMessage("§e§l[IRPG] §l§cYou do not have permission for this command");
            return false;
        }
        if (args.length > 0 && (args[0].equalsIgnoreCase("charactercreatelocation") || args[0].equalsIgnoreCase("charcreatelocation"))) {
            if(!player.hasPermission("dm.charactercreatelocation")){
                player.sendMessage("§e§l[IRPG] §l§cYou do not have permission to set the create a character location");
                return false;
            } else {
                if (args.length == 1){
                    DMCommandHelpers.handleCharacterCreateLocation(plugin, player);
                    return true;
                } else {
                    player.sendMessage("§e§l[IRPG] §l§cIncorrect usage try: /dm charactercreatelocation OR /dm charcreatelocation");
                    return false;
                }
            }
        }
        return false;
    }
}
