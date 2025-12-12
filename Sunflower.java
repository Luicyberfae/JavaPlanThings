/**
 * Sunflower Class - Extends Plant (Helianthus in Swedish)
 * Demonstrates INHERITANCE and POLYMORPHISM
 */
public class Sunflower extends Plant {

    public Sunflower() {
        super("Helianthus (Sunflower)");
    }

    /**
     * Sunflowers need moderate water
     * POLYMORPHIC implementation
     */
    @Override
    public void water() {
        int currentWater = this.getWaterLevel();
        this.setWaterLevel(currentWater + 20);
        System.out.println("You water the " + this.getName() + ". Water level: " + this.getWaterLevel());
    }

    /**
     * Sunflowers LOVE sunlight (as the name suggests!)
     * POLYMORPHIC implementation
     */
    @Override
    public void bask() {
        int currentSunlight = this.getSunlightLevel();
        this.setSunlightLevel(currentSunlight + 40); // Maximum sunlight
        System.out.println("Your " + this.getName() + " loves the sun! Sunlight level: " + this.getSunlightLevel());
    }

    /**
     * Sunflowers produce seeds
     */
    @Override
    public String harvestProduct() {
        if (this.getGrowthStage() < 6) {
            return "Not ready yet! Plant is only at stage " + this.getGrowthStage();
        }
        return "Beautiful sunflower seeds harvested! You got 80 seeds.";
    }
}
