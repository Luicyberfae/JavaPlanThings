import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * PlanTings Game - Main Game Controller
 * 
 * Converted from Python game to Java with OOP principles, Design Patterns, and
 * FUNCTIONAL PROGRAMMING
 * 
 * KEY OOP PRINCIPLES DEMONSTRATED:
 * ✓ Encapsulation - Player and Plant classes have private fields with
 * getters/setters
 * ✓ Inheritance - Potato, Marigold, Tomato, Cucumber, Sunflower extend Plant
 * ✓ Polymorphism - Each plant type implements water(), bask(), harvestProduct()
 * differently
 * ✓ Abstraction - Plant is an abstract class; Season is an enum
 * 
 * DESIGN PATTERN: Factory Method
 * - PlantFactory creates plant objects without exposing creation logic
 * - Makes code more maintainable and extensible
 * 
 * FUNCTIONAL PROGRAMMING FEATURES:
 * ✓ Lambdas - Concise action handlers and functional interfaces
 * ✓ Streams - Filter, map, and reduce operations on collections
 * ✓ Predicates - Functional validation and filtering
 * ✓ Optional - Null-safe operations
 * ✓ Method References - Concise lambda expressions
 */
public class PlanTings {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static Player player;
    private static Plant currentPlant;
    private static Season currentSeason;
    private static int seeds;
    private static int points = 0;
    private static final String GAME_STATE_FILE = "planTings_game_state.txt";

    // FUNCTIONAL PROGRAMMING: Predicates for plant health checks
    private static final Predicate<Plant> IS_HEALTHY = plant -> plant.getHealth() > 70;

    private static final Predicate<Plant> NEEDS_WATER = plant -> plant.getWaterLevel() < 30;

    private static final Predicate<Plant> NEEDS_SUNLIGHT = plant -> plant.getSunlightLevel() < 30;

    private static final Predicate<Plant> IS_READY_TO_HARVEST = plant -> plant.getGrowthStage() >= 5;

    // FUNCTIONAL: BiConsumer for game actions (action + points)
    private static final Map<String, BiConsumer<Plant, Integer>> GAME_ACTIONS = new HashMap<>() {
        {
            put("1", (plant, pts) -> {
                plant.water();
                points += pts;
            });
            put("2", (plant, pts) -> {
                plant.bask();
                points += pts;
            });
            put("5", (plant, pts) -> {
                plant.grow();
                points += pts;
            });
        }
    };

    public static void main(String[] args) {
        clearScreen();
        startGame();
    }

    /**
     * Main game flow
     */
    private static void startGame() {
        printHeader("Welcome to the PlanTings application!");
        pause(2);

        // Step 1: Get player name
        System.out.print("Please enter your name: ");
        String playerName = scanner.nextLine().trim();

        if (playerName.isEmpty()) {
            playerName = "Player";
        }

        player = new Player(playerName);
        System.out.println("\nHello " + player.getCharacterName() + "!\n");
        pause(2);
        clearScreen();

        // Step 2: Choose a plant using Factory Method
        printHeader("PlanTings Cultivations");
        System.out.println("\nIn this game you will take care of a plant.");
        System.out.println("First, let us choose a plant for you to take care of.\n");
        pause(3);
        clearScreen();

        currentPlant = choosePlant();
        System.out.println("\nYou have been given a " + currentPlant.getName() + " to take care of.");
        pause(2);
        clearScreen();

        // Step 3: Show other plants
        displayOtherPlants();
        pause(3);
        clearScreen();

        // Step 4: Get current season
        displaySeasonInfo();
        pause(3);
        clearScreen();

        // Step 5: Generate seeds
        generateSeeds();
        displaySeedMessage();
        pause(3);
        clearScreen();

        // Step 6: Show care instructions
        displayCareInstructions();
        pause(2);
        clearScreen();

        // Step 7: Start game loop - care for the plant
        gameLoop();

        // Step 8: Save game state
        saveGameState();
    }

    /**
     * FACTORY PATTERN: Uses PlantFactory to create plants
     * Demonstrates how Design Patterns improve code organization
     */
    private static Plant choosePlant() {
        printHeader("PlanTings Cultivations");

        while (true) {
            System.out.println("\nAvailable plants:");
            System.out.println("1. Peruna (Potato)");
            System.out.println("2. Calendula (Marigold)");
            System.out.println("3. Lycopersicum (Tomato)");
            System.out.println("4. Cucumis (Cucumber)");
            System.out.println("5. Helianthus (Sunflower)\n");

            System.out.print("Please pick a number between 1 and 5: ");
            String choice = scanner.nextLine().trim();

            Plant plant = PlantFactory.createPlant(choice);
            if (plant != null) {
                return plant;
            }
        }
    }

