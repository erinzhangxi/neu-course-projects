package cs3500.music.view;

import cs3500.music.model.IMusicEditorModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends javax.swing.JFrame implements GuiView {

  private final ConcreteGuiViewPanel displayPanel;
  private int height;
  private int width;
  private JScrollPane scrollPane;
  public JScrollBar hScroll;
  private JScrollBar vScroll;
  static int currBeat;
  IMusicEditorModel model;


  /**
   * Creates new GuiView
   */
  public GuiViewFrame(IMusicEditorModel model) {
    this.model = model;
    this.height = ConcreteGuiViewPanel.VOffset + model.countRange() * 23;
    this.width = model.lengthSong() * ConcreteGuiViewPanel.BW / model.getBpm()
      + ConcreteGuiViewPanel.VOffset * 2;
    this.displayPanel = new ConcreteGuiViewPanel(model);

    this.scrollPane = new JScrollPane(displayPanel);
    this.scrollPane.setVisible(true);

    this.hScroll = this.scrollPane.getHorizontalScrollBar();
    this.vScroll = this.scrollPane.getVerticalScrollBar();

    this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    this.getContentPane().add(scrollPane);
    this.displayPanel.setPreferredSize(this.getPreferredSize());

    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.pack();

  }

  /**
   * initiates the view
   */
  public void init() {
    this.setVisible(true);
  }

  @Override public Dimension getPreferredSize() {
    return new Dimension(this.width, this.height);
  }

  /**
   * Goes to the end of the view
   */
  @Override
  public void goToEnd() {
    this.hScroll.setValue(this.hScroll.getMaximum());
    this.currBeat = this.model.lengthSong();
  }

  /**
   * Goes to the beginning of the view
   */
  @Override
  public void goToBeg() {
    this.hScroll.setValue(this.hScroll.getMinimum());
    this.currBeat = 0;
  }

  /**
   * Scrolls the view up
   */
  @Override
  public void scrollUp() {
    this.vScroll.setValue(this.vScroll.getValue() - ConcreteGuiViewPanel.BH);
  }

  /**
   * Scrolls the view down
   */
  @Override
  public void scrollDown() {
    this.vScroll.setValue(this.vScroll.getValue() + ConcreteGuiViewPanel.BH);
  }

  /**
   * Scrolls the view left
   */
  @Override
  public void scrollLeft() {
    this.hScroll.setValue(this.hScroll.getValue() - ConcreteGuiViewPanel.BW);
  }

  /**
   * Scrolls the view right
   */
  @Override
  public void scrollRight() {
    this.hScroll.setValue(this.hScroll.getValue() + ConcreteGuiViewPanel.BW);
  }

  /**
   * Repaints the view
   */
  @Override
  public void repaint2() {
    this.repaint();
  }

  /**
   * Adds a MouseListener to this GuiView
   *
   * @param m the listener to add
   */
  @Override
  public void addMouseListener(MouseListener m) {
    this.displayPanel.addMouseListener(m);
  }

  /**
   * Removes a MouseListener to this GuiView
   *
   * @param m the listener to remove
   */
  @Override
  public void removeMouseListener(MouseListener m) {
    this.displayPanel.removeMouseListener(m);
  }

  /**
   * Adds a KeyListener to this GuiView
   *
   * @param l the listener to add
   */
  @Override
  public void addKeyListener(KeyListener l) {
    this.displayPanel.setFocusable(true);
    this.displayPanel.addKeyListener(l);
  }

  /**
   * Removess a KeyListener to this GuiView
   *
   * @param l the listener to remove
   */
  @Override
  public void removeKeyListener(KeyListener l) {
    this.displayPanel.removeKeyListener(l);
  }

  /**
   * Updates this GuiView
   */
  public void update() {
    if (this.currBeat * (ConcreteGuiViewPanel.BW / 4) >=
      this.getBounds().getSize().getWidth() - ConcreteGuiViewPanel.BW) {
      this.scrollPane.getHorizontalScrollBar().setValue
        (this.currBeat * (ConcreteGuiViewPanel.BW/4));
    }
  }

  /**
   * Pauses this GuiView
   */
  @Override public void pause() {

  }

  @Override public void resume() {

  }
}
