/**
 * PlantFactory - DESIGN PATTERN: Factory Method
 * 
 * This class implements the Factory Method design pattern.
 * Instead of directly instantiating plant objects, we use this factory
 * to create plants based on a choice parameter.
 * 
 * BENEFITS:
 * - Encapsulates object creation logic
 * - Easier to add new plant types without modifying existing code
 * - Centralizes plant instantiation
 * - Follows the Open/Closed Principle (open for extension, closed for modification)
 * 
 * Converts the Python dictionary approach into a more OOP way
 */
public class PlantFactory {

    // Available plant choices
    private static final String[] PLANT_CHOICES = {
            "1", "2", "3", "4", "5"
    };

    /**
     * Creates a plant based on the given choice
     * 
     * @param choice The plant choice (1-5)
     * @return A new Plant object of the selected type, or null if invalid choice
     */
    public static Plant createPlant(String choice) {
        switch (choice.trim()) {
            case "1":
                return new Potato();
            case "2":
                return new Marigold();
            case "3":
                return new Tomato();
            case "4":
                return new Cucumber();
            case "5":
                return new Sunflower();
            default:
                System.out.println("Invalid choice. Please pick a number between 1 and 5.");
                return null;
        }
    }

    /**
     * Returns the name of a plant based on choice (for display)
     * 
     * @param choice The plant choice
     * @return Plant name
     */
    public static String getPlantName(String choice) {
        switch (choice.trim()) {
            case "1":
                return "Peruna (Potato)";
            case "2":
                return "Calendula (Marigold)";
            case "3":
                return "Lycopersicum (Tomato)";
            case "4":
                return "Cucumis (Cucumber)";
            case "5":
                return "Helianthus (Sunflower)";
            default:
                return "Unknown Plant";
        }
    }

    /**
     * Returns all available plant choices
     * 
     * @return Array of valid plant choice strings
     */
    public static String[] getAvailableChoices() {
        return PLANT_CHOICES;
    }

    /**
     * Gets all plant names except the chosen one (for display purposes)
     * 
     * @param chosenPlantChoice The choice of the plant the player selected
     * @return Array of other plant names
     */
    public static String[] getOtherPlants(String chosenPlantChoice) {
        java.util.List<String> otherPlants = new java.util.ArrayList<>();
        for (String choice : PLANT_CHOICES) {
            if (!choice.equals(chosenPlantChoice.trim())) {
                otherPlants.add(getPlantName(choice));
            }
        }
        return otherPlants.toArray(new String[0]);
    }
}
