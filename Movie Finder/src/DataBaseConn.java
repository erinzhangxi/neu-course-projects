import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConn {
  /**
   * Get a new database connection
   *
   * @return A Connection
   * @throws SQLException
   */
  static Connection getConnection() throws SQLException, ClassNotFoundException {
    Properties properties =  new Properties();
    properties.put("user", "root");
    properties.put("password", "sin84385010ZQ");
    String url = "jdbc:mysql://localhost:3306/projectsDB?rautoReconnect=true&useSSL=false";
    Connection conn = DriverManager.getConnection(url,properties);

    return conn;
  }
}
