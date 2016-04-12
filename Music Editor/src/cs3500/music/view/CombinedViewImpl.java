package cs3500.music.view;

import java.awt.*;
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

  public void changePauseValue() {
    this.paused = !this.paused;
  }

  public void addNoteFromXandY(int x, int y, int duration) {
    this.gui.addNoteFromXandY(x, y, duration);
  }

  public void repaint() {
    this.gui.repaint();
  }

  public void removeNoteFromXandY(int x, int y) {
    this.gui.removeNoteFromXandY(x, y);
  }

  public Note getNote(int x1, int y1) {
    return this.gui.getNote(x1, y1);
  }

  public void addNote(int x2, int y2, Note tempNote) {
    this.gui.addNote(x2, y2, tempNote);
  }

  public ConcreteGuiViewPanel getPanel() {
    return this.gui.getPanel();
  }

  public void end() {
    this.gui.end();
  }

  public void home() {
    this.gui.home();
  }

  public void scrollUp() {
    this.gui.scrollUp();
  }

  public void scrollDown() {
    this.gui.scrollDown();
  }

  public void scrollLeft() {
    this.gui.scrollLeft();
  }

  public void scrollRight() {
    this.gui.scrollRight();
  }

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
  }

  /**
   * Task executing midi and gui view operation at the current beat
   */
  class Task extends TimerTask {
    public void run() {
      if (paused == false) {
        try {
          if (curBeat <= model.getHighBeat()) {
            midi.playNotesAtBeat(curBeat);
          }
          else {
            paused = true;
          }
        } catch (Exception e) {

        }
        gui.getPanel().line.moveLine(curBeat);
        gui.repaint();
        gui.nextWindowIfEnd();
        curBeat++;
      }
      else {
        pause();
      }
    }
  }
}
