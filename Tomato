/**
 * Tomato Class - Extends Plant (Lycopersicum in Swedish)
 * Demonstrates INHERITANCE and POLYMORPHISM
 */
public class Tomato extends Plant {

    public Tomato() {
        super("Lycopersicum (Tomato)");
    }

    /**
     * Tomatoes need lots of water
     * POLYMORPHIC implementation
     */
    @Override
    public void water() {
        int currentWater = this.getWaterLevel();
        this.setWaterLevel(currentWater + 35); // Lots of water needed
        System.out.println("You give the " + this.getName() + " plenty of water. Water level: " + this.getWaterLevel());
    }

    /**
     * Tomatoes need lots of sunlight
     * POLYMORPHIC implementation
     */
    @Override
    public void bask() {
        int currentSunlight = this.getSunlightLevel();
        this.setSunlightLevel(currentSunlight + 35); // Lots of sun
        System.out.println("Your " + this.getName() + " soaks up the sun! Sunlight level: " + this.getSunlightLevel());
    }

    /**
     * Tomatoes produce fruits
     */
    @Override
    public String harvestProduct() {
        if (this.getGrowthStage() < 6) {
            return "Not ready yet! Plant is only at stage " + this.getGrowthStage();
        }
        return "Delicious red tomatoes harvested! You got 12 tomatoes.";
    }
}
