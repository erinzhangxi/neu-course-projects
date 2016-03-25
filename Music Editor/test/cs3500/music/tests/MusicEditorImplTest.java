package cs3500.music.tests;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.music.model.MusicEditorImpl;
import cs3500.music.model.Note;
import cs3500.music.model.NoteImpl;
import cs3500.music.model.Pitch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link MusicEditorImpl}s.
 */
public class MusicEditorImplTest {

  Note c1 = new NoteImpl(Pitch.C, 1, 1, 2, 1, 50); // C1; duration: 1-2
  Note d1 = new NoteImpl(Pitch.D, 1, 1, 2, 1, 50); // D1; duration: 1-2
  Note f1 = new NoteImpl(Pitch.F, 1, 4, 5, 1, 50); // F1; duration: 4-5

  Note c0 = new NoteImpl(Pitch.C, 0, 1, 2, 1, 50);
  Note gs2 = new NoteImpl(Pitch.GSHARP, 2, 4, 2, 1, 50);
  Note a6 = new NoteImpl(Pitch.A, 6, 7, 12, 5, 50);
  Note f3 = new NoteImpl(Pitch.F, 3, 1, 2, 1, 50);
  Note b0 = new NoteImpl(Pitch.B, 0, 4, 10, 1, 50);
  Note as1 = new NoteImpl(Pitch.ASHARP, 1, 1, 2, 1, 50);
  Note c8 = new NoteImpl(Pitch.C, 8, 0, 1, 2, 50);

  Note cs1 = new NoteImpl(Pitch.CSHARP, 1, 50, 51, 1, 50);

  Note e0 = new NoteImpl(Pitch.E, 0, 2, 50, 1, 50);
  Note e1 = new NoteImpl(Pitch.E, 1, 2, 4, 1, 50);
  Note e2 = new NoteImpl(Pitch.E, 2, 3, 4, 1, 50);
  Note e4 = new NoteImpl(Pitch.E, 4, 2, 4, 1, 50);
  Note e6 = new NoteImpl(Pitch.E, 6, 2, 4, 1, 50);
  Note e8 = new NoteImpl(Pitch.E, 8, 2, 4, 1, 50);


  List<Note> mtl = new ArrayList<Note>();
  List<Note> lon = new ArrayList<Note>(Arrays.asList(c1, d1, f1, a6, c0, gs2,
          a6, f3, b0, as1, c8));
  List<Note> lon2 = new ArrayList<Note>(Arrays.asList(e8, e1, e2, e4, e6, e0));

  MusicEditorImpl musicMt = new MusicEditorImpl(mtl);
  MusicEditorImpl musicEd = new MusicEditorImpl(lon);
  MusicEditorImpl musicEd2 = new MusicEditorImpl(lon2);

  @Test
  public void testEmptyModel() {
    assertEquals(0, musicMt.getHighBeat());
    assertEquals(0, musicMt.getLowBeat());
    assertEquals(0, musicMt.getHighPitch());
    assertEquals(120, musicMt.getLowPitch());
    assertEquals(false, musicMt.containsNote(c1));
  }

  /**
   * add note to an empty model
   */
  @Test
  public void testAddNote1() {
      musicMt.addNote(c1);
    assertTrue(musicMt.containsNote(c1));
    assertEquals(2, musicMt.getHighBeat());
    assertEquals(0, musicMt.getLowBeat());
    assertEquals(12, musicMt.getHighPitch());
    assertEquals(12, musicMt.getLowPitch());
  }

  /**
   * add note to an non-empty model
   */
  @Test
  public void testAddNote2() {
    assertEquals(12, musicEd.getHighBeat());
    assertEquals(0, musicEd.getLowBeat());
    assertEquals(96, musicEd.getHighPitch()); // 8c = 8*12
    assertEquals(0, musicEd.getLowPitch()); //

    musicEd.addNote(cs1);

    assertEquals(51, musicEd.getHighBeat());
    assertEquals(0, musicEd.getLowBeat());
    assertEquals(96, musicEd.getHighPitch());
    assertEquals(0, musicEd.getLowPitch());
  }

  @Test
  public void testContainsNote() {
    assertEquals(false, musicMt.containsNote(c1));
    musicMt.addNote(c1);
    assertTrue(musicMt.containsNote(c1));
    assertEquals(false, musicMt.containsNote(f1));

    assertTrue(musicEd.containsNote(c1));
    assertEquals(false, musicEd.containsNote(cs1));
  }

  @Test
  public void testContainsNote2() {
    assertTrue(musicEd.containsNote(c1));
    musicEd.removeNote(c1);
    assertEquals(false, musicEd.containsNote(c1));
  }

