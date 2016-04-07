package cs3500.music.tests.View;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.music.model.MusicEditorImpl;
import cs3500.music.model.Note;
import cs3500.music.model.NoteImpl;
import cs3500.music.model.Pitch;
import cs3500.music.view.ConcreteGuiViewPanel;
import cs3500.music.view.GuiViewFrame;

import static org.junit.Assert.*;

/**
 * Created by Marija on 3/22/2016.
 */
public class GuiViewTest {

  Note c0 = new NoteImpl(Pitch.C, 0, 3, 9, 1, 72);
  Note gs2 = new NoteImpl(Pitch.GSHARP, 2, 4, 2, 1, 72);
  Note a6 = new NoteImpl(Pitch.A, 6, 7, 12, 5, 72);
  Note f3 = new NoteImpl(Pitch.F, 3, 1, 2, 1, 72);
  Note b0 = new NoteImpl(Pitch.B, 0, 4, 10, 1, 72);
  Note as1 = new NoteImpl(Pitch.ASHARP, 1, 1, 2, 1, 72);
  Note c8 = new NoteImpl(Pitch.C, 8, 0, 1, 2, 72);
  Note cs1 = new NoteImpl(Pitch.CSHARP, 1, 50, 51, 1, 72);
  Note e0 = new NoteImpl(Pitch.E, 0, 2, 50, 1, 72);
  Note e1 = new NoteImpl(Pitch.E, 1, 2, 4, 1, 72);
  Note e2 = new NoteImpl(Pitch.E, 2, 3, 4, 1, 72);
  Note e4 = new NoteImpl(Pitch.E, 4, 2, 4, 1, 72);
  Note e6 = new NoteImpl(Pitch.E, 6, 2, 4, 1, 72);
  Note e8 = new NoteImpl(Pitch.E, 8, 2, 4, 1, 72);

  List<Note> notes = new ArrayList<Note>(Arrays.asList(c0, gs2, a6, f3, b0, as1, c8, cs1,
          e0, e1, e8, e6, e4, e2));
  MusicEditorImpl testModel = new MusicEditorImpl(notes);
  ConcreteGuiViewPanel testPanel = new ConcreteGuiViewPanel(testModel);
  GuiViewFrame testFrame = new GuiViewFrame(testModel);

  @Test
  public void GuiTest1() throws Exception {
    assertEquals(testModel.getAll(), testPanel.getModelFromPanel().getAll());
  }

  @Test
  public void GuiTest2() throws Exception {
    assertEquals(testFrame.getModelFromFrame().getAll(),
            testPanel.getModelFromPanel().getAll());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddNoteFromXandY() {
    testFrame.addNoteFromXandY(-10, 0, 0);
    testFrame.addNoteFromXandY(0, 0, -4);
    testFrame.addNoteFromXandY(0, -14, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRemoveNoteFromXandY() {
    testFrame.removeNoteFromXandY(0, 0);
  }

  @Test
  public void testAddNote() {
    Note testNote = new NoteImpl(Pitch.CSHARP, 3, 1, 5, 1, 72);
    testFrame.addNote(100, 100, testNote);
  }

}