package me.flyingtaco725.indigorpg.PlayerInfo;

public class TheCharacter {
    private String name;
    private String characterClass;
    private int level;

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

    @Override
    public String toString() {
        return "Character{name='" + name + "', class='" + characterClass + "', level=" + level + "}";
    }
}