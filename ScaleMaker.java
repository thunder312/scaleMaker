/**
 * ScaleMaker - Zentrale API-Klasse für Tonleitern, Akkorde und Intervalle.
 *
 * <p>Diese Klasse bietet statische Factory-Methoden für die einfache Erstellung
 * von musikalischen Elementen wie Tonleitern, Akkorden und Einzeltönen.</p>
 *
 * <h2>Beispiel-Verwendung:</h2>
 * <pre>{@code
 * // Einzelton erstellen
 * MusicalNote c = ScaleMaker.note("C");
 * MusicalNote a440 = ScaleMaker.note("A", 440.0);
 *
 * // Tonleiter erstellen
 * Scale cDur = ScaleMaker.scale("C", ScaleType.MAJOR);
 * Scale aMinor = ScaleMaker.scale("A", ScaleType.MINOR);
 *
 * // Akkord erstellen
 * Chord cMajor = ScaleMaker.chord("C", ChordType.MAJOR);
 * Chord g7 = ScaleMaker.chord("G", ChordType.DOMINANT_7);
 *
 * // Frequenzen abrufen
 * double[] freqs = cDur.getFrequencies();
 * String[] notes = cDur.getNoteNames();
 *
 * // Stufenakkorde
 * Chord[] stufenakkorde = ScaleMaker.getDiatonicChords(cDur);
 * }</pre>
 *
 * @author ScaleMaker
 * @version 1.0
 */
public final class ScaleMaker {

    private ScaleMaker() {
        // Utility-Klasse, nicht instanziierbar
    }

    // ==================== NOTEN ====================

    /**
     * Erstellt eine Note mit dem angegebenen Buchstaben.
     * Die Frequenz wird aus der Standard-C-Dur-Skala (Kammerton A = 440 Hz) abgeleitet.
     *
     * @param letter Notenname (C, D, E, F, G, A, H, B, Cis, Des, etc.)
     * @return Die erstellte Note
     * @throws Exception wenn der Notenname ungültig ist
     *
     * @see #note(String, double)
     */
    public static MusicalNote note(String letter) throws Exception {
        return new MusicalNote(letter);
    }

    /**
     * Erstellt eine Note mit dem angegebenen Buchstaben und Frequenz.
     *
     * @param letter Notenname (C, D, E, F, G, A, H, B, Cis, Des, etc.)
     * @param frequency Frequenz in Hz
     * @return Die erstellte Note
     * @throws Exception wenn die Parameter ungültig sind
     */
    public static MusicalNote note(String letter, double frequency) throws Exception {
        return new MusicalNote(letter, frequency);
    }

    /**
     * Erstellt eine Note mit dem angegebenen Buchstaben, Frequenz und Oktave.
     *
     * @param letter Notenname
     * @param frequency Frequenz in Hz
     * @param octave Oktavlage (1 = eingestrichen, 2 = zweigestrichen, etc.)
     * @return Die erstellte Note
     * @throws Exception wenn die Parameter ungültig sind
     */
    public static MusicalNote note(String letter, double frequency, int octave) throws Exception {
        return new MusicalNote(letter, frequency, octave);
    }

    // ==================== TONLEITERN ====================

    /**
     * Erstellt eine Tonleiter mit dem angegebenen Grundton und Typ.
     *
     * <p>Beispiel:</p>
     * <pre>{@code
     * Scale cDur = ScaleMaker.scale("C", ScaleType.MAJOR);
     * Scale fisMinor = ScaleMaker.scale("Fis", ScaleType.HARMONIC_MINOR);
     * Scale dDorian = ScaleMaker.scale("D", ScaleType.DORIAN);
     * }</pre>
     *
     * @param rootNote Grundton als Buchstabe (C, D, E, F, G, A, H, B, Cis, etc.)
     * @param type Typ der Tonleiter (z.B. ScaleType.MAJOR, ScaleType.MINOR)
     * @return Die erstellte Tonleiter
     * @throws Exception wenn der Grundton ungültig ist
     *
     * @see ScaleType
     */
    public static Scale scale(String rootNote, ScaleType type) throws Exception {
        return new Scale(note(rootNote), type);
    }

