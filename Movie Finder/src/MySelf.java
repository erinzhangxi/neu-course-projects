import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MySelf extends JFrame implements ActionListener {
  JPanel p1 = new JPanel();
  JPanel p2 = new JPanel();
  JPanel p3 = new JPanel();
  JPanel p4 = new JPanel();
  JPanel p5 = new JPanel();
  JLabel l1 = new JLabel("User Id : "+ Login.userId);
  JLabel l2 = new JLabel("User Name :" + Login.userName);
  JLabel l3 = new JLabel("User Email :" + Login.userEmail);
  JLabel l4 = new JLabel("User PassWord : " + Login.userPassWord);
  JButton back = new JButton("Back");
  public MySelf() {
    p1.add(l1);
    p2.add(l2);
    p3.add(l3);
    p4.add(l4);
    p5.add(back);
    getContentPane().setLayout(new GridLayout(5,1));
    getContentPane().add(p1);
    getContentPane().add(p2);
    getContentPane().add(p3);
    getContentPane().add(p4);
    getContentPane().add(p5);
    setBounds(500,250,100,100);
    pack();
    setVisible(true);
    back.addActionListener(this);

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
