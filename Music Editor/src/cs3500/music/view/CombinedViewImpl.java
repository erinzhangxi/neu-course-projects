package cs3500.music.view;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;
import cs3500.music.model.MusicEditorImpl;
import cs3500.music.model.MusicEditorModel;

/**
 * Created by ErinZhang on 3/17/16.
 */
public class CombinedViewImpl implements CombinedView {
  public GuiViewFrame gui;
  public MidiViewImpl midi;
  KeyboardHandler keyboard = new KeyboardHandler();
  MouseHandler mouse = new MouseHandler();

  public CombinedViewImpl(MusicEditorImpl model) {
    this.gui = new GuiViewFrame(model);
    this.midi = new MidiViewImpl(model);
  }

  @Override
  public void display() throws InterruptedException {
    gui.display();
    midi.display();
  }

  @Override
  public void pause() {
    this.gui.pause();
  }

  @Override
  public void end() {
    this.gui.pause();
  }

  @Override
  public void home() {
    this.gui.pause();
  }

  @Override
  public void scrollUp() {
    this.gui.pause();
  }

  @Override
  public void scrollDown() {
    this.gui.pause();
  }

  @Override
  public void scrollLeft() {
    this.gui.pause();
  }

  @Override
  public void scrollRight() {
    this.gui.pause();
  }

  @Override
  public void setKeyboardListener(KeyboardHandler key) {
    this.keyboard = key;
    this.gui.setKeyboardListener(keyboard);
  }

  public void setMouseHandler(MouseHandler mouse) {
    this.mouse = mouse;
    this.gui.setMouseHandler(mouse);
  }
}
