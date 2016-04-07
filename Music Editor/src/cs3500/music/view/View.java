package cs3500.music.view;

import javax.sound.midi.InvalidMidiDataException;

/**
 * Created by ErinZhang on 3/17/16.
 */
public interface View {
  /**
   * Display a view of a music editor starting at a given beat
   */
  void display() throws InterruptedException;

  void pause() throws InvalidMidiDataException;
}
