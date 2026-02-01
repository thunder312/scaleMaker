public class Interval {
    // Wrapper-Klasse für IntervalType - ermöglicht Abwärtskompatibilität
    public final String shortName;
    public final String interval;
    public final String details;
    public final double propCounter;
    public final double propDenom;
    public final double proportion;
    public final IntervalType type;

    private Interval(IntervalType type) {
        this.type = type;
        this.shortName = type.getShortName();
        this.interval = type.getDisplayName();
        this.details = type.getDetails();
        this.propCounter = type.getNumerator();
        this.propDenom = type.getDenominator();
        this.proportion = type.getRatio();
    }

    // Factory-Methode
    public static Interval of(IntervalType type) {
        return new Interval(type);
    }

    // === SKALEN-INTERVALLE ===

    // Dur (Ionisch)
    static final Interval[] majorIntervalsClean = {
        of(IntervalType.UNISON),
        of(IntervalType.MAJOR_SECOND_SMALL),
        of(IntervalType.MAJOR_THIRD),
        of(IntervalType.PERFECT_FOURTH),
        of(IntervalType.PERFECT_FIFTH),
        of(IntervalType.MAJOR_SIXTH),
        of(IntervalType.MAJOR_SEVENTH),
        of(IntervalType.OCTAVE)
    };

    // Natürlich Moll (Äolisch)
    static final Interval[] minorIntervalsClean = {
        of(IntervalType.UNISON),
        of(IntervalType.MAJOR_SECOND_SMALL),
        of(IntervalType.MINOR_THIRD),
        of(IntervalType.PERFECT_FOURTH),
        of(IntervalType.PERFECT_FIFTH),
        of(IntervalType.MINOR_SIXTH),
        of(IntervalType.MINOR_SEVENTH_PYTHAGOREAN),
        of(IntervalType.OCTAVE)
    };

    // Dorisch
    static final Interval[] dorianIntervals = {
        of(IntervalType.UNISON),
        of(IntervalType.MAJOR_SECOND_SMALL),
        of(IntervalType.MINOR_THIRD),
        of(IntervalType.PERFECT_FOURTH),
        of(IntervalType.PERFECT_FIFTH),
        of(IntervalType.MAJOR_SIXTH),
        of(IntervalType.MINOR_SEVENTH_PYTHAGOREAN),
        of(IntervalType.OCTAVE)
    };

    // Phrygisch
    static final Interval[] phrygianIntervals = {
        of(IntervalType.UNISON),
        of(IntervalType.MINOR_SECOND),
        of(IntervalType.MINOR_THIRD),
        of(IntervalType.PERFECT_FOURTH),
        of(IntervalType.PERFECT_FIFTH),
        of(IntervalType.MINOR_SIXTH),
        of(IntervalType.MINOR_SEVENTH_PYTHAGOREAN),
        of(IntervalType.OCTAVE)
    };

    // Lydisch
    static final Interval[] lydianIntervals = {
        of(IntervalType.UNISON),
        of(IntervalType.MAJOR_SECOND_SMALL),
        of(IntervalType.MAJOR_THIRD),
        of(IntervalType.AUGMENTED_FOURTH),
        of(IntervalType.PERFECT_FIFTH),
        of(IntervalType.MAJOR_SIXTH),
        of(IntervalType.MAJOR_SEVENTH),
        of(IntervalType.OCTAVE)
    };

    // Mixolydisch
    static final Interval[] mixolydianIntervals = {
        of(IntervalType.UNISON),
        of(IntervalType.MAJOR_SECOND_SMALL),
        of(IntervalType.MAJOR_THIRD),
        of(IntervalType.PERFECT_FOURTH),
        of(IntervalType.PERFECT_FIFTH),
        of(IntervalType.MAJOR_SIXTH),
        of(IntervalType.MINOR_SEVENTH_PYTHAGOREAN),
        of(IntervalType.OCTAVE)
    };

    // Lokrisch
    static final Interval[] locrianIntervals = {
        of(IntervalType.UNISON),
        of(IntervalType.MINOR_SECOND),
        of(IntervalType.MINOR_THIRD),
        of(IntervalType.PERFECT_FOURTH),
        of(IntervalType.DIMINISHED_FIFTH),
        of(IntervalType.MINOR_SIXTH),
        of(IntervalType.MINOR_SEVENTH_PYTHAGOREAN),
        of(IntervalType.OCTAVE)
    };

    // Harmonisch Moll
    static final Interval[] harmonicMinorIntervals = {
        of(IntervalType.UNISON),
        of(IntervalType.MAJOR_SECOND_SMALL),
        of(IntervalType.MINOR_THIRD),
        of(IntervalType.PERFECT_FOURTH),
        of(IntervalType.PERFECT_FIFTH),
        of(IntervalType.MINOR_SIXTH),
        of(IntervalType.MAJOR_SEVENTH),
        of(IntervalType.OCTAVE)
    };

    // Melodisch Moll (aufwärts)
    static final Interval[] melodicMinorIntervals = {
        of(IntervalType.UNISON),
        of(IntervalType.MAJOR_SECOND_SMALL),
        of(IntervalType.MINOR_THIRD),
        of(IntervalType.PERFECT_FOURTH),
        of(IntervalType.PERFECT_FIFTH),
        of(IntervalType.MAJOR_SIXTH),
        of(IntervalType.MAJOR_SEVENTH),
        of(IntervalType.OCTAVE)
    };

    // Dur-Pentatonik
    static final Interval[] pentatonicMajorIntervals = {
        of(IntervalType.UNISON),
        of(IntervalType.MAJOR_SECOND_SMALL),
        of(IntervalType.MAJOR_THIRD),
        of(IntervalType.PERFECT_FIFTH),
        of(IntervalType.MAJOR_SIXTH),
        of(IntervalType.OCTAVE)
    };

    // Moll-Pentatonik
    static final Interval[] pentatonicMinorIntervals = {
        of(IntervalType.UNISON),
        of(IntervalType.MINOR_THIRD),
        of(IntervalType.PERFECT_FOURTH),
        of(IntervalType.PERFECT_FIFTH),
        of(IntervalType.MINOR_SEVENTH_PYTHAGOREAN),
        of(IntervalType.OCTAVE)
    };

    // Blues-Skala
    static final Interval[] bluesIntervals = {
        of(IntervalType.UNISON),
        of(IntervalType.MINOR_THIRD),
        of(IntervalType.PERFECT_FOURTH),
        of(IntervalType.DIMINISHED_FIFTH),
        of(IntervalType.PERFECT_FIFTH),
        of(IntervalType.MINOR_SEVENTH_PYTHAGOREAN),
        of(IntervalType.OCTAVE)
    };

    // === AKKORD-INTERVALLE ===

    // Dur-Dreiklang
    static final Interval[] majorTriad = {
        of(IntervalType.UNISON),
        of(IntervalType.MAJOR_THIRD),
        of(IntervalType.PERFECT_FIFTH)
    };

    // Moll-Dreiklang
    static final Interval[] minorTriad = {
        of(IntervalType.UNISON),
        of(IntervalType.MINOR_THIRD),
        of(IntervalType.PERFECT_FIFTH)
    };

    // Verminderter Dreiklang
    static final Interval[] diminishedTriad = {
        of(IntervalType.UNISON),
        of(IntervalType.MINOR_THIRD),
        of(IntervalType.DIMINISHED_FIFTH)
    };

    // Übermäßiger Dreiklang
    static final Interval[] augmentedTriad = {
        of(IntervalType.UNISON),
        of(IntervalType.MAJOR_THIRD),
        of(IntervalType.AUGMENTED_FIFTH)
    };

    // Dur-Septakkord (Maj7)
    static final Interval[] majorSeventh = {
        of(IntervalType.UNISON),
        of(IntervalType.MAJOR_THIRD),
        of(IntervalType.PERFECT_FIFTH),
        of(IntervalType.MAJOR_SEVENTH)
    };

    // Moll-Septakkord (Min7)
    static final Interval[] minorSeventh = {
        of(IntervalType.UNISON),
        of(IntervalType.MINOR_THIRD),
        of(IntervalType.PERFECT_FIFTH),
        of(IntervalType.MINOR_SEVENTH_PYTHAGOREAN)
    };

    // Dominantseptakkord (Dom7)
    static final Interval[] dominantSeventh = {
        of(IntervalType.UNISON),
        of(IntervalType.MAJOR_THIRD),
        of(IntervalType.PERFECT_FIFTH),
        of(IntervalType.MINOR_SEVENTH_PYTHAGOREAN)
    };

    // Verminderter Septakkord (Dim7)
    static final Interval[] diminishedSeventh = {
        of(IntervalType.UNISON),
        of(IntervalType.MINOR_THIRD),
        of(IntervalType.DIMINISHED_FIFTH),
        of(IntervalType.DIMINISHED_SEVENTH)
    };

    // Halbverminderter Septakkord (Min7b5)
    static final Interval[] halfDiminishedSeventh = {
        of(IntervalType.UNISON),
        of(IntervalType.MINOR_THIRD),
        of(IntervalType.DIMINISHED_FIFTH),
        of(IntervalType.MINOR_SEVENTH_PYTHAGOREAN)
    };

    // Moll-Dur-Septakkord (MinMaj7)
    static final Interval[] minorMajorSeventh = {
        of(IntervalType.UNISON),
        of(IntervalType.MINOR_THIRD),
        of(IntervalType.PERFECT_FIFTH),
        of(IntervalType.MAJOR_SEVENTH)
    };

    // Sus4-Akkord
    static final Interval[] sus4 = {
        of(IntervalType.UNISON),
        of(IntervalType.PERFECT_FOURTH),
        of(IntervalType.PERFECT_FIFTH)
    };

    // Sus2-Akkord
    static final Interval[] sus2 = {
        of(IntervalType.UNISON),
        of(IntervalType.MAJOR_SECOND_SMALL),
        of(IntervalType.PERFECT_FIFTH)
    };

    // Ausgabe aller Intervalle
    public static void printIntervals() {
        IntervalType.printAll();
    }
}
