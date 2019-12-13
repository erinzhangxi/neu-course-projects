import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class FriendName extends GeneFind implements ActionListener{
  public FriendName() {
    super();
    show.addActionListener(this);
    cancel.addActionListener(this);
  }

  /**
   * Invoked when an action occurs.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getActionCommand() == "SHOW"){
      String s = field.getText();
      text.setText("");
      try {
        Connection conn = DataBaseConn.getConnection();
        ResultSet rs;
        Statement sql = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        rs = sql.executeQuery("SELECT u_id,u_username,u_email FROM user " +
                "HAVING user.u_username  = '" + s+"';");

        text.append("Loading information from database: \n");
        text.append("Find user id is: " + s + "\n");
        text.append("ID   USERNAME        EMAIL\n");
        while(rs.next()){
          text.append(rs.getString("u_id") + "    " +rs.getString("u_username") +"        "+ rs.getString("u_email")  + "\n");
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
