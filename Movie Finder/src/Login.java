import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

import javax.swing.*;

class Login extends JFrame implements ActionListener{
  static int userId;
  static String userName;
  static String userEmail;
  static String userPassWord;

  private JTextField  name = new JTextField(15);
  private JPasswordField passwordField = new JPasswordField(15);
  Login(){
    setTitle("Movie Finder");
    getContentPane().setLayout(new GridLayout(3,1));
    JPanel panel1 = new JPanel();
    getContentPane().add(panel1);
    JPanel panel2 = new JPanel();
    getContentPane().add(panel2);
    JPanel panel3 = new JPanel();
    getContentPane().add(panel3);
    JLabel userName = new JLabel("UserName");
    panel1.add(userName);
    panel1.add(name);
    JLabel passWord = new JLabel("PassWord");
    panel2.add(passWord);
    panel2.add(passwordField);
    JButton login = new JButton("Login");
    panel3.add(login);
    JButton register = new JButton("New User");
    panel3.add(register);
    JButton exit = new JButton("Exit");
    panel3.add(exit);

    setBounds(500,250,200,200);
    pack();
    setVisible(true);
    passwordField.addActionListener(this);
    name.addActionListener(this);
    login.addActionListener(this);
    register.addActionListener(this);
    exit.addActionListener(this);
  }
  /**
   * Invoked when an action occurs.
   */
  @Override
  public void actionPerformed(ActionEvent e) {

    if(Objects.equals(e.getActionCommand(), "Login")){

      try {

        String passWord = passwordField.getText();
        String user = name.getText();
        Connection conn = DataBaseConn.getConnection();
        Statement sql = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = sql.executeQuery("SELECT * FROM user WHERE u_username  = '" + user +"'");
        if(rs.next()) {
          if (Objects.equals(rs.getString("u_password"), passWord.toString())) {
            userId = Integer.parseInt(rs.getString("u_id"));
            userEmail = rs.getString("u_email");
            userName = rs.getString("u_username");
            userPassWord = rs.getString("u_password");
            Function function = new Function();
            function.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(false);
            sql.close();
            conn.close();
          }
          else {
            JOptionPane.showMessageDialog(null,"Please input correct Username or Password", "Error",
                    JOptionPane.INFORMATION_MESSAGE);
            sql.close();
            conn.close();

          }
        }

      } catch (Exception e1) {
        e1.printStackTrace();
        JOptionPane.showMessageDialog(null,e1, "Error",
                JOptionPane.INFORMATION_MESSAGE);

      }


      /////

    }
    else if(Objects.equals(e.getActionCommand(), "New User")){
      NewUser newUser = new NewUser();
    }
    else if(Objects.equals(e.getActionCommand(), "Exit")){
      System.exit(0);
    }

  }


}
