import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class ShowMovie extends GeneList implements ActionListener{
  public ShowMovie() {
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
          rs = sql.executeQuery("SELECT favouriteList.type, movie.m_id,m_name,m_year,m_country,m_rating,m_review,m_actor " +
                  "FROM favouriteList INNER JOIN movie ON favouriteList.m_id = movie.m_id " +
                  "WHERE favouriteList.c_id  = " + Login.userId);
        } else {
          rs = sql.executeQuery("SELECT favouriteList.type, movie.m_id,m_name,m_year,m_country,m_rating,m_review,m_actor " +
                  "FROM favouriteList INNER JOIN movie ON favouriteList.m_id = movie.m_id " +
                  "WHERE favouriteList.type = '" +s +"' && favouriteList.c_id  = " + Login.userId );
        }
        text.append("Loading information from database: \n");
        text.append("You select: " + s + " list:\n");
        text.append("Type  Movie: ID  NAME  YEAR  COUNTRY RATING REVIEW ACTOR\n");
        while(rs.next()){
          text.append(rs.getString("type")+"       " + rs.getString("m_id") +"    "+ rs.getString("m_name") +"   "+ rs.getString("m_year")+"    "+ rs.getString("m_country")+"    "+ rs.getString("m_rating")+"   "+rs.getString("m_review")+"    "+rs.getString("m_actor")+"\n");

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