  @Test
  public void testContainsNote3() {
    assertTrue(musicEd.containsNote(c1));
    Note temp = new NoteImpl(Pitch.C, 0, 10, 11, 1, 50);
    musicEd.moveNote(c1, temp);
    assertEquals(false, musicEd.containsNote(c1));
    assertTrue(musicEd.containsNote(temp));
  }

  // given a note that is not in the map
  @Test (expected = IllegalArgumentException.class)
  public void testEditNoteExc1() {
    musicMt.addNote(c1);
    musicMt.addNote(d1);
    musicMt.addNote(f1);
    musicMt.editNote(a6, 10);
  }

  @Test
  public void testEditNote1() {
    assertEquals(1, c1.getStartBeat());
    assertEquals(2, c1.getEndBeat());
    musicEd.editNote(c1, 10);
    // add length to the current note
    assertEquals(12, c1.getEndBeat());
    // reduce length from the current note
    musicEd.editNote(c1, -10);
    assertEquals(2, c1.getEndBeat());
  }

  // add length so that the end beat goes out of range
  // exception should be handled in note class
  @Test (expected = IllegalArgumentException.class)
  public void testEditNoteExc2() {
    musicEd.editNote(c1, -100);
  }

  @Test
  public void testRemoveNote1() {
    assertTrue(musicEd.containsNote(c1));
    assertTrue(musicEd.containsNote(d1));
    assertTrue(musicEd.containsNote(f1));
    assertTrue(musicEd.containsNote(c1));
    musicEd.removeNote(c1);
    assertEquals(false, musicEd.containsNote(c1));

    assertTrue(musicEd.containsNote(d1));
    musicEd.removeNote(d1);
    assertEquals(false, musicEd.containsNote(d1));

    assertTrue(musicEd.containsNote(f1));
    musicEd.removeNote(f1);
    assertEquals(false, musicEd.containsNote(f1));

  }

  // remove a note that does not exist
  @Test (expected = IllegalArgumentException.class)
  public void testRemoveNoteExc() {
    musicMt.removeNote(c1);
  }

  // move a note that does not exist
  @Test (expected = IllegalArgumentException.class)
  public void testMoveNoteExc() {
    musicMt.moveNote(f1, a6);
  }

  // move a note onto an existing note when they have the same duration
  @Test
  public void testMoveNote1() {
    assertTrue(musicEd.containsNote(c1));
    assertTrue(musicEd.containsNote(d1));
    assertTrue(musicEd.containsNote(f1));
    musicEd.moveNote(f1, cs1);
    assertEquals(false, musicEd.containsNote(f1));
    assertTrue(musicEd.containsNote(cs1));
  }

  @Test
  public void testMoveNote2() {
    musicMt.addNote(c1);
    musicMt.addNote(d1);
    assertTrue(musicMt.containsNote(c1));
    assertTrue(musicMt.containsNote(d1));

    assertEquals(2, musicMt.getHighBeat());
    musicMt.moveNote(d1, a6);
    assertEquals(false, musicMt.containsNote(d1));

    assertEquals(12, musicMt.getHighBeat());
  }

  /**
   * when combining two empty music
   */
  @Test
  public void testPlaySimul1() {
    ArrayList<Note> mt = new ArrayList<Note>();
    musicMt.playSimul(musicMt);
    assertTrue(mt.containsAll(musicMt.getAll()));
    assertEquals(0, musicMt.getHighBeat());
    assertEquals(0, musicMt.getLowBeat());
    assertEquals(0, musicMt.getHighPitch());
  }

