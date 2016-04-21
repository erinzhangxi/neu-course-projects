package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;
import cs3500.music.model.Note;

/**
 * Created by ErinZhang on 3/17/16.
 */
public interface GuiView extends View {

  @Override
  void pause() throws InvalidMidiDataException;

  @Override
  void display() throws InterruptedException;

  /**
   * Go to the end of the grid
   */
  void end();

  /**
   * Go to the beginning of the grid
   */
  void home();

  /**
   * scroll up by pressing the arrow key
   */
  void scrollUp();
  /**
   * scroll down by pressing the arrow key
   */
  void scrollDown();
  /**
   * scroll left by pressing the arrow key
   */
  void scrollLeft();
  /**
   * scroll right by pressing the arrow key
   */
  void scrollRight();

  /**
   * add the keylistener to the view
   * @param key
   */
  void addKeyboardListener(KeyListener key);

  /**
   * Add the mouselistener to the view.
   *
   * @param mouse
   */
  void addMouseListener(MouseListener mouse);

  /**
   * Reset the focus of frame.
   */
  void resetFocus();

  /**
   * Change the pause status.
   */
  void changePauseValue();

  /**
   * Add a note given the note's x and y coordinate.
   *
   * @param x x coordinate of a mouse event
   * @param y y coordinate of a mouse event
   */
  void addNoteFromXandY(int x, int y, int duration);

  /**
   * repaint the panel
   */
  void repaint();

  /**
   * Remove a note given the note's x and y coordinate
   *
   * @param x x coordinate of a mouse event
   * @param y y coordinate of a mouse event
   */
  void removeNoteFromXandY(int x, int y);

  /**
   * Gets a note form the model with the coordinates from click event
   *
   * @param x1 x coordinate of the note
   * @param y1  x coordinate of the note
   * @return the corresponding note
   */
  Note getNote(int x1, int y1);

  /**
   * Adds a note to the view when using move operation.
   *
   * @param x2 the x coordinate of the new note to be added on
   * @param y2 the y coordinate of the new note to be added on
   * @param tempNote the note to be added
   */
  void addNote(int x2, int y2, Note tempNote);

  /**
   *
   * @return panel
   */
  ConcreteGuiViewPanel getPanel();
}
