package cs3500.music.model;

/**
 * Created by ErinZhang on 3/21/16.
 */
public class NoteImpl implements Note {

  /**
   * representing the position of the note, within range (0, 120)
   */
  private int pitchIdx;

  /**
   * the beginning time of a note
   */
  private int startBeat;

  /**
   * the end time of a note
   */
  private int endBeat;

  /**
   * the instrument the note is played in
   */
  private int instrument;

  /**
   * volume of the note
   */
  private int volume;

  /**
   * @param pitch a tone can be one of the twelve pitches: C C♯ D D♯ E F F♯ G G♯ A A♯ B
   * @param octave contains twelve distinct pitches.
   *               The thirteenth pitch is the “same” pitch in a different octave,
   *               must be between 0 and 10
   * @param start within the range of (0, 120)
   * @param end within the range of (0, 120)
   * @param instrument an integer representing the type of instrument within the range
   *                   of (0, 126)
   * @param volume integer representing the volume
   */
  public NoteImpl(Pitch pitch, int octave, int start, int end, int instrument, int volume) {  // TODO change to private

    if (octave < -1 || octave > 10) {
      throw new IllegalArgumentException("Octave must be between 0 and 10.");
    }

    this.pitchIdx = octave * 12 + pitch.ordinal();

    if (start < 0 || end < 0) {
      throw new IllegalArgumentException("Start and end beat must be between 0 and 120.");
    }
    this.startBeat = start;
    this.endBeat = end;

    if (instrument > 127 || instrument < 0) {
      throw new IllegalArgumentException("Instrument must be within range 0 to 126.");
    }
    this.instrument = instrument;

    if (volume < 0) {
      throw new IllegalArgumentException("Volume must be above 0.");
    }
    this.volume = volume;
  }

  /**
   * constructor for creating a deep copy of note
   * @param n a note
   */
  public NoteImpl(Note n) {
    this.pitchIdx = n.getPitchIdx();
    this.startBeat = n.getStartBeat();
    this.endBeat = n.getEndBeat();
    this.instrument = n.getInstrument();
    this.volume = n.getVolume();

  }

  @Override
  public int getPitchIdx() {
    return this.pitchIdx;
  }

  @Override
  public int getStartBeat() {
    return this.startBeat;
  }

  @Override
  public int getEndBeat() {
    return this.endBeat;
  }

  @Override
  public int getInstrument() {
    return this.instrument;
  }

  @Override
  public int getVolume() {return this.volume;}

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    } else if (!(that instanceof Note)) {
      return false;
    }
    return ((Note) that).getStartBeat() == this.startBeat &&
            ((Note) that).getEndBeat() == this.endBeat &&
            ((Note) that).getPitchIdx() == this.pitchIdx &&
            ((Note) that).getVolume() == this.volume &&
            ((Note) that).getInstrument() == this.instrument;
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(this.startBeat + this.endBeat + this.pitchIdx + this.volume
            + this.instrument);
  }

  @Override
  public void updateEnd(int len) {
    this.endBeat += len;
  }

  @Override
  public void updateStart(int len) {
    this.startBeat += len;
  }

  @Override
  public void updateNote(int len) {
    updateStart(len);
    updateEnd(len);
  }

  @Override
  public void updatePitchIdx(int len) {
    this.pitchIdx += len;
  }
}