    /**
     * FUNCTIONAL: Displays other available plants using Stream API
     */
    private static void displayOtherPlants() {
        printHeader("PlanTings Cultivations");
        System.out.println("\nOther members of Cultivations include:\n");

        // STREAM API: Create all plants, filter out current, and display
        PlantFactory.createAllPlants().stream()
                .map(Plant::getName)
                .filter(name -> !name.equals(currentPlant.getName()))
                .forEach(name -> System.out.println("  • " + name));

        System.out.println();
    }

    /**
     * Displays current season information
     */
    private static void displaySeasonInfo() {
        printHeader("PlanTings Cultivations");

        SeasonDetector detector = new SeasonDetector();
        currentSeason = detector.getCurrentSeason();

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println("\nCurrent date: " + today.format(formatter));
        System.out.println("Current season: " + currentSeason.getSeasonName() + "\n");

        System.out.println("To achieve a healthy plant, you need to water it and give it sunlight.");
        System.out.println("Let's help you get started with that.\n");
    }

    /**
     * Generates random number of seeds
     */
    private static void generateSeeds() {
        seeds = random.nextInt(20) + 1; // 1-20 seeds
    }

    /**
     * Displays seed message
     */
    private static void displaySeedMessage() {
        printHeader("PlanTings Cultivations");
        System.out.println("\nYou have " + seeds + " seeds to start with.\n");

        if (seeds < 5) {
            System.out.println("You can fit these in one big pot. Let's get started!");
        } else {
            System.out.println("Let's put these little babies down in a larger pot.");
        }
    }

    /**
     * Displays care instructions
     */
    private static void displayCareInstructions() {
        printHeader("PlanTings Cultivations");
        System.out.println("\nThere we go.");
        System.out.println("Now we just need to take well care of the plant so it starts to grow.\n");
        typeText("Happy planting " + player.getCharacterName() + "!", 0.05);
    }

    /**
     * FUNCTIONAL: Main game loop with lambda-based action handlers
     */
    private static void gameLoop() {
        clearScreen();
        boolean gameRunning = true;

        while (gameRunning && currentPlant.isAlive()) {
            displayPlantStatus();
            displayGameMenu();

            // FUNCTIONAL: Show plant care suggestions using predicates
            showCareSuggestions();

            System.out.print("Choose an action (1-6): ");
            String choice = scanner.nextLine().trim();

            // FUNCTIONAL: Execute action using BiConsumer from map
            if (GAME_ACTIONS.containsKey(choice)) {
                int actionPoints = choice.equals("5") ? 2 : 5;
                GAME_ACTIONS.get(choice).accept(currentPlant, actionPoints);
            } else {
                // Handle special cases using functional approach
                gameRunning = handleSpecialActions(choice, gameRunning);
            }

            pause(2);
            clearScreen();
        }

        displayGameOver();
    }

    /**
     * FUNCTIONAL: Displays plant status with health indicators
     */
    private static void displayPlantStatus() {
        printHeader("PlanTings Cultivations - Game Loop");
        System.out.println("\nCurrent Plant Status:");
        System.out.println("  Health: " + currentPlant.getHealth() + "/100 " +
                (IS_HEALTHY.test(currentPlant) ? "✓" : "⚠"));
        System.out.println("  Water: " + currentPlant.getWaterLevel() + "/100 " +
                (NEEDS_WATER.test(currentPlant) ? "⚠" : "✓"));
        System.out.println("  Sunlight: " + currentPlant.getSunlightLevel() + "/100 " +
                (NEEDS_SUNLIGHT.test(currentPlant) ? "⚠" : "✓"));
        System.out.println("  Growth Stage: " + currentPlant.getGrowthStage() +
                (IS_READY_TO_HARVEST.test(currentPlant) ? " (Ready to Harvest!)" : ""));
        System.out.println("  Points Earned: " + points + "\n");
    }

