package cs3500.music.view;

import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.Playable;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class for MIDI playback
 */
public class MidiViewImpl implements MidiView, IView {
  private Synthesizer synth = null;
  private Receiver receiver = null;
  IMusicEditorModel model;

  public MidiViewImpl(IMusicEditorModel model) {
    this.model = model;
    try {
      this.synth = MidiSystem.getSynthesizer();
      this.receiver = synth.getReceiver();
      this.synth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  public MidiViewImpl(IMusicEditorModel model, Synthesizer synth) { // synth will be Mock
    this.model = model;
    this.synth = synth;
    try {
      this.receiver = synth.getReceiver();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  /**
   * Relevant classes and methods from the javax.sound.midi library:
   * <ul>
   *  <li>{@link MidiSystem#getSynthesizer()}</li>
   *  <li>{@link Synthesizer}
   *    <ul>
   *      <li>{@link Synthesizer#open()}</li>
   *      <li>{@link Synthesizer#getReceiver()}</li>
   *      <li>{@link Synthesizer#getChannels()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link Receiver}
   *    <ul>
   *      <li>{@link Receiver#send(MidiMessage, long)}</li>
   *      <li>{@link Receiver#close()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link MidiMessage}</li>
   *  <li>{@link ShortMessage}</li>
   *  <li>{@link MidiChannel}
   *    <ul>
   *      <li>{@link MidiChannel#getProgram()}</li>
   *      <li>{@link MidiChannel#programChange(int)}</li>
   *    </ul>
   *  </li>
   * </ul>
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
   *   https://en.wikipedia.org/wiki/General_MIDI
   *   </a>
   */

  /**
   * plays the given playable
   *
   * @param n the playable to play
   * @throws InvalidMidiDataException when note is invalid
   */
  public void playNote(Playable n) throws InvalidMidiDataException {
    Instrument[] instruments = this.synth.getDefaultSoundbank().getInstruments();
    this.synth.getChannels()[0].programChange
      (instruments[n.getInstrument()].getPatch().getProgram());
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 0, n.toMidi(), n.getVolume());
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 0, n.toMidi(), n.getVolume());
    this.receiver.send(start, n.getBegBeat() * this.model.getTempo());
    this.receiver.send(stop, (n.getBegBeat() +
      (n.getBegBeat() + n.getDuration())) * this.model.getTempo());
  }

  /**
   * Plays all playables in a Song
   */
  public void init() {
    for (Playable n : this.model.getNotes()) {
      try {
        this.playNote(n);
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
    }
    try {
      Thread.sleep((int) (this.model.getTempo() * .001 * this.model.lengthSong()));
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
    this.receiver.close();
  }

  /**
   * Plays all playables in a Song
   * For testing - no sleep
   */
  public void playAll() {
    for (Playable n : this.model.getNotes()) {
      try {
        this.playNote(n);
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
    }
    this.receiver.close();
  }

  /**
   * Plays the notes at the given beat
   * @param beat the beat to play the notes at
   * @throws InvalidMidiDataException midi exception
   */
  public void playAtBeat(int beat) throws InvalidMidiDataException {
    List<Playable> playables = new ArrayList<>();

    for (Playable p : this.model.getNotes()) {
      if (p.getBegBeat() == beat) {
        playables.add(p);
      }
    }
    Map<Integer, List<Playable>> map = new HashMap<>();
    map.put(beat, playables);

    if (map.containsKey(beat)) {
      for (Playable p : playables) {
        this.playNote(p);
      }
    }
    else {

    }
  }

  /**
   * Tells if this MidiViewImpl is equal to that MidiViewImpl
   * @param o a given object to compare with this MidiViewImpl for equality
   * @return boolean true if they are the same, false if they are not
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof MidiViewImpl)) {
      return false;
    }
    else {
      MidiViewImpl that = (MidiViewImpl)o;
      return this.model.equals(that.model);
    }
  }
}
