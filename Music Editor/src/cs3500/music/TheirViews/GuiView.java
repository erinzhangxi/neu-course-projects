package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Timer;

/**
 * Created by demifilippou on 3/21/16.
 * An interface for a GuiView
 */
public interface GuiView extends IView {

  /**
   * Adds a MouseListener to this GuiView
   * @param m the listener to add
   */
  public void addMouseListener(MouseListener m);

  /**
   * Removes a MouseListener to this GuiView
   * @param m the listener to remove
   */
  public void removeMouseListener(MouseListener m);

  /**
   * Adds a KeyListener to this GuiView
   * @param l the listener to add
   */
  public void addKeyListener(KeyListener l);

  /**
   * Removess a KeyListener to this GuiView
   * @param l the listener to remove
   */
  public void removeKeyListener(KeyListener l);

  /**
   * Goes to the end of the view
   */
  public void goToEnd();

  /**
   * Goes to the beginning of the view
   */
  public void goToBeg();

  /**
   * Scrolls the view up
   */
  public void scrollUp();

  /**
   * Scrolls the view down
   */
  public void scrollDown();

  /**
   * Scrolls the view left
   */
  public void scrollLeft();

  /**
   * Scrolls the view right
   */
  public void scrollRight();

  /**
   * Repaints the view
   */
  public void repaint2();

  /**
   * Updates this GuiView
   */
  public void update();

  /**
   * Pauses this GuiView
   */
  public void pause();

  /**
   * Resumes this GuiView
   */
  public void resume();

}
