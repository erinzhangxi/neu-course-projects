package cs3500.music.controller;

import java.util.HashMap;
import java.util.Map;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.view.View;

/**
 * Created by ErinZhang on 3/31/16.
 */
public class Controller implements IController {
  private MusicEditorModel model;
  private View view;

  public Controller(MusicEditorModel m, View v) {
    this.model = m;
    this.view = v;
    configureKeyBoardHandler();
    //this.view.addActionListener(this);
  }

  /**
   * Creates and sets a keyboard listener for the view. In effect it creates snippets of code as
   * Runnable object, one for each time a key is typed, pressed and released, only for those that
   * the program needs.
   *
   * When user type
   */
  private void configureKeyBoardHandler() {
    Map<Integer, Runnable> keyTyped = new HashMap<>();
    Map<Integer, Runnable> keyPressed = new HashMap<>();
    Map<Integer, Runnable> keyReleased = new HashMap<>();



  }

}
