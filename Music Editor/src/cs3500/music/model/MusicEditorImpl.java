package cs3500.music.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import cs3500.music.util.CompositionBuilder;
import static cs3500.music.model.Pitch.getPitchName;

/**
 * Created by ErinZhang on 2/28/16.
 */
public class MusicEditorImpl implements MusicEditorModel {

  private final TreeMap<Integer, List<Note>> map = new TreeMap<Integer, List<Note>>();
  private int highPitch = 0;
  private int lowPitch = 120;
  private int highBeat = 0;
  private int lowBeat = 0;
  /**
   * the speed, in microseconds per beat, defaults to 200
   */
  private int tempo = 200;

  /**
   * Constructs a music editor model given a list of notes
   *
   * @param t a list of notes
   */
  public MusicEditorImpl(List<Note> t){
    t.forEach(this::addNote);
  }

  @Override
  public TreeMap getMap() {
    TreeMap mapCopy = new TreeMap<Integer, ArrayList<Note>>();
    for (int i = this.getLowBeat(); i < this.getHighBeat(); i++) {

      if (this.map.containsKey(i)) {
        ArrayList<Note> notesAtBeat = new ArrayList<Note>();
        for (Note n : this.map.get(i)) {
          notesAtBeat.add(new NoteImpl(n));
        }
        mapCopy.put(i, notesAtBeat);
      }
    }
    return mapCopy;
  }

  @Override
  public List<Note> getNotesAtBeat(int beat) {
    if (this.getMap().containsKey(beat)) {
      return (List<Note>) this.getMap().get(beat);
    }
    else {
      throw new IllegalArgumentException("No note at this beat.");
    }
  }

  @Override
  public int getTempo() {
    int copy = this.tempo;
    return copy;
  }

  @Override
  public void setTempo(int tempo) {
    if (tempo < 0) {
      throw new IllegalArgumentException("Tempo must be above 0!");
    }
    this.tempo = tempo;
  }

  @Override
  public int getHighPitch() {
    int highPitchCopy = this.highPitch;
    return highPitchCopy;
  }

  @Override
  public int getLowPitch() {
    int lowPitchCopy = this.lowPitch;
    return lowPitchCopy;
  }

  @Override
  public int getHighBeat() {
    int highBeatCopy = this.highBeat;
    return highBeatCopy;
  }

 @Override
  public int getLowBeat() {
    int lowBeatCopy = this.lowBeat;
    return lowBeatCopy;
  }

  @Override
  public void addNote(Note note) {

    int startb = note.getStartBeat();
    int endb = note.getEndBeat();
    int pitch = note.getPitchIdx();

    if (startb < lowBeat) {
      lowBeat = startb;
    }
    if (endb > highBeat) {
      highBeat = endb;
    }
    if (pitch > highPitch) {
      highPitch = pitch;
    }
    if (pitch < lowPitch) {
      lowPitch = pitch;
    }

    // when there is exiting note in this key
    if (map.containsKey(startb)) {
      map.get(startb).add(note);
    }
    // when there is no existing note in the key
    else {
      ArrayList<Note> newList = new ArrayList<Note>();
      newList.add(note);
      map.put(startb, newList);
    }
  }

  /**
   * Change the length of the given note
   *
   * @param note      an existing note with a given pitch at a given time
   * @param lenChange decrease length when lenChange is negative, increase length when lenChange is
   *                  positive
   *
   * INVARIANT: end beat and start beat are within range of [0, 120]
   */
  @Override
  public void editNote(Note note, int lenChange) {

    // If the given note is note in the map
    if (!containsNote(note)) {
      throw new IllegalArgumentException("You must edit an exiting note!");
    }

    if ((note.getEndBeat() + lenChange < 0) ||
            (note.getEndBeat() + lenChange > 9999)) {
      throw new IllegalArgumentException("Note range out of range. Edit again.");
    }

    // list of notes corresponding to the given key
    List<Note> selectedList = map.get(note.getStartBeat());
    // find the corresponding note from the map
    for (int i = 0; i < selectedList.size(); i++) {
      if (selectedList.get(i).equals(note)) {
        selectedList.get(i).updateEnd(lenChange);
        map.replace(i, selectedList);
      }
    }
  }

  @Override
  public boolean containsNote(Note note) {

    boolean temp = false;

    if (map.containsKey(note.getStartBeat())) {
      for (Note n : map.get(note.getStartBeat())) {
        temp = temp || n.equals(note);
      }
    }
    else {
      temp = false;
    }

    return temp;
  }

