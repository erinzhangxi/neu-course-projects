package cs3500.music.view;

import java.io.IOException;

import cs3500.music.model.MusicEditorImpl;

/**
 * Created by ErinZhang on 3/17/16.
 */
public class ConsoleViewImpl implements View {
  MusicEditorImpl model;
  Appendable output;

  /**
   * constructor
   * @param model
   */
  public ConsoleViewImpl(MusicEditorImpl model) {
    this.model = model;
  }

  /**
   * convenience constructor for testing
   * @param model
   * @param result
   */
  public ConsoleViewImpl(MusicEditorImpl model, Appendable result) {
    this.model = model;
    this.output = result;
  }

  /**
   * create a mock output for console view
   */
  public void mockOutput() {
    try {
      output.append(this.model.renderSong());
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(model.renderSong());
  }

  @Override
  public void display() {
    System.out.println(model.renderSong());
  }

}
