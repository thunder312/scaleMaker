import java.util.List;

/**
 * API-Interface für alle Musik-Theorie-Funktionen des ScaleMaker-Plugins.
 *
 * <p>Dieses Interface spiegelt die Funktionalität der statischen {@link ScaleMaker}-Klasse
 * als instanzbasierte, testbare Schnittstelle wider. Es wird vom Hauptprogramm verwendet,
 * um Noten, Tonleitern, Akkorde und Intervalle zu erstellen und zu analysieren.</p>
 *
 * <h2>Beispiel:</h2>
 * <pre>{@code
 * ScaleMakerApi api = plugin.getApi();
 *
 * // Tonleiter erstellen
 * Scale cDur = api.majorScale("C");
 * Scale aMinor = api.scale("A", ScaleType.MINOR);
 *
 * // Akkorde erstellen
 * Chord gDom7 = api.chord("G", ChordType.DOMINANT_7);
 *
 * // Stufenakkorde einer Tonleiter
 * String[] symbole = api.getDiatonicChordSymbols(cDur);
 * // ["C", "Dm", "Em", "F", "G", "Am", "Hdim"]
 * }</pre>
 *
 * @author ScaleMaker
 * @version 1.0
 * @see ScaleMakerPlugin
 */
public interface ScaleMakerApi {

    // ==================== NOTEN ====================

    /**
     * Erstellt eine Note anhand des Notennamens.
     * Die Frequenz wird aus der Standard-C-Dur-Skala (A = 440 Hz) abgeleitet.
     *
     * @param letter Notenname (C, D, E, F, G, A, H, B, Cis, Des, etc.)
     * @return Die erstellte Note
     * @throws Exception wenn der Notenname ungültig ist
     */
    MusicalNote note(String letter) throws Exception;

    /**
     * Erstellt eine Note mit expliziter Frequenz.
     *
     * @param letter    Notenname
     * @param frequency Frequenz in Hz
     * @return Die erstellte Note
     * @throws Exception wenn die Parameter ungültig sind
     */
    MusicalNote note(String letter, double frequency) throws Exception;

    /**
     * Erstellt eine Note mit expliziter Frequenz und Oktavlage.
     *
     * @param letter    Notenname
     * @param frequency Frequenz in Hz
     * @param octave    Oktavlage (1 = eingestrichen, 2 = zweigestrichen, etc.)
     * @return Die erstellte Note
     * @throws Exception wenn die Parameter ungültig sind
     */
    MusicalNote note(String letter, double frequency, int octave) throws Exception;

    // ==================== TONLEITERN ====================

    /**
     * Erstellt eine Tonleiter auf dem angegebenen Grundton.
     *
     * @param rootNote Grundton als Buchstabe (C, D, E, Fis, etc.)
     * @param type     Skalentyp (z.B. {@link ScaleType#MAJOR}, {@link ScaleType#DORIAN})
     * @return Die erstellte Tonleiter
     * @throws Exception wenn der Grundton ungültig ist
     * @see ScaleType
     */
    Scale scale(String rootNote, ScaleType type) throws Exception;

    /**
     * Erstellt eine Tonleiter auf der angegebenen Note als Grundton.
     *
     * @param rootNote Grundton als {@link MusicalNote}
     * @param type     Skalentyp
     * @return Die erstellte Tonleiter
     * @throws Exception wenn die Erstellung fehlschlägt
     */
    Scale scale(MusicalNote rootNote, ScaleType type) throws Exception;

    /**
     * Erstellt eine Dur-Tonleiter (Ionisch / Major).
     *
     * @param rootNote Grundton als Buchstabe
     * @return Die erstellte Dur-Tonleiter
     * @throws Exception wenn der Grundton ungültig ist
     */
    Scale majorScale(String rootNote) throws Exception;

    /**
     * Erstellt eine natürliche Moll-Tonleiter (Äolisch / Minor).
     *
     * @param rootNote Grundton als Buchstabe
     * @return Die erstellte Moll-Tonleiter
     * @throws Exception wenn der Grundton ungültig ist
     */
    Scale minorScale(String rootNote) throws Exception;

    // ==================== AKKORDE ====================

