package cs3500.music.tests;

import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.model.MusicEditorImpl;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.model.NoteImpl;
import cs3500.music.model.Pitch;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.MidiViewImpl;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by ErinZhang on 3/16/16.
 */

public class MidiViewImplTest {
  @Test
  public void testMidi1() throws Exception {
    CompositionBuilder<MusicEditorModel> builder = new MusicEditorImpl.Builder();

    MusicEditorModel model = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"),
            builder);

    StringBuilder sb = new StringBuilder();
    MidiViewImpl view = new MidiViewImpl((MusicEditorImpl) model, sb);
    view.mockOutput();

    assertEquals("Pitch: 64 Instrument: 1 is on at time: 0 Volume: 72\n" +
            "Pitch: 55 Instrument: 1 is on at time: 0 Volume: 70\n" +
            "Pitch: 52 Instrument: 1 is off at time: 400 Volume: 72\n" +
            "Pitch: 62 Instrument: 1 is on at time: 400 Volume: 72\n" +
            "Pitch: 50 Instrument: 1 is off at time: 800 Volume: 72\n" +
            "Pitch: 60 Instrument: 1 is on at time: 800 Volume: 71\n" +
            "Pitch: 48 Instrument: 1 is off at time: 1200 Volume: 71\n" +
            "Pitch: 62 Instrument: 1 is on at time: 1200 Volume: 79\n" +
            "Pitch: 43 Instrument: 1 is off at time: 1400 Volume: 70\n" +
            "Pitch: 50 Instrument: 1 is off at time: 1600 Volume: 79\n" +
            "Pitch: 55 Instrument: 1 is on at time: 1600 Volume: 79\n" +
            "Pitch: 64 Instrument: 1 is on at time: 1600 Volume: 85\n" +
            "Pitch: 52 Instrument: 1 is off at time: 2000 Volume: 85\n" +
            "Pitch: 64 Instrument: 1 is on at time: 2000 Volume: 78\n" +
            "Pitch: 52 Instrument: 1 is off at time: 2400 Volume: 78\n" +
            "Pitch: 64 Instrument: 1 is on at time: 2400 Volume: 74\n" +
            "Pitch: 43 Instrument: 1 is off at time: 3000 Volume: 79\n" +
            "Pitch: 52 Instrument: 1 is off at time: 3000 Volume: 74\n" +
            "Pitch: 55 Instrument: 1 is on at time: 3200 Volume: 77\n" +
            "Pitch: 62 Instrument: 1 is on at time: 3200 Volume: 75\n" +
            "Pitch: 50 Instrument: 1 is off at time: 3600 Volume: 75\n" +
            "Pitch: 62 Instrument: 1 is on at time: 3600 Volume: 77\n" +
            "Pitch: 50 Instrument: 1 is off at time: 4000 Volume: 77\n" +
            "Pitch: 62 Instrument: 1 is on at time: 4000 Volume: 75\n" +
            "Pitch: 43 Instrument: 1 is off at time: 4800 Volume: 77\n" +
            "Pitch: 50 Instrument: 1 is off at time: 4800 Volume: 75\n" +
            "Pitch: 55 Instrument: 1 is on at time: 4800 Volume: 79\n" +
            "Pitch: 64 Instrument: 1 is on at time: 4800 Volume: 82\n" +
            "Pitch: 43 Instrument: 1 is off at time: 5200 Volume: 79\n" +
            "Pitch: 52 Instrument: 1 is off at time: 5200 Volume: 82\n" +
            "Pitch: 67 Instrument: 1 is on at time: 5200 Volume: 84\n" +
            "Pitch: 55 Instrument: 1 is off at time: 5600 Volume: 84\n" +
            "Pitch: 67 Instrument: 1 is on at time: 5600 Volume: 75\n" +
            "Pitch: 55 Instrument: 1 is off at time: 6400 Volume: 75\n" +
            "Pitch: 55 Instrument: 1 is on at time: 6400 Volume: 78\n" +
            "Pitch: 64 Instrument: 1 is on at time: 6400 Volume: 73\n" +
            "Pitch: 52 Instrument: 1 is off at time: 6800 Volume: 73\n" +
            "Pitch: 62 Instrument: 1 is on at time: 6800 Volume: 69\n" +
            "Pitch: 50 Instrument: 1 is off at time: 7200 Volume: 69\n" +
            "Pitch: 60 Instrument: 1 is on at time: 7200 Volume: 71\n" +
            "Pitch: 48 Instrument: 1 is off at time: 7600 Volume: 71\n" +
            "Pitch: 62 Instrument: 1 is on at time: 7600 Volume: 80\n" +
            "Pitch: 43 Instrument: 1 is off at time: 8000 Volume: 78\n" +
            "Pitch: 50 Instrument: 1 is off at time: 8000 Volume: 80\n" +
            "Pitch: 55 Instrument: 1 is on at time: 8000 Volume: 79\n" +
            "Pitch: 64 Instrument: 1 is on at time: 8000 Volume: 84\n" +
            "Pitch: 52 Instrument: 1 is off at time: 8400 Volume: 84\n" +
            "Pitch: 64 Instrument: 1 is on at time: 8400 Volume: 76\n" +
            "Pitch: 52 Instrument: 1 is off at time: 8800 Volume: 76\n" +
            "Pitch: 64 Instrument: 1 is on at time: 8800 Volume: 74\n" +
            "Pitch: 52 Instrument: 1 is off at time: 9200 Volume: 74\n" +
            "Pitch: 64 Instrument: 1 is on at time: 9200 Volume: 77\n" +
            "Pitch: 43 Instrument: 1 is off at time: 9600 Volume: 79\n" +
            "Pitch: 52 Instrument: 1 is off at time: 9600 Volume: 77\n" +
            "Pitch: 55 Instrument: 1 is on at time: 9600 Volume: 78\n" +
            "Pitch: 62 Instrument: 1 is on at time: 9600 Volume: 75\n" +
            "Pitch: 50 Instrument: 1 is off at time: 10000 Volume: 75\n" +
            "Pitch: 62 Instrument: 1 is on at time: 10000 Volume: 74\n" +
            "Pitch: 50 Instrument: 1 is off at time: 10400 Volume: 74\n" +
            "Pitch: 64 Instrument: 1 is on at time: 10400 Volume: 81\n" +
            "Pitch: 52 Instrument: 1 is off at time: 10800 Volume: 81\n" +
            "Pitch: 62 Instrument: 1 is on at time: 10800 Volume: 70\n" +
            "Pitch: 43 Instrument: 1 is off at time: 11200 Volume: 78\n" +
            "Pitch: 50 Instrument: 1 is off at time: 11200 Volume: 70\n" +
            "Pitch: 52 Instrument: 1 is on at time: 11200 Volume: 72\n" +
            "Pitch: 60 Instrument: 1 is on at time: 11200 Volume: 73\n", sb.toString());
  }

  @Test
  public void testMidi2() throws InvalidMidiDataException, IOException {

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

    MusicEditorModel model = new MusicEditorImpl(notes);

    StringBuilder sb = new StringBuilder();
    MidiViewImpl view = new MidiViewImpl((MusicEditorImpl) model, sb);
    view.mockOutput();

    assertEquals("Pitch: 108 Instrument: 2 is on at time: 0 Volume: 72\n" +
            "Pitch: 96 Instrument: 2 is off at time: 0 Volume: 72\n" +
            "Pitch: 53 Instrument: 1 is on at time: 0 Volume: 72\n" +
            "Pitch: 34 Instrument: 1 is on at time: 0 Volume: 72\n" +
            "Pitch: 41 Instrument: 1 is off at time: 0 Volume: 72\n" +
            "Pitch: 22 Instrument: 1 is off at time: 0 Volume: 72\n" +
            "Pitch: 16 Instrument: 1 is on at time: 0 Volume: 72\n" +
            "Pitch: 28 Instrument: 1 is on at time: 0 Volume: 72\n" +
            "Pitch: 112 Instrument: 1 is on at time: 0 Volume: 72\n" +
            "Pitch: 88 Instrument: 1 is on at time: 0 Volume: 72\n" +
            "Pitch: 64 Instrument: 1 is on at time: 0 Volume: 72\n" +
            "Pitch: 32 Instrument: 1 is off at time: 0 Volume: 72\n" +
            "Pitch: 12 Instrument: 1 is on at time: 0 Volume: 72\n" +
            "Pitch: 40 Instrument: 1 is on at time: 0 Volume: 72\n" +
            "Pitch: 16 Instrument: 1 is off at time: 0 Volume: 72\n" +
            "Pitch: 100 Instrument: 1 is off at time: 0 Volume: 72\n" +
            "Pitch: 76 Instrument: 1 is off at time: 0 Volume: 72\n" +
            "Pitch: 52 Instrument: 1 is off at time: 0 Volume: 72\n" +
            "Pitch: 28 Instrument: 1 is off at time: 0 Volume: 72\n" +
            "Pitch: 44 Instrument: 1 is on at time: 0 Volume: 72\n" +
            "Pitch: 23 Instrument: 1 is on at time: 0 Volume: 72\n" +
            "Pitch: 93 Instrument: 5 is on at time: 1 Volume: 72\n" +
            "Pitch: 0 Instrument: 1 is off at time: 1 Volume: 72\n" +
            "Pitch: 11 Instrument: 1 is off at time: 2 Volume: 72\n" +
            "Pitch: 81 Instrument: 5 is off at time: 2 Volume: 72\n" +
            "Pitch: 4 Instrument: 1 is off at time: 10 Volume: 72\n" +
            "Pitch: 25 Instrument: 1 is on at time: 10 Volume: 72\n", sb.toString());
  }
}

