import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/hoteldb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "abagat1124";

    // Private constructor to prevent instantiation
    private DatabaseConnection() {}

    // Method to establish and return the database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
