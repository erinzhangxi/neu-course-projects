import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.*;

public class Function extends JFrame implements ActionListener{

  JPanel panel1 = new JPanel();
  JPanel panel2 = new JPanel();
  JPanel panel3 = new JPanel();
  JPanel panel4 = new JPanel();
  JPanel panel5 = new JPanel();
  JPanel panel6 = new JPanel();
  JPanel panel7 = new JPanel();
  JPanel panel8 = new JPanel();
  JPanel panel9 = new JPanel();
  JPanel panel10 = new JPanel();
  JLabel findMovie = new JLabel("Find Movie");
  JLabel movieList = new JLabel("Movie List");
  JLabel findFriends = new JLabel("Find Friends");
  JLabel friends = new JLabel("Friends");
  JLabel myself = new JLabel("My Information");

  JButton findMovieById = new JButton("Movie By Id");
  JButton findMovieByName = new JButton("Movie By Name");

  JButton movieListShow = new JButton("Show Movie List");
  JButton movieListAdd = new JButton("Add a Movie");
  JButton movieListDel = new JButton("Remove a Movie");

  JButton findFriendById = new JButton("Friend By Id");
  JButton findFriendByName = new JButton("Friend By Name");

  JButton friendListShow = new JButton("Show Friend List");
  JButton friendListAdd = new JButton("Add a Friend");
  JButton friendListDel = new JButton("Remove a Friend");

  JButton myInformation = new JButton("Myself");

  JMenuBar bar = new JMenuBar();
  JMenu m1 = new JMenu("Start");
  JMenuItem m1a = new JMenuItem("LogOut");
  JMenuItem m1b = new JMenuItem("Exit");
  JMenuItem m1c = new JMenuItem("Report Bug");

  JMenu m2 = new JMenu("FindMovie");
  JMenuItem m2a = new JMenuItem("Movie By Id");
  JMenuItem m2b = new JMenuItem("Movie By Name");

  JMenu m3 = new JMenu("MovieList");
  JMenuItem m3a = new JMenuItem("Add a Movie");
  JMenuItem m3b = new JMenuItem("Remove a Movie");
  JMenuItem m3c =new JMenuItem("Show Movie List");

  JMenu m4 = new JMenu("FindFriend");
  JMenuItem m4a = new JMenuItem("Friend By Id");
  JMenuItem m4b = new JMenuItem("Friend By Name");

  JMenu m5 = new JMenu("FriendList");
  JMenuItem m5a = new JMenuItem("Add a friend");
  JMenuItem m5b = new JMenuItem("Remove a friend");
  JMenuItem m5c =new JMenuItem("Show Friend List");
  public Function(){
    m1.add(m1a);
    m1.add(m1b);
    m1.add(m1c);
    m2.add(m2a);
    m2.add(m2b);
    m3.add(m3a);
    m3.add(m3b);
    m3.add(m3c);
    m4.add(m4a);
    m4.add(m4b);
    m5.add(m5a);
    m5.add(m5b);
    m5.add(m5c);
    bar.add(m1);
    bar.add(m2);
    bar.add(m3);
    bar.add(m4);
    bar.add(m5);
    getContentPane().setLayout(new GridLayout(11,1));
    getContentPane().add(bar);
    getContentPane().add(panel1);
    getContentPane().add(panel2);
    getContentPane().add(panel3);
    getContentPane().add(panel4);
    getContentPane().add(panel5);
    getContentPane().add(panel6);
    getContentPane().add(panel7);
    getContentPane().add(panel8);
    getContentPane().add(panel9);
    getContentPane().add(panel10);


    panel1.add(findMovie);
    panel2.add(findMovieById);
    panel2.add(findMovieByName);
    panel3.add(movieList);
    panel4.add(movieListAdd);
    panel4.add(movieListDel);
    panel4.add(movieListShow);
    panel5.add(findFriends);
    panel6.add(findFriendById);
    panel6.add(findFriendByName);
    panel7.add(friends);
    panel8.add(friendListAdd);
    panel8.add(friendListDel);
    panel8.add(friendListShow);
    panel9.add(myself);
    panel10.add(myInformation);
    setBounds(500, 250, 100, 100);
    pack();
    setVisible(true);

    findMovieById.addActionListener(this);
    findMovieByName.addActionListener(this);

    movieListShow.addActionListener(this);
    movieListAdd.addActionListener(this);
    movieListDel.addActionListener(this);

    findFriendById.addActionListener(this);
    findFriendByName .addActionListener(this);
            friendListShow.addActionListener(this);
friendListAdd.addActionListener(this);
friendListDel.addActionListener(this);
myInformation.addActionListener(this);

    m1a.addActionListener(this);
    m1b.addActionListener(this);
    m1c.addActionListener(this);
    m2a.addActionListener(this);
    m2b.addActionListener(this);
    m3a.addActionListener(this);
    m3b.addActionListener(this);
    m3c.addActionListener(this);
    m4a.addActionListener(this);
    m4b.addActionListener(this);
    m5a.addActionListener(this);
    m5b.addActionListener(this);
    m5c.addActionListener(this);

  }


  /**
   * Invoked when an action occurs.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if(Objects.equals(e.getActionCommand(), "Movie By Id")){
      MovieById movieById = new MovieById();
    }
    else if(e.getActionCommand() == "Movie By Name"){
      MovieByName movieByName = new MovieByName();
    }
    else if(e.getActionCommand() == "Add a Movie"){
      AddMovie addMovie = new AddMovie();
    }
    else if(e.getActionCommand() == "Remove a Movie"){
      RemoveMovie removeMovie = new RemoveMovie();
    }
    else if(e.getActionCommand() == "Show Movie List"){
      ShowMovie showMovie = new ShowMovie();
    }
    else if(e.getActionCommand() == "Friend By Id"){
      FriendId friendId = new FriendId();
    }
    else if(e.getActionCommand() == "Friend By Name"){
      FriendName friendName = new FriendName();
    }
    else if(e.getActionCommand() == "Show Friend List"){
      FriendList friendList = new FriendList();
    }
    else if(e.getActionCommand() == "Add a Friend"){
      AddFriend addFriend = new AddFriend();
    }
    else if(e.getActionCommand() == "Remove a Friend"){
      RemoveFriend removeFriend = new RemoveFriend();
    }
    else if(e.getActionCommand() == "Myself"){
      MySelf mySelf = new MySelf();
    }
    else if(e.getActionCommand() == "LogOut"){
      setVisible(false);
      Login login = new Login();
      login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    else if(e.getActionCommand() == "Exit"){
      System.exit(0);
    }
    else if(e.getActionCommand() == "Report Bug"){
      ReportBug reportBug = new ReportBug();
    }
  }
}
