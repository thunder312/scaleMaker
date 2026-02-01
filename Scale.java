Super. Im mimport java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static java.util.Map.entry;

public class Scale {

    private String symbol;
    private MusicalNote fundamentalTone;
    private Interval[] intervals;
    private ScaleType scaleType;
    private List<MusicalNote> scale = new ArrayList<>();

    // Chromatische Skala für Notenberechnung
    private static final String[] CHROMATIC_SHARP = {"C", "Cis", "D", "Dis", "E", "F", "Fis", "G", "Gis", "A", "B", "H"};
    private static final String[] CHROMATIC_FLAT = {"C", "Des", "D", "Es", "E", "F", "Ges", "G", "As", "A", "B", "H"};

    // Intervall-Stufen in Halbtönen
    private static final Map<String, Integer> INTERVAL_SEMITONES = Map.ofEntries(
        entry("P", 0),
        entry("Sk1", 1), entry("Sk2", 1),
        entry("Sg1", 2), entry("Sg2", 2),
        entry("Tk1", 3), entry("Tk2", 3),
        entry("Tg1", 4), entry("Tg2", 4),
        entry("Qua", 5),
        entry("Qua1", 6), entry("Qua2", 6), entry("Qua3", 6),
        entry("Qui1", 6), entry("Qui2", 6), entry("Qui3", 6),
        entry("Qui", 7),
        entry("Qui+", 8),
        entry("Sxk", 8),
        entry("Sxg", 9),
        entry("Sepk1", 10), entry("Sepk2", 10), entry("Sepk3", 10),
        entry("Sep--", 9),
        entry("Sepg", 11),
        entry("Ok", 12)
    );

    public Scale(MusicalNote fundamentalTone, ScaleType scaleType) throws Exception {
        this.fundamentalTone = fundamentalTone;
        this.scaleType = scaleType;
        this.intervals = scaleType.getIntervals();
        this.symbol = MusicalNote.octaveToSymbols(fundamentalTone.octave);
        createScale();
    }

    private void createScale() throws Exception {
        String[] noteLetters = calculateNoteLetters();

        this.scale.add(this.fundamentalTone);
        for (int i = 1; i < this.intervals.length; i++) {
            double freq = this.fundamentalTone.frequency * this.intervals[i].proportion;
            int octave = this.fundamentalTone.octave;

            if (this.intervals[i].proportion >= 2.0) {
                octave++;
            }

            this.scale.add(new MusicalNote(noteLetters[i], freq, octave));
        }
    }

    private String[] calculateNoteLetters() {
        String[] result = new String[intervals.length];
        int rootIndex = findChromaticIndex(fundamentalTone.letter);

        boolean useSharp = shouldUseSharp(fundamentalTone.letter);
        String[] chromatic = useSharp ? CHROMATIC_SHARP : CHROMATIC_FLAT;

        for (int i = 0; i < intervals.length; i++) {
            int semitones = getSemitones(intervals[i]);
            int noteIndex = (rootIndex + semitones) % 12;
            result[i] = chromatic[noteIndex];
        }

        return result;
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
            case "g": case "d": case "a": case "e": case "h":
            case "fis": case "cis": case "gis": case "dis": case "ais":
                return true;
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
        System.out.println("Grundton: " + fundamentalTone.letter);
        System.out.println("Skala: " + scaleType.getDisplayName());
        System.out.println("Intervalle: " + intervals.length + " (inkl. Prim)");
        System.out.println("\n" + fundamentalTone.letter + "-" + scaleType.getDisplayName() + ":");
        System.out.println("-".repeat(40));

        for (int i = 0; i < scale.size(); i++) {
            MusicalNote note = scale.get(i);
            String intervalName = intervals[i].interval;
            System.out.println(String.format("%-3d", i + 1) + ". " +
                note.letter + MusicalNote.octaveToSymbols(note.octave) +
                "\t(" + String.format("%.2f", note.frequency) + " Hz)" +
                "\t[" + intervalName + "]");
        }
        System.out.println();
    }

    // Getter
    public List<MusicalNote> getNotes() {
        return scale;
    }

    public MusicalNote getNote(int degree) {
        if (degree < 1 || degree > scale.size()) {
            return null;
        }
        return scale.get(degree - 1);
    }

    public double[] getFrequencies() {
        double[] freqs = new double[scale.size()];
        for (int i = 0; i < scale.size(); i++) {
            freqs[i] = scale.get(i).frequency;
        }
        return freqs;
    }

    public String[] getNoteNames() {
        String[] names = new String[scale.size()];
        for (int i = 0; i < scale.size(); i++) {
            names[i] = scale.get(i).letter;
        }
        return names;
    }

    public ScaleType getScaleType() {
        return scaleType;
    }

    public MusicalNote getFundamentalTone() {
        return fundamentalTone;
    }

    // Dreiklang auf einer Stufe der Tonleiter erzeugen
    public Chord getTriadOnDegree(int degree) throws Exception {
        if (degree < 1 || degree > scale.size() - 1) {
            throw new Exception("Stufe muss zwischen 1 und " + (scale.size() - 1) + " liegen.");
        }

        MusicalNote root = scale.get(degree - 1);
        ChordType chordType;

        if (scaleType == ScaleType.MAJOR || scaleType == ScaleType.IONIAN) {
            switch (degree) {
                case 1: case 4: case 5: chordType = ChordType.MAJOR; break;
                case 2: case 3: case 6: chordType = ChordType.MINOR; break;
                case 7: chordType = ChordType.DIMINISHED; break;
                default: chordType = ChordType.MAJOR;
            }
        } else if (scaleType == ScaleType.MINOR || scaleType == ScaleType.AEOLIAN) {
            switch (degree) {
                case 1: case 4: case 5: chordType = ChordType.MINOR; break;
                case 3: case 6: case 7: chordType = ChordType.MAJOR; break;
                case 2: chordType = ChordType.DIMINISHED; break;
                default: chordType = ChordType.MINOR;
            }
        } else {
            chordType = ChordType.MAJOR;
        }

        return new Chord(root, chordType);
    }
}
