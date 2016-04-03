package cs3500.music.view;

import cs3500.music.controller.KeyboardHandler;

/**
 * Created by ErinZhang on 3/17/16.
 */
public interface GuiView extends View {

  void pause();
  void end();
  void home();
  void scrollUp();
  void scrollDown();
  void scrollLeft();
  void scrollRight();

  void setKeyboardListener(KeyboardHandler key);
}
