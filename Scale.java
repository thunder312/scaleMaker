import java.util.ArrayList;
import java.util.List;

public class Scale {

    String symbol;

    MusicalNote fundamentalTone;
    Interval[] intervals;
    String modus;
    List<MusicalNote> scale = new ArrayList<MusicalNote>();

    Scale(MusicalNote fundamentalTone, String modus) {
        this.fundamentalTone = fundamentalTone;
        if(Modi.modus.containsKey(modus.toLowerCase())) {
            this.modus = modus;
        } else {
            System.err.println("Unknown modus: " + modus);
        };
        this.intervals = Modi.modus.get(modus.toLowerCase());
        this.symbol = MusicalNote.octaveToSymbols(fundamentalTone.octave);
        //System.out.println("Fundamental tone: " + fundamentalTone.letter + "\nModus: " + modus + "\nIntervals: " + intervals.length);
        createScale();
    }

    private void createScale() {
        String[] tonnamen = getNoteLetters(this.fundamentalTone.letter, this.modus, this.fundamentalTone.octave);
        scale.add(this.fundamentalTone);
         for(int i = 1; i < this.intervals.length; i++) {
            scale.add(new MusicalNote(tonnamen[i], this.fundamentalTone.frequency * this.intervals[i].interval, this.fundamentalTone.octave));
            //System.out.println(i + " Tonname:" + tonnamen[i] + "\nFrequenz: " + this.fundamentalTone.frequency + " * " + this.intervals[i].interval + " = " + this.fundamentalTone.frequency * this.intervals[i].interval);
        }
    }

    private String[] getNoteLetters(String letter, String modus, int octave) {
        String[] result = {};
        switch(modus) {
            case "Dur":
                switch(letter) {
                    case "C":
                        result = addSymbols(new String[]{"C", "D", "E", "F", "G", "A", "H", "C"});
                        break;
                }

            case "Moll":
                switch(letter) {}
        }
        return result;
    }

    private String[] addSymbols(String[] data) {
        for(int i = 0; i < data.length; i++) {
            if(i == data.length-1){
                data[i]+=this.symbol;
            }
        }
        return data;
    }

    protected void print() {
        System.out.println(fundamentalTone.letter + "-" + modus);
        for(int i = 0; i< scale.size(); i++) {
            scale.get(i).print();
        }
        System.out.println("");
    }
}

    /* weitere Modi
        // Ionisch (Dur)
        // Dorisch
        // Phrygisch
        // Lydisch
        // Mixolydisch
        // Äolisch (Moll)
        // Lokrisch
    */