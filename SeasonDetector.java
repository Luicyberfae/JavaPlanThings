import java.time.LocalDate;
import java.time.Month;

/**
 * SeasonDetector - Detects the current season based on the current date
 * Encapsulates season detection logic (Abstraction)
 * Converted from Python's SeasonDetector class
 */
public class SeasonDetector {

    // Private mapping of months to seasons
    private static final Season[] MONTH_TO_SEASON = new Season[]{
            Season.WINTER,        // January (0)
            Season.EARLY_SPRING,  // February (1)
            Season.EARLY_SPRING,  // March (2)
            Season.SPRING,        // April (3)
            Season.LATE_SPRING,   // May (4)
            Season.SUMMER,        // June (5)
            Season.EARLY_AUTUMN,  // July (6)
            Season.EARLY_AUTUMN,  // August (7)
            Season.AUTUMN,        // September (8)
            Season.AUTUMN,        // October (9)
            Season.EARLY_WINTER,  // November (10)
            Season.WINTER         // December (11)
    };

    /**
     * Gets the current season based on today's date
     * @return The current Season enum value
     */
    public Season getCurrentSeason() {
        LocalDate today = LocalDate.now();
        int monthIndex = today.getMonthValue() - 1; // Convert 1-12 to 0-11
        
        if (monthIndex < 0 || monthIndex >= MONTH_TO_SEASON.length) {
            System.out.println("Error: Season not found.");
            return Season.SPRING; // Default fallback
        }
        
        return MONTH_TO_SEASON[monthIndex];
    }
}
