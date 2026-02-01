public enum ChordType {
    // Dreiklänge
    MAJOR("Dur", ""),
    MINOR("Moll", "m"),
    DIMINISHED("Vermindert", "dim"),
    AUGMENTED("Übermäßig", "+"),
    SUS2("Suspended 2", "sus2"),
    SUS4("Suspended 4", "sus4"),

    // Septakkorde
    MAJOR_7("Dur-Septakkord", "maj7"),
    MINOR_7("Moll-Septakkord", "m7"),
    DOMINANT_7("Dominantseptakkord", "7"),
    DIMINISHED_7("Verminderter Septakkord", "dim7"),
    HALF_DIMINISHED_7("Halbverminderter Septakkord", "m7b5"),
    MINOR_MAJOR_7("Moll-Dur-Septakkord", "m(maj7)");

    private final String displayName;
    private final String symbol;

    ChordType(String displayName, String symbol) {
        this.displayName = displayName;
        this.symbol = symbol;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getSymbol() {
        return symbol;
    }

    public Interval[] getIntervals() {
        switch (this) {
            case MAJOR:           return Interval.majorTriad;
            case MINOR:           return Interval.minorTriad;
            case DIMINISHED:      return Interval.diminishedTriad;
            case AUGMENTED:       return Interval.augmentedTriad;
            case SUS2:            return Interval.sus2;
            case SUS4:            return Interval.sus4;
            case MAJOR_7:         return Interval.majorSeventh;
            case MINOR_7:         return Interval.minorSeventh;
            case DOMINANT_7:      return Interval.dominantSeventh;
            case DIMINISHED_7:    return Interval.diminishedSeventh;
            case HALF_DIMINISHED_7: return Interval.halfDiminishedSeventh;
            case MINOR_MAJOR_7:   return Interval.minorMajorSeventh;
            default:              return Interval.majorTriad;
        }
    }

    public static void printAll() {
        System.out.println("Verfügbare Akkordtypen:\n");
        System.out.println("Dreiklänge:");
        for (ChordType type : values()) {
            if (type.ordinal() <= SUS4.ordinal()) {
                System.out.println("  " + type.name() + " - " + type.displayName + " (" + type.symbol + ")");
            }
        }
        System.out.println("\nSeptakkorde:");
        for (ChordType type : values()) {
            if (type.ordinal() > SUS4.ordinal()) {
                System.out.println("  " + type.name() + " - " + type.displayName + " (" + type.symbol + ")");
            }
        }
    }
}
