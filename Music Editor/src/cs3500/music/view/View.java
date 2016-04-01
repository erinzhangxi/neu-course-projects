package cs3500.music.view;

import cs3500.music.controller.Controller;
import cs3500.music.model.MusicEditorImpl;

/**
 * Created by ErinZhang on 3/17/16.
 */
public interface View {
  /**
   * Display a view of a music editor starting at a given beat
   */
  void display() throws InterruptedException;
}
