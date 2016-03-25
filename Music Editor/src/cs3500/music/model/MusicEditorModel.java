package cs3500.music.model;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by ErinZhang on 2/28/16.
 */
public interface MusicEditorModel {

  /**
   * Add a note to the song
   * <></>If a note is added to an existing note, the old note would be replaced
   * by the new note<></>
   *
   * @param newNote a note with a given pitch at a given time
   */
  void addNote(Note newNote);

  /**
   * Edit a note from the song to change its length
   * @param note a note with a given pitch at a given time
   */
  void editNote(Note note, int lenChange);

  /**
   * remove a given note from a music editor
   * @param note
   */
  void removeNote(Note note);

  /**
   * move a given note to a new location
   * @param oldNote an existing note that needed to be removed
   * @param newNote a new note. the old note will be moved to this location
   */
  void moveNote(Note oldNote, Note newNote);

  /**
   * Determine if the given note is in the treemap
   *
   * @param note a given note with duration and pitch
   * @return true if the note is in the map
   */
  boolean containsNote(Note note);

  /**
   * Given a new song, this new song will be added to the old song.
   * These two songs will be played simultaneously.
   * If some notes are overlapped, play them together, and keep playing until
   * the the note with the longer beat is finished playing.
   *
   * @param song a new song that needed to be added to the current song
   */
  void playSimul(MusicEditorImpl song);

  /**
   * Given a new song, this new song will be added to the old song.
   * But this new song is played after the current song is finished playing.
   *
   * So if the last beat of the current song is 2, the first beat of the new song
   * is played at 3.
   *
   * @param song a new song that needed to be played consecutively after the first song.
   */
  void playConsec(MusicEditorImpl song);

  /**
   * Get the current view of a piece of song
   *
   * @return a string containing all notes in a song
   */
  String renderSong();


  /**
   * Get the current TreeMap of this music editor
   * @return the current treemap consisted of beats and notes
   */
  TreeMap getMap();

  /**
   * Retrieve all the notes at a specific beat
   * @param beat given beat
   * @return notes in a beat
   */
  List<Note> getNotesAtBeat(int beat);

  /**
   * Get the tempo
   */
  int getTempo();

  /**
   * update the tempo
   */
  void setTempo(int tempo);

  /**
   * Get the highest pitch
   * @return index of highest pitch within range [0, 120]
   */
  int getHighPitch();

  /**
   * Get the lowest pitch
   * @return index of lowest pitch within range [0, 120]
   */
  int getLowPitch();

  /**
   * Get the highest beat
   * @return index of highest beat
   */
  int getHighBeat();

  /**
   * Get the highest beat
   * @return index of lowest beat, cannot be below 0
   */
  int getLowBeat();
}
