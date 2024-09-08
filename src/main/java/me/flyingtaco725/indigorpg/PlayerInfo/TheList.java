package me.flyingtaco725.indigorpg.PlayerInfo;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TheList {

    private List<ThePlayer> playerList;
    private Location creationLocation;

    public TheList() {
        this.playerList = new ArrayList<>();
    }

    // Should only add a player, if they are not on the list already
    public void addPlayer(ThePlayer player) {
        if (!containsPlayer(player.getPlayerUUID())) {
            playerList.add(player);
        }
    }

    // Should check if the list already contains a player
    public boolean containsPlayer(UUID playerUUID) {
        return playerList.stream().anyMatch(player -> player.getPlayerUUID().equals(playerUUID));
    }

    // Should find player by uuid
    public ThePlayer getPlayer(UUID playerUUID) {
        Optional<ThePlayer> player = playerList.stream()
                .filter(p -> p.getPlayerUUID().equals(playerUUID))
                .findFirst();
        return player.orElse(null);
    }

    // Remove player by uuid
    public void removePlayer(UUID playerUUID) {
        playerList.removeIf(player -> player.getPlayerUUID().equals(playerUUID));
    }

    public boolean doesCharacterExistInAnyPlayer(String charName) {
        for (ThePlayer player : playerList) {
            if (player.doesCharacterExist(charName)) {
                return true; // Character found
            }
        }
        return false; // Character not found in any player
    }

    // Should return all players
    public List<ThePlayer> getPlayers() {
        return playerList;
    }

    public void setCreationLocation(Location location){
        this.creationLocation = location;
    }

    public Location getCreationLocation(){
        return this.creationLocation;
    }
}
