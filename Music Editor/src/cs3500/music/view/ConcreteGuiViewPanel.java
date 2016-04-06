package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Line;
import javax.swing.*;

import cs3500.music.model.MusicEditorImpl;
import cs3500.music.model.Note;

import static cs3500.music.model.Pitch.getPitchName;

/**
 * A Gui view class that fills the panel with notes
 */
public class ConcreteGuiViewPanel extends JPanel {


  List<Note> notes = new ArrayList<>();
  MusicEditorImpl model = new MusicEditorImpl(notes);


  int startGridX = 80;
  int startGridY = 40;
  /**
   * the width of a rectangle representing one beat of a note
   */
  int rectwidth = 20;

  Timer timer;
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
    if(g instanceof Graphics2D)
    {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      this.paintNotes(model, g2);
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
    this.paintGrid(song.getLowBeat(),song.getHighBeat(),song.getLowPitch(),
            song.getHighPitch(), g2);
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
//    /**
//     * draws the first beat of a note
//     */
//    g2.setColor(Color.BLACK);
//    g2.fillRect(startGridX + (note.getStartBeat()) * rectwidth, startGridY
//            + (maxPitch - note.getPitchIdx()) * rectheight, rectwidth, rectheight);
//    /**
//     * draws the sustaining beats of a note
//     */
//    g2.setColor(Color.GREEN);
//    g2.fillRect(((startGridX + (note.getStartBeat()) * rectwidth) + rectwidth),
//            startGridY + (maxPitch - note.getPitchIdx()) * rectheight,
//            rectwidth * (note.getEndBeat() - note.getStartBeat() - 1),
//            rectheight);
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

  class MyRectangle {
    int x;
    int y;
    int height;
    int width;
    Note note;
    public MyRectangle(Note note, int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.note = note;
    }

    public void changePosition(int newX, int newY) {
      x = newX;
      y = newY;
    }

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

  class MyLine {
    int beginX;
    int beginY;
    int endX;
    int endY;

    public MyLine(int x1, int y1, int x2, int y2) {
      this.beginX = x1 - 20;
      this.beginY = y1;
      this.endX = x2 - 20;
      this.endY = y2;
    }

    public void makeLine(Graphics g2) {
      g2.setColor(Color.RED);
      //g2.drawLine(startGridX, startGridY, 80, 40 + (maxPitch - minPitch + 1) * 20);
      g2.drawLine(beginX, beginY, endX, endY);
    }
    public void moveLine() {
      if (beginX < (model.getHighBeat()*20 + startGridX)) {
        beginX = beginX + 20;
        endX = endX + 20;
      }
    }

    @Override
    public String toString() {
      return this.beginX + " " + this.endX;
    }
  }

}
