package cs3500.music.view;

import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.MusicEditor;
import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;
import cs3500.music.model.MusicEditorImpl;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;

/**
 * Created by ErinZhang on 3/17/16.
 */
public class CombinedViewImpl implements CombinedView {
  public GuiViewFrame gui;
  public MidiViewImpl midi;
  public MusicEditorImpl model;
  KeyboardHandler keyboard = new KeyboardHandler();
  MouseHandler mouse = new MouseHandler();
  private Timer timer = new Timer();
  private int curBeat;
  public boolean paused;

  public CombinedViewImpl(MusicEditorImpl model) {
    this.gui = new GuiViewFrame(model);
    this.midi = new MidiViewImpl(model);
    this.model = model;
    this.paused = false;
  }

  public GuiViewFrame getGui() {
    return this.gui;
  }

  public MidiViewImpl getMidi() {
    return this.midi;
  }
  @Override
  public void display() throws InterruptedException {
    gui.display();
    this.initTimer();
  }

  @Override
  public void pause() {
    try {
      this.midi.pause();
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void changePauseValue() {
    this.paused = !this.paused;
  }

  @Override
  public void addNoteFromXandY(int x, int y, int duration) {
    this.gui.addNoteFromXandY(x, y, duration);
  }

  @Override
  public void repaint() {
    this.gui.repaint();
  }

  @Override
  public void removeNoteFromXandY(int x, int y) {
    this.gui.removeNoteFromXandY(x, y);
  }

  @Override
  public Note getNote(int x1, int y1) {
    return this.gui.getNote(x1, y1);
  }

  @Override
  public void addNote(int x2, int y2, Note tempNote) {
    this.gui.addNote(x2, y2, tempNote);
  }

  @Override
  public ConcreteGuiViewPanel getPanel() {
    return this.gui.getPanel();
  }

  @Override
  public void end() {
    this.gui.end();
  }

  @Override
  public void home() {
    this.gui.home();
  }

  @Override
  public void scrollUp() {
    this.gui.scrollUp();
  }

  @Override
  public void scrollDown() {
    this.gui.scrollDown();
  }

  @Override
  public void scrollLeft() {
    this.gui.scrollLeft();
  }

  @Override
  public void scrollRight() {
    this.gui.scrollRight();
  }

  @Override
  public void addKeyboardListener(KeyboardHandler key) {
    this.keyboard = key;
    this.gui.addKeyboardListener(keyboard);
  }

  public void addMouseListener(MouseHandler mouse) {
    this.mouse = mouse;
    this.gui.addMouseListener(mouse);
  }

  @Override
  public void resetFocus() {
    gui.resetFocus();
  }

  /**
   * initialize the timer by setting the task to be scheduled and
   * the time in milliseconds between successive task executions.
   */
  public void initTimer() {
    timer = new Timer();
    timer.schedule(new Task(), 0, model.getTempo() / 1000);
    // this.receiver.close(); // Only call this once you're done playing *all* notes
  }

  class Task extends TimerTask {
    // the actual action to be performed
    public void run() {
      if (paused == false) {
        // play the current beat you're on
        try {
          if (curBeat <= model.getHighBeat()) {
            midi.playNotesAtBeat(curBeat);
          }
        } catch (Exception e) {

        }

        gui.getPanel().getLine().moveLine();
        gui.repaint();
        gui.nextWindowIfEnd();
        // increment the current beat
        curBeat++;
      }
      else {
        pause();
      }
    }

  }

}
