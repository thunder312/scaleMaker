import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import static java.util.Map.entry;

/**
 * Repräsentiert einen musikalischen Akkord.
 *
 * <p>Ein Akkord besteht aus einem Grundton und einer Kombination von Intervallen,
 * die den Akkordtyp definieren (z.B. Dur-Dreiklang, Moll-Septakkord).</p>
 *
 * <h2>Beispiel:</h2>
 * <pre>{@code
 * MusicalNote c = new MusicalNote("C");
 *
 * // Dur-Dreiklang
 * Chord cDur = new Chord(c, ChordType.MAJOR);
 *
 * // Dominantseptakkord
 * Chord g7 = new Chord(new MusicalNote("G"), ChordType.DOMINANT_7);
 *
 * // Akkordtöne abrufen
 * List<MusicalNote> notes = cDur.getNotes();
 * double[] frequencies = cDur.getFrequencies();
 * String symbol = cDur.getSymbol(); // "C"
 * }</pre>
 *
 * @see ChordType
 * @see MusicalNote
 * @see Scale#getTriadOnDegree(int)
 */
public class Chord {

    private MusicalNote root;
    private ChordType type;
    private String symbol;
    private Interval[] intervals;
    private List<MusicalNote> notes = new ArrayList<>();

    private static final String[] CHROMATIC_SHARP = {"C", "Cis", "D", "Dis", "E", "F", "Fis", "G", "Gis", "A", "B", "H"};
    private static final String[] CHROMATIC_FLAT = {"C", "Des", "D", "Es", "E", "F", "Ges", "G", "As", "A", "B", "H"};

    private static final Map<String, Integer> INTERVAL_SEMITONES = Map.ofEntries(
        entry("P", 0),
        entry("Sg1", 2), entry("Sg2", 2),
        entry("Tk1", 3), entry("Tk2", 3),
        entry("Tg1", 4), entry("Tg2", 4),
        entry("Qua", 5),
        entry("Qui", 7),
        entry("Qui+", 8),
        entry("Qui1", 6), entry("Qui2", 6), entry("Qui3", 6),
        entry("Sxk", 8),
        entry("Sxg", 9),
        entry("Sepk1", 10), entry("Sepk2", 10), entry("Sepk3", 10),
        entry("Sep--", 9),
        entry("Sepg", 11),
        entry("Ok", 12)
    );

    /**
     * Erstellt einen neuen Akkord.
     *
     * @param root Der Grundton des Akkords
     * @param type Der Typ des Akkords (z.B. MAJOR, MINOR, DOMINANT_7)
     * @throws Exception wenn der Akkord nicht erstellt werden kann
     */
    public Chord(MusicalNote root, ChordType type) throws Exception {
        this.root = root;
        this.type = type;
        this.intervals = type.getIntervals();
        this.symbol = root.letter + type.getSymbol();
        createChord();
    }

    private void createChord() throws Exception {
        for (int i = 0; i < intervals.length; i++) {
            double freq = root.frequency * intervals[i].proportion;
            int octave = root.octave;
            if (intervals[i].proportion >= 2.0) {
                octave++;
            }
            notes.add(new MusicalNote(getNoteLetter(i), freq, octave));
        }
    }

    private String getNoteLetter(int index) {
        Interval interval = intervals[index];
        int semitones = getSemitones(interval);
        int rootChromIndex = findChromaticIndex(root.letter);
        int targetChromIndex = (rootChromIndex + semitones) % 12;

        boolean useSharp = shouldUseSharp(root.letter);
        String[] chromatic = useSharp ? CHROMATIC_SHARP : CHROMATIC_FLAT;

        return chromatic[targetChromIndex];
    }

    private int findChromaticIndex(String letter) {
        String clean = letter.replace("'", "").toLowerCase();
        switch (clean) {
            case "c": return 0;
            case "cis": case "des": return 1;
            case "d": return 2;
            case "dis": case "es": return 3;
            case "e": return 4;
            case "f": return 5;
            case "fis": case "ges": return 6;
            case "g": return 7;
            case "gis": case "as": return 8;
            case "a": return 9;
            case "b": case "ais": return 10;
            case "h": return 11;
            default: return 0;
        }
    }

    private boolean shouldUseSharp(String letter) {
        String clean = letter.replace("'", "").toLowerCase();
        switch (clean) {
            case "f": case "b": case "es": case "as": case "des": case "ges":
                return false;
            default:
                return true;
        }
    }

    private int getSemitones(Interval interval) {
        Integer semitones = INTERVAL_SEMITONES.get(interval.shortName);
        if (semitones != null) {
            return semitones;
        }
        return (int) Math.round(12 * Math.log(interval.proportion) / Math.log(2));
    }