    /**
     * Erstellt eine Tonleiter mit der angegebenen Note als Grundton.
     *
     * @param rootNote Grundton als MusicalNote
     * @param type Typ der Tonleiter
     * @return Die erstellte Tonleiter
     * @throws Exception wenn die Erstellung fehlschlägt
     */
    public static Scale scale(MusicalNote rootNote, ScaleType type) throws Exception {
        return new Scale(rootNote, type);
    }

    /**
     * Erstellt eine Dur-Tonleiter (Major) auf dem angegebenen Grundton.
     *
     * @param rootNote Grundton als Buchstabe
     * @return Die erstellte Dur-Tonleiter
     * @throws Exception wenn der Grundton ungültig ist
     */
    public static Scale majorScale(String rootNote) throws Exception {
        return scale(rootNote, ScaleType.MAJOR);
    }

    /**
     * Erstellt eine natürliche Moll-Tonleiter auf dem angegebenen Grundton.
     *
     * @param rootNote Grundton als Buchstabe
     * @return Die erstellte Moll-Tonleiter
     * @throws Exception wenn der Grundton ungültig ist
     */
    public static Scale minorScale(String rootNote) throws Exception {
        return scale(rootNote, ScaleType.MINOR);
    }

    // ==================== AKKORDE ====================

    /**
     * Erstellt einen Akkord mit dem angegebenen Grundton und Typ.
     *
     * <p>Beispiel:</p>
     * <pre>{@code
     * Chord cDur = ScaleMaker.chord("C", ChordType.MAJOR);
     * Chord am = ScaleMaker.chord("A", ChordType.MINOR);
     * Chord g7 = ScaleMaker.chord("G", ChordType.DOMINANT_7);
     * }</pre>
     *
     * @param rootNote Grundton als Buchstabe
     * @param type Typ des Akkords (z.B. ChordType.MAJOR, ChordType.MINOR_7)
     * @return Der erstellte Akkord
     * @throws Exception wenn der Grundton ungültig ist
     *
     * @see ChordType
     */
    public static Chord chord(String rootNote, ChordType type) throws Exception {
        return new Chord(note(rootNote), type);
    }

    /**
     * Erstellt einen Akkord mit der angegebenen Note als Grundton.
     *
     * @param rootNote Grundton als MusicalNote
     * @param type Typ des Akkords
     * @return Der erstellte Akkord
     * @throws Exception wenn die Erstellung fehlschlägt
     */
    public static Chord chord(MusicalNote rootNote, ChordType type) throws Exception {
        return new Chord(rootNote, type);
    }

    /**
     * Erstellt einen Dur-Dreiklang auf dem angegebenen Grundton.
     *
     * @param rootNote Grundton als Buchstabe
     * @return Der erstellte Dur-Dreiklang
     * @throws Exception wenn der Grundton ungültig ist
     */
    public static Chord majorChord(String rootNote) throws Exception {
        return chord(rootNote, ChordType.MAJOR);
    }

    /**
     * Erstellt einen Moll-Dreiklang auf dem angegebenen Grundton.
     *
     * @param rootNote Grundton als Buchstabe
     * @return Der erstellte Moll-Dreiklang
     * @throws Exception wenn der Grundton ungültig ist
     */
    public static Chord minorChord(String rootNote) throws Exception {
        return chord(rootNote, ChordType.MINOR);
    }

    // ==================== STUFENAKKORDE ====================

