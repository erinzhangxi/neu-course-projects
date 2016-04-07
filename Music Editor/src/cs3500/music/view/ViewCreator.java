package cs3500.music.view;

import java.util.ArrayList;
import java.util.List;

import cs3500.music.model.MusicEditorImpl;
import cs3500.music.model.Note;

/**
 * Created by Marija on 3/18/2016.
 */
public class ViewCreator {

  public static View create(String viewType, MusicEditorImpl model) {
    if (viewType.equals("console")) {
      return new ConsoleViewImpl(model);
    }
    else if (viewType.equals("gui")) {
      return new GuiViewFrame(model);
    }
    else if (viewType.equals("midi")) {
      return new MidiViewImpl(model);
    }
    else if (viewType.equals("combined")) {
      return new CombinedViewImpl(model);
    }
    else {
      throw new IllegalArgumentException("Wrong Model Type");
    }
  }
}
