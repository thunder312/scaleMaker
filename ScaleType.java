public enum ScaleType {
    // Kirchentonarten
    IONIAN("Ionisch (Dur)"),
    DORIAN("Dorisch"),
    PHRYGIAN("Phrygisch"),
    LYDIAN("Lydisch"),
    MIXOLYDIAN("Mixolydisch"),
    AEOLIAN("Äolisch (Moll)"),
    LOCRIAN("Lokrisch"),

    // Dur/Moll Aliase
    MAJOR("Dur"),
    MINOR("Natürlich Moll"),

    // Moll-Varianten
    HARMONIC_MINOR("Harmonisch Moll"),
    MELODIC_MINOR("Melodisch Moll"),

    // Pentatoniken
    PENTATONIC_MAJOR("Dur-Pentatonik"),
    PENTATONIC_MINOR("Moll-Pentatonik"),

    // Blues
    BLUES("Blues-Skala");

    private final String displayName;

    ScaleType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Interval[] getIntervals() {
        switch (this) {
            case IONIAN:
            case MAJOR:           return Interval.majorIntervalsClean;
            case DORIAN:          return Interval.dorianIntervals;
            case PHRYGIAN:        return Interval.phrygianIntervals;
            case LYDIAN:          return Interval.lydianIntervals;
            case MIXOLYDIAN:      return Interval.mixolydianIntervals;
            case AEOLIAN:
            case MINOR:           return Interval.minorIntervalsClean;
            case LOCRIAN:         return Interval.locrianIntervals;
            case HARMONIC_MINOR:  return Interval.harmonicMinorIntervals;
            case MELODIC_MINOR:   return Interval.melodicMinorIntervals;
            case PENTATONIC_MAJOR: return Interval.pentatonicMajorIntervals;
            case PENTATONIC_MINOR: return Interval.pentatonicMinorIntervals;
            case BLUES:           return Interval.bluesIntervals;
            default:              return Interval.majorIntervalsClean;
        }
    }

    public static void printAll() {
        System.out.println("Verfügbare Skalen/Modi:\n");

        System.out.println("Kirchentonarten:");
        for (ScaleType type : new ScaleType[]{IONIAN, DORIAN, PHRYGIAN, LYDIAN, MIXOLYDIAN, AEOLIAN, LOCRIAN}) {
            System.out.println("  " + type.name() + " - " + type.displayName);
        }

        System.out.println("\nDur/Moll:");
        for (ScaleType type : new ScaleType[]{MAJOR, MINOR, HARMONIC_MINOR, MELODIC_MINOR}) {
            System.out.println("  " + type.name() + " - " + type.displayName);
        }

        System.out.println("\nPentatonik & Blues:");
        for (ScaleType type : new ScaleType[]{PENTATONIC_MAJOR, PENTATONIC_MINOR, BLUES}) {
            System.out.println("  " + type.name() + " - " + type.displayName);
        }
    }
}
