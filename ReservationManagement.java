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
            System.out.print("Enter Hotel ID: ");
            int selectedHotelId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Step 3: Fetch and display rooms for the selected hotel in a detailed table format
            String fetchRoomsQuery = "SELECT ROOM_ID, ROOM_NO FROM rooms WHERE HOTEL_ID = ?";
            try (PreparedStatement roomStmt = con.prepareStatement(fetchRoomsQuery)) {
                roomStmt.setInt(1, selectedHotelId);
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
                    return;
                }
            }

            // Step 4: Prompt the user to enter a room ID and validate it
            System.out.print("Enter Room ID: ");
            int selectedRoomId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            String validateRoomQuery = "SELECT COUNT(*) AS room_count FROM rooms WHERE ROOM_ID = ? AND HOTEL_ID = ?";
            try (PreparedStatement validateRoomStmt = con.prepareStatement(validateRoomQuery)) {
                validateRoomStmt.setInt(1, selectedRoomId);
                validateRoomStmt.setInt(2, selectedHotelId);
                ResultSet validateRoomRs = validateRoomStmt.executeQuery();

                validateRoomRs.next();
                int roomCount = validateRoomRs.getInt("room_count");
                if (roomCount == 0) {
                    System.out.println("Invalid Room ID! Aborting reservation.");
                    return;
                }
            }

            // Step 5: Collect reservation details
            System.out.print("Enter Guest ID: ");
            int guestId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter Check-in Date (YYYY-MM-DD): ");
            String checkinDate = scanner.nextLine();

            System.out.print("Enter Check-out Date (YYYY-MM-DD): ");
            String checkoutDate = scanner.nextLine();

            // Step 6: Check for overlapping reservations
            String checkOverlapQuery = "SELECT COUNT(*) AS overlap_count FROM reservation " +
                    "WHERE ROOM_ID = ? AND " +
                    "((CHECKIN_DATE <= ? AND CHECKOUT_DATE > ?) OR " +
                    "(CHECKIN_DATE < ? AND CHECKOUT_DATE >= ?))";

            try (PreparedStatement checkStmt = con.prepareStatement(checkOverlapQuery)) {
                checkStmt.setInt(1, selectedRoomId);
                checkStmt.setDate(2, Date.valueOf(checkinDate));
                checkStmt.setDate(3, Date.valueOf(checkinDate));
                checkStmt.setDate(4, Date.valueOf(checkoutDate));
                checkStmt.setDate(5, Date.valueOf(checkoutDate));

                ResultSet overlapRs = checkStmt.executeQuery();
                overlapRs.next();
                int overlapCount = overlapRs.getInt("overlap_count");

                if (overlapCount > 0) {
                    System.out.println("Cannot add reservation! The selected room already has a reservation on the selected dates.");
                    return;
                }
            }

            // Step 7: Insert the reservation
            String insertQuery = "INSERT INTO reservation (GUEST_ID, ROOM_ID, CHECKIN_DATE, CHECKOUT_DATE, RESERVATION_STATUS) " +
                    "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
                insertStmt.setInt(1, guestId);
                insertStmt.setInt(2, selectedRoomId);
                insertStmt.setDate(3, Date.valueOf(checkinDate));
                insertStmt.setDate(4, Date.valueOf(checkoutDate));
                insertStmt.setString(5, "Confirmed");

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
        System.out.print("Enter Reservation ID: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Ask for Room ID before the dates
        System.out.print("Enter Room ID for the reservation: ");
        int roomId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Check for overlapping reservations before proceeding
        System.out.print("Enter New Check-in Date (YYYY-MM-DD): ");
        String newCheckinDate = scanner.nextLine();

        System.out.print("Enter New Check-out Date (YYYY-MM-DD): ");
        String newCheckoutDate = scanner.nextLine();

        // Query to check for overlapping reservations in the same room
        String checkOverlapQuery = "SELECT COUNT(*) AS overlap_count FROM reservation " +
                "WHERE ROOM_ID = ? AND RESERVATION_ID != ? AND " +
                "((CHECKIN_DATE <= ? AND CHECKOUT_DATE > ?) OR " +
                "(CHECKIN_DATE < ? AND CHECKOUT_DATE >= ?))";

        // SQL query to update the reservation
        String updateQuery = "UPDATE reservation " +
                "SET CHECKIN_DATE = ?, CHECKOUT_DATE = ?, RESERVATION_STATUS = ? " +
                "WHERE RESERVATION_ID = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = con.prepareStatement(checkOverlapQuery);
             PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {

            // Check for overlapping reservations for the selected room
            checkStmt.setInt(1, roomId);
            checkStmt.setInt(2, reservationId); // Exclude the current reservation from the overlap check
            checkStmt.setDate(3, Date.valueOf(newCheckinDate));
            checkStmt.setDate(4, Date.valueOf(newCheckinDate));
            checkStmt.setDate(5, Date.valueOf(newCheckoutDate));
            checkStmt.setDate(6, Date.valueOf(newCheckoutDate));

            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int overlapCount = rs.getInt("overlap_count");

            if (overlapCount > 0) {
                System.out.println("Cannot update reservation! The selected room already has a reservation on the selected dates.");
                return;
            }

            // Ask for Reservation Status after confirming availability
            System.out.print("Enter New Reservation Status: ");
            String newReservationStatus = scanner.nextLine();

            // Proceed to update the reservation
            updateStmt.setDate(1, Date.valueOf(newCheckinDate));
            updateStmt.setDate(2, Date.valueOf(newCheckoutDate));
            updateStmt.setString(3, newReservationStatus); // Set the new reservation status
            updateStmt.setInt(4, reservationId);

            int rowsAffected = updateStmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Reservation updated successfully.");
            } else {
                System.out.println("Failed to update reservation.");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while updating reservation", e);
        }
    }

    public static void cancelReservation() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Reservation ID to cancel: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

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
}
