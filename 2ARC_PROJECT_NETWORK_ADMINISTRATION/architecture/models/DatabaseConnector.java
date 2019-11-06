package architecture.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
	
	static final String JDBC_SERVER = "localhost";
	static final String JDBC_DBNAME = "cap2019";
	static final String JDBC_USER = "root";
    static final String JDBC_PASS = "";
    
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String JDBC_DB_URL = "jdbc:mysql://" + JDBC_SERVER + "/" + JDBC_DBNAME + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
 
    public static Connection conn() {
        try {
            Class.forName(JDBC_DRIVER);  
            Connection connObj = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
            return connObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
