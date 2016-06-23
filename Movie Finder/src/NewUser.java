import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

import javax.swing.*;

public class NewUser extends JFrame implements ActionListener{
  JPanel p1 = new JPanel();
  JPanel p2 = new JPanel();
  JPanel p3 = new JPanel();
  JPanel p4 = new JPanel();
  JPanel p5 = new JPanel();
  JLabel l1 = new JLabel("User Name");
  JLabel l2 = new JLabel("PassWord");
  JLabel l3 = new JLabel("Re-PassWord");
  JLabel l4 = new JLabel("E-mail");
  JTextField t1 = new JTextField(15);
  JPasswordField pw1 = new JPasswordField(15);
  JPasswordField pw2 = new JPasswordField(15);
  JTextField t2 = new JTextField(15);
  JButton confirm = new JButton("Confirm");
  JButton cancel = new JButton("Cancel");
  public NewUser(){
    getContentPane().setLayout(new GridLayout(5,1));
    getContentPane().add(p1);
    getContentPane().add(p2);
    getContentPane().add(p3);
    getContentPane().add(p4);
    getContentPane().add(p5);
    p1.add(l1);
    p1.add(t1);
    p2.add(l2);
    p2.add(pw1);
    p3.add(l3);
    p3.add(pw2);
    p4.add(l4);
    p4.add(t2);
    p5.add(confirm);
    p5.add(cancel);
    confirm.addActionListener(this);
    cancel.addActionListener(this);
    pack();
    setVisible(true);
  }
  /**
   * Invoked when an action occurs.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getActionCommand() == "Confirm"){
      String name = t1.getText();
      String pass1 = pw1.getText();
      String pass2 = pw2.getText();
      String email = t2.getText();
      if(!Objects.equals(pass1, pass2)){
        JOptionPane.showMessageDialog(null,"Two password do not match!");
        setVisible(false);
        NewUser newUser = new NewUser();
      }
      try {
        Connection connection = DataBaseConn.getConnection();
        Statement sql = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        sql.executeUpdate("INSERT INTO user(u_username, u_password,u_email) VALUES("+
                "'"+ name  + "',"+
                "'"+ pass1 + "',"+
                "'" +email+"'"+ ");");

        sql.close();
        connection.close();
        JOptionPane.showMessageDialog(null, "Register Successful", "Message", JOptionPane.INFORMATION_MESSAGE);
        setVisible(false);
      } catch (Exception e1) {
        JOptionPane.showMessageDialog(null, e1.getMessage(), "SQL", JOptionPane.INFORMATION_MESSAGE);
        e1.printStackTrace();
      }


    }
    if(e.getActionCommand() == "Cancel"){
      setVisible(false);
    }
  }
}
