import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class AddFriend extends GeneId implements ActionListener{
  public AddFriend(){
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
      if(i == Login.userId){
        JOptionPane.showMessageDialog(null, "The same ID with you!", "Message", ResultSet.CONCUR_UPDATABLE);
        this.setVisible(true);
        AddFriend addFriend = new AddFriend();
      }
      else {
        try {
          Connection conn = DataBaseConn.getConnection();
          Statement sql = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
          sql.executeUpdate("INSERT INTO friendslist(c_id1, c_id2,friendsType) VALUES(" +
                  "'" + Login.userId + "'," +
                  "'" + i + "'," +
                  "'" + s + "'" + ");");
          sql.close();
          conn.close();
          JOptionPane.showMessageDialog(null, "Update Successful", "Message", JOptionPane.INFORMATION_MESSAGE);
          setVisible(false);

        } catch (Exception e1) {
          JOptionPane.showMessageDialog(null, e1.getMessage(), "SQL", JOptionPane.INFORMATION_MESSAGE);
          e1.printStackTrace();
        }
      }
    }
    else if(e.getActionCommand() == "Cancel"){
      setVisible(false);
    }
  }
}
