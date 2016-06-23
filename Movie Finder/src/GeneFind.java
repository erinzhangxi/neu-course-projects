import java.awt.*;

import javax.swing.*;

public class GeneFind extends JFrame{
  JTextField field = new JTextField(15);
  JButton show = new JButton("SHOW");
  JButton cancel = new JButton("CANCEL");
  JTextArea text = new JTextArea(20,40);
  JPanel p1 = new JPanel();
  JPanel p2 = new JPanel();
  JScrollPane s1 = new JScrollPane(text);
  public GeneFind(){
    p1.add(field);
    p2.add(show);
    p2.add(cancel);
    p2.add(s1);
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(p1,BorderLayout.NORTH);
    getContentPane().add(s1,BorderLayout.CENTER);
    getContentPane().add(p2, BorderLayout.SOUTH);
    setBounds(500,250,100,100);
    pack();
    setVisible(true);

  }

}
