package cs3500.music.view;

import cs3500.music.model.MusicEditorImpl;
import cs3500.music.model.MusicEditorModel;

/**
 * Created by ErinZhang on 3/17/16.
 */
public class CombinedViewImpl implements CombinedView {
  public GuiViewFrame gui;
  public MidiViewImpl midi;

  public CombinedViewImpl(MusicEditorImpl model) {
    this.gui = new GuiViewFrame(model);
    this.midi = new MidiViewImpl(model);
  }

  @Override
  public void display() throws InterruptedException {
    gui.display();
    midi.display();
  }
}
