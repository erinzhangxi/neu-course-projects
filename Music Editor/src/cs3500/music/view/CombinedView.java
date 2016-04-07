package cs3500.music.view;

import javax.sound.midi.InvalidMidiDataException;

/**
 * Created by ErinZhang on 3/17/16.
 */
public interface CombinedView extends GuiView {
  @Override
  void display() throws InterruptedException;
  @Override
  void pause() throws InvalidMidiDataException;

  @Override
  void changePauseValue();

  GuiViewFrame getGui();

  MidiViewImpl getMidi();

}
