package UTILS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 *
 * @author User
 */
public class DBIUtils {
    public static Connection getConnnection() throws SQLException{
        Connection conn=null;
        try {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String url = "jdbc:sqlserver://localhost:1433; databaseName=Assignment;"
                    + "user=sa;password=Vuongvt2k";
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
       
        return conn;
    }
}
