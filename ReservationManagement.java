import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationManagement {
    private static final Logger logger = Logger.getLogger(ReservationManagement.class.getName());

    public static void viewReservations() {
        String query = "SELECT " +
            "r.RESERVATION_ID, r.GUEST_ID, g.GUEST_NAME, r.CHECKIN_DATE, r.CHECKOUT_DATE, " +
            "r.RESERVATION_STATUS, h.HOTEL_NAME " +
            "FROM reservation r " +
            "JOIN hotel h ON r.HOTEL_ID = h.HOTEL_ID " +
            "JOIN guest g ON r.GUEST_ID = g.GUEST_ID " +
            "ORDER BY r.RESERVATION_ID";  // Ordering by RESERVATION_ID in ascending order


        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Print the table header with partitions
            System.out.println("+----------------+------------+---------------------------+--------------+---------------+----------------------+------------------+");
            System.out.printf("| %-14s | %-10s | %-25s | %-12s | %-15s | %-20s | %-16s |\n",
                    "Reservation ID", "Guest ID", "Guest Name", "Check-in Date", "Check-out Date", "Reservation Status", "Hotel Name");
            System.out.println("+----------------+------------+---------------------------+--------------+---------------+----------------------+------------------+");

            // Loop through the result set and print each reservation record in tabular format
            while (rs.next()) {
                int reservationId = rs.getInt("RESERVATION_ID");
                int guestId = rs.getInt("GUEST_ID");
                String guestName = rs.getString("GUEST_NAME");
                Date checkinDate = rs.getDate("CHECKIN_DATE");
                Date checkoutDate = rs.getDate("CHECKOUT_DATE");
                String reservationStatus = rs.getString("RESERVATION_STATUS");
                String hotelName = rs.getString("HOTEL_NAME");

                // Print the reservation details with partitions and proper borders
                System.out.printf("| %-14d | %-10d | %-25s | %-12s | %-15s | %-20s | %-16s |\n",
                        reservationId, guestId, guestName, 
                        (checkinDate != null ? checkinDate.toString() : "null"),
                        (checkoutDate != null ? checkoutDate.toString() : "null"),
                        reservationStatus, hotelName);
                System.out.println("+----------------+------------+---------------------------+--------------+---------------+----------------------+------------------+");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while viewing reservation details", e);
        }
    }
}
