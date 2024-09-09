package me.flyingtaco725.indigorpg.PlayerInfo;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TheList {

    // CLASS VARIABLES
    private List<ThePlayer> playerList;
    private Location creationLocation;

    public TheList() {
        this.playerList = new ArrayList<>();
    }

    // GETTERS
    public Location getCreationLocation(){
        return this.creationLocation;
    }
    // SETTERS
    public void setCreationLocation(Location location){
        this.creationLocation = location;
    }

    // HELPER FUNCTIONS
    /*
        This function adds a player to "theList" using
        their UUID
     */
    public void addPlayer(ThePlayer player) {
        if (!containsPlayer(player.getPlayerUUID())) {
            playerList.add(player);
        }
    }

    /*
        This function checks if a player already exists within
        "theList"
     */
    public boolean containsPlayer(UUID playerUUID) {
        return playerList.stream().anyMatch(player -> player.getPlayerUUID().equals(playerUUID));
    }

    /*
        This function gets a player from "theList" by using
        their UUID
     */
    public ThePlayer getPlayer(UUID playerUUID) {
        Optional<ThePlayer> player = playerList.stream()
                .filter(p -> p.getPlayerUUID().equals(playerUUID))
                .findFirst();
        return player.orElse(null);
    }

    /*
        This function deletes a player from "theList" by using
        their UUID
     */
    public void removePlayer(UUID playerUUID) {
        playerList.removeIf(player -> player.getPlayerUUID().equals(playerUUID));
    }

    /*
        Checks the entire list for a character name,
        and verifies whether it exists
     */
    public boolean doesCharacterExistInAnyPlayer(String charName) {
        for (ThePlayer player : playerList) {
            if (player.doesCharacterExist(charName)) {
                return true;
            }
        }
        return false;
    }

    /*
        returns "theList"
     */
    public List<ThePlayer> getPlayers() {
        return playerList;
    }
}
