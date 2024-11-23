import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/hoteldb";
    private static String username; // To hold the user-provided username
    private static String password; // To hold the user-provided password

    // Private constructor to prevent instantiation
    private DatabaseConnection() {}

    // Method to set the credentials
    private static void setCredentials(String user, String pass) {
        username = user;
        password = pass;
    }

    // Method to handle the login process
    public static boolean login(String user, String pass) {
        setCredentials(user, pass);
        try (Connection connection = DriverManager.getConnection(URL, username, password)) {
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get a connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, username, password);
    }
}