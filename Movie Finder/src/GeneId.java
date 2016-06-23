import java.awt.*;

import javax.swing.*;

public class GeneId extends JFrame {
  JPanel p11 = new JPanel();
  JPanel p1 = new JPanel();
  JPanel p2 = new JPanel();
  JLabel l1 = new JLabel("ID:");
  JLabel ty = new JLabel("Type:");
  JTextField id = new JTextField(15);
  String[] type = {"NORMAL", "BEST", "BLOCK"};
  JComboBox box = new JComboBox(type);
  JButton go = new JButton("Go");
  JButton cancel = new JButton("Cancel");
  public GeneId(){
    p1.add(l1);
    p1.add(id);
    p11.add(ty);
    p11.add(box);
    p2.add(go);
    p2.add(cancel);
    getContentPane().setLayout(new GridLayout(3,2));
    getContentPane().add(p1);
    getContentPane().add(p11);
    getContentPane().add(p2);
    setBounds(500,250,100,100);
    pack();
    setVisible(true);
  }


}
