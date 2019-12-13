package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Line;
import javax.swing.*;

import cs3500.music.model.MusicEditorImpl;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;

import static cs3500.music.model.Pitch.getPitchName;

/**
 * A Gui view class that fills the panel with notes
 */
public class ConcreteGuiViewPanel extends JPanel {


  List<Note> notes = new ArrayList<>();
  MusicEditorModel model = new MusicEditorImpl(notes);


  int startGridX = 80;
  int startGridY = 40;

  /**
   * the width of a rectangle representing one beat of a note
   */
  int rectwidth = 20;

  /**
   * the height of a rectangle representing one beat of a note
   */
  int rectheight = 20;
  MyLine line;

  /**
   * Constructs a panel part of the view given the MusicEditorImpl model
   *
   * @param model model of the MusicEditorImpl
   */
  public ConcreteGuiViewPanel(MusicEditorImpl model) {
    this.model = model;
    line = new MyLine(startGridX, startGridY, 80, 40 + (model.getHighPitch() -
            model.getLowPitch() + 1) * 20);
  }

  public MyLine getLine() {
    return this.line;
  }

  /**
   * Fills the panel with custom data
   *
   * @param g Graphics component which is automatically created when paintComponent is called
   */
  @Override
  public void paintComponent(Graphics g) {
    if (g instanceof Graphics2D) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      this.paintNotes((MusicEditorImpl) model, g2);
      this.paintGrid(model.getLowBeat(),model.getHighBeat(),model.getLowPitch(),
              model.getHighPitch(), g2);
    }
  }

  /**
   * Paints the notes from the model to the panel
   *
   * @param song  MusicEditorImpl model with data
   * @param g2    graphics reference to be painted to
   */
  public void paintNotes(MusicEditorImpl song, Graphics2D g2) {
    for (Note n : song.getAll()) {
      this.paintNote(n, g2, song.getHighPitch());
    }
  }

  /**
   * Draws the individual note with its duration
   *
   * @param note      Note type to be drawn
   * @param g2        reference to graphics
   * @param maxPitch  max pitch of the piece
   */
  public void paintNote(Note note, Graphics2D g2, int maxPitch) {
    MyRectangle newRect = new MyRectangle(note, startGridX + (note.getStartBeat()) * rectwidth,
            startGridY + (maxPitch - note.getPitchIdx()) * rectheight, rectwidth, rectheight);
    newRect.draw(g2, maxPitch);
  }

  /**
   * Draws the initial grid to be filled with notes.
   *
   * @param minBeat   lowest beat in the piece
   * @param maxBeat   highest beat in the piece
   * @param minPitch  lowest pitch in the piece
   * @param maxPitch  highest pitch in the piece
   * @param g2        reference to graphic
   */
  public void paintGrid(int minBeat, int maxBeat, int minPitch, int maxPitch, Graphics2D g2) {
    g2.setColor(Color.BLACK);
    int xMaxTable = maxBeat / 4;
    int y = startGridY;
    g2.setStroke(new BasicStroke(2));
    for (int i = maxPitch; i >= minPitch; i--) {
      g2.drawString(getPitchName(i % 12) + Integer.toString(i / 12),
              rectwidth, y + (rectheight / 2));
      int x = startGridX;
      g2.drawRect(x, y, 4 * rectwidth, rectheight);
      if (i % 12 == 0) {
        g2.setStroke(new BasicStroke(4));
        g2.drawLine(startGridX, y + rectheight,
                startGridX + (maxBeat - minBeat + (4 - (maxBeat - minBeat) % 4)) * rectwidth,
                y + rectheight);
        g2.setStroke(new BasicStroke(2));
      }
      for (int j = minBeat; j <= xMaxTable; j++) {
        if (i == maxPitch && (j % 4 == 0)) {
          g2.drawString(Integer.toString(j * 4), x + ((i - maxPitch) * (4 * rectwidth)),
                  startGridY - rectheight / 2);
        }
        g2.drawRect(x, y, 4 * rectwidth, rectheight);
        x += 80;
      }
      y += 20;
    }
    line.makeLine(g2);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension((this.model.getHighBeat() - this.model.getLowBeat()) * rectwidth + 200,
            rectheight * ((this.model.getHighPitch() - this.model.getLowPitch()) + 1) + 100);
  }

  /**
   * Constructs a copy of the model
   *
   * @return a copy of the Panel's model
   */
  public MusicEditorImpl getModelFromPanel() {
    return new MusicEditorImpl(model.getAll());
  }


  /**
   * Note object represented by a rectangle with black rectangle representing the first beat
   * and the green rectangle representing the rest
   */
  class MyRectangle {
    int x;
    int y;
    int height;
    int width;
    Note note;

    /**
     * Constructs a rectangle representation of a note.
     *
     * @param note    note being drawn
     * @param x       x coordinate of the rectangle start
     * @param y       y coordinate of the rectangle start
     * @param width   rectangle width
     * @param height  rectangle height
     */
    public MyRectangle(Note note, int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.note = note;
    }

    /**
     * Draws the rectangle representing a note
     *
     * @param g2        Graphics component
     * @param maxPitch  max pitch of the model
     */
    public void draw(Graphics g2, int maxPitch) {
      /**
       * draws the first beat of a note
       */
      g2.setColor(Color.BLACK);
      g2.fillRect(x, y, width, height);
      /**
       * draws the sustaining beats of a note
       */
      g2.setColor(Color.GREEN);
      g2.fillRect((x + rectwidth), y, rectwidth * (note.getEndBeat() - note.getStartBeat() - 1),
              height);
    }
  }

  /**
   * Defines the line object which we use to track the song
   */
  public class MyLine {
    public int beginY;
    public int endX;
    public int endY;
    public int beginX;

    public MyLine(int x1, int y1, int x2, int y2) {
      this.beginX = x1 - 20;
      this.beginY = y1;
      this.endX = x2 - 20;
      this.endY = y2;
    }

    /**
     * Generates the red line
     *
     * @param g2 Graphics component
     */
    public void makeLine(Graphics g2) {
      g2.setColor(Color.RED);
      g2.drawLine(beginX, beginY, endX, endY);
    }

    /**
     * Moves the line to the current beat
     *
     * @param beat current beat played
     */
    public void moveLine(int beat) {
      if (beginX < (model.getHighBeat()*20 + startGridX)) {
        beginX = 60 + beat * 20;
        endX = beginX;
      }
    }

    /**
     * Adjusts the line to the size of the grid in case the pitch range expands
     */
    public void adjustLine() {
      this.endY = 40 + (model.getHighPitch() - model.getLowPitch() + 1) * 20;
    }
  }
}
