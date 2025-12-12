/**
 * Player Class - Represents a player in the PlanTings game
 * Demonstrates ENCAPSULATION with private fields and public getters/setters
 * Follows naming conventions from Python game (PlantParent → Player)
 */
public class Player {

    // Private fields - Encapsulation principle
    private String name;
    private String characterName;

    /**
     * Constructor - creates a player with a given name
     * @param playerName The name of the player (input by user)
     */
    public Player(String playerName) {
        this.name = playerName;
        // Create character name by taking first letter of player name in uppercase
        this.characterName = "Plant_" + playerName.substring(0, 1).toUpperCase();
    }

    // Getter for player name
    public String getName() {
        return this.name;
    }

    // Setter for player name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for character name
    public String getCharacterName() {
        return this.characterName;
    }

    // Setter for character name
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", characterName='" + characterName + '\'' +
                '}';
    }
}
