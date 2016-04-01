package cs3500.music.view;

/**
 * Created by ErinZhang on 3/17/16.
 */
public interface GuiView extends View {
  /**
   * pause the gui view
   */
  void pause();

  /**
   * go to the end of the song
   */
  void end();

  /**
   * go to the home of the song
   */
  void home();

  void scrollUp();

  void scrollDown();

  void scrollLeft();

  void scrollRight();




}
