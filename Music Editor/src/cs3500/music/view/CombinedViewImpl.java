package cs3500.music.view;

import javax.sound.midi.InvalidMidiDataException;

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
    try {
      this.midi.pause();
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void end() {
    this.gui.end();
  }

  @Override
  public void home() {
    this.gui.home();
  }

  @Override
  public void scrollUp() {
    this.gui.scrollUp();
  }

  @Override
  public void scrollDown() {
    this.gui.scrollDown();
  }

  @Override
  public void scrollLeft() {
    this.gui.scrollLeft();
  }

  @Override
  public void scrollRight() {
    this.gui.scrollRight();
  }

  @Override
  public void addKeyboardListener(KeyboardHandler key) {
    this.keyboard = key;
    this.gui.addKeyboardListener(keyboard);
  }

  public void addMouseListener(MouseHandler mouse) {
    this.mouse = mouse;
    this.gui.addMouseListener(mouse);
  }

  @Override
  public void resetFocus() {
    gui.resetFocus();
  }
}