    /**
     * Gibt den Akkord formatiert auf der Konsole aus.
     */
    public void print() {
        System.out.println("Akkord: " + symbol + " (" + type.getDisplayName() + ")");
        System.out.println("Töne:");
        for (int i = 0; i < notes.size(); i++) {
            MusicalNote note = notes.get(i);
            String intervalName = intervals[i].interval;
            System.out.println("  " + note.letter + MusicalNote.octaveToSymbols(note.octave)
                + "\t(" + String.format("%.2f", note.frequency) + " Hz)\t[" + intervalName + "]");
        }
        System.out.println();
    }

    /**
     * Gibt alle Töne des Akkords zurück.
     *
     * @return Unveränderliche Liste aller Akkordtöne
     */
    public List<MusicalNote> getNotes() {
        return Collections.unmodifiableList(notes);
    }

    /**
     * Gibt alle Frequenzen des Akkords als Array zurück.
     *
     * @return Array mit Frequenzen in Hz
     */
    public double[] getFrequencies() {
        double[] freqs = new double[notes.size()];
        for (int i = 0; i < notes.size(); i++) {
            freqs[i] = notes.get(i).frequency;
        }
        return freqs;
    }

    /**
     * Gibt alle Notennamen des Akkords als Array zurück.
     *
     * @return Array mit Notennamen (z.B. ["C", "E", "G"])
     */
    public String[] getNoteNames() {
        String[] names = new String[notes.size()];
        for (int i = 0; i < notes.size(); i++) {
            names[i] = notes.get(i).letter;
        }
        return names;
    }

    /**
     * Gibt das Akkordsymbol zurück (z.B. "C", "Am", "G7", "Hdim").
     *
     * @return Das Akkordsymbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Gibt den Akkordtyp zurück.
     *
     * @return Der ChordType dieses Akkords
     */
    public ChordType getType() {
        return type;
    }

    /**
     * Gibt den Grundton des Akkords zurück.
     *
     * @return Der Grundton als MusicalNote
     */
    public MusicalNote getRoot() {
        return root;
    }

    /**
     * Gibt die Anzahl der Töne im Akkord zurück.
     *
     * @return Anzahl der Töne (3 für Dreiklänge, 4 für Septakkorde)
     */
    public int size() {
        return notes.size();
    }

    /**
     * Prüft, ob der Akkord ein Dreiklang ist.
     *
     * @return true wenn der Akkord 3 Töne hat
     */
    public boolean isTriad() {
        return notes.size() == 3;
    }

    /**
     * Prüft, ob der Akkord ein Septakkord ist.
     *
     * @return true wenn der Akkord 4 Töne hat
     */
    public boolean isSeventhChord() {
        return notes.size() == 4;
    }

    /**
     * Gibt eine String-Repräsentation des Akkords zurück.
     *
     * @return Das Akkordsymbol
     */
    @Override
    public String toString() {
        return symbol;
    }

    // ==================== FACTORY-METHODEN ====================

    /**
     * Erstellt einen Dur-Dreiklang.
     *
     * @param root Der Grundton
     * @return Der Dur-Dreiklang
     * @throws Exception wenn der Akkord nicht erstellt werden kann
     */
    public static Chord major(MusicalNote root) throws Exception {
        return new Chord(root, ChordType.MAJOR);
    }

    /**
     * Erstellt einen Moll-Dreiklang.
     *
     * @param root Der Grundton
     * @return Der Moll-Dreiklang
     * @throws Exception wenn der Akkord nicht erstellt werden kann
     */
    public static Chord minor(MusicalNote root) throws Exception {
        return new Chord(root, ChordType.MINOR);
    }

    /**
     * Erstellt einen verminderten Dreiklang.
     *
     * @param root Der Grundton
     * @return Der verminderte Dreiklang
     * @throws Exception wenn der Akkord nicht erstellt werden kann
     */
    public static Chord diminished(MusicalNote root) throws Exception {
        return new Chord(root, ChordType.DIMINISHED);
    }

    /**
     * Erstellt einen übermäßigen Dreiklang.
     *
     * @param root Der Grundton
     * @return Der übermäßige Dreiklang
     * @throws Exception wenn der Akkord nicht erstellt werden kann
     */
    public static Chord augmented(MusicalNote root) throws Exception {
        return new Chord(root, ChordType.AUGMENTED);
    }

    /**
     * Erstellt einen Dominantseptakkord.
     *
     * @param root Der Grundton
     * @return Der Dominantseptakkord
     * @throws Exception wenn der Akkord nicht erstellt werden kann
     */
    public static Chord dominant7(MusicalNote root) throws Exception {
        return new Chord(root, ChordType.DOMINANT_7);
    }
}
