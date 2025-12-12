/**
 * Cucumber Class - Extends Plant (Cucumis in Swedish)
 * Demonstrates INHERITANCE and POLYMORPHISM
 */
public class Cucumber extends Plant {

    public Cucumber() {
        super("Cucumis (Cucumber)");
    }

    /**
     * Cucumbers need moderate water
     * POLYMORPHIC implementation
     */
    @Override
    public void water() {
        int currentWater = this.getWaterLevel();
        this.setWaterLevel(currentWater + 28);
        System.out.println("You water the " + this.getName() + ". Water level: " + this.getWaterLevel());
    }

    /**
     * Cucumbers need good sunlight
     * POLYMORPHIC implementation
     */
    @Override
    public void bask() {
        int currentSunlight = this.getSunlightLevel();
        this.setSunlightLevel(currentSunlight + 28);
        System.out.println("Your " + this.getName() + " receives good sunlight. Sunlight level: " + this.getSunlightLevel());
    }

    /**
     * Cucumbers produce fruits
     */
    @Override
    public String harvestProduct() {
        if (this.getGrowthStage() < 5) {
            return "Not ready yet! Plant is only at stage " + this.getGrowthStage();
        }
        return "Fresh cucumbers harvested! You got 8 cucumbers.";
    }
}
