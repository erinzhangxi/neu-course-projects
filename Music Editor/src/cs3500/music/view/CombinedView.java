package cs3500.music.view;

/**
 * Created by ErinZhang on 3/17/16.
 */
public interface CombinedView extends GuiView {
  @Override
  void display() throws InterruptedException;
  @Override
  void pause();
}
