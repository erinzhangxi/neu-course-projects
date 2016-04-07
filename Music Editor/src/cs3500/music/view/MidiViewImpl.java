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
   *      <li>{@link Synthesizer#ggetChannels()}</li>
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

  @Override
  public void pause() throws InvalidMidiDataException {
    this.paused = !paused;
    if (paused == true) {
      if (curBeat <= model.getHighBeat()) {
        for (Note n : model.getNotesAtBeat(curBeat)) {
          if (n.getStartBeat() <= curBeat && n.getEndBeat() >= curBeat) {
            MidiMessage end = new ShortMessage(ShortMessage.NOTE_OFF, n.getInstrument(),
                    n.getPitchIdx(), n.getVolume());
            this.receiver.send(end, -1);
          }
        }
      }
    }
  }

  /**
   * @throws InterruptedException
   */
  @Override
  public void display() throws InterruptedException {
    timer = new Timer();
    timer.schedule(new Task(), 0, model.getTempo() / 1000);
  }

  class Task extends TimerTask {
    // the actual action to be performed
    public void run() {

      // play the current beat you're on
      try {
        if (curBeat <= model.getHighBeat()) {
          playNotesAtBeat(curBeat);
        }
      } catch (Exception e) {
      }
      curBeat++;
    }
  }

  /**
   * plays notes starting at a specific time
   *
   * @throws InvalidMidiDataException
   * @throws InterruptedException
   */
  public void playNotesAtBeat(int beat) throws InvalidMidiDataException {
      List<Note> notes = this.model.getNotesAtBeat(beat);

      for (int i = 0; i < notes.size(); i++) {
        Note note = notes.get(i);

        MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, note.getInstrument(),
                note.getPitchIdx(), note.getVolume());
        MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, note.getInstrument(),
                note.getPitchIdx(), note.getVolume());

        this.receiver.send(start, this.synth.getMicrosecondPosition());
        this.receiver.send(stop, this.synth.getMicrosecondPosition() +
                (note.getEndBeat() - note.getStartBeat()) * model.getTempo());
      }
  }
}

