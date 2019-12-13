import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ReportBug extends JFrame implements ActionListener {
  JPanel p1 = new JPanel();
  JPanel p2 = new JPanel();
  JPanel p3 = new JPanel();
  JLabel l1 = new JLabel("E-mail: li.xua@husky.neu.edu");
  JLabel l2 = new JLabel("E-mail: zhang.xi@husky.neu.edu");
  JButton b1 = new JButton("Back");
  public ReportBug(){
    p1.add(l1);
    p2.add(l2);
    p3.add(b1);
    b1.addActionListener(this);
    getContentPane().setLayout(new GridLayout(3,1));
    getContentPane().add(p1);
    getContentPane().add(p2);
    getContentPane().add(p3);
    setBounds(500,250,100,100);
    pack();
    setVisible(true);
  }

  /**
   * Invoked when an action occurs.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getActionCommand() == "Back"){
      setVisible(false);
    }
  }
}
