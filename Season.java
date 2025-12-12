/**
 * Season Enum - Represents different seasons in the PlanTings game
 * Converted from Python's Enum class
 */
public enum Season {
    EARLY_SPRING("Early Spring"),
    SPRING("Spring"),
    LATE_SPRING("Late Spring"),
    SUMMER("Summer"),
    EARLY_AUTUMN("Early Autumn"),
    AUTUMN("Autumn"),
    EARLY_WINTER("Early Winter"),
    WINTER("Winter");

    private final String seasonName;

    Season(String seasonName) {
        this.seasonName = seasonName;
    }

    public String getSeasonName() {
        return seasonName;
    }
}
