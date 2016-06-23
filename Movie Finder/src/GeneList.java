import java.awt.*;

import javax.swing.*;

public class GeneList extends JFrame{
  JPanel p0 = new JPanel();
  String[] type ={"","NORMAL", "BEST", "BLOCK"};
  JComboBox box = new JComboBox(type);
  JLabel l1 = new JLabel("Select by type(initial is ALl tpyes)");
  JButton show = new JButton("SHOW");
  JButton cancel = new JButton("CANCEL");
  JTextArea text = new JTextArea(20,40);
  JPanel p1 = new JPanel();
  JPanel p2 = new JPanel();
  JScrollPane s1 = new JScrollPane(text);
  public GeneList(){
    p0.add(l1);
    p1.add(box);
    p2.add(show);
    p2.add(cancel);
    p2.add(s1);
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(p0,BorderLayout.NORTH);
    getContentPane().add(p1,BorderLayout.NORTH);
    getContentPane().add(s1,BorderLayout.CENTER);
    getContentPane().add(p2, BorderLayout.SOUTH);
    setBounds(500,250,100,100);
    pack();
    setVisible(true);

  }

}
