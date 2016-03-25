package cs3500.music.model;

/**
 * A note includes a pitch and a duration. A duration is measured in beats, typically three
 * or four beats is called a measure.
 */
public interface Note {
  /**
   * Get the pitch index of this note.
   * Pitch index is calculated as octave * 12 + the index of a pitch in an octave
   *
   * @return the location of a pitch within the range of [0, 120]
   */
   int getPitchIdx();

  /**
   * get the first beat of a note
   *
   * @return an integer representing the first beat of a note
   */
   int getStartBeat();

  /**
   * get the end beat of a note
   *
   * @return an integer representing the end beat of a note
   */
   int getEndBeat();

  /**
   * Get the instrument of a note.
   *
   * @return an integer representing the instrument
   */
   int getInstrument();

  /**
   * Get the volume of a note.
   *
   * @return an integer representing the volume
   */
   int getVolume();

  /**
   * Update the ending beat of a note
   *
   * @param len the change of the ending beat
   *            a negative number meaning reduces the length
   *            a positive number meaning increase the length
   */
   void updateEnd(int len);

  /**
   * Update the starting beat of a note
   *
   * @param len the change of the starting beat
   *            a negative number meaning reduces the length
   *            a positive number meaning increase the length
   */
   void updateStart(int len);

  /**
   * Given a length, change the location of a note(both the starting beat and the ending beat)
   * @param len the changing length of a note
   */
   void updateNote(int len);

  /**
   * Update the pitch index
   * @param len the amount of change
   */
   void updatePitchIdx(int len);

}