  /**
   * combining one music with another empty music
   */
  @Test
  public void testPlaySimul2() {
    musicEd.playSimul(musicMt);
    assertTrue(lon.containsAll(musicEd.getAll()));
    assertEquals(12, musicEd.getHighBeat());
    assertEquals(0, musicEd.getLowBeat());
    assertEquals(96, musicEd.getHighPitch());
    String output = "    C0  C#0   D0  D#0   E0   F0  F#0   G0  G#0   A0  A#0   B0   C1  " +
            "C#1   D1  D#1   E1   F1  F#1   G1  G#1   A1  A#1   B1   C2  C#2   D2  D#2   E2   " +
            "F2  F#2   G2  G#2   A2  A#2   B2   C3  C#3   D3  D#3   E3   F3  F#3   G3  G#3   " +
            "A3  A#3   B3   C4  C#4   D4  D#4   E4   F4  F#4   G4  G#4   A4  A#4   B4   C5 " +
            " C#5   D5  D#5   E5   F5  F#5   G5  G#5   A5  A#5   B5   C6  C#6   D6  D#6   E6  " +
            " F6  F#6   G6  G#6   A6  A#6   B6   C7  C#7   D7  D#7   E7   F7  F#7   G7  G#7 " +
            "  A7  A#7   B7   C8 \n" +
            " 0                                                               " +
            "                                                                       " +
            "                                                                        " +
            "                                                                         " +
            "                                                                           " +
            "                                                                            " +
            "                                                     X \n" +
            " 1   X                                                           X         X   " +
            "                                    X                                     " +
            "                                                         X                " +
            "                                                                           " +
            "                                                                           " +
            "                                                                            " +
            "                                | \n" +
            " 2   |                                                           |         |   " +
            "                                    |                                          " +
            "                                                    |                           " +
            "                                                                             " +
            "                                                                                  " +
            "                                                                              " +
            "            \n" +
            " 3                                                                                  " +
            "                                                                                " +
            "                                                                                  " +
            "                                                                                " +
            "                                                                                " +
            "                                                                                 \n" +
            " 4                                                          X                  " +
            "           X                                                               " +
            "           X                                                                 " +
            "                                                                             " +
            "                                                                              " +
            "                                                                       " +
            "                              \n" +
            " 5                                                          |                  " +
            "           |                                                                    " +
            "                                                                              " +
            "                                                                               " +
            "                                                                               " +
            "                                                                           " +
            "                 \n" +
            " 6                                                          |            " +
            "                                                                       " +
            "                                                                       " +
            "                                                                      " +
            "                                                                            " +
            "                                                                           " +
            "                                                   \n" +
            " 7                                                          |           " +
            "                                                                        " +
            "                                                                        " +
            "                                                                        " +
            "                                                                        " +
            "                                                  X                      " +
            "                                                      \n" +
            " 8                                                          |         " +
            "                                                                         " +
            "                                                                        " +
            "                                                                       " +
            "                                                                   " +
            "                                                         |           " +
            "                                                                 \n" +
            " 9                                                          |      " +
            "                                                                     " +
            "                                                                      " +
            "                                                                     " +
            "                                                                      " +
            "                                                                 |      " +
            "                                                                      \n" +
            "10                                                          |        " +
            "                                                                      " +
            "                                                                        " +
            "                                                                          " +
            "                                                                           " +
            "                                                  |                         " +
            "                                                   \n" +
            "11                                                             " +
            "                                                                  " +
            "                                                                   " +
            "                                                                    " +
            "                                                                      " +
            "                                                                            |  " +
            "                                                                          \n" +
            "12                                                                          " +
            "                                                                           " +
            "                                                                            " +
            "                                                                            " +
            "                                                                             " +
            "                              |                                              " +
            "                              \n";
  }

  /**
   * combining two non-empty songs
   */
  @Test
  public void testPlaySimul3() {

    musicEd.playSimul(musicEd2);

    assertTrue(lon2.containsAll(musicEd2.getAll()));
    assertEquals(50, musicEd.getHighBeat());
    assertEquals(0, musicEd.getLowBeat());
    assertEquals(100, musicEd.getHighPitch());

  }

  /**
   * test when a new note added is overlapped with the old note
   */
  @Test
  public void testPlaySimul4() {
    musicMt.addNote(c1);
    musicMt.addNote(d1);
    MusicEditorImpl temp = new MusicEditorImpl(mtl);
    temp.addNote(new NoteImpl(Pitch.E, 1, 0, 2, 1, 50));
    temp.addNote(new NoteImpl(Pitch.F, 1, 1, 5, 1, 50));
    temp.addNote(new NoteImpl(Pitch.C, 1, 1, 6, 1, 50));
    temp.addNote(new NoteImpl(Pitch.D, 1, 2, 4, 1, 50));
    temp.addNote(new NoteImpl(Pitch.E, 1, 3, 5, 1, 50));
    temp.addNote(new NoteImpl(Pitch.F, 1, 1, 10, 1, 50));

    musicMt.playSimul(temp);

    String out = "    C1  C#1   D1  D#1   E1   F1 \n" +
            " 0                       X      \n" +
            " 1   X         X         |    X \n" +
            " 2   |         X              | \n" +
            " 3   |         |         X    | \n" +
            " 4   |                   |    | \n" +
            " 5   |                        | \n" +
            " 6                            | \n" +
            " 7                            | \n" +
            " 8                            | \n" +
            " 9                            | \n";

    assertEquals(out, musicMt.renderSong());
  }


