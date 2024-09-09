package me.flyingtaco725.indigorpg.PlayerInfo;

import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import me.flyingtaco725.indigorpg.PlayerInfo.TheCharacter;
public class ThePlayer {
    // CLASS VARIABLES
    private TheCharacter activeCharacter;
    private UUID playerUUID;
    private List<TheCharacter> characters;
    public boolean isCharacterSelected = false;
    public boolean isClassSelected = false;
    // GETTERS
    public boolean isCharacterSelected() {
        return isCharacterSelected;
    }
    public boolean isClassSelected() {
        return isClassSelected;
    }
    public List<TheCharacter> getCharacters() {
        return characters;
    }
    public UUID getPlayerUUID() {
        return playerUUID;
    }
    public TheCharacter getActiveCharacter() { return activeCharacter; }
    // SETTERS
    public void setCharacterSelected(boolean selected){
        this.isCharacterSelected = selected;
    }
    public void setClassSelected(boolean selected){
        this.isClassSelected = selected;
    }
    public void setPendingCharacter(String charClass, int lvl){
        this.activeCharacter = new TheCharacter("", charClass, lvl);
    }
    public void setActiveCharacter(TheCharacter theCharacter){
        this.activeCharacter = theCharacter;
    }
    public void setPendingCharacterName(String charName){
        this.activeCharacter.setName(charName);
    }

    // CONSTRUCTOR
    public ThePlayer(Player player) {
        // gets the players uuid
        this.playerUUID = player.getUniqueId();
        this.characters = new ArrayList<>();
    }

    // HELPER FUNCTIONS
    /*
        This function adds a character to the player object
     */
    public void addCharacter(TheCharacter character) {
        characters.add(character);
    }
    /*
        This function deletes a character by name
     */
    public void removeCharacter(String name) {
        characters.removeIf(character -> character.getName().equalsIgnoreCase(name));
    }
    /*
        This function finds a character by name
     */
    public TheCharacter getCharacter(String name) {
        for (TheCharacter character : characters) {
            if (character.getName().equalsIgnoreCase(name)) {
                return character;
            }
        }
        return null;
    }
    /*
        This function clears the players activeCharacter information
     */
    public void clearActiveCharacter(){
        this.activeCharacter = null;
    }
    /*
        This function verifies if a character exists within a player
        object
     */
    public boolean doesCharacterExist(String name) {
        for (TheCharacter character : characters) {
            if (character.getName().equalsIgnoreCase(name)) {
                return true; // Character found
            }
        }
        return false; // Character not found
    }
    /*
        This function handles saving character data,
        if the character does not exist, new entry
        if it does, overwrite old character
     */
    public void saveActiveCharacter() {
        if (activeCharacter == null) {
            throw new IllegalStateException("No active character to save.");
        }

        // Check if a character with the same name exists
        boolean characterUpdated = false;
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i).getName().equalsIgnoreCase(activeCharacter.getName())) {
                // Overwrite the existing character
                characters.set(i, activeCharacter);
                characterUpdated = true;
                break;
            }
        }

        // If no character was updated, add the active character to the list
        if (!characterUpdated) {
            addCharacter(activeCharacter);
        }
    }
}

