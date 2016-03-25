package cs3500.music.model;

/**
 * Pitches are sorted in the following order: C C♯ D D♯ E F F♯ G G♯ A A♯ B,
 * and the note after B is again C.
 *
 * When no notes are playing, we say the music has a rest, which is likewise measured in beats.
 */
public enum Pitch {
  C, CSHARP, D, DSHARP, E, F, FSHARP, G, GSHARP, A, ASHARP, B;

  public static Pitch getPitch(int idx) {
    switch (idx) {
      case 0:
        return Pitch.C;
      case 1:
        return Pitch.CSHARP;
      case 2:
        return Pitch.D;
      case 3:
        return Pitch.DSHARP;
      case 4:
        return Pitch.E;
      case 5:
        return Pitch.F;
      case 6:
        return Pitch.FSHARP;
      case 7:
        return Pitch.G;
      case 8:
        return Pitch.GSHARP;
      case 9:
        return Pitch.A;
      case 10:
        return Pitch.ASHARP;
      case 11:
        return Pitch.B;
      default:
        throw new IllegalArgumentException("Invalid Pitch");
    }
  }

  /**
   * Get the name of a pitch
   * @param idx given the index of a pitch in enum
   * @return a String representing the its name
   */
  public static String getPitchName(int idx) {

    switch (idx) {
      case 0: // C
        return "  C";
      case 1: // C#
        return " C#";
      case 2: // D
        return "  D";
      case 3: // D#
        return " D#";
      case 4: // E
        return  "  E";
      case 5: // F
        return  "  F";
      case 6: // F#
        return  " F#";
      case 7: // G
        return "  G";
      case 8: //G#
        return  " G#";
      case 9: // A
        return  "  A";
      case 10: // A#
        return  " A#";
      case 11: // B
        return  "  B";
      default:
        throw new IllegalArgumentException("Invalid Pitch");
    }
  }
}
