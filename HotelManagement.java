import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HotelManagement {
    private static final Logger logger = Logger.getLogger(HotelManagement.class.getName());

    public static void viewHotelDetails() {
        String query = "SELECT " +
                "h.HOTEL_ID, h.HOTEL_NAME, " +
                "l.STREET_ADDRESS, l.CITY, l.POSTAL_CODE, l.STATE_PROVINCE, " +
                "c.COUNTRY_NAME, h.HOTEL_EMAIL, h.CONTACT_NO " +
                "FROM hotel h " +
                "JOIN location l ON h.LOCATION_ID = l.LOCATION_ID " +
                "JOIN country c ON l.COUNTRY_ID = c.COUNTRY_ID";

        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Print the table header with partitions
            System.out.println("+------------+--------------------------------+------------------------------------------+----------------------+--------------+--------------------------+--------------------+----------------------------------------------------+-----------------+");
            System.out.printf("| %-10s | %-30s | %-40s | %-20s | %-12s | %-24s | %-19s | %-50s | %-15s |\n",
                    "Hotel ID", "Hotel Name", "Street Address", "City", "Postal Code",
                    "State/Province", "Country", "Hotel Email", "Contact No");
            System.out.println("+------------+--------------------------------+------------------------------------------+----------------------+--------------+--------------------------+---------------------+----------------------------------------------------+-----------------+");

            // Loop through the result set and print each hotel record in tabular format with partitions
            while (rs.next()) {
                int hotelId = rs.getInt("HOTEL_ID");
                String hotelName = rs.getString("HOTEL_NAME");
                String streetAddress = rs.getString("STREET_ADDRESS");
                String city = rs.getString("CITY");
                String postalCode = rs.getString("POSTAL_CODE");
                String stateProvince = rs.getString("STATE_PROVINCE");
                String countryName = rs.getString("COUNTRY_NAME");
                String hotelEmail = rs.getString("HOTEL_EMAIL");
                String contactNo = rs.getString("CONTACT_NO");

                // Print the hotel details with partitions and proper borders
                System.out.printf("| %-10d | %-30s | %-40s | %-20s | %-12s | %-24s | %-19s | %-50s | %-15s |\n",
                        hotelId, hotelName, streetAddress, city, postalCode,
                        stateProvince, countryName, hotelEmail, contactNo);
                System.out.println("+------------+--------------------------------+------------------------------------------+----------------------+--------------+--------------------------+---------------------+----------------------------------------------------+-----------------+");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while viewing hotel details", e);
        } 
    }

    public static void updateHotelDetails() {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Prompt the user for input
            System.out.print("Enter Hotel ID to update: ");
            int hotelId = scanner.nextInt();
            scanner.nextLine();  // Consume the newline left by nextInt()

            System.out.print("Enter new Hotel Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter new Hotel Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter new Hotel Contact Number: ");
            String contact = scanner.nextLine();

            // Establish a connection to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_db", "username", "password");

            // Prepare the SQL UPDATE statement
            String updateSQL = "UPDATE hotel SET HOTEL_NAME = ?, HOTEL_EMAIL = ?, CONTACT_NO = ? WHERE HOTEL_ID = ?";
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, contact);
            preparedStatement.setInt(4, hotelId);

            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Hotel details updated successfully.");
            } else {
                System.out.println("No hotel found with the given ID.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while updating hotel details", e);
        } finally {
            // Close the resources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "SQL Exception occurred while closing resources", e);
            }
        }
    }
}