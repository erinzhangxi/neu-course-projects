package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.view.GuiView;
import cs3500.music.view.View;

/**
 * Created by ErinZhang on 3/31/16.
 */
public class Controller implements IController {
  private MusicEditorModel model;
  private GuiView view;

  public Controller(MusicEditorModel m, GuiView v) {
    this.model = m;
    this.view = v;
    configureKeyBoardHandler();
  }

  /**
   * Creates and sets a keyboard listener for the view. In effect it creates snippets of code as
   * Runnable object, one for each time a key is typed, pressed and released, only for those that
   * the program needs.
   *
   *
   */
  private void configureKeyBoardHandler() {
    Map<Integer, Runnable> keyTyped = new HashMap<>();
    Map<Integer, Runnable> keyPressed = new HashMap<>();
    Map<Integer, Runnable> keyReleased = new HashMap<>();

    // play back
    keyPressed.put(KeyEvent.VK_SPACE, () -> { this.view.pause(); });
    keyPressed.put(KeyEvent.VK_END, () -> { this.view.end(); });

    // scroll commands
    keyPressed.put(KeyEvent.VK_HOME, () -> { this.view.home(); });
    keyPressed.put(KeyEvent.VK_UP, () -> { this.view.scrollUp(); });
    keyPressed.put(KeyEvent.VK_DOWN, () -> { this.view.scrollDown(); });
    keyPressed.put(KeyEvent.VK_LEFT, () -> { this.view.scrollLeft(); });
    keyPressed.put(KeyEvent.VK_RIGHT, () -> { this.view.scrollRight(); });

    // addNote command

    // removeNote command
    // editNote command

  }

}
