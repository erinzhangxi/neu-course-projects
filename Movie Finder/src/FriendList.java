import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class FriendList extends GeneList implements ActionListener{
  public FriendList() {
    super();
    show.addActionListener(this);
    cancel.addActionListener(this);
  }

  /**
   * Invoked when an action occurs.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    String s = box.getSelectedItem().toString();
    if(e.getActionCommand() == "SHOW"){
      text.setText("");
        try {
          Connection conn = DataBaseConn.getConnection();
          ResultSet rs;
          Statement sql = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
          if(s==""){
            rs = sql.executeQuery("SELECT friendslist.friendstype ,friendslist.c_id2," +
                    "user.u_username,user.u_email FROM friendslist INNER JOIN user ON " +
                    "c_id2 = user.u_id " +
                    "WHERE friendslist.c_id1   = " + Login.userId);
          } else {
             rs = sql.executeQuery("SELECT friendslist.friendstype,friendslist.c_id2,user.u_username,user.u_email " +
                    "FROM friendslist INNER JOIN user ON c_id2 = user.u_id " +
                    "WHERE friendstype = '" + s + "' && friendslist.c_id1   = " + Login.userId);
          }
          text.append("Loading information from database: \n");
          text.append("You select: " + s + " list:\n");
          text.append("Type  Friend ID            Friend Name        Friend Email\n");
          while(rs.next()){
            text.append(rs.getString("friendstype") +String.format("%1$15s",rs.getString("c_id2")) +"      "+String.format("%1$25s",rs.getString("u_username"))  + "           "+ rs.getString("u_email") + "\n" );
          }
        } catch (Exception e1) {
          e1.printStackTrace();
          JOptionPane.showMessageDialog(null, e1.getMessage());
        }


    }
    if(e.getActionCommand() == "CANCEL"){
      this.setVisible(false);
    }
  }
}
