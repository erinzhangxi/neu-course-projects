package cs3500.music.tests.Controller;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import cs3500.music.controller.KeyboardHandler;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by ErinZhang on 4/6/16.
 */
public class KeyboardHandlerTest {
  KeyboardHandler testKeyboard = new KeyboardHandler();
  StringBuilder result;
  Button a = new Button();

  @Test
  public void testKeyTyped() throws Exception {

    result = new StringBuilder();

    KeyEvent key = new KeyEvent(a, 1, -1, 0, KeyEvent.VK_1, '1');

    class TestTyped implements Runnable {
      public void run() {
        result.append("1 is typed");
      }
    }
    testKeyboard.addKeyTyped(KeyEvent.VK_1, new TestTyped());
    testKeyboard.keyTyped(key);
    assertEquals("1 is typed", result.toString());
  }


  @Test
  public void testKeyPressed() {

    result = new StringBuilder();

    KeyEvent key = new KeyEvent(a, 1, -1, 0, KeyEvent.VK_SPACE, ' ');

    class TestPressed implements Runnable {
      public void run() {
       result.append("press space");
      }
    }

    testKeyboard.addKeyPressed(KeyEvent.VK_SPACE, new TestPressed());
    testKeyboard.keyPressed(key);
    assertEquals("press space", result.toString());
  }


  @Test
  public void testKeyReleased() {

    result = new StringBuilder();

    KeyEvent key = new KeyEvent(a, 1, -1, 0, KeyEvent.VK_SPACE, ' ');

    class TestReleased implements Runnable {
      public void run() {
        result.append("release space");
      }
    }

    testKeyboard.addKeyReleased(KeyEvent.VK_SPACE, new TestReleased());
    testKeyboard.keyReleased(key);
    assertEquals("release space", result.toString());
  }
}
