/**
 * Potato Class - Extends Plant (Peruna in Swedish)
 * Demonstrates INHERITANCE and POLYMORPHISM
 * Each plant type has different watering and sunlight needs
 */
public class Potato extends Plant {

    public Potato() {
        super("Peruna (Potato)");
    }

    /**
     * Potatoes need moderate watering
     * POLYMORPHIC implementation of abstract water() method
     */
    @Override
    public void water() {
        int currentWater = this.getWaterLevel();
        this.setWaterLevel(currentWater + 25);
        System.out.println("You water the " + this.getName() + ". Water level: " + this.getWaterLevel());
    }

    /**
     * Potatoes prefer indirect sunlight
     * POLYMORPHIC implementation of abstract bask() method
     */
    @Override
    public void bask() {
        int currentSunlight = this.getSunlightLevel();
        this.setSunlightLevel(currentSunlight + 15); // Less sunlight needed
        System.out.println("Your " + this.getName() + " gets some gentle sunlight. Sunlight level: " + this.getSunlightLevel());
    }

    /**
     * Potatoes produce tubers/potatoes
     */
    @Override
    public String harvestProduct() {
        if (this.getGrowthStage() < 5) {
            return "Not ready yet! Plant is only at stage " + this.getGrowthStage();
        }
        return "Fresh potatoes harvested! You got 15 potatoes.";
    }
}