  /**
   * play two empty song consecutively
   */
  @Test
  public void testPlayConsec1() {
    ArrayList<Note> mt = new ArrayList<Note>();
    musicMt.playConsec(musicMt);
    assertTrue(mt.containsAll(musicMt.getAll()));
    assertEquals(0, musicMt.getHighBeat());
    assertEquals(0, musicMt.getLowBeat());
    assertEquals(0, musicMt.getHighPitch());
  }

  /**
   * add a song to an empty song and play them consecutively
   */
  @Test
  public void testPlayConsec2() {
    musicEd.playConsec(musicMt);
    assertTrue(lon.containsAll(musicEd.getAll()));
    assertEquals(12, musicEd.getHighBeat());
    assertEquals(0, musicEd.getLowBeat());
    assertEquals(96, musicEd.getHighPitch());
  }

  @Test
  public void testPlayConsec3() {
    musicMt.addNote(c1);
    musicMt.addNote(d1);
    MusicEditorImpl temp = new MusicEditorImpl(mtl);
    temp.addNote(new NoteImpl(Pitch.E, 1, 0, 2, 1, 50));
    temp.addNote(new NoteImpl(Pitch.F, 1, 1, 5, 1, 50));
    musicMt.playConsec(temp);

    String out = "    C1  C#1   D1  D#1   E1   F1 \n" +
            "0                              \n" +
            "1   X         X                \n" +
            "2                              \n" +
            "3                       X      \n" +
            "4                       |    X \n" +
            "5                            | \n" +
            "6                            | \n" +
            "7                            | \n";
    assertEquals(out, musicMt.renderSong());
  }

/**
  @Test
  public void testPrintPitch() {
    musicMt.addNote(c0);
    musicMt.addNote(e0);
    musicMt.addNote(b0);
    musicMt.addNote(c1);
    musicMt.addNote(d1);
    musicMt.addNote(e1);
    musicMt.addNote(f1);

    String temp = "    C0  C#0   D0  D#0   E0   F0  F#0   G0  G#0   A0  A#0   B0 " +
            "  C1  C#1   D1  D#1   E1   F1 \n";
    assertEquals(temp, musicMt.printPitch());

  }

  @Test
  public void testPrintPitch2() {
    musicMt.addNote(b0);
    musicMt.addNote(f3);

    String result = "    B0   C1  C#1   D1  D#1   E1   F1  F#1   G1  G#1   A1  A#1 " +
            "  B1   C2  C#2   D2  D#2   E2   F2  F#2   G2  G#2   A2  A#2 " +
            "  B2   C3  C#3   D3  D#3   E3   F3 \n";

    assertEquals(result, musicMt.printPitch());
  }
*/

  @Test
  public void testRenderSong() {
    musicMt.addNote(new NoteImpl(Pitch.E, 4, 32, 35, 5, 50));
    musicMt.addNote(new NoteImpl(Pitch.F, 4, 2, 3, 5, 50));
    musicMt.addNote(new NoteImpl(Pitch.FSHARP, 4, 2, 3, 5, 50));
    musicMt.addNote(new NoteImpl(Pitch.G, 4, 2, 3, 5, 50));
    musicMt.addNote(new NoteImpl(Pitch.GSHARP, 4, 4, 7, 5, 50));
    musicMt.addNote(new NoteImpl(Pitch.GSHARP, 4, 9, 12, 5, 50));

    String output =
            "    E4   F4  F#4   G4  G#4 \n" +
                    " 0                         \n" +
                    " 1                         \n" +
                    " 2        X    X    X      \n" +
                    " 3                         \n" +
                    " 4                       X \n" +
                    " 5                       | \n" +
                    " 6                       | \n" +
                    " 7                         \n" +
                    " 8                         \n" +
                    " 9                       X \n" +
                    "10                       | \n" +
                    "11                       | \n" +
                    "12                         \n" +
                    "13                         \n" +
                    "14                         \n" +
                    "15                         \n" +
                    "16                         \n" +
                    "17                         \n" +
                    "18                         \n" +
                    "19                         \n" +
                    "20                         \n" +
                    "21                         \n" +
                    "22                         \n" +
                    "23                         \n" +
                    "24                         \n" +
                    "25                         \n" +
                    "26                         \n" +
                    "27                         \n" +
                    "28                         \n" +
                    "29                         \n" +
                    "30                         \n" +
                    "31                         \n" +
                    "32   X                     \n" +
                    "33   |                     \n" +
                    "34   |                     \n";

    assertEquals(output, musicMt.renderSong());
  }

}
