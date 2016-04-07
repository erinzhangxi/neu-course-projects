package cs3500.music.view;

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

  void end();
  void home();
  void scrollUp();
  void scrollDown();
  void scrollLeft();
  void scrollRight();
  void addKeyboardListener(KeyboardHandler key);
  void addMouseListener(MouseHandler mouse);
  void resetFocus();

  void changePauseValue();
  void addNoteFromXandY(int x, int y, int duration);

  void repaint();

  void removeNoteFromXandY(int x, int y);

  Note getNote(int x1, int y1);

  void addNote(int x2, int y2, Note tempNote);

  ConcreteGuiViewPanel getPanel();
}
