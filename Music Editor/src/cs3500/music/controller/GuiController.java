package cs3500.music.controller;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Scanner;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.model.NoteImpl;
import cs3500.music.view.CombinedView;
import cs3500.music.view.GuiView;
import cs3500.music.view.View;

/**
 * Created by ErinZhang on 3/31/16.
 */
public class GuiController implements IController {
  private MusicEditorModel model;
  private GuiView view;
  private KeyboardHandler keyboard;
  private MouseHandler mouse;
  private Status status;
  private Note tempNote;

  public GuiController(MusicEditorModel m, View v) throws InterruptedException {
    this.model = m;
    if (v instanceof GuiView) {
      this.view = (GuiView) v;
      keyboard = new KeyboardHandler();
      mouse = new MouseHandler();
      configureHandler();
      view.resetFocus();
      view.display();
      this.status = Status.Rest;
    }
    v.display();
  }

  /**
   * Four states representing the game
   */
  public enum Status {
    Add, Remove, Move1, Move2, Rest
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
      this.status = Status.Move1;
    });

    this.view.addKeyboardListener(this.keyboard);

    // edit notes
    mouse.getClickedMouse().put(MouseEvent.BUTTON1, new MouseExecute());
    mouse.getPressedMouse().put(MouseEvent.BUTTON1, new MoveNote1());
    mouse.getReleasedMouse().put(MouseEvent.BUTTON1, new MoveNote2());

    this.view.addMouseListener(this.mouse);
  }

  /**
   *
   * @return the model
   */
  public MusicEditorModel getModel() {
    return this.model;
  }

  /**
   *
   * @return the view
   */
  public View getView() {
    return this.view;
  }

  /**
   * Scroll to the end of the screen
   */
  public class End implements Runnable {
    // make a mock constructor that returns string
    public void run() {
      view.end();
    }
  }

  /**
   * Scroll to the home of the screen
   */
  public class Home implements Runnable {
    public void run() {
      view.home();
    }
  }

  /**
   * Execute an action
   * Either add a note or remove a note
   */
  public class MouseExecute implements Runnable {

    public void run() {

      int x = mouse.getCurEvent().getX();
      int y = mouse.getCurEvent().getY();

      if (status == Status.Add) {
        System.out.println("Enter the duration: ");
        Scanner dur = new Scanner(System.in);
        int duration = 0;
        duration = dur.nextInt();
        view.addNoteFromXandY(x, y, duration);
        view.repaint();
        status = Status.Rest;
      }

      if (status == Status.Remove) {
        // convert coordinates into Note
        view.removeNoteFromXandY(x, y);
        view.repaint();
        status = Status.Rest;
      }
    }

  }

  /**
   * Move a note
   */
  class MoveNote1 implements Runnable {
    public void run() {
      if (status == Status.Move1) {
        tempNote = new NoteImpl();
        int x1 = mouse.getPressedEvent().getX();
        int y1 = mouse.getPressedEvent().getY();
        tempNote = view.getNote(x1, y1);

        view.removeNoteFromXandY(x1, y1);
        status = Status.Move2;
      }
    }
  }

  class MoveNote2 implements Runnable {
    public void run() {
      if (status == Status.Move2) {
        int x2 = mouse.getReleasedEvent().getX();
        int y2 = mouse.getReleasedEvent().getY();

        System.out.println(x2 + "  " + y2);
        System.out.println(tempNote.getPitchIdx() + "  " + tempNote.getStartBeat());

        view.addNote(x2, y2, tempNote);
        view.repaint();

        status = Status.Rest;
        tempNote = new NoteImpl();
      }
    }
  }
}
