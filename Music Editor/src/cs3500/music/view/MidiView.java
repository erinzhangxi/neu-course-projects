package cs3500.music.view;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;

/**
 * Created by ErinZhang on 3/15/16.
 */

public interface MidiView extends View {
  @Override
  void pause();
  @Override
  void display() throws InterruptedException;
}
