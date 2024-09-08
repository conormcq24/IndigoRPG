package me.flyingtaco725.indigorpg.PlayerInfo;

import org.bukkit.Location;

public class TheCharacter {
    private String name;
    private String characterClass;
    private int level;
    private Location currentLocation;

    public TheCharacter(String name, String characterClass, int level) {
        this.name = name;
        this.characterClass = characterClass;
        this.level = level;
    }

    // Getters
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

    // Setters
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
    @Override
    public String toString() {
        return "Character{name='" + name + "', class='" + characterClass + "', level=" + level + "}";
    }
}