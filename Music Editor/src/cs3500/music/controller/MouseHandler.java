package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ErinZhang on 3/31/16.
 */
public class MouseHandler implements MouseListener {
  private final Map<Integer, Runnable> clickedMouse;
  private final Map<Integer, Runnable> pressedMouse;
  private final Map<Integer, Runnable> releasedMouse;
  private MouseEvent curEvent;
  private MouseEvent pressed;
  private MouseEvent released;

  public MouseHandler() {
    this.clickedMouse = new HashMap<>();
    this.pressedMouse = new HashMap<>();
    this.releasedMouse = new HashMap<>();
  }

  public MouseEvent getCurEvent() {
    return this.curEvent;
  }

  public MouseEvent getPressedEvent() {
    return this.pressed;
  }

  public MouseEvent getReleasedEvent() {
    return this.curEvent;
  }


  public Map<Integer, Runnable> getClickedMouse() {
    return this.clickedMouse;
  }

  public Map<Integer, Runnable> getPressedMouse() {
    return this.pressedMouse;
  }

  public Map<Integer, Runnable> getReleasedMouse() {
    return this.releasedMouse;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    curEvent = e;
    int mouse = e.getButton();
    if (clickedMouse.containsKey(mouse)) {
      clickedMouse.get(mouse).run();
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    pressed = e;
    int mouse = e.getButton();
    if (pressedMouse.containsKey(mouse)) {
      pressedMouse.get(mouse).run();
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    released = e;
    int mouse = e.getButton();
    if (releasedMouse.containsKey(mouse)) {
      releasedMouse.get(mouse).run();
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    curEvent = e;
  // do nothing
  }

  @Override
  public void mouseExited(MouseEvent e) {
    curEvent = e;
  // do nothing
  }
}
