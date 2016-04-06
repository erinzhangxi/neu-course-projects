package cs3500.music.controller;

import org.junit.Test;

import java.awt.*;
import java.awt.event.MouseEvent;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by ErinZhang on 4/6/16.
 */
public class MouseHandlerTest {
  MouseHandler testMouse = new MouseHandler();
  StringBuilder result;
  Button a = new Button();

  @Test
  public void testMouseClicked() throws Exception {
    result = new StringBuilder();

    MouseEvent mouse = new MouseEvent(a, 1, -1, 0, 10, 50, 1, true, MouseEvent.BUTTON1);

    class MouseClicked implements Runnable {
      public void run() {
        result.append("mouse is clicked");
      }
    }
    testMouse.getClickedMouse().put(MouseEvent.BUTTON1, new MouseClicked());
    testMouse.mouseClicked(mouse);
    assertEquals("mouse is clicked", result.toString());
  }

  @Test
  public void testCoordinates() throws Exception {
    result = new StringBuilder();

    MouseEvent mouse1 = new MouseEvent(a, 1, -1, 0, 10, 50, 1, true, MouseEvent.BUTTON1);
    MouseEvent mouse2 = new MouseEvent(a, 1, -1, 0, 10, 50, 1, true, MouseEvent.BUTTON2);

    class GetX implements Runnable {
      public void run() {
        result.append(mouse1.getX());
      }
    }
    testMouse.getClickedMouse().put(MouseEvent.BUTTON1, new GetX());
    testMouse.mouseClicked(mouse1);
    assertEquals("10", result.toString());

    result = new StringBuilder();

    class GetY implements Runnable {
      public void run() {
        result.append(mouse2.getY());
      }
    }

    testMouse.getClickedMouse().put(MouseEvent.BUTTON2, new GetY());
    testMouse.mouseClicked(mouse2);
    assertEquals("50", result.toString());
  }
}
