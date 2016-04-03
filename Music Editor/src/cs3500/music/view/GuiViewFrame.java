package cs3500.music.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseListener; // Possibly of interest for handling mouse events
import java.util.*;
import java.util.List;

import javax.swing.*;

import cs3500.music.controller.Controller;
import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;
import cs3500.music.model.MusicEditorImpl;
import cs3500.music.model.Note;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends JFrame implements GuiView {

  private JPanel displayPanel; // You may want to refine this to a subtype of JPanel
  private List<Note> notes = new ArrayList<>();
  private MusicEditorImpl model = new MusicEditorImpl(notes);
  private JScrollPane scroll;
  private KeyboardHandler keyboard = new KeyboardHandler();
  private MouseHandler mouse = new MouseHandler();
  private boolean pause = false;

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
    pause = !pause;
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

  // TODO WHY??
  public void removeMouseHandler(MouseHandler mouse) {}

}