    /**
     * Erstellt einen Akkord auf dem angegebenen Grundton.
     *
     * @param rootNote Grundton als Buchstabe
     * @param type     Akkordtyp (z.B. {@link ChordType#MAJOR}, {@link ChordType#DOMINANT_7})
     * @return Der erstellte Akkord
     * @throws Exception wenn der Grundton ungültig ist
     * @see ChordType
     */
    Chord chord(String rootNote, ChordType type) throws Exception;

    /**
     * Erstellt einen Akkord auf der angegebenen Note als Grundton.
     *
     * @param rootNote Grundton als {@link MusicalNote}
     * @param type     Akkordtyp
     * @return Der erstellte Akkord
     * @throws Exception wenn die Erstellung fehlschlägt
     */
    Chord chord(MusicalNote rootNote, ChordType type) throws Exception;

    /**
     * Erstellt einen Dur-Dreiklang.
     *
     * @param rootNote Grundton als Buchstabe
     * @return Der erstellte Dur-Dreiklang
     * @throws Exception wenn der Grundton ungültig ist
     */
    Chord majorChord(String rootNote) throws Exception;

    /**
     * Erstellt einen Moll-Dreiklang.
     *
     * @param rootNote Grundton als Buchstabe
     * @return Der erstellte Moll-Dreiklang
     * @throws Exception wenn der Grundton ungültig ist
     */
    Chord minorChord(String rootNote) throws Exception;

    // ==================== STUFENAKKORDE ====================

    /**
     * Gibt alle diatonischen Dreiklänge einer Tonleiter zurück.
     *
     * <p>Für eine Dur-Tonleiter: I (Dur), ii (Moll), iii (Moll), IV (Dur),
     * V (Dur), vi (Moll), vii° (vermindert)</p>
     *
     * @param scale Die Tonleiter
     * @return Array mit allen Stufenakkorden
     * @throws Exception wenn die Erstellung fehlschlägt
     */
    Chord[] getDiatonicChords(Scale scale) throws Exception;

    /**
     * Gibt die Akkordsymbole aller Stufenakkorde einer Tonleiter zurück.
     *
     * @param scale Die Tonleiter
     * @return Array mit Akkordsymbolen (z.B. ["C", "Dm", "Em", "F", "G", "Am", "Hdim"])
     * @throws Exception wenn die Erstellung fehlschlägt
     */
    String[] getDiatonicChordSymbols(Scale scale) throws Exception;

    // ==================== FREQUENZ-BERECHNUNG ====================

    /**
     * Berechnet die Frequenz anhand des Halbtonabstands zu A = 440 Hz.
     *
     * @param semitonesFromA Halbtonabstand zu A (positiv = höher, negativ = tiefer)
     * @return Die berechnete Frequenz in Hz
     */
    double calculateFrequency(int semitonesFromA);

    /**
     * Berechnet die Frequenz relativ zu einer Referenzfrequenz.
     *
     * @param referenceFreq Referenzfrequenz in Hz
     * @param semitones     Halbtonabstand zur Referenz
     * @return Die berechnete Frequenz in Hz
     */
    double calculateFrequency(double referenceFreq, int semitones);

    /**
     * Wendet ein Intervall auf eine Frequenz an.
     *
     * @param baseFrequency Ausgangsfrequenz in Hz
     * @param interval      Das anzuwendende Intervall
     * @return Die resultierende Frequenz in Hz
     */
    double applyInterval(double baseFrequency, IntervalType interval);

    // ==================== VERFÜGBARE TYPEN ====================

    /**
     * Gibt alle verfügbaren Skalentypen zurück.
     *
     * @return Array aller {@link ScaleType}-Werte
     */
    ScaleType[] getAvailableScaleTypes();

    /**
     * Gibt alle verfügbaren Akkordtypen zurück.
     *
     * @return Array aller {@link ChordType}-Werte
     */
    ChordType[] getAvailableChordTypes();

    /**
     * Gibt alle verfügbaren Intervalltypen zurück.
     *
     * @return Array aller {@link IntervalType}-Werte
     */
    IntervalType[] getAvailableIntervalTypes();
}
