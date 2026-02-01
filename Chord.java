import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static java.util.Map.entry;

public class Chord {

    private MusicalNote root;
    private ChordType type;
    private String symbol;
    private Interval[] intervals;
    private List<MusicalNote> notes = new ArrayList<>();

    // Chromatische Skala für Notenberechnung
    private static final String[] CHROMATIC_SHARP = {"C", "Cis", "D", "Dis", "E", "F", "Fis", "G", "Gis", "A", "B", "H"};
    private static final String[] CHROMATIC_FLAT = {"C", "Des", "D", "Es", "E", "F", "Ges", "G", "As", "A", "B", "H"};

    // Mapping: Intervall-Shortname -> Halbtöne
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

    // Getter
    public List<MusicalNote> getNotes() {
        return notes;
    }

    public double[] getFrequencies() {
        double[] freqs = new double[notes.size()];
        for (int i = 0; i < notes.size(); i++) {
            freqs[i] = notes.get(i).frequency;
        }
        return freqs;
    }

    public String getSymbol() {
        return symbol;
    }

    public ChordType getType() {
        return type;
    }

    public MusicalNote getRoot() {
        return root;
    }

    // Factory-Methoden
    public static Chord major(MusicalNote root) throws Exception {
        return new Chord(root, ChordType.MAJOR);
    }

    public static Chord minor(MusicalNote root) throws Exception {
        return new Chord(root, ChordType.MINOR);
    }

    public static Chord diminished(MusicalNote root) throws Exception {
        return new Chord(root, ChordType.DIMINISHED);
    }

    public static Chord augmented(MusicalNote root) throws Exception {
        return new Chord(root, ChordType.AUGMENTED);
    }

    public static Chord dominant7(MusicalNote root) throws Exception {
        return new Chord(root, ChordType.DOMINANT_7);
    }
}
