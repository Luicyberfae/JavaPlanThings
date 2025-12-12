/**
 * Marigold Class - Extends Plant (Calendula in Swedish)
 * Demonstrates INHERITANCE and POLYMORPHISM
 */
public class Marigold extends Plant {

    public Marigold() {
        super("Calendula (Marigold)");
    }

    /**
     * Marigolds are drought-tolerant, need less water
     * POLYMORPHIC implementation
     */
    @Override
    public void water() {
        int currentWater = this.getWaterLevel();
        this.setWaterLevel(currentWater + 15); // Less water needed
        System.out.println("You give the " + this.getName() + " a little water. Water level: " + this.getWaterLevel());
    }

    /**
     * Marigolds love sunlight
     * POLYMORPHIC implementation
     */
    @Override
    public void bask() {
        int currentSunlight = this.getSunlightLevel();
        this.setSunlightLevel(currentSunlight + 30); // More sunlight
        System.out.println("Your " + this.getName() + " basks in the sun! Sunlight level: " + this.getSunlightLevel());
    }

    /**
     * Marigolds produce seeds
     */
    @Override
    public String harvestProduct() {
        if (this.getGrowthStage() < 4) {
            return "Not ready yet! Plant is only at stage " + this.getGrowthStage();
        }
        return "Beautiful marigold seeds collected! You got 50 seeds.";
    }
}
