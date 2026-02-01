/**
 * Demo-Klasse zur Demonstration der ScaleMaker-API.
 *
 * <p>Diese Klasse zeigt Beispiele für die Verwendung der Library.</p>
 */
public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("=== SCALEMAKER API DEMO ===");
        System.out.println("Version: " + ScaleMaker.getVersion());
        System.out.println();

        // ==================== NOTEN ====================
        System.out.println("--- Noten erstellen ---");

        MusicalNote c = ScaleMaker.note("C");
        System.out.println("C: " + c.getFrequency() + " Hz");

        MusicalNote a440 = ScaleMaker.note("A", 440.0);
        System.out.println("A: " + a440.getFrequency() + " Hz");

        // ==================== TONLEITERN ====================
        System.out.println("\n--- Tonleitern ---");

        Scale cDur = ScaleMaker.majorScale("C");
        System.out.println(cDur + ": " + String.join(", ", cDur.getNoteNames()));

        Scale aMinor = ScaleMaker.minorScale("A");
        System.out.println(aMinor + ": " + String.join(", ", aMinor.getNoteNames()));

        Scale dDorian = ScaleMaker.scale("D", ScaleType.DORIAN);
        System.out.println(dDorian + ": " + String.join(", ", dDorian.getNoteNames()));

        // ==================== AKKORDE ====================
        System.out.println("\n--- Akkorde ---");

        Chord cMajorChord = ScaleMaker.majorChord("C");
        System.out.println(cMajorChord.getSymbol() + ": " + String.join(", ", cMajorChord.getNoteNames()));

        Chord am = ScaleMaker.minorChord("A");
        System.out.println(am.getSymbol() + ": " + String.join(", ", am.getNoteNames()));

        Chord g7 = ScaleMaker.chord("G", ChordType.DOMINANT_7);
        System.out.println(g7.getSymbol() + ": " + String.join(", ", g7.getNoteNames()));

        // ==================== STUFENAKKORDE ====================
        System.out.println("\n--- Stufenakkorde C-Dur ---");

        String[] diatonicChords = ScaleMaker.getDiatonicChordSymbols(cDur);
        for (int i = 0; i < diatonicChords.length; i++) {
            System.out.println("Stufe " + (i + 1) + ": " + diatonicChords[i]);
        }

        // ==================== FREQUENZ-BERECHNUNG ====================
        System.out.println("\n--- Frequenz-Berechnung ---");

        double freq = ScaleMaker.calculateFrequency(0);  // A = 440 Hz
        System.out.println("A (0 Halbtöne): " + freq + " Hz");

        freq = ScaleMaker.calculateFrequency(-9);  // C unter A
        System.out.println("C (-9 Halbtöne): " + String.format("%.2f", freq) + " Hz");

        freq = ScaleMaker.applyInterval(264.0, IntervalType.PERFECT_FIFTH);
        System.out.println("C + Quinte: " + freq + " Hz (G)");

        // ==================== VERFÜGBARE TYPEN ====================
        System.out.println("\n--- Verfügbare Skalen ---");
        ScaleMaker.printAvailableScales();

        System.out.println("\n--- Verfügbare Akkorde ---");
        ScaleMaker.printAvailableChords();
    }
}
