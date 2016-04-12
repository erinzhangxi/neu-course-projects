package cs3500.music.view;

import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.model.Playable;

import java.util.Map;
import java.util.TreeMap;

/**
 * The text view of the song
 * Created by demifilippou on 3/22/16.
 */
public class ConsoleView implements IView {
  IMusicEditorModel model;

  public ConsoleView(IMusicEditorModel model) {
    this.model = model;
  }

  /**
   * initializes the console view
   */
  @Override
  public void init() {
    Map<String, String> map = new TreeMap<String, String>();

    Playable first = this.model.minNote();
    Playable last = this.model.maxNote();
    Playable curr = this.model.minNote();

    while (curr.compareTo(last) != 0) {
      if (this.model.isSharp(curr)) {
        System.out.print("  ");
        System.out.print(curr);
        curr = curr.nextNote();
      } else {
        System.out.print("   ");
        System.out.print(curr);
        curr = curr.nextNote();
      }
    }
    if (this.model.isSharp(last)) {
      System.out.print("  ");
    }
    else {
      System.out.print("   ");
    }
    System.out.print(this.model.maxNote().toString());
    System.out.print("\n");

    for (int i = 0; i < this.model.lengthSong(); i++) {
      if (i < 10) {
        System.out.print(" ");
        System.out.print(Integer.toString(i));
      }
      else {
        System.out.print(Integer.toString(i));
      }
      for (int j = 0; j < this.model.getNotes().size(); j++) {

        if (this.model.getNotes().get(j).getBegBeat() == i) {
          map.put(this.model.getNotes().get(j).toString(), "X");
        }
        else if ((i < ((this.model.getNotes().get(j).getBegBeat() +
          this.model.getNotes().get(j).getDuration()))) &&
          (i >= this.model.getNotes().get(j).getBegBeat())) {
          map.put(this.model.getNotes().get(j).toString(), "|");
        }
      }
      curr = this.model.minNote();
      while (curr.compareTo(last) != 0) {
        if (map.containsKey(curr.toString())) {
          System.out.print("  ");
          System.out.print(map.get(curr.toString()));
          System.out.print("  ");
        }
        else {
          System.out.print("     ");
        }
        curr = curr.nextNote();
      }
      if (map.containsKey(this.model.maxNote().toString())) {
        System.out.print("  ");
        System.out.print(map.get(this.model.maxNote().toString()));
        System.out.print("  ");
      }
      else {
        System.out.print("     ");
      }
      map.clear();
      System.out.print("\n");
    }
  }
  /**
   * Tells if this ConsoleView is equal to that ConsoleView
   * @param o a given object to compare with this ConsoleView for equality
   * @return boolean true if they are the same, false if they are not
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ConsoleView)) {
      return false;
    }
    else {
      ConsoleView that = (ConsoleView)o;
      return this.model.equals(that.model);
    }
  }
}
