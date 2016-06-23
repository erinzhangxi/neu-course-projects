import java.awt.*;

import javax.swing.*;

public class GeneName extends JFrame{
  JPanel p1 = new JPanel();
  JPanel p2 = new JPanel();
  JLabel l1 = new JLabel("Name:");
  JTextField id = new JTextField(15);
  JButton go = new JButton("Go");
  JButton cancel = new JButton("Cancel");
  public GeneName(){
    p1.add(l1);
    p1.add(id);
    p2.add(go);
    p2.add(cancel);
    getContentPane().setLayout(new GridLayout(2,2));
    getContentPane().add(p1);
    getContentPane().add(p2);
    setBounds(500,250,100,100);
    pack();
    setVisible(true);
  }

}
