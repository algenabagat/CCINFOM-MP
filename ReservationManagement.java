import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationManagement {
    private static final Logger logger = Logger.getLogger(ReservationManagement.class.getName());

    public static void addReservation() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Guest ID: ");
        int guestId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Hotel ID: ");
        int hotelId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Check-in Date (YYYY-MM-DD): ");
        String checkinDate = scanner.nextLine();

        System.out.print("Enter Check-out Date (YYYY-MM-DD): ");
        String checkoutDate = scanner.nextLine();

        System.out.print("Enter Reservation Status: ");
        String reservationStatus = scanner.nextLine();

        String query = "INSERT INTO reservation (GUEST_ID, HOTEL_ID, CHECKIN_DATE, CHECKOUT_DATE, RESERVATION_STATUS) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, guestId);
            pstmt.setInt(2, hotelId);
            pstmt.setDate(3, Date.valueOf(checkinDate));
            pstmt.setDate(4, Date.valueOf(checkoutDate));
            pstmt.setString(5, reservationStatus);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Reservation added successfully.");
            } else {
                System.out.println("Failed to add reservation.");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while adding reservation", e);
        }
    }

    public static void viewReservations() {
        String query = "SELECT " +
                "r.RESERVATION_ID, r.GUEST_ID, g.GUEST_NAME, r.CHECKIN_DATE, r.CHECKOUT_DATE, " +
                "r.RESERVATION_STATUS, h.HOTEL_NAME, ro.ROOM_NO " +  // Added ROOM_NO
                "FROM reservation r " +
                "JOIN guest g ON r.GUEST_ID = g.GUEST_ID " +  // Join with guest table
                "JOIN rooms ro ON r.ROOM_ID = ro.ROOM_ID " +  // Join with rooms table using ROOM_ID
                "JOIN hotel h ON ro.HOTEL_ID = h.HOTEL_ID " +  // Join with hotel table using HOTEL_ID from rooms
                "ORDER BY r.RESERVATION_ID";  // Ordering by RESERVATION_ID in ascending order

        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Print the table header with partitions
            System.out.println("+----------------+------------+---------------------------+--------------+---------------+----------------------+------------------+--------------------+");
            System.out.printf("| %-14s | %-10s | %-25s | %-12s | %-15s | %-20s | %-16s | %-18s |\n",
                    "Reservation ID", "Guest ID", "Guest Name", "Check-in Date", "Check-out Date", "Reservation Status", "Hotel Name", "Room Number");
            System.out.println("+----------------+------------+---------------------------+--------------+---------------+----------------------+------------------+--------------------+");

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
                System.out.printf("| %-14d | %-10d | %-25s | %-12s | %-15s | %-20s | %-16s | %-18s |\n",
                        reservationId, guestId, guestName,
                        (checkinDate != null ? checkinDate.toString() : "null"),
                        (checkoutDate != null ? checkoutDate.toString() : "null"),
                        reservationStatus, hotelName, roomNumber);
                System.out.println("+----------------+------------+---------------------------+--------------+---------------+----------------------+------------------+--------------------+");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while viewing reservation details", e);
        }
    }



    public static void updateReservation() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Reservation ID: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter New Check-in Date (YYYY-MM-DD): ");
        String newCheckinDate = scanner.nextLine();

        System.out.print("Enter New Check-out Date (YYYY-MM-DD): ");
        String newCheckoutDate = scanner.nextLine();

        System.out.print("Enter New Reservation Status: ");
        String newReservationStatus = scanner.nextLine();

        String query = "UPDATE reservation " +
                "SET CHECKIN_DATE = ?, CHECKOUT_DATE = ?, RESERVATION_STATUS = ? " +
                "WHERE RESERVATION_ID = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setDate(1, Date.valueOf(newCheckinDate));
            pstmt.setDate(2, Date.valueOf(newCheckoutDate));
            pstmt.setString(3, newReservationStatus);
            pstmt.setInt(4, reservationId);

            int rowsAffected = pstmt.executeUpdate();
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