    /**
     * Displays game menu
     */
    private static void displayGameMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1. Water the plant");
        System.out.println("2. Give sunlight");
        System.out.println("3. Check plant status");
        System.out.println("4. Try to harvest");
        System.out.println("5. Pass time (plant grows)");
        System.out.println("6. Quit game\n");
    }

    /**
     * FUNCTIONAL: Shows care suggestions based on plant predicates
     */
    private static void showCareSuggestions() {
        List<String> suggestions = new ArrayList<>();

        // PREDICATE-based suggestions
        if (NEEDS_WATER.test(currentPlant)) {
            suggestions.add("💧 Plant needs water!");
        }
        if (NEEDS_SUNLIGHT.test(currentPlant)) {
            suggestions.add("☀ Plant needs sunlight!");
        }
        if (IS_READY_TO_HARVEST.test(currentPlant)) {
            suggestions.add("🌱 Plant is ready to harvest!");
        }

        // STREAM: Display all suggestions
        if (!suggestions.isEmpty()) {
            System.out.println("Suggestions:");
            suggestions.forEach(s -> System.out.println("  " + s));
            System.out.println();
        }
    }

    /**
     * FUNCTIONAL: Handles special actions (3, 4, 6) using Optional
     */
    private static boolean handleSpecialActions(String choice, boolean gameRunning) {
        switch (choice) {
            case "3":
                displayDetailedPlantInfo();
                return gameRunning;
            case "4":
                return handleHarvest();
            case "6":
                System.out.println("\nThanks for playing PlanTings!");
                return false;
            default:
                System.out.println("Invalid choice. Please try again.");
                return gameRunning;
        }
    }

    /**
     * FUNCTIONAL: Displays detailed plant information
     */
    private static void displayDetailedPlantInfo() {
        System.out.println("\n" + currentPlant);
        System.out.println("\nHealth Status: " +
                (IS_HEALTHY.test(currentPlant) ? "Healthy" : "Needs Care"));
    }

    /**
     * FUNCTIONAL: Handles harvest with predicate validation
     */
    private static boolean handleHarvest() {
        String harvestMessage = currentPlant.harvestProduct();
        System.out.println("\n" + harvestMessage);

        // PREDICATE: Check if harvest was successful
        if (IS_READY_TO_HARVEST.test(currentPlant)) {
            points += 50;
            System.out.println("You earned 50 bonus points!");
            return false; // End game after successful harvest
        }
        return true; // Continue game
    }

    /**
     * FUNCTIONAL: Displays game over screen
     */
    private static void displayGameOver() {
        Optional.of(currentPlant)
                .filter(plant -> !plant.isAlive())
                .ifPresent(plant -> System.out.println("\nGame Over! Your plant has withered.\n"));

        System.out.println("Final Score: " + points + " points");
    }

    /**
     * Saves the current game state to a file
     */
    private static void saveGameState() {
        try (FileWriter fw = new FileWriter(GAME_STATE_FILE)) {
            fw.write("PlanTings Game State\n");
            fw.write("====================\n\n");
            fw.write("Player: " + player.getName() + "\n");
            fw.write("Character Name: " + player.getCharacterName() + "\n");
            fw.write("Plant: " + currentPlant.getName() + "\n");
            fw.write("Plant Health: " + currentPlant.getHealth() + "\n");
            fw.write("Plant Growth Stage: " + currentPlant.getGrowthStage() + "\n");
            fw.write("Seeds: " + seeds + "\n");
            fw.write("Season: " + currentSeason.getSeasonName() + "\n");
            fw.write("Points Earned: " + points + "\n");
            fw.write("Plant Status: " + (currentPlant.isAlive() ? "Alive" : "Dead") + "\n");

            System.out.println("\nGame state saved successfully to " + GAME_STATE_FILE);
        } catch (IOException e) {
            System.out.println("Error saving game state: " + e.getMessage());
        }
    }

    // ========== UTILITY METHODS ==========

    /**
     * Clears the console screen (cross-platform)
     */
    private static void clearScreen() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n");
        }
    }

    /**
     * Pauses execution for a given number of seconds
     */
    private static void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Types text character by character with a delay
     */
    private static void typeText(String text, double delaySeconds) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            System.out.flush();
            try {
                Thread.sleep((long) (delaySeconds * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    /**
     * Prints a formatted header
     */
    private static void printHeader(String title) {
        System.out.println("\n" + title);
        System.out.println("=".repeat(title.length()));
    }
}
