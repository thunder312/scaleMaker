import java.util.Map;
import static java.util.Map.entry;

/**
 * Repräsentiert einen einzelnen musikalischen Ton mit Frequenz.
 *
 * <p>Eine MusicalNote besteht aus einem Notennamen (z.B. "C", "Fis", "Es"),
 * einer Frequenz in Hz und einer Oktavlage.</p>
 *
 * <h2>Beispiel:</h2>
 * <pre>{@code
 * // Note mit automatischer Frequenz aus C-Dur-Skala
 * MusicalNote c = new MusicalNote("C");
 *
 * // Note mit expliziter Frequenz
 * MusicalNote a440 = new MusicalNote("A", 440.0);
 *
 * // Note mit Frequenz und Oktave
 * MusicalNote c2 = new MusicalNote("C", 528.0, 2);
 *
 * // Frequenz abrufen
 * double freq = c.getFrequency(); // 264.0
 * }</pre>
 *
 * @see Scale
 * @see Chord
 */
public class MusicalNote {

    /** Notenname (C, D, E, F, G, A, H, B, Cis, Des, etc.) */
    String letter;

    /** Oktavlage (1 = eingestrichen, 2 = zweigestrichen, etc.) */
    int octave = 0;

    /** Optionaler beschreibender Name */
    String name = "";

    /** Frequenz in Hz */
    double frequency;

    /** SI-Einheit für Frequenz */
    final String siUnit = "Hz";

    /** Kammerton-Frequenzen nach Region */
    static final Map<String, Double> CONCERT_PITCH = Map.ofEntries(
        entry("default", 440.0),
        entry("de", 443.0),
        entry("at", 443.0),
        entry("ch", 442.0)
    );

    /** Standard-Frequenzen der C-Dur-Skala (basierend auf A = 440 Hz) */
    static final Map<String, Double> DEFAULT_SCALE = Map.ofEntries(
        entry("C", 264.0),
        entry("Cis", 280.5),
        entry("D", 297.0),
        entry("Dis", 313.5),
        entry("E", 330.0),
        entry("Eis", 341.0),
        entry("F", 352.0),
        entry("Fis", 374.0),
        entry("G", 396.0),
        entry("Gis", 418.0),
        entry("A", 440.0),
        entry("B", 466.0),
        entry("H", 495.0)
    );

    /**
     * Erstellt den Kammerton A (440 Hz) als Standard.
     */
    public MusicalNote() {
        this.letter = "A";
        this.frequency = CONCERT_PITCH.get("default");
        this.name = "Kammerton A";
        this.octave = 1;
    }

    /**
     * Erstellt eine Note mit allen Parametern.
     *
     * @param letter Notenname
     * @param frequency Frequenz in Hz (-1 für automatische Berechnung)
     * @param name Beschreibender Name
     * @param octave Oktavlage
     * @throws Exception wenn die Frequenz nicht ermittelt werden kann
     */
    public MusicalNote(String letter, double frequency, String name, int octave) throws Exception {
        this.letter = letter;
        if (frequency == -1) {
            if (DEFAULT_SCALE.containsKey(letter)) {
                this.frequency = DEFAULT_SCALE.get(letter);
            } else {
                throw new IllegalArgumentException("Unbekannte Note: '" + letter + "'");
            }
        } else {
            this.frequency = frequency;
        }
        this.name = name;
        this.octave = octave;
    }

    /**
     * Erstellt eine Note mit Frequenz und Name.
     *
     * @param letter Notenname
     * @param frequency Frequenz in Hz
     * @param name Beschreibender Name
     * @throws Exception wenn die Erstellung fehlschlägt
     */
    public MusicalNote(String letter, double frequency, String name) throws Exception {
        this(letter, frequency, name, 1);
    }

    /**
     * Erstellt eine Note mit Frequenz und Oktave.
     *
     * @param letter Notenname
     * @param frequency Frequenz in Hz
     * @param octave Oktavlage
     * @throws Exception wenn die Erstellung fehlschlägt
     */
    public MusicalNote(String letter, double frequency, int octave) throws Exception {
        this(letter, frequency, "", octave);
    }

    /**
     * Erstellt eine Note mit Frequenz.
     *
     * @param letter Notenname
     * @param frequency Frequenz in Hz
     * @throws Exception wenn die Erstellung fehlschlägt
     */
    public MusicalNote(String letter, double frequency) throws Exception {
        this(letter, frequency, "", 1);
    }

    /**
     * Erstellt eine Note mit automatischer Frequenz aus der C-Dur-Skala.
     *
     * @param letter Notenname (C, D, E, F, G, A, H, B, Cis, etc.)
     * @throws Exception wenn der Notenname unbekannt ist
     */
    public MusicalNote(String letter) throws Exception {
        this(letter, -1, "", 1);
    }

    /**
     * Gibt die Note formatiert auf der Konsole aus.
     */
    public void print() {
        System.out.println(letter + octaveToSymbols(octave) + "\t( " + frequency + siUnit + " )");
    }

    /**
     * Konvertiert eine Oktavzahl in Apostrophsymbole.
     *
     * @param octave Oktavzahl (1 = ', 2 = '', etc.)
     * @return String mit Apostrophen
     */
    public static String octaveToSymbols(int octave) {
        StringBuilder symbols = new StringBuilder();
        for (int i = 0; i < octave; i++) {
            symbols.append("'");
        }
        return symbols.toString();
    }

    // ==================== GETTER ====================

    /**
     * Gibt den Notennamen zurück.
     *
     * @return Notenname (z.B. "C", "Fis", "Es")
     */
    public String getLetter() {
        return letter;
    }

    /**
     * Gibt die Frequenz in Hz zurück.
     *
     * @return Frequenz in Hz
     */
    public double getFrequency() {
        return frequency;
    }

    /**
     * Gibt die Oktavlage zurück.
     *
     * @return Oktavzahl (1 = eingestrichen, etc.)
     */
    public int getOctave() {
        return octave;
    }

    /**
     * Gibt den beschreibenden Namen zurück.
     *
     * @return Beschreibender Name oder leerer String
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt eine String-Repräsentation der Note zurück.
     *
     * @return String im Format "C'" oder "A''"
     */
    @Override
    public String toString() {
        return letter + octaveToSymbols(octave);
    }
}
