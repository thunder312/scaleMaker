import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import static java.util.Map.entry;

/**
 * Repräsentiert eine musikalische Tonleiter (Skala).
 *
 * <p>Eine Tonleiter besteht aus einem Grundton und einer Folge von Intervallen,
 * die den Skalentyp definieren (z.B. Dur, Moll, Dorisch).</p>
 *
 * <h2>Beispiel:</h2>
 * <pre>{@code
 * MusicalNote c = new MusicalNote("C");
 * Scale cDur = new Scale(c, ScaleType.MAJOR);
 *
 * // Alle Noten der Tonleiter
 * List<MusicalNote> notes = cDur.getNotes();
 *
 * // Frequenzen als Array
 * double[] frequencies = cDur.getFrequencies();
 *
 * // Notennamen als Array
 * String[] names = cDur.getNoteNames();
 *
 * // Stufenakkord auf der 5. Stufe (Dominante)
 * Chord dominant = cDur.getTriadOnDegree(5);
 * }</pre>
 *
 * @see ScaleType
 * @see MusicalNote
 * @see Chord
 */
public class Scale {

    private String symbol;
    private MusicalNote fundamentalTone;
    private Interval[] intervals;
    private ScaleType scaleType;
    private List<MusicalNote> scale = new ArrayList<>();

    private static final String[] CHROMATIC_SHARP = {"C", "Cis", "D", "Dis", "E", "F", "Fis", "G", "Gis", "A", "B", "H"};
    private static final String[] CHROMATIC_FLAT = {"C", "Des", "D", "Es", "E", "F", "Ges", "G", "As", "A", "B", "H"};

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

    /**
     * Erstellt eine neue Tonleiter.
     *
     * @param fundamentalTone Der Grundton der Tonleiter
     * @param scaleType Der Typ der Tonleiter (z.B. MAJOR, MINOR, DORIAN)
     * @throws Exception wenn die Tonleiter nicht erstellt werden kann
     */
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

    /**
     * Gibt die Tonleiter formatiert auf der Konsole aus.
     */
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

    /**
     * Gibt alle Noten der Tonleiter zurück.
     *
     * @return Unveränderliche Liste aller Noten (inkl. Oktave am Ende)
     */
    public List<MusicalNote> getNotes() {
        return Collections.unmodifiableList(scale);
    }

    /**
     * Gibt eine bestimmte Stufe der Tonleiter zurück.
     *
     * @param degree Stufe (1-basiert, 1 = Grundton, 8 = Oktave)
     * @return Die Note auf dieser Stufe, oder null wenn ungültig
     */
    public MusicalNote getNote(int degree) {
        if (degree < 1 || degree > scale.size()) {
            return null;
        }
        return scale.get(degree - 1);
    }

    /**
     * Gibt alle Frequenzen der Tonleiter als Array zurück.
     *
     * @return Array mit Frequenzen in Hz
     */
    public double[] getFrequencies() {
        double[] freqs = new double[scale.size()];
        for (int i = 0; i < scale.size(); i++) {
            freqs[i] = scale.get(i).frequency;
        }
        return freqs;
    }

    /**
     * Gibt alle Notennamen der Tonleiter als Array zurück.
     *
     * @return Array mit Notennamen (z.B. ["C", "D", "E", "F", "G", "A", "H", "C"])
     */
    public String[] getNoteNames() {
        String[] names = new String[scale.size()];
        for (int i = 0; i < scale.size(); i++) {
            names[i] = scale.get(i).letter;
        }
        return names;
    }

    /**
     * Gibt den Typ der Tonleiter zurück.
     *
     * @return Der ScaleType dieser Tonleiter
     */
    public ScaleType getScaleType() {
        return scaleType;
    }

    /**
     * Gibt den Grundton der Tonleiter zurück.
     *
     * @return Der Grundton als MusicalNote
     */
    public MusicalNote getFundamentalTone() {
        return fundamentalTone;
    }

    /**
     * Gibt die Anzahl der Töne in der Tonleiter zurück (inkl. Oktave).
     *
     * @return Anzahl der Töne
     */
    public int size() {
        return scale.size();
    }

    /**
     * Erstellt den leitereigenen Dreiklang auf einer bestimmten Stufe.
     *
     * <p>Der Akkordtyp wird automatisch basierend auf der Tonleiter bestimmt.
     * Für Dur: I, IV, V = Dur; ii, iii, vi = Moll; vii° = vermindert</p>
     *
     * @param degree Stufe (1-7 für heptatonische Skalen)
     * @return Der Stufenakkord
     * @throws Exception wenn die Stufe ungültig ist
     */
    public Chord getTriadOnDegree(int degree) throws Exception {
        if (degree < 1 || degree > scale.size() - 1) {
            throw new IllegalArgumentException("Stufe muss zwischen 1 und " + (scale.size() - 1) + " liegen.");
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

    /**
     * Gibt eine String-Repräsentation der Tonleiter zurück.
     *
     * @return String im Format "C-Dur" oder "A-Natürlich Moll"
     */
    @Override
    public String toString() {
        return fundamentalTone.letter + "-" + scaleType.getDisplayName();
    }
}
