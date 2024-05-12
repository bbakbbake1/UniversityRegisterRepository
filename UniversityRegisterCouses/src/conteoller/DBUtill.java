package conteoller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtill {
  public static Connection makeConnection() {
    String filePath = "src/db.properties";
    Connection con = null;
    try {
      Properties properties = new Properties();
      properties.load(new FileReader(filePath));
      String url = properties.getProperty("url");
      String user = properties.getProperty("user");
      String password = properties.getProperty("password");
      // ORACLE JDBC LODING
      Class.forName("oracle.jdbc.driver.OracleDriver");
      // DATABASE CONNECT
      con = DriverManager.getConnection(url, user, password);
      System.out.println("데이터베이스 접속 성공");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("DB.PROPERTIES 연결 실패");
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("DB.PROPERTIES 연결 실패");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.out.println("데이터베이스 드라이버 로드 실패");
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("데이터베이스 연결 실패");
    }
    return con;
  }
}
