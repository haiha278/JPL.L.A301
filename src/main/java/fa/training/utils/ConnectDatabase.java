package fa.training.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DatabaseInfo.CONNECT_URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
    }
}
