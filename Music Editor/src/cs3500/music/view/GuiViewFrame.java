package cs3500.music.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseListener; // Possibly of interest for handling mouse events
import java.util.*;
import java.util.List;

import javax.swing.*;

import cs3500.music.controller.Controller;
import cs3500.music.model.MusicEditorImpl;
import cs3500.music.model.Note;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends JFrame implements GuiView {

  private JPanel displayPanel; // You may want to refine this to a subtype of JPanel
  private List<Note> notes = new ArrayList<>();
  private MusicEditorImpl model = new MusicEditorImpl(notes);

  /**
   * Creates a new GuiView
   */
  public GuiViewFrame(MusicEditorImpl newSong) {
    this.model = newSong;
    this.displayPanel = new ConcreteGuiViewPanel(newSong);
    JScrollPane scroll = new JScrollPane(displayPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
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

}