    /**
     * Gibt alle diatonischen Dreiklänge einer Tonleiter zurück.
     *
     * <p>Für eine Dur-Tonleiter werden die Stufenakkorde I-VII zurückgegeben:
     * I (Dur), ii (Moll), iii (Moll), IV (Dur), V (Dur), vi (Moll), vii° (vermindert)</p>
     *
     * <p>Beispiel:</p>
     * <pre>{@code
     * Scale cDur = ScaleMaker.majorScale("C");
     * Chord[] akkorde = ScaleMaker.getDiatonicChords(cDur);
     * // akkorde[0] = C, akkorde[1] = Dm, akkorde[2] = Em, etc.
     * }</pre>
     *
     * @param scale Die Tonleiter
     * @return Array mit allen Stufenakkorden (7 Akkorde für heptatonische Skalen)
     * @throws Exception wenn die Erstellung fehlschlägt
     */
    public static Chord[] getDiatonicChords(Scale scale) throws Exception {
        int numDegrees = scale.getNotes().size() - 1; // ohne Oktave
        Chord[] chords = new Chord[numDegrees];
        for (int i = 1; i <= numDegrees; i++) {
            chords[i - 1] = scale.getTriadOnDegree(i);
        }
        return chords;
    }

    /**
     * Gibt die Akkordsymbole aller Stufenakkorde einer Tonleiter zurück.
     *
     * @param scale Die Tonleiter
     * @return Array mit Akkordsymbolen (z.B. ["C", "Dm", "Em", "F", "G", "Am", "Hdim"])
     * @throws Exception wenn die Erstellung fehlschlägt
     */
    public static String[] getDiatonicChordSymbols(Scale scale) throws Exception {
        Chord[] chords = getDiatonicChords(scale);
        String[] symbols = new String[chords.length];
        for (int i = 0; i < chords.length; i++) {
            symbols[i] = chords[i].getSymbol();
        }
        return symbols;
    }

    // ==================== FREQUENZ-BERECHNUNG ====================

    /**
     * Berechnet die Frequenz einer Note basierend auf Halbtonabstand zum Kammerton A.
     *
     * <p>Verwendet die Formel: f = 440 * 2^(n/12), wobei n der Halbtonabstand zu A ist.</p>
     *
     * @param semitonesFromA Halbtonabstand zu A (positiv = höher, negativ = tiefer)
     * @return Die berechnete Frequenz in Hz
     */
    public static double calculateFrequency(int semitonesFromA) {
        return 440.0 * Math.pow(2, semitonesFromA / 12.0);
    }

    /**
     * Berechnet die Frequenz einer Note basierend auf einem Referenzton.
     *
     * @param referenceFreq Referenzfrequenz in Hz
     * @param semitones Halbtonabstand zur Referenz
     * @return Die berechnete Frequenz in Hz
     */
    public static double calculateFrequency(double referenceFreq, int semitones) {
        return referenceFreq * Math.pow(2, semitones / 12.0);
    }

    /**
     * Wendet ein Intervall auf eine Frequenz an.
     *
     * @param baseFrequency Ausgangsfrequenz in Hz
     * @param interval Das anzuwendende Intervall
     * @return Die resultierende Frequenz in Hz
     */
    public static double applyInterval(double baseFrequency, IntervalType interval) {
        return baseFrequency * interval.getRatio();
    }

    // ==================== INFORMATIONEN ====================

    /**
     * Gibt alle verfügbaren Skalentypen auf der Konsole aus.
     */
    public static void printAvailableScales() {
        ScaleType.printAll();
    }

    /**
     * Gibt alle verfügbaren Akkordtypen auf der Konsole aus.
     */
    public static void printAvailableChords() {
        ChordType.printAll();
    }

    /**
     * Gibt alle verfügbaren Intervalle auf der Konsole aus.
     */
    public static void printAvailableIntervals() {
        IntervalType.printAll();
    }

    /**
     * Gibt die Versionsnummer der Library zurück.
     *
     * @return Versionsnummer als String
     */
    public static String getVersion() {
        return "1.0.0";
    }
}
