package me.flyingtaco725.indigorpg.PlayerInfo;

import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import me.flyingtaco725.indigorpg.PlayerInfo.TheCharacter;
public class ThePlayer {
    // the players active character, or character in process of being created
    private TheCharacter activeCharacter;
    private UUID playerUUID;
    // list of characters associated with a player
    private List<TheCharacter> characters;
    // boolean values to control menu locks
    public boolean isCharacterSelected = false;
    public boolean isClassSelected = false;



    // Constructor
    public ThePlayer(Player player) {
        // gets the players uuid
        this.playerUUID = player.getUniqueId();
        this.characters = new ArrayList<>();
    }

    // for adding characters
    public void addCharacter(TheCharacter character) {
        characters.add(character);
    }
    // for deleting characters
    public void removeCharacter(String name) {
        characters.removeIf(character -> character.getName().equalsIgnoreCase(name));
    }
    // finding a character by name
    public TheCharacter getCharacter(String name) {
        for (TheCharacter character : characters) {
            if (character.getName().equalsIgnoreCase(name)) {
                return character;
            }
        }
        return null; // If character is not found
    }
    // deleting active character object
    public void clearActiveCharacter(){
        this.activeCharacter = null;
    }

    public boolean doesCharacterExist(String name) {
        for (TheCharacter character : characters) {
            if (character.getName().equalsIgnoreCase(name)) {
                return true; // Character found
            }
        }
        return false; // Character not found
    }

    // getters
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

    // setters
    public void setCharacterSelected(boolean selected){
        this.isCharacterSelected = selected;
    }
    public void setClassSelected(boolean selected){
        this.isClassSelected = selected;
    }
    public void setPendingCharacter(String charClass, int lvl){
        this.activeCharacter = new TheCharacter("", charClass, lvl);
    }
    public void setActiveCharacter(String charName, String charClass, int lvl){
        this.activeCharacter = new TheCharacter(charName, charClass, lvl);
    }
    public void setPendingCharacterName(String charName){
        this.activeCharacter.setName(charName);
    }
}

