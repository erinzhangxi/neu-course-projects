import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class RemoveFriend extends GeneId implements ActionListener{
  public RemoveFriend() {
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
        JOptionPane.showMessageDialog(null, "You cannot remove yourself", "Message", ResultSet.CONCUR_UPDATABLE);
        this.setVisible(false);
        RemoveFriend removeFriend = new RemoveFriend();
      }
      else {
        try {
          Connection conn = DataBaseConn.getConnection();
          Statement sql = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
          sql.executeUpdate("DELETE FROM friendsList WHERE c_id1 = "+Login.userId+" && c_id2 = "+i+" && friendsType = '"+s+"';" );
          sql.close();
          conn.close();
          JOptionPane.showMessageDialog(null, "Remove Successful", "Message", JOptionPane.INFORMATION_MESSAGE);
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
