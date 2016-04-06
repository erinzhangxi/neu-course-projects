package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
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
  private Status status;


  public Controller(MusicEditorModel m, CombinedView v) throws InterruptedException {
    this.model = m;
    this.view = v;
    keyboard = new KeyboardHandler();
    mouse = new MouseHandler();
    configureHandler();
    view.resetFocus();
    view.display();
    this.status = Status.Rest;
  }

  public enum Status {
    Add, Remove, Move, Rest
  }

  /**
   * Creates and sets a keyboard listener for the view. In effect it creates snippets of code as
   * Runnable object, one for each time a key is typed, pressed and released, only for those that
   * the program needs.
   *
   */
  private void configureHandler() {
    // callback
    keyboard.addKeyPressed(KeyEvent.VK_SPACE, () -> {
      view.changePauseValue();
    });
    // scroll commands
    keyboard.addKeyPressed(KeyEvent.VK_ENTER, new End());
    keyboard.addKeyPressed(KeyEvent.VK_H, new Home());
    keyboard.addKeyPressed(KeyEvent.VK_UP, () -> this.view.scrollUp());
    keyboard.addKeyPressed(KeyEvent.VK_DOWN, () -> this.view.scrollDown());
    keyboard.addKeyPressed(KeyEvent.VK_LEFT, () -> this.view.scrollLeft());
    keyboard.addKeyPressed(KeyEvent.VK_RIGHT, () -> this.view.scrollRight());
    // change the status
    keyboard.addKeyPressed(KeyEvent.VK_A, () -> {
      this.status = Status.Add;
    });
    keyboard.addKeyPressed(KeyEvent.VK_R, () -> {
      this.status = Status.Remove;
    });
    keyboard.addKeyPressed(KeyEvent.VK_M, () -> {
      this.status = Status.Move;
    });

    this.view.addKeyboardListener(this.keyboard);

    // edit notes
    mouse.getClickedMouse().put(MouseEvent.BUTTON1, new MouseExecute());

    this.view.addMouseListener(this.mouse);
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

  class MouseExecute implements Runnable {
    public void run() {

      int x = mouse.getCurEvent().getX();
      int y = mouse.getCurEvent().getY();

      if (status == Status.Add) {
        System.out.println("Enter the duration: ");
        Scanner dur = new Scanner(System.in);
        int duration = 0;
        duration = dur.nextInt();
        view.getGui().getNoteFromXandY(x, y, duration);
        view.getGui().repaint();
        status = Status.Rest;
      }

      if (status == Status.Remove) {
        // convert coordinates into Note
        view.getGui().removeNoteFromXandY(x, y);
        view.getGui().repaint();
        status = Status.Rest;
      }

      if (status == Status.Move) {
        int tempX = 0;
        int tempY = 0;
        // remove the note
        view.getGui().removeNoteFromXandY(x, y);
        // add the new note

        status = Status.Rest;
      }
    }
  }
}
