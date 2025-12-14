import java.util.*;
import java.util.stream.Collectors;

/**
 * FunctionalProgrammingDemo - Demonstrates functional programming features
 * This class showcases the functional programming capabilities added to the
 * project
 */
public class FunctionalProgrammingDemo {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("FUNCTIONAL PROGRAMMING FEATURES DEMONSTRATION");
        System.out.println("=".repeat(60));
        System.out.println();

        // Demo 1: Lambda Expressions with Factory
        demoLambdaFactory();

        // Demo 2: Stream API Operations
        demoStreamOperations();

        // Demo 3: Predicates
        demoPredicates();

        // Demo 4: Optional
        demoOptional();

        // Demo 5: Method References
        demoMethodReferences();

        // Demo 6: Functional Interfaces
        demoFunctionalInterfaces();
    }

    /**
     * DEMO 1: Lambda Expressions with Factory Pattern
     */
    private static void demoLambdaFactory() {
        printHeader("1. Lambda Expressions with Factory Pattern");

        // Using lambda-based factory to create plants
        System.out.println("Creating plants using lambda-based factory:");

        Optional<Plant> potato = PlantFactory.createPlantSafe("1");
        potato.ifPresent(p -> System.out.println("✓ Created: " + p.getName()));

        Optional<Plant> tomato = PlantFactory.createPlantSafe("3");
        tomato.ifPresent(p -> System.out.println("✓ Created: " + p.getName()));

        // Invalid choice returns empty Optional
        Optional<Plant> invalid = PlantFactory.createPlantSafe("99");
        System.out.println("Invalid choice result: " +
                (invalid.isEmpty() ? "✓ Empty (safe)" : "✗ Should be empty"));

        System.out.println();
    }

    /**
     * DEMO 2: Stream API Operations
     */
    private static void demoStreamOperations() {
        printHeader("2. Stream API Operations");

        // Create all plants and process with streams
        List<Plant> allPlants = PlantFactory.createAllPlants();

        System.out.println("All plants created using Stream:");
        allPlants.stream()
                .map(Plant::getName)
                .forEach(name -> System.out.println("  • " + name));

        System.out.println("\nPlant count using Stream: " + PlantFactory.getPlantCount());

        // Filter plants by name length
        System.out.println("\nPlants with names longer than 20 characters:");
        allPlants.stream()
                .filter(p -> p.getName().length() > 20)
                .map(Plant::getName)
                .forEach(name -> System.out.println("  • " + name));

        System.out.println();
    }

    /**
     * DEMO 3: Predicates
     */
    private static void demoPredicates() {
        printHeader("3. Predicates for Validation and Filtering");

        // Create a test plant
        Plant testPlant = new Potato();
        testPlant.setHealth(85);
        testPlant.setWaterLevel(60);
        testPlant.setSunlightLevel(70);

        System.out.println("Test Plant: " + testPlant.getName());
        System.out.println("  Health: " + testPlant.getHealth());
        System.out.println("  Water: " + testPlant.getWaterLevel());
        System.out.println("  Sunlight: " + testPlant.getSunlightLevel());
        System.out.println();

        // Test various predicates
        System.out.println("Predicate Tests:");
        System.out.println("  Is Thriving? " +
                (Plant.IS_THRIVING.test(testPlant) ? "✓ Yes" : "✗ No"));
        System.out.println("  Needs Immediate Care? " +
                (Plant.NEEDS_IMMEDIATE_CARE.test(testPlant) ? "✓ Yes" : "✗ No"));
        System.out.println("  Can Grow? " +
                (Plant.CAN_GROW.test(testPlant) ? "✓ Yes" : "✗ No"));

        // Test with unhealthy plant
        System.out.println("\nAfter neglecting the plant:");
        testPlant.setHealth(25);
        testPlant.setWaterLevel(15);
        System.out.println("  Health: " + testPlant.getHealth());
        System.out.println("  Water: " + testPlant.getWaterLevel());
        System.out.println("  Needs Immediate Care? " +
                (Plant.NEEDS_IMMEDIATE_CARE.test(testPlant) ? "✓ Yes" : "✗ No"));

        System.out.println();
    }

    /**
     * DEMO 4: Optional for Null Safety
     */
    private static void demoOptional() {
        printHeader("4. Optional for Null Safety");

        // Safe plant creation
        System.out.println("Safe plant creation with Optional:");

        Optional<Plant> validPlant = PlantFactory.createPlantSafe("2");
        String plantName = validPlant
                .map(Plant::getName)
                .orElse("No plant created");
        System.out.println("  Valid choice → " + plantName);

        Optional<Plant> invalidPlant = PlantFactory.createPlantSafe(null);
        String noPlantName = invalidPlant
                .map(Plant::getName)
                .orElse("No plant created");
        System.out.println("  Null choice → " + noPlantName);

        // Chain Optional operations
        System.out.println("\nChaining Optional operations:");
        validPlant
                .filter(Plant::isAlive)
                .ifPresentOrElse(
                        p -> System.out.println("  ✓ Plant is alive: " + p.getName()),
                        () -> System.out.println("  ✗ Plant is not alive or doesn't exist"));

        System.out.println();
    }

    /**
     * DEMO 5: Method References
     */
    private static void demoMethodReferences() {
        printHeader("5. Method References");

        List<Plant> plants = PlantFactory.createAllPlants();

        System.out.println("Different types of method references:");

        // 1. Static method reference
        System.out.println("\n1. Static method reference (String::valueOf):");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream()
                .map(String::valueOf)
                .forEach(s -> System.out.print(s + " "));
        System.out.println();

        // 2. Instance method reference
        System.out.println("\n2. Instance method reference (Plant::getName):");
        plants.stream()
                .map(Plant::getName)
                .limit(3)
                .forEach(name -> System.out.println("  • " + name));

        // 3. Constructor reference
        System.out.println("\n3. Constructor reference (Potato::new):");
        System.out.println("  Factory uses: Potato::new");
        System.out.println("  Equivalent to: () -> new Potato()");

        System.out.println();
    }

    /**
     * DEMO 6: Functional Interfaces
     */
    private static void demoFunctionalInterfaces() {
        printHeader("6. Functional Interfaces");

        Plant plant = new Marigold();

        // Supplier
        System.out.println("Supplier<Plant>: Supplies values");
        var supplier = (java.util.function.Supplier<Plant>) Marigold::new;
        Plant suppliedPlant = supplier.get();
        System.out.println("  Created: " + suppliedPlant.getName());

        // Consumer
        System.out.println("\nConsumer<Plant>: Accepts input, performs action");
        java.util.function.Consumer<Plant> waterPlant = p -> {
            p.setWaterLevel(p.getWaterLevel() + 20);
            System.out.println("  Watered " + p.getName() +
                    " → Water level: " + p.getWaterLevel());
        };
        waterPlant.accept(plant);

        // Predicate
        System.out.println("\nPredicate<Plant>: Tests condition");
        java.util.function.Predicate<Plant> hasHighHealth = p -> p.getHealth() > 90;
        System.out.println("  Health > 90? " +
                (hasHighHealth.test(plant) ? "Yes" : "No"));

        // Function
        System.out.println("\nFunction<Plant, String>: Transforms input to output");
        java.util.function.Function<Plant, String> getStatus = p -> p.getName() + " (Health: " + p.getHealth() + ")";
        System.out.println("  " + getStatus.apply(plant));

        // BiConsumer
        System.out.println("\nBiConsumer<Plant, Integer>: Two inputs, performs action");
        java.util.function.BiConsumer<Plant, Integer> adjustHealth = (p, amount) -> {
            p.setHealth(p.getHealth() + amount);
            System.out.println("  Adjusted health by " + amount +
                    " → New health: " + p.getHealth());
        };
        adjustHealth.accept(plant, -10);

        System.out.println();
    }

    /**
     * Utility method to print section headers
     */
    private static void printHeader(String title) {
        System.out.println("-".repeat(60));
        System.out.println(title);
        System.out.println("-".repeat(60));
    }
}
