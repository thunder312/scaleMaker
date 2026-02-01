public enum IntervalType {
    // Prim
    UNISON("Prim", "P", "", 1, 1),
    AUGMENTED_UNISON_SMALL("Überm. Prim", "P1", "kleiner chromatischer Halbton", 24, 25),
    AUGMENTED_UNISON_LARGE("Überm. Prim", "P2", "großer chromatischer Halbton", 135, 128),

    // Sekunden
    MINOR_SECOND_PYTHAGOREAN("Kl. Sekunde", "Sk1", "Leimma (pythagoreisch)", 256, 243),
    MINOR_SECOND("Kl. Sekunde", "Sk2", "diatonischer Halbton (rein)", 16, 15),
    MAJOR_SECOND_SMALL("Gr. Sekunde", "Sg1", "kleiner Ganzton (rein)", 10, 9),
    MAJOR_SECOND("Gr. Sekunde", "Sg2", "großer Ganzton (pythagoreisch/rein)", 9, 8),

    // Terzen
    MINOR_THIRD_PYTHAGOREAN("Kl. Terz", "Tk1", "pythagoreisch", 32, 27),
    MINOR_THIRD("Kl. Terz", "Tk2", "rein", 6, 5),
    MAJOR_THIRD("Gr. Terz", "Tg1", "rein", 5, 4),
    MAJOR_THIRD_PYTHAGOREAN("Gr. Terz", "Tg2", "Ditonus (pythagoreisch)", 81, 64),

    // Quarten
    PERFECT_FOURTH("Quarte", "Qua", "rein", 4, 3),
    AUGMENTED_FOURTH_HUYGENS("Überm. Quarte", "Qua2", "Huygens' Tritonus", 7, 5),
    AUGMENTED_FOURTH("Überm. Quarte", "Qua1", "diatonischer Tritonus", 45, 32),
    AUGMENTED_FOURTH_PYTHAGOREAN("Überm. Quarte", "Qua3", "pythagoreisch", 729, 512),

    // Quinten
    DIMINISHED_FIFTH_PYTHAGOREAN("Verm. Quinte", "Qui1", "pythagoreisch", 1024, 729),
    DIMINISHED_FIFTH("Verm. Quinte", "Qui2", "rein", 64, 45),
    DIMINISHED_FIFTH_EULER("Verm. Quinte", "Qui3", "Eulers Tritonus", 10, 7),
    PERFECT_FIFTH("Quinte", "Qui", "rein", 3, 2),
    AUGMENTED_FIFTH("Überm. Quinte", "Qui+", "rein", 25, 16),

    // Sexten
    MINOR_SIXTH("Kl. Sexte", "Sxk", "rein", 8, 5),
    MAJOR_SIXTH("Gr. Sexte", "Sxg", "rein", 5, 3),

    // Septimen
    MINOR_SEVENTH_PYTHAGOREAN("Kl. Septime", "Sepk1", "pythagoreisch", 16, 9),
    MINOR_SEVENTH("Kl. Septime", "Sepk2", "rein (Oktave - kleiner Ganzton)", 9, 5),
    MINOR_SEVENTH_NATURAL("Kl. Septime", "Sepk3", "Naturseptime", 7, 4),
    DIMINISHED_SEVENTH("Verm. Septime", "Sep--", "doppelt vermindert", 128, 75),
    MAJOR_SEVENTH("Gr. Septime", "Sepg", "diatonisch rein", 15, 8),

    // Oktave
    OCTAVE("Oktave", "Ok", "rein", 2, 1);

    private final String displayName;
    private final String shortName;
    private final String details;
    private final double numerator;
    private final double denominator;
    private final double ratio;

    IntervalType(String displayName, String shortName, String details, double numerator, double denominator) {
        this.displayName = displayName;
        this.shortName = shortName;
        this.details = details;
        this.numerator = numerator;
        this.denominator = denominator;
        this.ratio = numerator / denominator;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDetails() {
        return details;
    }

    public double getNumerator() {
        return numerator;
    }

    public double getDenominator() {
        return denominator;
    }

    public double getRatio() {
        return ratio;
    }

    public String getRatioString() {
        return (int) numerator + ":" + (int) denominator;
    }

    public static void printAll() {
        System.out.println("Verfügbare Intervalle:\n");
        System.out.println(String.format("%-35s %-6s %-40s %-12s %s",
            "ENUM", "Kurz", "Beschreibung", "Verhältnis", "Faktor"));
        System.out.println("-".repeat(110));

        for (IntervalType interval : values()) {
            String description = interval.displayName;
            if (!interval.details.isEmpty()) {
                description += " (" + interval.details + ")";
            }
            System.out.println(String.format("%-35s %-6s %-40s %-12s %.6f",
                interval.name(),
                interval.shortName,
                description,
                interval.getRatioString(),
                interval.ratio));
        }
    }

    // Hilfsmethode: Finde Intervall nach Kurzname
    public static IntervalType fromShortName(String shortName) {
        for (IntervalType interval : values()) {
            if (interval.shortName.equals(shortName)) {
                return interval;
            }
        }
        return null;
    }
}
