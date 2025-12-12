import java.util.*;
import java.util.function.*;

/**
 * Plant Abstract Class - Base class for all plants
 * Demonstrates ABSTRACTION, INHERITANCE, and FUNCTIONAL PROGRAMMING principles
 */
public abstract class Plant {

    // Private fields - Encapsulation
    private String name;
    private int health;
    private int waterLevel;
    private int sunlightLevel;
    private int growthStage;
    private boolean isAlive;

    // FUNCTIONAL PROGRAMMING: Predicates for plant conditions
    public static final Predicate<Plant> IS_THRIVING = plant -> plant.health > 80 && plant.waterLevel > 50
            && plant.sunlightLevel > 50;

    public static final Predicate<Plant> NEEDS_IMMEDIATE_CARE = plant -> plant.health < 30 || plant.waterLevel < 20
            || plant.sunlightLevel < 20;

    public static final Predicate<Plant> CAN_GROW = plant -> plant.waterLevel > 30 && plant.sunlightLevel > 30
            && plant.health > 50;

    // FUNCTIONAL: Consumer for state updates
    private static final Consumer<Plant> UPDATE_HEALTH_STATUS = plant -> {
        int healthBoost = (plant.waterLevel / 2) + (plant.sunlightLevel / 3);
        plant.health = Math.min(100, plant.health + healthBoost / 10);
    };

    /**
     * Constructor for Plant
     * 
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
     * FUNCTIONAL: Concrete method - common to all plants
     * Uses Predicates and Consumers for cleaner logic
     */
    public void grow() {
        // OPTIONAL: Safe operation check
        Optional.of(this)
                .filter(plant -> plant.isAlive)
                .ifPresentOrElse(
                        plant -> performGrowth(),
                        () -> System.out.println("Your " + name + " is no longer alive."));
    }

    /**
     * FUNCTIONAL: Performs growth operations using predicates
     */
    private void performGrowth() {
        // Check critical conditions using predicates
        checkCriticalNeeds();

        // Natural resource decay using lambda
        Arrays.asList(
                (Runnable) () -> waterLevel = Math.max(0, waterLevel - 5),
                (Runnable) () -> sunlightLevel = Math.max(0, sunlightLevel - 3)).forEach(Runnable::run);

        // PREDICATE: Check if plant can grow
        if (CAN_GROW.test(this)) {
            growthStage++;
            System.out.println("Your " + name + " is growing! Stage: " + growthStage);
        }

        // CONSUMER: Update health
        UPDATE_HEALTH_STATUS.accept(this);
    }

    /**
     * FUNCTIONAL: Checks critical needs using predicates
     */
    private void checkCriticalNeeds() {
        // Create list of condition checks with their messages
        Map<Predicate<Plant>, String> criticalChecks = new LinkedHashMap<>();
        criticalChecks.put(plant -> plant.waterLevel < 20, "Your " + name + " needs water!");
        criticalChecks.put(plant -> plant.sunlightLevel < 20, "Your " + name + " needs sunlight!");

        // STREAM: Process all checks
        criticalChecks.forEach((predicate, message) -> {
            if (predicate.test(this)) {
                health -= 10;
                System.out.println(message);
            }
        });

        // Check if plant dies
        if (health <= 0) {
            health = 0;
            isAlive = false;
            System.out.println("Oh no! Your " + name + " has withered away.");
        }
    }

    /**
     * FUNCTIONAL: Get comprehensive plant status using Stream
     * 
     * @return Map of status attributes
     */
    public Map<String, Object> getStatus() {
        Map<String, Object> status = new LinkedHashMap<>();
        status.put("name", name);
        status.put("health", health);
        status.put("waterLevel", waterLevel);
        status.put("sunlightLevel", sunlightLevel);
        status.put("growthStage", growthStage);
        status.put("isAlive", isAlive);
        status.put("isThriving", IS_THRIVING.test(this));
        status.put("needsCare", NEEDS_IMMEDIATE_CARE.test(this));
        return status;
    }

    /**
     * FUNCTIONAL: Get diagnostic messages using Stream
     * 
     * @return List of diagnostic messages
     */
    public List<String> getDiagnostics() {
        List<String> diagnostics = new ArrayList<>();

        // STREAM with predicates
        Map<Predicate<Plant>, String> checks = new LinkedHashMap<>();
        checks.put(IS_THRIVING, "✓ Plant is thriving!");
        checks.put(NEEDS_IMMEDIATE_CARE, "⚠ Plant needs immediate care!");
        checks.put(plant -> plant.waterLevel < 50, "💧 Consider watering");
        checks.put(plant -> plant.sunlightLevel < 50, "☀ Needs more sunlight");
        checks.put(plant -> plant.growthStage >= 5, "🌱 Ready for harvest!");

        checks.forEach((predicate, message) -> {
            if (predicate.test(this)) {
                diagnostics.add(message);
            }
        });

        return diagnostics;
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
