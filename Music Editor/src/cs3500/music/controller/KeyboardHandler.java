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
    int keyCode = e.getKeyCode();
    if (keyTyped.containsKey(keyCode)) {
      keyTyped.get(keyCode).run();
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();
    if (keyPressed.containsKey(keyCode)) {
      keyPressed.get(keyCode).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int keyCode = e.getKeyCode();
    if (keyReleased.containsKey(keyCode)) {
      keyReleased.get(keyCode).run();
    }
  }

  /**
   * add a key to the map of key typed commands
   * @param key key event being added to the map
   * @param r the runnable corresponding to the key event
   */
  public void addKeyTyped(int key, Runnable r) {
    this.keyTyped.put(key, r);
  }

  /**
   * add a key to the map of key released commands
   * @param key key event being added to the map
   * @param r the runnable corresponding to the key event
   */
  public void addKeyReleased(int key, Runnable r) {
    this.keyReleased.put(key, r);
  }

  /**
   * add a key to the map of key pressed commands
   * @param key key event being added to the map
   * @param r the runnable corresponding to the key event
   */
  public void addKeyPressed(int key, Runnable r) {
    this.keyPressed.put(key, r);
  }

}
