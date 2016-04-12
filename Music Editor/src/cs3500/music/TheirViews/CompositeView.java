package cs3500.music.view;

import cs3500.music.model.IMusicEditorModel;

import javax.sound.midi.InvalidMidiDataException;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by demifilippou on 4/1/16.
 * Represents a composite view
 */
public class CompositeView implements GuiView {
  GuiViewFrame gui;
  MidiViewImpl midi;
  boolean isPaused;
  Timer timer;
  IMusicEditorModel model;


  public CompositeView(GuiViewFrame gui, MidiViewImpl midi, IMusicEditorModel model) {
    this.gui = gui;
    this.midi = midi;
    this.model = model;
  }

  /**
   * Gets the model
   * @return the model
   */
  public IMusicEditorModel getModel(){
    return this.model;
  }

  /**
   * Gets this value for isPaused
   * @return the value of isPaused
   */
  public boolean getIsPaused(){
    return this.isPaused;
  }

  /**
   * Adds a MouseListener to this GuiView
   *
   * @param m the listener to add
   */
  @Override
  public void addMouseListener(MouseListener m) {
    this.gui.addMouseListener(m);
  }

  /**
   * Removes a MouseListener to this GuiView
   *
   * @param m the listener to remove
   */
  @Override
  public void removeMouseListener(MouseListener m) {
    this.gui.removeMouseListener(m);
  }

  /**
   * Adds a KeyListener to this GuiView
   *
   * @param l the listener to add
   */
  @Override
  public void addKeyListener(KeyListener l) {
    this.gui.addKeyListener(l);
  }

  /**
   * Removess a KeyListener to this GuiView
   *
   * @param l the listener to remove
   */
  @Override
  public void removeKeyListener(KeyListener l) {
    this.gui.removeKeyListener(l);
  }

  /**
   * Goes to the end of the view
   */
  @Override
  public void goToEnd() {
    this.gui.goToEnd();
  }

  /**
   * Goes to the beginning of the view
   */
  @Override
  public void goToBeg() {
    this.gui.goToBeg();
  }

  /**
   * Scrolls the view up
   */
  @Override
  public void scrollUp() {
    this.gui.scrollUp();
  }

  /**
   * Scrolls the view down
   */
  @Override
  public void scrollDown() {
    this.gui.scrollDown();
  }

  /**
   * Scrolls the view left
   */
  @Override
  public void scrollLeft() {
    this.gui.scrollLeft();
  }

  /**
   * Scrolls the view right
   */
  @Override
  public void scrollRight() {
    this.gui.scrollRight();
  }

  /**
   * Repaints the gui
   */
  @Override
  public void repaint2() {
    this.gui.repaint2();
  }

  /**
   * Updates this GuiView
   */
  @Override
  public void update() {
    this.gui.update();
    this.gui.repaint2();
  }

  /**
   * Initiates the view
   */
  @Override
  public void init() {
    this.gui.init();
    this.initTimer();
  }

  /**
   * Pauses the song
   */
  @Override
  public void pause() {
    this.isPaused = true;
  }

  /**
   * Resumes the song
   */
  @Override
  public void resume() {
    this.isPaused = false;
  }

  /**
   * Initiates the Timer
   */
  public void initTimer() {
    this.timer = new Timer();
    this.timer.schedule(new Task(), 0, this.midi.model.getTempo() / 1000);
  }


  /**
   * A class representing a timer Task
   */
  class Task extends TimerTask {
    /**
     * Runs the timer task
     */
    @Override
    public void run() {
      if (!isPaused) {
        try {
          midi.playAtBeat(GuiViewFrame.currBeat);
        } catch (InvalidMidiDataException e) {
          e.printStackTrace();
        }
        gui.update();
        gui.repaint();
        if(GuiViewFrame.currBeat < midi.model.lengthSong()) {
          GuiViewFrame.currBeat++;
        }
        else if(GuiViewFrame.currBeat == midi.model.lengthSong()) {
          pause();
        }
      }
      else {
        pause();
      }
    }
  }
}
