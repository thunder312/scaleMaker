# ScaleMaker

A Java library for generating musical scales, chords, and intervals with accurate frequency calculations based on just intonation.

## Features

- **14 Scale Types**: Major, Minor, all 7 church modes (Dorian, Phrygian, Lydian, etc.), Harmonic/Melodic Minor, Pentatonic, Blues
- **12 Chord Types**: Triads (Major, Minor, Diminished, Augmented, Sus2, Sus4) and Seventh chords (Maj7, Min7, Dom7, Dim7, m7b5, MinMaj7)
- **28 Intervals**: All common intervals with precise frequency ratios based on just intonation and Pythagorean tuning
- **Automatic diatonic chord generation**: Get all chords of a scale with correct chord qualities
- **Frequency calculations**: Based on A = 440 Hz concert pitch

## Installation

Download `scalemaker.jar` from the `bin` folder and add it to your classpath:

```bash
javac -cp scalemaker.jar YourProgram.java
java -cp scalemaker.jar;. YourProgram
```

## Quick Start

```java
// Create scales
Scale cMajor = ScaleMaker.majorScale("C");
Scale aMinor = ScaleMaker.minorScale("A");
Scale dDorian = ScaleMaker.scale("D", ScaleType.DORIAN);

// Create chords
Chord cMaj = ScaleMaker.majorChord("C");
Chord g7 = ScaleMaker.chord("G", ChordType.DOMINANT_7);

// Get scale notes and frequencies
String[] notes = cMajor.getNoteNames();      // ["C", "D", "E", "F", "G", "A", "H", "C"]
double[] freqs = cMajor.getFrequencies();    // [264.0, 293.33, 330.0, ...]

// Get diatonic chords of a scale
String[] chords = ScaleMaker.getDiatonicChordSymbols(cMajor);
// ["C", "Dm", "Em", "F", "G", "Am", "Hdim"]

// Calculate frequencies
double freq = ScaleMaker.applyInterval(264.0, IntervalType.PERFECT_FIFTH);  // 396.0 Hz
```

## API Reference

### ScaleMaker (Main Entry Point)

| Method | Description |
|--------|-------------|
| `note(String letter)` | Create a note with automatic frequency lookup |
| `note(String letter, double frequency)` | Create a note with specific frequency |
| `scale(String root, ScaleType type)` | Create a scale |
| `majorScale(String root)` | Shorthand for major scale |
| `minorScale(String root)` | Shorthand for natural minor scale |
| `chord(String root, ChordType type)` | Create a chord |
| `majorChord(String root)` | Shorthand for major triad |
| `minorChord(String root)` | Shorthand for minor triad |
| `getDiatonicChords(Scale scale)` | Get all diatonic triads of a scale |
| `getDiatonicChordSymbols(Scale scale)` | Get chord symbols as string array |
| `calculateFrequency(int semitonesFromA)` | Calculate frequency from A = 440 Hz |
| `applyInterval(double freq, IntervalType interval)` | Apply interval to frequency |

### Scale

| Method | Description |
|--------|-------------|
| `getNotes()` | Get all notes as List<MusicalNote> |
| `getNoteNames()` | Get note names as String[] |
| `getFrequencies()` | Get frequencies as double[] |
| `getNote(int degree)` | Get note at scale degree (1-based) |
| `getTriadOnDegree(int degree)` | Get diatonic triad on scale degree |
| `getFundamentalTone()` | Get the root note |
| `getScaleType()` | Get the scale type |

### Chord

| Method | Description |
|--------|-------------|
| `getNotes()` | Get all notes as List<MusicalNote> |
| `getNoteNames()` | Get note names as String[] |
| `getFrequencies()` | Get frequencies as double[] |
| `getSymbol()` | Get chord symbol (e.g., "Am", "G7") |
| `getRoot()` | Get the root note |
| `getType()` | Get the chord type |
| `isTriad()` | Check if chord has 3 notes |
| `isSeventhChord()` | Check if chord has 4 notes |

### Available Types

#### ScaleType
```
IONIAN / MAJOR          DORIAN              PHRYGIAN
LYDIAN                  MIXOLYDIAN          AEOLIAN / MINOR
LOCRIAN                 HARMONIC_MINOR      MELODIC_MINOR
PENTATONIC_MAJOR        PENTATONIC_MINOR    BLUES
```

#### ChordType
```
MAJOR          MINOR           DIMINISHED      AUGMENTED
SUS2           SUS4            MAJOR_7         MINOR_7
DOMINANT_7     DIMINISHED_7    HALF_DIMINISHED_7    MINOR_MAJOR_7
```

#### IntervalType
```
UNISON                  MINOR_SECOND        MAJOR_SECOND
MINOR_THIRD             MAJOR_THIRD         PERFECT_FOURTH
AUGMENTED_FOURTH        DIMINISHED_FIFTH    PERFECT_FIFTH
AUGMENTED_FIFTH         MINOR_SIXTH         MAJOR_SIXTH
MINOR_SEVENTH           MAJOR_SEVENTH       OCTAVE
... and more variants (Pythagorean, just intonation)
```

## Note Names

This library uses German note naming convention:
- **H** instead of B
- **B** instead of Bb
- Sharp notes: Cis, Dis, Fis, Gis, Ais
- Flat notes: Des, Es, Ges, As, B

## Tuning

Frequencies are calculated using **just intonation** ratios for pure intervals:

| Interval | Ratio | Example (from C = 264 Hz) |
|----------|-------|---------------------------|
| Unison | 1:1 | 264.00 Hz |
| Major Second | 10:9 | 293.33 Hz |
| Major Third | 5:4 | 330.00 Hz |
| Perfect Fourth | 4:3 | 352.00 Hz |
| Perfect Fifth | 3:2 | 396.00 Hz |
| Major Sixth | 5:3 | 440.00 Hz |
| Major Seventh | 15:8 | 495.00 Hz |
| Octave | 2:1 | 528.00 Hz |

## Building from Source

```bash
# Compile
javac -encoding UTF-8 -d bin *.java

# Create JAR
jar cvf bin/scalemaker.jar -C bin .

# Generate JavaDoc
javadoc -encoding UTF-8 -charset UTF-8 -d doc -author -version *.java

# Run demo
java -cp bin Main
```

## Requirements

- Java 11 or higher

## License

MIT License

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.
