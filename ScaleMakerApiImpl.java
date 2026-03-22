/**
 * Nicht-statische Implementierung der {@link ScaleMakerApi}.
 *
 * <p>Diese Klasse delegiert alle Aufrufe an die statische {@link ScaleMaker}-Klasse
 * und macht die API testbar und instanziierbar.</p>
 *
 * <p>Instanzen dieser Klasse werden von {@link ScaleMakerPluginImpl} erstellt und
 * verwaltet. Das Hauptprogramm sollte diese Klasse nicht direkt instanziieren,
 * sondern die Instanz über {@link ScaleMakerPlugin#getApi()} beziehen.</p>
 *
 * @author ScaleMaker
 * @version 1.0
 * @see ScaleMakerApi
 * @see ScaleMakerPluginImpl
 */
class ScaleMakerApiImpl implements ScaleMakerApi {

    // ==================== NOTEN ====================

    @Override
    public MusicalNote note(String letter) throws Exception {
        return ScaleMaker.note(letter);
    }

    @Override
    public MusicalNote note(String letter, double frequency) throws Exception {
        return ScaleMaker.note(letter, frequency);
    }

    @Override
    public MusicalNote note(String letter, double frequency, int octave) throws Exception {
        return ScaleMaker.note(letter, frequency, octave);
    }

    // ==================== TONLEITERN ====================

    @Override
    public Scale scale(String rootNote, ScaleType type) throws Exception {
        return ScaleMaker.scale(rootNote, type);
    }

    @Override
    public Scale scale(MusicalNote rootNote, ScaleType type) throws Exception {
        return ScaleMaker.scale(rootNote, type);
    }

    @Override
    public Scale majorScale(String rootNote) throws Exception {
        return ScaleMaker.majorScale(rootNote);
    }

    @Override
    public Scale minorScale(String rootNote) throws Exception {
        return ScaleMaker.minorScale(rootNote);
    }

    // ==================== AKKORDE ====================

    @Override
    public Chord chord(String rootNote, ChordType type) throws Exception {
        return ScaleMaker.chord(rootNote, type);
    }

    @Override
    public Chord chord(MusicalNote rootNote, ChordType type) throws Exception {
        return ScaleMaker.chord(rootNote, type);
    }

    @Override
    public Chord majorChord(String rootNote) throws Exception {
        return ScaleMaker.majorChord(rootNote);
    }

    @Override
    public Chord minorChord(String rootNote) throws Exception {
        return ScaleMaker.minorChord(rootNote);
    }

    // ==================== STUFENAKKORDE ====================

    @Override
    public Chord[] getDiatonicChords(Scale scale) throws Exception {
        return ScaleMaker.getDiatonicChords(scale);
    }

    @Override
    public String[] getDiatonicChordSymbols(Scale scale) throws Exception {
        return ScaleMaker.getDiatonicChordSymbols(scale);
    }

    // ==================== FREQUENZ-BERECHNUNG ====================

    @Override
    public double calculateFrequency(int semitonesFromA) {
        return ScaleMaker.calculateFrequency(semitonesFromA);
    }

    @Override
    public double calculateFrequency(double referenceFreq, int semitones) {
        return ScaleMaker.calculateFrequency(referenceFreq, semitones);
    }

    @Override
    public double applyInterval(double baseFrequency, IntervalType interval) {
        return ScaleMaker.applyInterval(baseFrequency, interval);
    }

    // ==================== VERFÜGBARE TYPEN ====================

    @Override
    public ScaleType[] getAvailableScaleTypes() {
        return ScaleType.values();
    }

    @Override
    public ChordType[] getAvailableChordTypes() {
        return ChordType.values();
    }

    @Override
    public IntervalType[] getAvailableIntervalTypes() {
        return IntervalType.values();
    }
}
