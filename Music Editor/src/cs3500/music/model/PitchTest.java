package cs3500.music.model;

import org.junit.Test;

import static cs3500.music.model.Pitch.getPitchName;
import static org.junit.Assert.assertEquals;

/**
 * Created by ErinZhang on 3/5/16.
 */
public class PitchTest {
  /**
   * to test the toString() method
   */
  @Test
  public void testPitchToString() {
    assertEquals("  C", getPitchName(0));
    assertEquals(" C#", getPitchName(1));
    assertEquals("  D", getPitchName(2));
    assertEquals(" D#", getPitchName(3));
    assertEquals("  E", getPitchName(4));
    assertEquals("  F", getPitchName(5));
    assertEquals(" F#", getPitchName(6));
    assertEquals("  G", getPitchName(7));
    assertEquals(" G#", getPitchName(8));
    assertEquals("  A", getPitchName(9));
    assertEquals(" A#", getPitchName(10));
    assertEquals("  B", getPitchName(11));
  }

}
