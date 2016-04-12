package cs3500.music.view;

import cs3500.music.controller.Controller;
import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.Playable;

import javax.swing.*;
import java.awt.*;

/**
 * A dummy view that simply draws a string
 */
public class ConcreteGuiViewPanel extends JPanel {
  IMusicEditorModel song;
  public static final int BW = 60; // box width
  public static final int BH = 20; // box height
  public static final int VOffset = 40; // vertical offset
  public static final int HOffset = 5; // horizontal offset
  Controller controller;

  public ConcreteGuiViewPanel(IMusicEditorModel song) {
    this.song = song;
  }

  public ConcreteGuiViewPanel(IMusicEditorModel song, Controller controller) {
    this.song = song;
    this.controller = controller;
  }

  public ConcreteGuiViewPanel() {
  }

  /**
   * paints the header (the pitches) of the song
   * @param g the graphics
   */
  public void paintHeader(Graphics g) {
    Playable first = song.maxNote();
    Playable last = song.minNote();
    Playable curr = song.maxNote();

    for (int i = 0; i < song.countRange(); i++) {
      g.drawString(curr.toString(), 0, VOffset + (i * BH));
      curr = curr.prevNote();
    }
  }

  /**
   * paints the frame (rectangles) of the song
   * @param g the graphics
   */
  public void printFrame(Graphics g) {
    for (Playable n : song.getNotes()) {
      g.setColor(Color.pink);
      g.fillRect(VOffset + n.getBegBeat() * (BW / song.getBpm()),
        HOffset + song.noteNum(n) * BH, n.getDuration() * BW/song.getBpm(), BH);
      g.setColor(Color.black);
      g.fillRect(VOffset + n.getBegBeat() * (BW / song.getBpm()),
        HOffset + song.noteNum(n) * BH, 1 * BW/song.getBpm(), BH);
    }
    for (int x = 0; x < Math.ceil(song.lengthSong() / song.getBpm()); x++) { // horizontal
      for (int y = 0; y < song.countRange(); y++) { // vertical
        g.drawString(Integer.toString(x * song.getBpm()), VOffset + (x * BW), BH);
        g.drawRect((x * BW) + VOffset, (y * BH) + 5 + BH, BW, BH); // 5 is diff btwn number & boxes
      }
    }
  }

  /**
   * Paints the red line at the current beat
   * @param g the Graphics
   */
  public void paintLine(Graphics g) {
    g.setColor(Color.RED);
      g.drawLine(this.VOffset + (GuiViewFrame.currBeat * (this.BW / song.getBpm())),
        this.BH + this.HOffset,
        this.VOffset + (GuiViewFrame.currBeat * (this.BW / song.getBpm())),
        this.BH + this.HOffset + (this.song.countRange() * this.BH));

  }

  /**
   * Paints the panel
   * @param g the graphics
   */
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    this.paintHeader(g);
    this.printFrame(g);
    this.paintLine(g);
    this.repaint();
  }
}
