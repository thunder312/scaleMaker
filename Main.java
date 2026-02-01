public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("=== SCALEMAKER DEMO ===\n");

        // 1. Verfügbare Skalen anzeigen
        ScaleType.printAll();
        System.out.println();

        // 2. C-Dur Tonleiter
        MusicalNote c = new MusicalNote("C");
        Scale cMajor = new Scale(c, ScaleType.MAJOR);
        cMajor.print();

        // 3. A-Moll Tonleiter (natürlich)
        MusicalNote a = new MusicalNote("A");
        Scale aMinor = new Scale(a, ScaleType.MINOR);
        aMinor.print();

        // 4. D-Dorisch
        MusicalNote d = new MusicalNote("D");
        Scale dDorian = new Scale(d, ScaleType.DORIAN);
        dDorian.print();

        // 5. Blues-Skala auf E
        MusicalNote e = new MusicalNote("E");
        Scale eBlues = new Scale(e, ScaleType.BLUES);
        eBlues.print();

        // 6. Akkorde
        System.out.println("=== AKKORDE ===\n");
        ChordType.printAll();
        System.out.println();

        // C-Dur Dreiklang
        Chord cMajorChord = new Chord(c, ChordType.MAJOR);
        cMajorChord.print();

        // A-Moll Dreiklang
        Chord aMinorChord = new Chord(a, ChordType.MINOR);
        aMinorChord.print();

        // G7 Dominantseptakkord
        MusicalNote g = new MusicalNote("G");
        Chord g7 = new Chord(g, ChordType.DOMINANT_7);
        g7.print();

        // 7. Stufenakkorde aus einer Tonleiter
        System.out.println("=== STUFENAKKORDE C-DUR ===\n");
        for (int i = 1; i <= 7; i++) {
            Chord triad = cMajor.getTriadOnDegree(i);
            System.out.println("Stufe " + i + ": " + triad.getSymbol());
        }
        System.out.println();

        // 8. Intervall-Tabelle
        System.out.println("=== ALLE INTERVALLE ===\n");
        Interval.printIntervals();
    }
}
