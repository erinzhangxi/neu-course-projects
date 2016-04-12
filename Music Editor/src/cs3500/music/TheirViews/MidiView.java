package cs3500.music.view;

import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.model.Playable;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.ShortMessage;

/**
 * Created by demifilippou on 3/15/16.
 * An interface representing MidiView
 */
public interface MidiView {

  /**
   * plays the given playable
   * @param n the playable to play
   * @throws InvalidMidiDataException when note is invalid
   */
  public void playNote(Playable n) throws InvalidMidiDataException;

  /**
   * Plays all playables in a Song
   */
  public void init();

  /**
   * Plays all playables in a Song
   * For testing - no sleep
   */
  public void playAll();

}
