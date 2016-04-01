package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ErinZhang on 3/31/16.
 */
public class KeyboardHandler implements KeyListener {
  Map<Integer, Runnable> keyTyped;
  Map<Integer, Runnable> keyPressed;
  Map<Integer, Runnable> keyReleased;

  public KeyboardHandler() {
    this.keyTyped = new HashMap<>();
    this.keyPressed = new HashMap<>();
    this.keyReleased = new HashMap<>();
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {

  }

  /**
   * add a key to the map of key typed commands
   * @param e key event being added to the map
   * @param r the runnable corresponding to the key event
   */
  public void addKeyTyped(KeyEvent e, Runnable r) {}

  /**
   * add a key to the map of key released commands
   * @param e key event being added to the map
   * @param r the runnable corresponding to the key event
   */
  public void addKeyReleased(KeyEvent e, Runnable r) {}

  /**
   * add a key to the map of key pressed commands
   * @param e key event being added to the map
   * @param r the runnable corresponding to the key event
   */
  public void addKeyPressed(KeyEvent e, Runnable r) {}

}
