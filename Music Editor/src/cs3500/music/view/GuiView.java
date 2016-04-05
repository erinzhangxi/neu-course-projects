package cs3500.music.view;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;

/**
 * Created by ErinZhang on 3/17/16.
 */
public interface GuiView extends View {

  @Override
  void pause();
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
}
