package cs3500.music.view;

import java.awt.*;
import javax.swing.*;

import java.util.*;
import java.util.List;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;
import cs3500.music.model.MusicEditorImpl;
import cs3500.music.model.Note;
import cs3500.music.model.NoteImpl;
import cs3500.music.model.Pitch;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends JFrame implements GuiView {

  private ConcreteGuiViewPanel displayPanel; // You may want to refine this to a subtype of JPanel
  private List<Note> notes = new ArrayList<>();
  private MusicEditorImpl model = new MusicEditorImpl(notes);
  private JScrollPane scroll;
  private KeyboardHandler keyboard = new KeyboardHandler();
  private MouseHandler mouse = new MouseHandler();
  public boolean paused = false;
  public ConcreteGuiViewPanel.MyLine line;

  /**
   * Creates a new GuiView
   */
  public GuiViewFrame(MusicEditorImpl newSong) {
    this.model = newSong;
    this.displayPanel = new ConcreteGuiViewPanel(newSong);
    scroll = new JScrollPane(displayPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    this.getContentPane().add(scroll);
    this.pack();
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    line = displayPanel.getLine();
  }

  public void nextWindowIfEnd() {
    if (line.beginX % 1300 == 0) {
      this.scroll.getHorizontalScrollBar().setValue(1300 * (line.beginX / 1300));
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

  public void resetFocus() {
    displayPanel.setFocusable(true);
    displayPanel.requestFocus();
  }

  public ConcreteGuiViewPanel getPanel() {
    return this.displayPanel;
  }

  public boolean getPauseState() {
    return this.paused;
  }

  public void addNoteFromXandY(int x, int y, int duration) {
    int noteBeat = ((x - displayPanel.startGridX) / displayPanel.rectwidth);
    int notePitch = model.getHighPitch() - ((y - displayPanel.startGridY) /
            displayPanel.rectheight);
    Note note = new NoteImpl(Pitch.getPitch(notePitch % 12), notePitch / 12,
            noteBeat, noteBeat + duration, 1, 64);
    model.addNote(note);
  }

  public void removeNoteFromXandY(int x, int y) {
    int noteBeat = ((x - displayPanel.startGridX) / displayPanel.rectwidth);
    int notePitch = model.getHighPitch() - ((y - displayPanel.startGridY) /
            displayPanel.rectheight);
    model.removeNote(notePitch, noteBeat);
  }

  public Note getNote(int x, int y) {
    System.out.println("x is : " + x + " y is : " + y);  // TODO
    int noteBeat = ((x - displayPanel.startGridX) / displayPanel.rectwidth);
    int notePitch = model.getHighPitch() - ((y - displayPanel.startGridY) /
            displayPanel.rectheight);
    Note newNote = null;
    for (Note n : model.getAll()) {
      if ((n.getPitchIdx() == notePitch) && (n.getStartBeat() == noteBeat)) {
        System.out.println(n.getPitchIdx() + "  " + n.getStartBeat());//TODO
        newNote = n;
      }
    }
    if (newNote == null) {
      throw new IllegalArgumentException("This note does not exist");
    }
    return newNote;
  }

  public void removeMouseHandler(MouseHandler mouse) {}

}
