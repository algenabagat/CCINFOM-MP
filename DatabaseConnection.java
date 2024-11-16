import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/hoteldb";
    private static String username; // To hold the user-provided username
    private static String password; // To hold the user-provided password

    // Private constructor to prevent instantiation
    private DatabaseConnection() {}

    // Method to prompt the user for their credentials and set them
    private static void setCredentials(String user, String pass) {
        username = user;
        password = pass;
    }

    // Method to handle the login process
    public static boolean login() {
        Scanner input = new Scanner(System.in);
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.println("--- DATABASE LOGIN ---");
            System.out.print("Enter your username: ");
            String user = input.nextLine();
            System.out.print("Enter your password: ");
            String pass = input.nextLine();

            // Set the credentials
            setCredentials(user, pass);

            // Try connecting with the provided credentials
            try (Connection conn = getConnection()) {
                loggedIn = true; // Connection successful, set loggedIn to true
                System.out.println("Login successful!\n");
            } catch (SQLException e) {
                System.out.println("Login failed. Please check your credentials and try again.\n");
            }
        }
        return loggedIn;
    }

    // Method to establish and return the database connection
    public static Connection getConnection() throws SQLException {
        if (username == null || password == null) {
            throw new SQLException("User credentials are not set.");
        }
        return DriverManager.getConnection(URL, username, password);
    }
}