  @Override
  public void removeNote(Note note) {
    if (!containsNote(note)) {
      throw new IllegalArgumentException("Cannot remove a note that does not exist.");
    }

    List<Note> loNote = map.get(note.getStartBeat());
    loNote.remove(note);

    this.map.replace(note.getStartBeat(), loNote);

    this.lowPitch = 131;
    this.highPitch = 0;
    this.lowBeat = 0;

    this.lowPitch = Math.min(note.getPitchIdx(), this.lowBeat);
    this.lowPitch = Math.max(note.getPitchIdx(), this.highPitch);
    this.lowBeat = Math.max(note.getEndBeat(), this.highPitch);
  }


  @Override
  public void moveNote(Note oldNote, Note newNote) {
    if (!containsNote(oldNote)) {
      throw new IllegalArgumentException("Cannot move a note that does not exist.");
    }

    removeNote(oldNote);
    addNote(newNote);
  }

  public List<Note> getAll() {
    List<Note> lon = new ArrayList<>(Arrays.asList());
    map.values().forEach(lon::addAll);
    return lon;
  }

  @Override
  public void playSimul(MusicEditorImpl song) {
    List<Note> songNotes = song.getAll();
    songNotes.forEach(this::addNote);
  }

  @Override
  public void playConsec(MusicEditorImpl song) {
    List<Note> songNotes = song.getAll();

    for (Note n : songNotes) {
      n.updateNote(this.highBeat + 1);
    }

    songNotes.forEach(this::addNote);
  }


  @Override
  public String renderSong() {
    String result = "";
    result += this.printPitch();
    result += this.drawGrid();
    return result;
  }

  /**
   * First leave two spaces for the duration column
   *
   * A sequence of columns, each five characters wide, representing each pitch. The first line
   * prints out the names of the pitches, more-or-less centered within the five-character column.
   * I.e., "  F2 " and " G#3 " and " D#10".
   *
   * @return a String containing all pitches in a song
   */
  private String printPitch() {
    String result = "  ";
    String s;
    for (int i = lowPitch; i <= highPitch; i++) {
      s = getPitchName(i % 12) + Integer.toString(i / 12);
      // when the name of the pitch name is too long
      if (s.length() == 4) {
        s += " ";
      }
      result += s;
    }
    result += "\n";
    return result;
  }

  /**
   * draw out all the beats with spaces following the same line
   *
   * @return a String containing all beats each on one line
   */
  private String drawBeat() {
    String result = "";
    int indexSize = Integer.toString(this.highBeat).length();
    for (int i = 0; i < this.highBeat; i++) {
      if (indexSize == 1) {
        result = result + String.format("%d", i);
      }
      if (indexSize == 2) {
        result += String.format("%2d" , i);
      }
      if (indexSize == 3) {
        result += String.format("%3d" , i);
      }
      if (indexSize == 4) {
        result += String.format("%4d" , i);
      }
      // set up a line of space for later to add "X" or "|"
      result += new String(new char[(highPitch - lowPitch + 1) * 5]).replace("\0", " ") + "\n";
    }
    return result;
  }

  /**
   * Draws the grid containing the beat index and the notes
   * Each note-head is rendered as an "  X  ", and each note-sustain is rendered as "  |  ".
   * When a note is not played, five spaces are rendered (as "     ").
   *
   * @return a String representing the state of a music
   */
  private String drawGrid() {

    StringBuilder result = new StringBuilder(drawBeat());
    int indexSize = Integer.toString(this.highBeat).length();
    int linecount = indexSize + Math.abs(highPitch - lowPitch + 1) * 5;

    for (int i = lowBeat; i <= highBeat; i++) {
      for (Note n: this.getAll()) {

        if (n.getStartBeat() == i) {
          result.setCharAt(i * (1 + linecount) +
                  (n.getPitchIdx() - lowPitch + 1) * 5 + indexSize - 2, 'X');


        }
        else if (n.getStartBeat() < i &&
                n.getEndBeat() > i) {
          result.setCharAt(i * (1 + linecount) +
                  (n.getPitchIdx() - lowPitch  + 1) * 5  + indexSize - 2, '|');

        }
      }
    }

    return result.toString();
  }

  /**
   * Builds a {@link MusicEditorImpl} with the given parameters.
   */
  public static final class Builder implements CompositionBuilder<MusicEditorModel> {
    MusicEditorImpl song = new MusicEditorImpl(new ArrayList<Note>());

    @Override
    public MusicEditorImpl build() {
      return song;
    }

    @Override
    public CompositionBuilder<MusicEditorModel> setTempo(int tempo) {
      song.setTempo(tempo);
      return this;
    }

    @Override
    public CompositionBuilder<MusicEditorModel> addNote(int start, int end, int instrument,
                                                        int pitch, int volume) {
      Note note = new NoteImpl(Pitch.getPitch(pitch % 12), pitch / 12 - 1,
              start, end, instrument, volume);
      song.addNote(note);
      return this;
    }
  }
}
