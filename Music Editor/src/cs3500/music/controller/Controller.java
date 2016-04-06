package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.view.CombinedView;
import cs3500.music.view.GuiView;
import cs3500.music.view.View;

/**
 * Created by ErinZhang on 3/31/16.
 */
public class Controller implements IController {
  private final MusicEditorModel model;
  private CombinedView view;
  private KeyboardHandler keyboard;
  private MouseHandler mouse;

  public Controller(MusicEditorModel m, CombinedView v) throws InterruptedException {
    this.model = m;
    this.view = v;
    keyboard = new KeyboardHandler();
    mouse = new MouseHandler();
    configureKeyBoardHandler();
    view.resetFocus();
    this.view.addMouseListener(this.mouse);
    view.display();
  }

  /**
   * Creates and sets a keyboard listener for the view. In effect it creates snippets of code as
   * Runnable object, one for each time a key is typed, pressed and released, only for those that
   * the program needs.
   *
   */
  private void configureKeyBoardHandler() {
    // play back
//    keyboard.addKeyPressed(KeyEvent.VK_SPACE, () -> {
//      try {
//        this.view.pause();
//      } catch (InvalidMidiDataException e) {
//        e.printStackTrace();
//      }
//    });
    keyboard.addKeyPressed(KeyEvent.VK_SPACE, () -> {
      view.changePauseValue();

    });

    keyboard.addKeyPressed(KeyEvent.VK_ENTER, new End());
    keyboard.addKeyPressed(KeyEvent.VK_H, new Home());
    // scroll commands
    keyboard.addKeyPressed(KeyEvent.VK_UP, () -> this.view.scrollUp());
    keyboard.addKeyPressed(KeyEvent.VK_DOWN, () -> this.view.scrollDown());
    keyboard.addKeyPressed(KeyEvent.VK_LEFT, () -> this.view.scrollLeft());
    keyboard.addKeyPressed(KeyEvent.VK_RIGHT, () -> this.view.scrollRight());

    this.view.addKeyboardListener(this.keyboard);
    // addNote command

    // removeNote command
    // editNote command

  }

  class End implements Runnable {
    // make a mock constructor that returns string
    public void run() {
      view.end();
    }
  }

  class Home implements Runnable {
    public void run() {
      view.home();
    }
  }

}
