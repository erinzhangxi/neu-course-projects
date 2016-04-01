package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 * Created by ErinZhang on 3/31/16.
 */
public class KeyHandler implements KeyListener {
  Map<Integer, Runnable> keyPressed;
  Map<Integer, Runnable> keyReleased;
  Map<Integer, Runnable> keyTyped;

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
