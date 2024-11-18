import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentManagement {
    private static final Logger logger = Logger.getLogger(PaymentManagement.class.getName());

    public static void viewPayments() {
        String query = "SELECT " +
                "p.PAYMENT_ID, p.RESERVATION_ID, p.PAYMENT_DATE, p.AMOUNT, " +
                "p.DISCOUNT_ID, p.PAYMENT_METHOD_ID, r.RESERVATION_STATUS, g.GUEST_NAME " + // Added guest name
                "FROM payment p " +
                "JOIN reservation r ON p.RESERVATION_ID = r.RESERVATION_ID " + // Join with reservation table
                "JOIN guest g ON r.GUEST_ID = g.GUEST_ID";  // Join with guest table

        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Print the table header with partitions
            System.out.println("+----------------+----------------+----------------+--------------+-------------------+------------------------+---------------------------+");
            System.out.printf("| %-14s | %-14s | %-14s | %-12s | %-17s | %-22s | %-25s |\n",
                    "Payment ID", "Reservation ID", "Payment Date", "Amount", "Discount ID", "Payment Method ID", "Guest Name");
            System.out.println("+----------------+----------------+----------------+--------------+-------------------+------------------------+---------------------------+");

            // Loop through the result set and print each payment record in tabular format
            while (rs.next()) {
                int paymentId = rs.getInt("PAYMENT_ID");
                int reservationId = rs.getInt("RESERVATION_ID");
                Date paymentDate = rs.getDate("PAYMENT_DATE");
                double amount = rs.getDouble("AMOUNT");
                int discountId = rs.getInt("DISCOUNT_ID");
                int paymentMethodId = rs.getInt("PAYMENT_METHOD_ID");
                String guestName = rs.getString("GUEST_NAME");

                // Print the payment details with partitions and proper borders
                System.out.printf("| %-14d | %-14d | %-14s | %-12.2f | %-17d | %-22d | %-25s |\n",
                        paymentId, reservationId, (paymentDate != null ? paymentDate.toString() : "null"),
                        amount, discountId, paymentMethodId, guestName);
                System.out.println("+----------------+----------------+----------------+--------------+-------------------+------------------------+---------------------------+");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while viewing payment details", e);
        }
    }
    public static void processPayment() {
        // Placeholder method for processing payment
        // Adds payment record to the database
        // Includes discounts and other types of payments
        System.out.println("Processing payment...");
        //room price * (1 - discount_percent) = total price
    }

    public static void addDiscount() {
        // Placeholder method for adding discount
        // Adds discount record to the database
        System.out.println("Adding discount...");
    }

    public static void updateDiscount() {
        // Placeholder method for updating payment
        // Updates payment record in the database
        System.out.println("Updating payment...");
    }

    public static void deleteDiscount() {
        // Placeholder method for deleting payment
        // Deletes payment record from the database
        System.out.println("Deleting payment...");
    }
}
