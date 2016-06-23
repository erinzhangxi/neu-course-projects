import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class MovieById extends GeneFind implements ActionListener{

  public MovieById() {
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
      Integer i = Integer.parseInt(field.getText());
      text.setText("");
      try {
        Connection conn = DataBaseConn.getConnection();
        ResultSet rs;
        Statement sql = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        rs = sql.executeQuery("SELECT *" +
                "FROM movie " +
                "WHERE m_id  = " + i);

        text.append("Loading information from database: \n");
        text.append("Find movie id is: " + i+ "\n");
        text.append("ID NAME  YEAR COUNTRY RATING REVIEW ACTOR\n");
        while(rs.next()){
          text.append(rs.getString("m_id") + " " +rs.getString("m_name") +" "+ rs.getString("m_year")  +" "+ rs.getString("m_country")+" "+ rs.getString("m_rating")+" "+ rs.getString("m_review") +" "+ rs.getString("m_actor")+ "\n");
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
