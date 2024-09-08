package me.flyingtaco725.indigorpg.GUI;

import me.flyingtaco725.indigorpg.IndigoRPG;
import me.flyingtaco725.indigorpg.PlayerInfo.TheCharacter;
import me.flyingtaco725.indigorpg.PlayerInfo.ThePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class CharacterSelector {
    private IndigoRPG plugin;

    public CharacterSelector(IndigoRPG plugin) {
        this.plugin = plugin;
    }

    public void openCharacterSelector(Player player) {
        // grab the player object from the list
        ThePlayer thePlayer = plugin.theList.getPlayer(player.getUniqueId());

        // TODO: build functionality to retrieve player info rather than use example info
        // load any existing classes: coming later

        // Create a custom inventory with 9 slots and a custom title
        Inventory customInventory = Bukkit.createInventory(null, 9, "Character Selection");

        // Define the item that will be used as a character creation option
        ItemStack createChar = new ItemStack(Material.ARMOR_STAND);
        ItemMeta createCharMeta = createChar.getItemMeta();
        createCharMeta.setDisplayName("§aCreate Character");
        createChar.setItemMeta(createCharMeta);

        // Define the player skull that will be used to represent players characters
        ItemStack selectChar = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) selectChar.getItemMeta();

        // Ensure list is not empty
        if (thePlayer != null){
            List<TheCharacter> theCharacters = thePlayer.getCharacters();
            int characterCount = theCharacters.size();

            for (int i = 0; i <  5; i++){
                if (i < characterCount){
                    // build a head for each character
                    me.flyingtaco725.indigorpg.PlayerInfo.TheCharacter theCharacter = theCharacters.get(i);
                    skullMeta.setDisplayName("§e" + theCharacter.getName());
                    skullMeta.setOwningPlayer(player);
                    List<String> lore = List.of(
                            "§7Class: " + theCharacter.getCharacterClass(),
                            "§7Level: " + theCharacter.getLevel()
                    );
                    skullMeta.setLore(lore);
                    selectChar.setItemMeta(skullMeta);

                    customInventory.setItem(i + 2, selectChar);
                }
                else {
                    // fill remaining slots with an armor stand
                    customInventory.setItem(i + 2, createChar);
                }
            }
        }
        // Open the inventory for the player
        player.openInventory(customInventory);
    }

    public void handleCharacterClick (InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        // find the player in the list if exists
        ThePlayer thePlayer = plugin.theList.getPlayer(player.getUniqueId());
        event.setCancelled(true);

        // Ensure the clicked item is not null or air
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
            return; // Exit early if the item is invalid
        }

        // Check if the clicked item is the "CREATE CHARACTER" item
        if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.ARMOR_STAND) {
            thePlayer.setCharacterSelected(true);
            player.closeInventory();
            // advance to class selection screen
            plugin.classSelector.openClassSelector(player);
        }
        // Check if the clicked item is the "SELECT CHARACTER" item
        if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.PLAYER_HEAD) {
            thePlayer.setCharacterSelected(true);
            player.closeInventory();
            // collect information on the character we clicked on
            ItemMeta meta = event.getCurrentItem().getItemMeta();
            List<String> lore = meta.getLore();
            String uneditedClass = lore.get(0);

            // instead of setting everything manually pull from theList.thePlayer.theCharacter
            // it should have saved the last time they disconnected
            // use thePlayer information to find character by name:
            TheCharacter theCharacter = thePlayer.getCharacter(meta.getDisplayName().replace("§e", ""));
            thePlayer.setActiveCharacter(theCharacter);

            if (thePlayer.getCharacters() != null){
                // teleport player to the characters last known location
                player.teleport(thePlayer.getActiveCharacter().getCurrentLocation());
            }
        }
    }
    // Helper method to extract value from lore string
    private String extractValue(String loreLine, String prefix) {
        if (loreLine != null && loreLine.startsWith("§7" + prefix)) {
            return loreLine.replace("§7" + prefix, "").trim();
        }
        return null;
    }

    // Helper method to extract level from lore string
    private int extractLevel(String loreLine, String prefix) {
        String value = extractValue(loreLine, prefix);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return -1; // Return -1 if parsing fails
            }
        }
        return -1; // Return -1 if value is null
    }
    // helper function to make location string
    public String locationToString(Location location) {
        String world = location.getWorld().getName();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        float yaw = location.getYaw();
        float pitch = location.getPitch();

        return world + "," + x + "," + y + "," + z + "," + yaw + "," + pitch;
    }
}
