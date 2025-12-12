/**
 * Plant Abstract Class - Base class for all plants
 * Demonstrates ABSTRACTION and INHERITANCE principles
 * Similar to the abstract Sport class in Abstraction.java
 */
public abstract class Plant {

    // Private fields - Encapsulation
    private String name;
    private int health;
    private int waterLevel;
    private int sunlightLevel;
    private int growthStage;
    private boolean isAlive;

    /**
     * Constructor for Plant
     * @param name The name of the plant
     */
    public Plant(String name) {
        this.name = name;
        this.health = 100;
        this.waterLevel = 50;
        this.sunlightLevel = 50;
        this.growthStage = 1; // Seedling stage
        this.isAlive = true;
    }

    /**
     * Abstract method - Each plant type waters differently
     * Demonstrates ABSTRACTION
     */
    public abstract void water();

    /**
     * Abstract method - Each plant has different sunlight needs
     * Demonstrates ABSTRACTION
     */
    public abstract void bask();

    /**
     * Abstract method - Each plant type gives different fruit/seeds
     * Demonstrates ABSTRACTION
     */
    public abstract String harvestProduct();

    /**
     * Concrete method - common to all plants
     * Updates plant health based on water and sunlight
     */
    public void grow() {
        if (!isAlive) {
            System.out.println("Your " + name + " is no longer alive.");
            return;
        }

        // Health decreases if plant is neglected
        if (waterLevel < 20) {
            health -= 10;
            System.out.println("Your " + name + " needs water!");
        }
        if (sunlightLevel < 20) {
            health -= 10;
            System.out.println("Your " + name + " needs sunlight!");
        }

        // Plant dies if health reaches 0
        if (health <= 0) {
            health = 0;
            isAlive = false;
            System.out.println("Oh no! Your " + name + " has withered away.");
        }

        // Natural decay of water and sunlight
        waterLevel = Math.max(0, waterLevel - 5);
        sunlightLevel = Math.max(0, sunlightLevel - 3);

        // Plant grows if conditions are good
        if (waterLevel > 30 && sunlightLevel > 30 && health > 50) {
            growthStage++;
            System.out.println("Your " + name + " is growing! Stage: " + growthStage);
        }

        updateHealth();
    }

    /**
     * Updates plant health based on current conditions
     */
    private void updateHealth() {
        int healthBoost = (waterLevel / 2) + (sunlightLevel / 3);
        health = Math.min(100, health + healthBoost / 10);
    }

    // ========== ENCAPSULATION: Getters and Setters ==========

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.min(100, Math.max(0, health));
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = Math.min(100, Math.max(0, waterLevel));
    }

    public int getSunlightLevel() {
        return sunlightLevel;
    }

    public void setSunlightLevel(int sunlightLevel) {
        this.sunlightLevel = Math.min(100, Math.max(0, sunlightLevel));
    }

    public int getGrowthStage() {
        return growthStage;
    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public String toString() {
        return name + " {" +
                "health=" + health +
                ", waterLevel=" + waterLevel +
                ", sunlightLevel=" + sunlightLevel +
                ", growthStage=" + growthStage +
                ", isAlive=" + isAlive +
                '}';
    }
}
