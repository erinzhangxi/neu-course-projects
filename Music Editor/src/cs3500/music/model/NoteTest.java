package cs3500.music.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by ErinZhang on 3/1/16.
 */
public class NoteTest {

  Note c0 = new NoteImpl(Pitch.C, 0, 1, 2, 0, 50);
  Note c0copy = new NoteImpl(Pitch.C, 0, 1, 2, 0, 50);
  Note gs2 = new NoteImpl(Pitch.GSHARP, 2, 4, 2, 0, 30);
  Note a6 = new NoteImpl(Pitch.A, 6, 7, 12, 1, 50);
  Note f3 = new NoteImpl(Pitch.F, 3, 1, 2, 0, 90);
  Note b0 = new NoteImpl(Pitch.B, 0, 4, 10, 0, 50);
  Note as1 = new NoteImpl(Pitch.ASHARP, 1, 1, 2, 0, 50);
  Note c8 = new NoteImpl(Pitch.C, 8, 0, 1, 2, 50);

  /**
   * invalid octave inputs
   */
  @Test (expected = IllegalArgumentException.class)
  public void testNoteException1() {

    Note ex1 = new NoteImpl(Pitch.C, -10, 0, 1, 2, 50);
    Note ex2 = new NoteImpl(Pitch.C, 40, 0, 1, 2, 50);
    Note ex3 = new NoteImpl(Pitch.C, 9, 0, 1, 2, 50);
  }

  /**
   * when begin or end beat is invalid
   */
  @Test (expected = IllegalArgumentException.class)
  public void testNoteException2() {
    Note ex1 = new NoteImpl(Pitch.C, 6, 3, -1, 2, 50);
    Note ex2 = new NoteImpl(Pitch.C, 5, 6, 0, 2, 50);
    Note ex3 = new NoteImpl(Pitch.C, 6, 0, 1, 2, 50);
    Note ex4 = new NoteImpl(Pitch.C, 5, -2, 1, 2, 50);
  }

  /**
   *  to test the getStartBeat method
   */
  @Test
  public void testGetStartBeat() {
    assertEquals(c0.getStartBeat(), 1);
    assertEquals(a6.getStartBeat(), 7);
    assertEquals(c8.getStartBeat(), 0);
  }
  /**
   *  to test the getEndBeat method
   */
  @Test
  public void testGetEndBeat() {
    assertEquals(c0.getEndBeat(), 2);
    assertEquals(a6.getEndBeat(),12);
    assertEquals(c8.getEndBeat(), 1);
  }


  @Test
  /**
   *  to test the getPitchIdx() method
   */
  public void testGetPitchIdx() {
    assertEquals(c0.getPitchIdx(), 0);
    assertEquals(b0.getPitchIdx(), 11);
    assertEquals(gs2.getPitchIdx(), 32);
  }

  @Test
  /**
   * to test the getInstrument method
   */
  public void testGetInstrument() {
    assertEquals(c0.getInstrument(), 0);
    assertEquals(a6.getInstrument(), 1);
    assertEquals(c8.getInstrument(), 2);
  }

  @Test
  /**
   * to test the getVolume method
   */
  public void testGetVolume() {
    assertEquals(c0.getVolume(), 50);
    assertEquals(gs2.getVolume(), 30);
    assertEquals(f3.getVolume(), 90);
  }


  @Test
  /**
   *  to test the updateStart method
   */
  public void testUpdateStart() {
    c0.updateStart(5);//was 1
    a6.updateStart(0);//was 7
    c8.updateStart(-1);//was 0
    assertEquals(c0.getStartBeat(), 6);
    assertEquals(a6.getStartBeat(), 7);
    assertEquals(c8.getStartBeat(), -1);
  }


  @Test
  /**
   * to test the updateEnd() method
   */
  public void testUpdateEnd() {
    c0.updateEnd(-5);//2
    a6.updateEnd(0);//12
    c8.updateEnd(5);//1
    assertEquals(c0.getEndBeat(), -3);
    assertEquals(a6.getEndBeat(), 12);
    assertEquals(c8.getEndBeat(), 6);
  }

  @Test
  /**
   * to test the updatePosition method
   */
  public void testUpdatePosition() {
    c0.updatePitchIdx(5);
    b0.updatePitchIdx(0);
    gs2.updatePitchIdx(5);
    assertEquals(c0.getPitchIdx(),5);
    assertEquals(b0.getPitchIdx(),11);
    assertEquals(gs2.getPitchIdx(),37);
  }

  @Test
  /**
   *  to test the Equals()
   */
  public void testEquals() {
    assertTrue(c0.equals(c0copy));
    assertFalse(c0.equals(b0));
    assertTrue(c0.equals(c0));
  }
}
