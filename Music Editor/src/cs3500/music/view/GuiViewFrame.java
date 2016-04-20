package cs3500.music.view;

import java.awt.*;
import javax.swing.*;

import java.util.*;
import java.util.List;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;
import cs3500.music.model.MusicEditorImpl;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.model.NoteImpl;
import cs3500.music.model.Pitch;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends JFrame implements GuiView {

  private ConcreteGuiViewPanel displayPanel; // You may want to refine this to a subtype of JPanel
  private List<Note> notes = new ArrayList<>();
  private MusicEditorModel model = new MusicEditorImpl(notes);
  private JScrollPane scroll;
  private KeyboardHandler keyboard = new KeyboardHandler();
  private MouseHandler mouse = new MouseHandler();
  public boolean paused = false;
  public ConcreteGuiViewPanel.MyLine line;

  /**
   * Creates a new GuiView
   */
  public GuiViewFrame(MusicEditorModel newSong) {
    this.model = newSong;
    this.displayPanel = new ConcreteGuiViewPanel((MusicEditorImpl) newSong);
    scroll = new JScrollPane(displayPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    this.getContentPane().add(scroll);
    this.pack();
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    line = displayPanel.getLine();
  }

  /**
   * Determines if the red line moves to the end of the window
   * scroll to the next window and red line starts from the beginning of new window
   */
  public void nextWindowIfEnd() {
    if (line.beginX % this.getBounds().width < 40) {
      this.scroll.getHorizontalScrollBar().setValue(this.getBounds().width *
              (line.beginX / this.getBounds().width));
    }
  }

  /**
   * Sets the size of the Window Frame.
   *
   * @return new Dimension of the window frame
   */
  @Override
  public Dimension getPreferredSize(){
    return new Dimension(1300, 800);
  }

  /**
   * @return music editor model implementation
   */
  public MusicEditorImpl getModelFromFrame() {
    return new MusicEditorImpl(model.getAll());
  }

  @Override
  public void display(){
    this.setVisible(true);
  }

  @Override
  public void pause() {
    paused = !paused;
  }

  @Override
  public void end() {
     this.scroll.getHorizontalScrollBar().setValue(
             this.scroll.getHorizontalScrollBar().getMaximum());
  }

  @Override
  public void home() {
     this.scroll.getHorizontalScrollBar().setValue(0);
  }

  @Override
  public void scrollUp() {
     this.scroll.getVerticalScrollBar().setValue(this.scroll.getVerticalScrollBar().getValue()
             - this.scroll.getVerticalScrollBar().getUnitIncrement());
  }

  @Override
  public void scrollDown() {
    this.scroll.getVerticalScrollBar().setValue(this.scroll.getVerticalScrollBar().getValue()
            + this.scroll.getVerticalScrollBar().getUnitIncrement());
  }

  @Override
  public void scrollLeft() {
    this.scroll.getHorizontalScrollBar().setValue(this.scroll.getHorizontalScrollBar().getValue()
    - this.scroll.getHorizontalScrollBar().getUnitIncrement());
  }

  @Override
  public void scrollRight() {
    this.scroll.getHorizontalScrollBar().setValue(this.scroll.getHorizontalScrollBar().getValue()
            + this.scroll.getHorizontalScrollBar().getUnitIncrement());
  }

  @Override
  public void addMouseListener(MouseHandler mouse) {
    this.mouse = mouse;
    this.displayPanel.addMouseListener(mouse);
  }

  @Override
  public void addKeyboardListener(KeyboardHandler key) {
    this.keyboard = key;
    this.displayPanel.addKeyListener(keyboard);
  }

  /**
   * reset focus on the frame
   */
  public void resetFocus() {
    displayPanel.setFocusable(true);
    displayPanel.requestFocus();
  }

  /**
   *
   * @return the GuiViewPanel
   */
  @Override
  public ConcreteGuiViewPanel getPanel() {
    return this.displayPanel;
  }

  /**
   *
   * @return pause state
   */
  public boolean getPauseState() {
    return this.paused;
  }

  /**
   *
   * @param x x coordinate of a mouse event
   * @param y y coordinate of a mouse event
   * @param duration the length of a note that needs to be added
   */
  @Override
  public void addNoteFromXandY(int x, int y, int duration) {
    int noteBeat = ((x - displayPanel.startGridX) / displayPanel.rectwidth);
    int notePitch = model.getHighPitch() - ((y - displayPanel.startGridY) /
            displayPanel.rectheight);
    Note note = new NoteImpl(Pitch.getPitch(notePitch % 12), notePitch / 12,
            noteBeat, noteBeat + duration, 1, 64);
    model.addNote(note);
    getPanel().getLine().adjustLine();
  }

  @Override
  public void removeNoteFromXandY(int x5, int y5) {
    Note removeNote = getNote(x5, y5);
    int duration = removeNote.getEndBeat() - removeNote.getStartBeat();
    int noteBeat = ((x5 - displayPanel.startGridX) / displayPanel.rectwidth);
    int notePitch = model.getHighPitch() - ((y5 - displayPanel.startGridY) /
            displayPanel.rectheight);
    model.removeNote(notePitch, noteBeat, duration);
    getPanel().getLine().adjustLine();
  }

  /**
   * Given x and y coordinates of a mouse event, give back the corresponding note
   * @param x x coordinate of a mouse event
   * @param y y coordinate of a mouse event
   * @return a Note
   */
  @Override
  public Note getNote(int x, int y) {
    int noteBeat = ((x - displayPanel.startGridX) / displayPanel.rectwidth);
    int notePitch = model.getHighPitch() - ((y - displayPanel.startGridY) /
            displayPanel.rectheight);
    Note newNote = null;
    for (Note n : model.getAll()) {
      if ((n.getPitchIdx() == notePitch) && (n.getStartBeat() == noteBeat)) {
        newNote = n;
      }
    }
    if (newNote == null) {
      throw new IllegalArgumentException("This note does not exist");
    }
    return newNote;
  }

  public void addNote(int x4, int y4, Note note) {
    int noteStart = ((x4 - displayPanel.startGridX) / displayPanel.rectwidth);
    int noteEnd = noteStart + (note.getEndBeat() - note.getStartBeat());
    int notePitch = model.getHighPitch() - ((y4 - displayPanel.startGridY) /
            displayPanel.rectheight);
    Note newNote = new NoteImpl(Pitch.getPitch(notePitch % 12), notePitch / 12,
            noteStart, noteEnd, 1, 64);
    model.addNote(newNote);
    getPanel().getLine().adjustLine();
  }

  /**
   *
   * @param mouse mouse handler
   */
  public void removeMouseHandler(MouseHandler mouse) {}

  @Override
  public void changePauseValue() {
    this.paused = !this.paused;
  }
}
