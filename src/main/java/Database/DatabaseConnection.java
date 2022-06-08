package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "NEW_STUDENT";
    private static final String PASSWORD = "NEW_STUDENT";
    private static Connection connection = null;

    private DatabaseConnection() {}
    public static Connection getConnection() throws SQLException {
        if(connection == null){
            connection = createConnection();
            connection.setAutoCommit(true);
        }

        return connection;
    }

    private static Connection createConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(
                    URL, USER, PASSWORD);
        } catch(SQLException e) {
            //System.err.println("Cannot connect to DB: " + e);
        }
        return con;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }
}