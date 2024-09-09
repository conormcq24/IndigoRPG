package me.flyingtaco725.indigorpg.PlayerInfo;

import org.bukkit.Location;

public class TheCharacter {
    // CLASS VARIABLES
    private String name;
    private String characterClass;
    private int level;
    private Location currentLocation;
    // GETTERS
    public String getName() {
        return name;
    }
    public String getCharacterClass() {
        return characterClass;
    }
    public int getLevel() {
        return level;
    }
    public Location getCurrentLocation(){
        return this.currentLocation;
    }
    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setCurrentLocation(Location location){
        this.currentLocation = location;
    }
    // CONSTRUCTOR
    public TheCharacter(String name, String characterClass, int level) {
        this.name = name;
        this.characterClass = characterClass;
        this.level = level;
    }
}