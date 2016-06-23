import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class AddMovie extends GeneId implements ActionListener{
  public AddMovie() {
    super();
    go.addActionListener(this);
    cancel.addActionListener(this);
  }

  /**
   * Invoked when an action occurs.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getActionCommand() == "Go") {
      Integer i = Integer.parseInt(id.getText());
      String s = box.getSelectedItem().toString();
      try {
        Connection conn = DataBaseConn.getConnection();
        Statement sql = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        sql.executeUpdate("INSERT INTO favouriteList(m_id,c_id,type)VALUES("+i+","+Login.userId+",'" +s +"'); ");
        sql.close();
        conn.close();
        JOptionPane.showMessageDialog(null, "Movie Added Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
        setVisible(false);

      } catch (Exception e1) {
        JOptionPane.showMessageDialog(null, e1.getMessage(), "SQL", JOptionPane.INFORMATION_MESSAGE);
        e1.printStackTrace();
      }
    }

    else if(e.getActionCommand() == "Cancel"){
      setVisible(false);
    }
  }
}
