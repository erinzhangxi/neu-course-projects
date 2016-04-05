package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ErinZhang on 3/31/16.
 */
public class KeyboardHandler implements KeyListener {
  Map<Integer, Runnable> keyTypedMap;
  Map<Integer, Runnable> keyPressedMap;
  Map<Integer, Runnable> keyReleasedMap;

  public KeyboardHandler() {
    this.keyTypedMap = new HashMap<>();
    this.keyPressedMap = new HashMap<>();
    this.keyReleasedMap = new HashMap<>();
  }

//  public void setKeyTypedMap(Map<Integer, Runnable> map) {
//    keyTypedMap = map;
//  }
//
//  public void setKeyPressedMap(Map<Integer, Runnable> map) {
//    keyPressedMap = map;
//  }
//  public void setKeyReleasedMap(Map<Integer, Runnable> map) {
//    keyReleasedMap = map;
//  }

  @Override
  public void keyTyped(KeyEvent e) {
    int keyCode = e.getKeyCode();
    if (keyTypedMap.containsKey(keyCode)) {
      keyTypedMap.get(keyCode).run();
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();
    if (keyPressedMap.containsKey(keyCode)) {
      keyPressedMap.get(keyCode).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int keyCode = e.getKeyCode();
    if (keyReleasedMap.containsKey(keyCode)) {
      keyReleasedMap.get(keyCode).run();
    }
  }

  /**
   * add a key to the map of key typed commands
   * @param key key event being added to the map
   * @param r the runnable corresponding to the key event
   */
  public void addKeyTyped(int key, Runnable r) {
    this.keyTypedMap.put(key, r);
  }

  /**
   * add a key to the map of key released commands
   * @param key key event being added to the map
   * @param r the runnable corresponding to the key event
   */
  public void addKeyReleased(int key, Runnable r) {
    this.keyReleasedMap.put(key, r);
  }

  /**
   * add a key to the map of key pressed commands
   * @param key key event being added to the map
   * @param r the runnable corresponding to the key event
   */
  public void addKeyPressed(int key, Runnable r) {
    this.keyPressedMap.put(key, r);
  }

}
