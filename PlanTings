import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

/**
 * PlanTings Game - Main Game Controller
 * 
 * Converted from Python game to Java with OOP principles and Design Patterns
 * 
 * KEY OOP PRINCIPLES DEMONSTRATED:
 * ✓ Encapsulation - Player and Plant classes have private fields with getters/setters
 * ✓ Inheritance - Potato, Marigold, Tomato, Cucumber, Sunflower extend Plant
 * ✓ Polymorphism - Each plant type implements water(), bask(), harvestProduct() differently
 * ✓ Abstraction - Plant is an abstract class; Season is an enum
 * 
 * DESIGN PATTERN: Factory Method
 * - PlantFactory creates plant objects without exposing creation logic
 * - Makes code more maintainable and extensible
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
     * Displays other available plants
     */
    private static void displayOtherPlants() {
        printHeader("PlanTings Cultivations");
        System.out.println("\nOther members of Cultivations include:\n");

        // Get current plant choice (simplified - show all except current)
        String[] otherPlants = {"Peruna (Potato)", "Calendula (Marigold)", "Lycopersicum (Tomato)",
                "Cucumis (Cucumber)", "Helianthus (Sunflower)"};

        for (String plant : otherPlants) {
            if (!plant.equals(currentPlant.getName())) {
                System.out.println("  • " + plant);
            }
        }
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
     * Main game loop - player takes care of plant
     */
    private static void gameLoop() {
        clearScreen();
        boolean gameRunning = true;

        while (gameRunning && currentPlant.isAlive()) {
            printHeader("PlanTings Cultivations - Game Loop");
            System.out.println("\nCurrent Plant Status:");
            System.out.println("  Health: " + currentPlant.getHealth() + "/100");
            System.out.println("  Water: " + currentPlant.getWaterLevel() + "/100");
            System.out.println("  Sunlight: " + currentPlant.getSunlightLevel() + "/100");
            System.out.println("  Growth Stage: " + currentPlant.getGrowthStage());
            System.out.println("  Points Earned: " + points + "\n");

            System.out.println("What would you like to do?");
            System.out.println("1. Water the plant");
            System.out.println("2. Give sunlight");
            System.out.println("3. Check plant status");
            System.out.println("4. Try to harvest");
            System.out.println("5. Pass time (plant grows)");
            System.out.println("6. Quit game\n");

            System.out.print("Choose an action (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    currentPlant.water();
                    points += 5;
                    break;
                case "2":
                    currentPlant.bask();
                    points += 5;
                    break;
                case "3":
                    System.out.println("\n" + currentPlant);
                    break;
                case "4":
                    System.out.println("\n" + currentPlant.harvestProduct());
                    if (currentPlant.getGrowthStage() >= 5) {
                        points += 50;
                        System.out.println("You earned 50 bonus points!");
                        gameRunning = false;
                    }
                    break;
                case "5":
                    currentPlant.grow();
                    points += 2;
                    break;
                case "6":
                    System.out.println("\nThanks for playing PlanTings!");
                    gameRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

            pause(2);
            clearScreen();
        }

        if (!currentPlant.isAlive()) {
            System.out.println("\nGame Over! Your plant has withered.\n");
        }

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
