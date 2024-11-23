import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationManagement {
    private static final Logger logger = Logger.getLogger(ReservationManagement.class.getName());

    public static void addReservation() {
        Scanner scanner = new Scanner(System.in);

        try (Connection con = DatabaseConnection.getConnection()) {
            // Step 1: Display available hotels
            HotelManagement.viewHotelDetails();

            // Step 2: Prompt the user to select a hotel ID
            int selectedHotelId = InputValidator.getValidIntInput("Enter Hotel ID: ");

            if (!InputValidator.validateHotel(selectedHotelId)) {
                System.out.println("Reservation aborted due to invalid Hotel ID.");
                return; // Abort if the hotel ID is invalid
            }

            // Step 3: Fetch and display rooms for the selected hotel in a detailed table format
            displayRooms(con, selectedHotelId);

            // Step 4: Prompt the user to enter a room ID and validate it
            int selectedRoomId = InputValidator.getValidIntInput("Enter Room ID: ");

            // Validate room
            if (!InputValidator.validateRoom(con, selectedRoomId, selectedHotelId)) {
                System.out.println("Reservation aborted due to invalid Room ID.");
                return; // Abort reservation if the room is invalid
            }

            // Step 5: Collect reservation details
            showGuests();
            int guestId = InputValidator.getValidIntInput("Enter Guest ID: ");

            // Validate guest
            if (!InputValidator.validateGuestId(guestId)) {
                System.out.println("Reservation aborted due to invalid Guest ID.");
                return; // Abort if the guest ID is invalid
            }

            System.out.print("Enter Check-in Date (YYYY-MM-DD): ");
            String checkinDate = scanner.nextLine();

            if (!InputValidator.validateDateFormat(checkinDate)) {
                System.out.println("Invalid date format. Please use the format YYYY-MM-DD.");
                return; // Abort reservation if the date format is invalid
            }

            System.out.print("Enter Check-out Date (YYYY-MM-DD): ");
            String checkoutDate = scanner.nextLine();

            if (!InputValidator.validateDateFormat(checkoutDate)) {
                System.out.println("Invalid date format. Please use the format YYYY-MM-DD.");
                return; // Abort reservation if the date format is invalid
            }

            // Step 6: Check for overlapping reservations
            if (!InputValidator.checkReservationOverlap(selectedRoomId, checkinDate, checkoutDate)) {
                System.out.println("Cannot add reservation! The selected room is already booked for the selected dates.");
                return; // Abort reservation if there is an overlap
            }

            // Step 7: Insert the reservation
            String insertQuery = "INSERT INTO reservation (GUEST_ID, ROOM_ID, CHECKIN_DATE, CHECKOUT_DATE, RESERVATION_STATUS) " +
                    "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
                insertStmt.setInt(1, guestId);
                insertStmt.setInt(2, selectedRoomId);
                insertStmt.setDate(3, Date.valueOf(checkinDate));
                insertStmt.setDate(4, Date.valueOf(checkoutDate));
                insertStmt.setString(5, "Not Paid");

                int rowsAffected = insertStmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Reservation added successfully.");
                } else {
                    System.out.println("Failed to add reservation.");
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while adding reservation", e);
        }
    }

    public static void viewReservations() {
        String query = "SELECT " +
                "r.RESERVATION_ID, r.GUEST_ID, g.GUEST_NAME, r.CHECKIN_DATE, r.CHECKOUT_DATE, " +
                "r.RESERVATION_STATUS, h.HOTEL_NAME, ro.ROOM_NO " +
                "FROM reservation r " +
                "JOIN guest g ON r.GUEST_ID = g.GUEST_ID " +  // Join with guest table
                "JOIN rooms ro ON r.ROOM_ID = ro.ROOM_ID " +  // Join with rooms table using ROOM_ID
                "JOIN hotel h ON ro.HOTEL_ID = h.HOTEL_ID " +  // Join with hotel table using HOTEL_ID from rooms
                "ORDER BY r.RESERVATION_ID";  // Ordering by RESERVATION_ID in ascending order

        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Print the table header with partitions
            System.out.println("+----------------+------------+---------------------------+--------------+-----------------+---------------------+--------------------------------+--------------------+");
            System.out.printf("| %-14s | %-10s | %-25s | %-12s | %-15s | %-19s | %-30s | %-18s |\n",
                    "Reservation ID", "Guest ID", "Guest Name", "Check-in", "Check-out", "Reservation Status", "Hotel Name", "Room Number");
            System.out.println("+----------------+------------+---------------------------+--------------+-----------------+---------------------+--------------------------------+--------------------+");

            // Loop through the result set and print each reservation record in tabular format
            while (rs.next()) {
                int reservationId = rs.getInt("RESERVATION_ID");
                int guestId = rs.getInt("GUEST_ID");
                String guestName = rs.getString("GUEST_NAME");
                Date checkinDate = rs.getDate("CHECKIN_DATE");
                Date checkoutDate = rs.getDate("CHECKOUT_DATE");
                String reservationStatus = rs.getString("RESERVATION_STATUS");
                String hotelName = rs.getString("HOTEL_NAME");
                String roomNumber = rs.getString("ROOM_NO");

                // Print the reservation details with partitions and proper borders
                System.out.printf("| %-14d | %-10d | %-25s | %-12s | %-15s | %-19s | %-30s | %-18s |\n",
                        reservationId, guestId, guestName,
                        (checkinDate != null ? checkinDate.toString() : "null"),
                        (checkoutDate != null ? checkoutDate.toString() : "null"),
                        reservationStatus, hotelName, roomNumber);
                System.out.println("+----------------+------------+---------------------------+--------------+-----------------+---------------------+--------------------------------+--------------------+");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while viewing reservation details", e);
        }
    }


    public static void updateReservation() {
        Scanner scanner = new Scanner(System.in);

        // Ask for Reservation ID
        int reservationId = InputValidator.getValidIntInput("Enter Reservation ID to update: ");

        // Establishing database connection inside try-catch block
        try (Connection con = DatabaseConnection.getConnection()) {

            // Validate Reservation ID
            if (!InputValidator.validateReservationId(con, reservationId)) {
                System.out.println("Invalid Reservation ID. Aborting update.");
                return; // Abort if the reservation ID is invalid
            }

            // Get the Hotel ID for the reservation
            int hotelId = getHotelIdFromReservation(con, reservationId);
            if (hotelId == -1) {
                System.out.println("Hotel ID not found for the reservation. Aborting update.");
                return; // Abort if hotel ID is not found
            }

            // Ask for Room ID before the dates
            int roomId = InputValidator.getValidIntInput("Enter Room ID: ");

            // Validate the Room ID for the given Hotel ID
            if (!InputValidator.validateRoom(con, roomId, hotelId)) {
                System.out.println("Invalid Room ID for the selected hotel. Aborting update.");
                return; // Abort if the room ID is invalid for the hotel
            }

            // Check for overlapping reservations before proceeding
            System.out.print("Enter New Check-in Date (YYYY-MM-DD): ");
            String newCheckinDate = scanner.nextLine();

            if (!InputValidator.validateDateFormat(newCheckinDate)) {
                System.out.println("Invalid date format. Please use the format YYYY-MM-DD.");
                return; // Abort reservation if the date format is invalid
            }

            System.out.print("Enter New Check-out Date (YYYY-MM-DD): ");
            String newCheckoutDate = scanner.nextLine();

            if (!InputValidator.validateDateFormat(newCheckoutDate)) {
                System.out.println("Invalid date format. Please use the format YYYY-MM-DD.");
                return; // Abort reservation if the date format is invalid
            }

            // Call checkReservationOverlap to check for overlapping reservations
            boolean isOverlapping = InputValidator.checkReservationOverlap(roomId, newCheckinDate, newCheckoutDate);
            if (isOverlapping) {
                System.out.println("Cannot update reservation! The selected room already has a reservation on the selected dates.");
                return; // Abort if there is an overlap
            }

            // SQL query to update the reservation (without the reservation status)
            String updateQuery = "UPDATE reservation " +
                    "SET CHECKIN_DATE = ?, CHECKOUT_DATE = ? " +
                    "WHERE RESERVATION_ID = ?";

            // Proceed to update the reservation
            try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
                updateStmt.setDate(1, Date.valueOf(newCheckinDate));
                updateStmt.setDate(2, Date.valueOf(newCheckoutDate));
                updateStmt.setInt(3, reservationId);

                int rowsAffected = updateStmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Reservation updated successfully.");
                } else {
                    System.out.println("Failed to update reservation.");
                }

            } catch (SQLException e) {
                // Log and handle exceptions that may arise during database operations
                logger.log(Level.SEVERE, "SQL Exception occurred while updating reservation", e);
                System.out.println("An error occurred while processing the request.");
            }

        } catch (SQLException e) {
            // Handle exceptions that occur when establishing the connection
            logger.log(Level.SEVERE, "Connection error while updating reservation", e);
            System.out.println("Unable to establish a connection to the database.");
        }
    }

    public static void cancelReservation() {
        // Ask for Reservation ID
        int reservationId = InputValidator.getValidIntInput("Enter Reservation ID to cancel: ");

        String query = "DELETE FROM reservation WHERE RESERVATION_ID = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, reservationId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Reservation cancelled successfully.");
            } else {
                System.out.println("Failed to cancel reservation.");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while cancelling reservation", e);
        }
    }

    public static void displayRooms(Connection con, int hotelId) {
        String fetchRoomsQuery = "SELECT ROOM_ID, ROOM_NO FROM rooms WHERE HOTEL_ID = ?";

        try (PreparedStatement roomStmt = con.prepareStatement(fetchRoomsQuery)) {
            roomStmt.setInt(1, hotelId);
            ResultSet roomRs = roomStmt.executeQuery();

            System.out.println("+----------+----------+");
            System.out.printf("| %-8s | %-8s |\n", "ROOM_ID", "ROOM_NO");
            System.out.println("+----------+----------+");

            boolean roomsAvailable = false;
            while (roomRs.next()) {
                roomsAvailable = true;
                int roomId = roomRs.getInt("ROOM_ID");
                int roomNo = roomRs.getInt("ROOM_NO");
                System.out.printf("| %-8d | %-8d |\n", roomId, roomNo);
                System.out.println("+----------+----------+");
            }

            if (!roomsAvailable) {
                System.out.println("No rooms available for the selected hotel.");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching rooms: " + e.getMessage());
        }
    }

    // Helper method to get Hotel ID from Reservation ID
    private static int getHotelIdFromReservation(Connection con, int reservationId) throws SQLException {
        String getHotelQuery = "SELECT HOTEL_ID FROM reservation WHERE RESERVATION_ID = ?";
        try (PreparedStatement stmt = con.prepareStatement(getHotelQuery)) {
            stmt.setInt(1, reservationId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("HOTEL_ID");
            } else {
                return -1; // If no hotel found for the reservation
            }
        }
    }

    // Function to show all guests
    public static void showGuests() {
        System.out.println("Fetching guest details...");

        // SQL query to fetch guest details
        String sql = "SELECT GUEST_ID, GUEST_NAME, CONTACT_NO, EMAIL FROM guest";

        // Print the table header
        System.out.println("+----------+-----------------------------+-----------------+----------------------------------------------------+");
        System.out.printf("| %-8s | %-27s | %-15s | %-50s |\n", "Guest ID", "Guest Name", "Contact No", "Email");
        System.out.println("+----------+-----------------------------+-----------------+----------------------------------------------------+");

        // Establish database connection and execute query
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Iterate through the result set and display each guest
            while (rs.next()) {
                int guestId = rs.getInt("GUEST_ID");
                String guestName = rs.getString("GUEST_NAME");
                String contactNo = rs.getString("CONTACT_NO");
                String email = rs.getString("EMAIL");

                // Print each guest's information
                System.out.printf("| %-8d | %-27s | %-15s | %-50s |\n", guestId, guestName, contactNo, email);
                System.out.println("+----------+-----------------------------+-----------------+----------------------------------------------------+");
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching guest details: " + e.getMessage());
        }
    }
}
