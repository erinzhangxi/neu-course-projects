package cs3500.music.tests.Controller;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.music.controller.GuiController;
import cs3500.music.model.MusicEditorImpl;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.GuiView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiView;
import cs3500.music.view.MidiViewImpl;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by ErinZhang on 4/6/16.
 */
public class ControllerTest {
//  KeyboardHandler testKeyboard = new KeyboardHandler();
//  Button a = new Button();
  /**
   * controller takes in a GuiView
   * @throws FileNotFoundException
   * @throws InterruptedException
   */
  @Test
  public void testConstructor() throws FileNotFoundException, InterruptedException {
    CompositionBuilder<MusicEditorModel> builder = new MusicEditorImpl.Builder();
    MusicEditorModel model = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"),
            builder);

    GuiView view = new GuiViewFrame((MusicEditorImpl) model);
    GuiController guiController = new GuiController(model, view);

    assertEquals(guiController.getModel(), model);
    assertEquals(guiController.getView(), view);
  }

  /**
   * controller takes a MidiView
   */
  @Test
  public void testConstructorMidi() throws InterruptedException, FileNotFoundException {
    CompositionBuilder<MusicEditorModel> builder = new MusicEditorImpl.Builder();
    MusicEditorModel model = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"),
            builder);

    MidiView view = new MidiViewImpl((MusicEditorImpl) model);
    GuiController guiController = new GuiController(model, view);
    assertEquals(guiController.getModel(), model);
  }

//  @Test
//  public void testHome() throws InterruptedException, FileNotFoundException {
//    CompositionBuilder<MusicEditorModel> builder = new MusicEditorImpl.Builder();
//    MusicEditorModel model = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"),
//            builder);
//
//    CombinedView view = new CombinedViewImpl((MusicEditorImpl) model);
//    GuiController guiController = new GuiController(model, view);
//
//    GuiController.Home home = new GuiController(model, view).new Home();
//    KeyEvent key = new KeyEvent(a, 1, -1, 0, KeyEvent.VK_HOME, ' ');
//
//    testKeyboard.addKeyPressed(KeyEvent.VK_HOME, home);
//    testKeyboard.keyPressed(key);
//    assertEquals(0, view.getGui().getPanel().getLine().beginX % 1300);
//  }

//  @Test
//  public void testEnd() throws InterruptedException, FileNotFoundException {
//    CompositionBuilder<MusicEditorModel> builder = new MusicEditorImpl.Builder();
//    MusicEditorModel model = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"),
//            builder);
//
//    CombinedView view = new CombinedViewImpl((MusicEditorImpl) model);
//    GuiController guiController = new GuiController(model, view);
//
//    GuiController.End end = new GuiController(model, view).new End();
//    KeyEvent key = new KeyEvent(a, 1, -1, 0, KeyEvent.VK_END, ' ');
//
//    testKeyboard.addKeyPressed(KeyEvent.VK_END, end);
//    testKeyboard.keyPressed(key);
//    assertEquals(model.getHighBeat() * 20 + 80, view.getGui().getPanel().getLine().beginX);
//  }
}
