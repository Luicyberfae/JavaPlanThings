import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * PlantFactory - DESIGN PATTERN: Factory Method + FUNCTIONAL PROGRAMMING
 * 
 * This class implements the Factory Method design pattern with FUNCTIONAL enhancements:
 * - Lambda expressions for plant creation
 * - Optional for null safety
 * - Stream API for filtering and mapping
 * - Predicates for validation
 * 
 * BENEFITS:
 * - Encapsulates object creation logic
 * - Easier to add new plant types without modifying existing code
 * - Centralizes plant instantiation
 * - Follows the Open/Closed Principle (open for extension, closed for modification)
 * - Functional programming for cleaner, more expressive code
 */
public class PlantFactory {

    // FUNCTIONAL PROGRAMMING: HashMap with Supplier lambdas for plant creation
    private static final Map<String, Supplier<Plant>> PLANT_SUPPLIERS = new HashMap<>() {{
        put("1", Potato::new);          // Method reference (lambda shorthand)
        put("2", Marigold::new);
        put("3", Tomato::new);
        put("4", Cucumber::new);
        put("5", Sunflower::new);
    }};

    // Plant metadata using functional approach
    private static final Map<String, String> PLANT_NAMES = new HashMap<>() {{
        put("1", "Peruna (Potato)");
        put("2", "Calendula (Marigold)");
        put("3", "Lycopersicum (Tomato)");
        put("4", "Cucumis (Cucumber)");
        put("5", "Helianthus (Sunflower)");
    }};

    // PREDICATE: Validates plant choice
    private static final Predicate<String> IS_VALID_CHOICE = 
        choice -> PLANT_SUPPLIERS.containsKey(choice.trim());

    /**
     * FUNCTIONAL: Creates a plant using Optional and lambda
     * 
     * @param choice The plant choice (1-5)
     * @return Optional containing Plant object, or empty if invalid
     */
    public static Optional<Plant> createPlantSafe(String choice) {
        return Optional.ofNullable(choice)
                .map(String::trim)
                .filter(IS_VALID_CHOICE)
                .map(PLANT_SUPPLIERS::get)
                .map(Supplier::get);
    }

    /**
     * Creates a plant based on the given choice (backward compatible)
     * 
     * @param choice The plant choice (1-5)
     * @return A new Plant object of the selected type, or null if invalid choice
     */
    public static Plant createPlant(String choice) {
        return createPlantSafe(choice)
                .orElseGet(() -> {
                    System.out.println("Invalid choice. Please pick a number between 1 and 5.");
                    return null;
                });
    }

    /**
     * FUNCTIONAL: Returns the name of a plant using Optional
     * 
     * @param choice The plant choice
     * @return Plant name or "Unknown Plant"
     */
    public static String getPlantName(String choice) {
        return Optional.ofNullable(choice)
                .map(String::trim)
                .map(PLANT_NAMES::get)
                .orElse("Unknown Plant");
    }

    /**
     * STREAM API: Returns all available plant choices
     * 
     * @return List of valid plant choice strings
     */
    public static List<String> getAvailableChoices() {
        return new ArrayList<>(PLANT_SUPPLIERS.keySet());
    }

    /**
     * STREAM + LAMBDA: Gets all plant names except the chosen one
     * 
     * @param chosenPlantChoice The choice of the plant the player selected
     * @return List of other plant names
     */
    public static List<String> getOtherPlants(String chosenPlantChoice) {
        String normalizedChoice = chosenPlantChoice.trim();
        
        // STREAM with filter and map operations
        return PLANT_NAMES.entrySet().stream()
                .filter(entry -> !entry.getKey().equals(normalizedChoice))  // Predicate lambda
                .map(Map.Entry::getValue)                                   // Function lambda
                .collect(Collectors.toList());
    }

    /**
     * FUNCTIONAL: Creates all plant types as a list using Stream
     * Useful for batch operations or testing
     * 
     * @return List of all plant instances
     */
    public static List<Plant> createAllPlants() {
        return PLANT_SUPPLIERS.values().stream()
                .map(Supplier::get)
                .collect(Collectors.toList());
    }

    /**
     * PREDICATE: Validates if a choice is valid
     * 
     * @param choice The choice to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidChoice(String choice) {
        return IS_VALID_CHOICE.test(choice);
    }

    /**
     * FUNCTIONAL: Get plant count using functional approach
     * 
     * @return Number of available plant types
     */
    public static long getPlantCount() {
        return PLANT_SUPPLIERS.keySet().stream().count();
    }
}
