package cs3500.music.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.music.util.CompositionBuilder;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ivana on 3/23/2016.
 */
public class BuilderTest {

  @Test
  public void testBuild() {
    CompositionBuilder<MusicEditorModel> testBuilder = new MusicEditorImpl.Builder();
    MusicEditorModel testModel = testBuilder.build();
    assertEquals(testModel, testBuilder.build());
  }

  @Test
  public void testBuild2() {
    Note c0 = new NoteImpl(Pitch.C, 0, 3, 9, 1, 72);
    CompositionBuilder<MusicEditorModel> testBuilder = new MusicEditorImpl.Builder();
    MusicEditorModel testModel4 = testBuilder.build();
    List<Note> testNotes = new ArrayList<Note>();
    MusicEditorImpl testModel5 = new MusicEditorImpl(testNotes);
    testModel4.addNote(c0);
    testModel5.addNote(c0);
    assertEquals(testModel4.renderSong(), testModel5.renderSong());
  }

  @Test
  public void testSetTempo() throws Exception {
    CompositionBuilder<MusicEditorModel> testBuilder = new MusicEditorImpl.Builder();
    MusicEditorModel testModel3 = testBuilder.build();
    testModel3.setTempo(300);
    assertEquals(300, testModel3.getTempo());
  }

  @Test
  public void testAddNote() {
    Note c0 = new NoteImpl(Pitch.C, 0, 3, 9, 1, 72);
    CompositionBuilder<MusicEditorModel> testBuilder = new MusicEditorImpl.Builder();
    MusicEditorModel testModel2 = testBuilder.build();
    testModel2.addNote(c0);
    assertEquals(true, testModel2.containsNote(c0));
  }
}