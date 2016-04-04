package cs3500.music.view;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import cs3500.music.model.MusicEditorImpl;
import cs3500.music.model.Note;

/**
 * A skeleton for MIDI playback
 */
public class MidiViewImpl implements MidiView {
  private Synthesizer synth;
  private Receiver receiver;
  private MusicEditorImpl model;
  private MockRec mockRec;
  private MockSyn mockSyn;
  private Appendable log;
  private Timer timer = new Timer();
  private boolean paused = false;
  private int curBeat;

  /**
   * constructor
   * @param model a music editor implementation
   */
//  public MidiViewImpl(MusicEditorImpl model) {
//    try {
//      tempSyn = MidiSystem.getSynthesizer();
//      tempSyn.open();
//      this.model = model;
//    } catch (MidiUnavailableException e) {
//      e.printStackTrace();
//    }
//    this.synth = tempSyn;
//  }

    public MidiViewImpl(MusicEditorImpl model) {
    try {
      synth = MidiSystem.getSynthesizer();
      receiver = synth.getReceiver();
      synth.open();
      this.model = model;
      this.curBeat = model.getLowBeat();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  /**
   * convenience constructor for testing a mock object
   *
   * @param model a music editor model
   * @param out   string output
   */
  public MidiViewImpl(MusicEditorImpl model, Appendable out) {
    this.model = model;
    this.mockSyn = new MockSyn();
    this.mockRec = new MockRec();
    this.log = out;
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

  // play without using Receiver
//  @Override
//  public void display() throws InterruptedException {
//    MidiChannel[] channels = synth.getChannels();
//
//    for (int i = 0; i <= model.getHighBeat(); i++) {
//      for (Note n : model.getAll()) {
//        if (i == n.getStartBeat()) {
//          channels[n.getInstrument()].noteOn(n.getPitchIdx(), n.getVolume());
//        }
//        else if (i == n.getEndBeat()) {
//          channels[n.getInstrument()].noteOff(n.getPitchIdx(), n.getVolume());
//        }
//      }
//      Thread.sleep(this.model.getTempo() / 1000);
//    }
//  }

  /**
   * mock the output of an actual MidiDevice
   *
   * log that records every note's information
   */
  public void mockOutput() throws InvalidMidiDataException, IOException {

    MidiMessage start;
    MidiMessage end;

    for (int i = 0; i < model.getHighBeat(); i++) {
      for (Note n : model.getAll()) {
        if (i == n.getStartBeat()) {
          start = new ShortMessage(ShortMessage.NOTE_ON, n.getInstrument(), n.getPitchIdx(),
                  n.getVolume());
          this.mockRec.send(start,
                  this.mockSyn.getMicrosecondPosition() + n.getStartBeat() * model.getTempo());
        }
        if (i == n.getEndBeat()) {
          end = new ShortMessage(ShortMessage.NOTE_OFF, n.getInstrument(), n.getPitchIdx(),
                  n.getVolume());
          this.mockRec.send(end,
                  this.mockSyn.getMicrosecondPosition() + n.getEndBeat() * model.getTempo());
        }
      }
    }
    log.append(mockRec.result.toString());
  }

  /**
   * plays notes starting at a specific time
   *
   * @throws InvalidMidiDataException
   * @throws InterruptedException
   */
//  public void playNotes() throws InvalidMidiDataException, InterruptedException {
//
//   // for (int i = model.getLowBeat(); i < model.getHighBeat(); i++) { TODO
//    for (int i = this.curBeat; i < model.getHighBeat(); i++) {
//      for (Note note : model.getAll()) {
//
//        MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, note.getInstrument(),
//                note.getPitchIdx(), note.getVolume());
//        MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, note.getInstrument(),
//                note.getPitchIdx(), note.getVolume());
//
//        if (i == note.getStartBeat()) {
//          this.receiver.send(start, -1);
//
//        }
//        else if (i == note.getEndBeat()) {
//          this.receiver.send(stop, -1);
//        }
//      }
//     // Thread.sleep(model.getTempo() / 1000);
//    }
//  }

  public void playNotesAtBeat(int curBeat) throws InvalidMidiDataException, InterruptedException {
    List<Note> notes = this.model.getNotesAtBeat(curBeat);
    for (int i = 0; i < notes.size(); i++) {
      Note note = notes.get(i);

      MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, note.getInstrument(),
              note.getPitchIdx(), note.getVolume());
      MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, note.getInstrument(),
              note.getPitchIdx(), note.getVolume());

      if (i == note.getStartBeat()) {
        this.receiver.send(start, -1);

      }
      else if (i == note.getEndBeat()) {
        this.receiver.send(stop, -1);
      }
    }
  }

  // TODO
  @Override
  public void pause() {
    this.paused = !paused;
    if (paused == true) {

    }
    else {

    }
  }

  /**
   * @throws InterruptedException
   */
  @Override
  public void display() throws InterruptedException {
    this.initTimer();
    }

  /**
   * initialize the timer by setting the task to be scheduled and
   * the time in milliseconds between successive task executions.
   */
  public void initTimer() {
    timer = new Timer();
    timer.schedule(new Task(), model.getTempo() / 1000);
    // this.receiver.close(); // Only call this once you're done playing *all* notes
  }

  class Task extends TimerTask {
    // the actual action to be performed
    public void run() {
      if (!paused) {
        // play the current beat you're on
        try {
          playNotesAtBeat(curBeat);
        } catch (InvalidMidiDataException e) {
          e.printStackTrace();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        // increment the current beat
        curBeat++;
      }
    }
  }
}

