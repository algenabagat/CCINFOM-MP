import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HotelManagement {
    private static final Logger logger = Logger.getLogger(HotelManagement.class.getName());

    public static void createHotel() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Hotel Name: ");
        String hotelName = scanner.nextLine();

        System.out.print("Enter Hotel Email: ");
        String hotelEmail = scanner.nextLine();

        System.out.print("Enter Contact Number: ");
        String contactNo = scanner.nextLine();

        showLocations();    // Display locations to choose from
        System.out.print("Enter Location ID: ");
        int locationId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Insert query for adding a hotel
        String query = "INSERT INTO hotel (HOTEL_NAME, HOTEL_EMAIL, CONTACT_NO, LOCATION_ID) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            // Set parameters for the query
            pstmt.setString(1, hotelName);
            pstmt.setString(2, hotelEmail);
            pstmt.setString(3, contactNo);
            pstmt.setInt(4, locationId);

            // Execute the query
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int hotelId = generatedKeys.getInt(1);
                        System.out.println("Hotel created successfully with ID: " + hotelId);
                    }
                }
            } else {
                System.out.println("Failed to create hotel.");
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Invalid Location ID. Hotel creation aborted.");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while creating hotel", e);
        }
    }

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
            System.out.println("+------------+--------------------------------+------------------------------------------+----------------------+--------------+--------------------------+---------------------+----------------------------------------------------+-----------------+");
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

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement checkHotelStmt = connection.prepareStatement(
                     "SELECT 1 FROM hotel WHERE HOTEL_ID = ?");
             PreparedStatement updateHotelStmt = connection.prepareStatement(
                     "UPDATE hotel SET HOTEL_NAME = ?, HOTEL_EMAIL = ?, CONTACT_NO = ? WHERE HOTEL_ID = ?")) {

            // Prompt the user for input
            System.out.print("Enter Hotel ID to update: ");
            int hotelId = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            // Check if the hotel ID exists
            checkHotelStmt.setInt(1, hotelId);
            try (ResultSet rs = checkHotelStmt.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("No hotel found with the given ID.");
                    return;  // Exit the method if no hotel is found
                }
            }

            // If the hotel exists, prompt for the new details
            System.out.print("Enter new Hotel Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter new Hotel Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter new Hotel Contact Number: ");
            String contact = scanner.nextLine();

            // Prepare the SQL statement for updating
            updateHotelStmt.setString(1, name);
            updateHotelStmt.setString(2, email);
            updateHotelStmt.setString(3, contact);
            updateHotelStmt.setInt(4, hotelId);

            // Execute the update
            int rowsAffected = updateHotelStmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Hotel details updated successfully.");
            } else {
                System.out.println("Failed to update hotel details.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while updating hotel details", e);
        }
    }


    public static void deleteHotel() {
        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement checkHotelStatement = connection.prepareStatement(
                     "SELECT COUNT(*) FROM hotel WHERE HOTEL_ID = ?");
             PreparedStatement checkUpcomingReservationsStatement = connection.prepareStatement(
                     "SELECT COUNT(*) FROM reservation r " +
                             "INNER JOIN rooms rm ON r.ROOM_ID = rm.ROOM_ID " +
                             "WHERE rm.HOTEL_ID = ? AND r.CHECKOUT_DATE >= CURRENT_DATE");
             PreparedStatement deleteHotelStatement = connection.prepareStatement(
                     "DELETE FROM hotel WHERE HOTEL_ID = ?")) {

            // Prompt the user for input
            System.out.print("Enter Hotel ID to delete: ");
            int hotelId = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            // Check if the hotel exists
            checkHotelStatement.setInt(1, hotelId);
            try (ResultSet hotelResultSet = checkHotelStatement.executeQuery()) {
                if (hotelResultSet.next() && hotelResultSet.getInt(1) == 0) {
                    System.out.println("No hotel found with the given ID.");
                    return;
                }
            }

            // Check for active or upcoming reservations
            checkUpcomingReservationsStatement.setInt(1, hotelId);
            try (ResultSet reservationsResultSet = checkUpcomingReservationsStatement.executeQuery()) {
                if (reservationsResultSet.next() && reservationsResultSet.getInt(1) > 0) {
                    System.out.println("Cannot delete hotel! There are active or upcoming reservations.");
                    return;
                }
            }

            // Delete the hotel
            deleteHotelStatement.setInt(1, hotelId);
            int rowsAffected = deleteHotelStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Hotel deleted successfully.");
            } else {
                System.out.println("Failed to delete hotel.");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while deleting hotel", e);
        }
    }

    public static void createRoom() {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Prompt the user for input
            System.out.print("Enter Room Number: ");
            int roomNumber = scanner.nextInt();
            scanner.nextLine();  // Consume the newline left by nextInt()

            System.out.print("Enter Room Type: ");
            String roomType = scanner.nextLine();

            System.out.print("Enter Room Rate: ");
            double roomRate = scanner.nextDouble();
            scanner.nextLine();  // Consume the newline left by nextDouble()

            // Establish a connection to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_db", "username", "password");

            // Prepare the SQL INSERT statement
            String insertSQL = "INSERT INTO room (ROOM_NUMBER, ROOM_TYPE, ROOM_RATE) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, roomNumber);
            preparedStatement.setString(2, roomType);
            preparedStatement.setDouble(3, roomRate);

            // Execute the insert
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Room created successfully.");
            } else {
                System.out.println("Room creation failed.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while creating room", e);
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

    public static void showLocations() {
        try (Connection con = DatabaseConnection.getConnection()) {
            // SQL query to fetch location details
            String query = "SELECT LOCATION_ID, STREET_ADDRESS, CITY, POSTAL_CODE, STATE_PROVINCE, COUNTRY_ID FROM location";

            try (PreparedStatement stmt = con.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                // Print the header of the table
                System.out.println("+--------------+------------------------------+-----------------+--------------+-------------------------+-------------+");
                System.out.printf("| %-12s | %-28s | %-15s | %-12s | %-23s | %-11s |\n",
                        "LOCATION_ID", "STREET_ADDRESS", "CITY", "POSTAL_CODE", "STATE_PROVINCE", "COUNTRY_ID");
                System.out.println("+--------------+------------------------------+-----------------+--------------+-------------------------+-------------+");

                // Loop through the result set and print each location record
                while (rs.next()) {
                    int locationId = rs.getInt("LOCATION_ID");
                    String streetAddress = rs.getString("STREET_ADDRESS");
                    String city = rs.getString("CITY");
                    String postalCode = rs.getString("POSTAL_CODE");
                    String stateProvince = rs.getString("STATE_PROVINCE");
                    int countryId = rs.getInt("COUNTRY_ID");

                    // Print the location details
                    System.out.printf("| %-12d | %-28s | %-15s | %-12s | %-23s | %-11d |\n",
                            locationId, streetAddress, city, postalCode, stateProvince, countryId);
                    System.out.println("+--------------+------------------------------+-----------------+--------------+-------------------------+-------------+");
                }

            } catch (SQLException e) {
                logger.log(Level.SEVERE, "SQL Exception occurred while fetching location details", e);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database connection error", e);
        }
    }
}