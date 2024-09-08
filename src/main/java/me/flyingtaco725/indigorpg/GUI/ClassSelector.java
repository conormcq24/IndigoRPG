package me.flyingtaco725.indigorpg.GUI;

import me.flyingtaco725.indigorpg.IndigoRPG;
import me.flyingtaco725.indigorpg.PlayerInfo.TheCharacter;
import me.flyingtaco725.indigorpg.PlayerInfo.ThePlayer;
import me.flyingtaco725.indigorpg.Utility.utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ClassSelector {
    private IndigoRPG plugin;

    public ClassSelector(IndigoRPG plugin) {
        this.plugin = plugin;
    }
    public void openClassSelector(Player player){
        // Create a custom inventory with 9 slots and the Select a Class titel
        Inventory customInventory = Bukkit.createInventory(null, 9, "Select a Class");
        // set the 5 classes
        ItemStack cleric = utils.createClassItem(Material.LIME_TERRACOTTA, "Cleric", "Staff/Totem", "Healer");
        ItemStack knight = utils.createClassItem(Material.RED_TERRACOTTA, "Knight", "Sword/Axe/Hammer", "Tank");
        ItemStack ranger = utils.createClassItem(Material.GREEN_TERRACOTTA, "Ranger", "Bow/Crossbow", "Ranged Attack");
        ItemStack rogue = utils.createClassItem(Material.BLACK_TERRACOTTA, "Rogue", "Daggers", "Close Attack");
        ItemStack sorcerer = utils.createClassItem(Material.BLUE_TERRACOTTA, "Sorcerer", "Wand/Sceptre", "Ranged Attack");
        ItemStack back = utils.createClassItem(Material.BARRIER, "Go Back", "", "");

        // Add the items to the inventory
        customInventory.setItem(2, cleric);
        customInventory.setItem(3, knight);
        customInventory.setItem(4, ranger);
        customInventory.setItem(5, rogue);
        customInventory.setItem(6, sorcerer);
        customInventory.setItem(0, back);
        customInventory.setItem(8, back);

        player.openInventory(customInventory);
    }

    public void handleClassClick (InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        // find the player in the list if exists
        ThePlayer thePlayer = plugin.theList.getPlayer(player.getUniqueId());
        event.setCancelled(true);

        Material option = event.getCurrentItem().getType();
        switch(option){
            case LIME_TERRACOTTA:
                thePlayer.setClassSelected(true);
                player.closeInventory();
                freezeAndReset(thePlayer, player, "cleric");
                break;
            case RED_TERRACOTTA:
                thePlayer.setClassSelected(true);
                player.closeInventory();
                freezeAndReset(thePlayer, player, "knight");
                break;
            case GREEN_TERRACOTTA:
                thePlayer.setClassSelected(true);
                player.closeInventory();
                freezeAndReset(thePlayer, player, "ranger");
                break;
            case BLACK_TERRACOTTA:
                thePlayer.setClassSelected(true);
                player.closeInventory();
                freezeAndReset(thePlayer, player, "rogue");
                break;
            case BLUE_TERRACOTTA:
                thePlayer.setClassSelected(true);
                player.closeInventory();
                freezeAndReset(thePlayer, player, "sorcerer");
                break;
            case BARRIER:
                thePlayer.setCharacterSelected(false);
                player.closeInventory();
                plugin.characterSelector.openCharacterSelector(player);
                break;
            default:
                break;
        }
    }
    private void freezeAndReset(ThePlayer thePlayer, Player player, String charClass){
        utils.freezePlayer(player);
        player.sendMessage("§e§l[IRPG] §l§cImmobilized until you set a name for your " + charClass);
        player.sendMessage("§e§lUse: §l§c/char setname <name>");
        // reset isCharacterSelected + classSelected so that when they log in again they are still forced to select/create character
        thePlayer.setCharacterSelected(false);
        thePlayer.setClassSelected(false);
        // create a temporary character to import to your character list later (sets class and level 1, and an empty name)
        thePlayer.setPendingCharacter(charClass, 1);
    }
}
