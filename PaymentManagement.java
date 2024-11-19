import java.sql.*;
import java.util.Scanner;
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
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Discount Name: ");
        String discountName = scanner.nextLine();

        System.out.print("Enter Discount Percentage (e.g., 0.10 for 10%): ");
        double discountPercentage;
        try {
            discountPercentage = scanner.nextDouble();
            if (discountPercentage < 0 || discountPercentage > 1) {
                System.out.println("Error: Discount percentage must be between 0 and 1.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Error: Invalid discount percentage format.");
            return;
        }

        String insertQuery = "INSERT INTO discount (DISCOUNT_NAME, DISCOUNT_PERCENTAGE) VALUES (?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(insertQuery)) {

            pstmt.setString(1, discountName);
            pstmt.setDouble(2, discountPercentage);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Discount added successfully.");
            } else {
                System.out.println("Failed to add discount.");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while adding discount", e);
        }
    }

    public static void showDiscount() {
        String query = "SELECT * FROM discount";

        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Print the table header with partitions
            System.out.println("+-------------+------------------------+----------------------+");
            System.out.printf("| %-11s | %-22s | %-20s |\n", "Discount ID", "Discount Name", "Discount Percentage");
            System.out.println("+-------------+------------------------+----------------------+");

            // Loop through the result set and print each discount record in tabular format
            while (rs.next()) {
                int discountId = rs.getInt("DISCOUNT_ID");
                String discountName = rs.getString("DISCOUNT_NAME");
                double discountPercentage = rs.getDouble("DISCOUNT_PERCENTAGE");

                // Print the discount details with partitions and proper borders
                System.out.printf("| %-11d | %-22s | %-20.4f |\n", discountId, discountName, discountPercentage);
                System.out.println("+-------------+------------------------+----------------------+");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while showing discount details", e);
        }
    }

    public static void deleteDiscount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Discount ID to delete: ");
        int discountId = scanner.nextInt();

        String checkQuery = "SELECT COUNT(*) AS count FROM discount WHERE DISCOUNT_ID = ?";
        String deleteQuery = "DELETE FROM discount WHERE DISCOUNT_ID = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = con.prepareStatement(checkQuery);
             PreparedStatement deleteStmt = con.prepareStatement(deleteQuery)) {

            // Check if the discount exists
            checkStmt.setInt(1, discountId);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt("count");

            if (count == 0) {
                System.out.println("Error: Discount ID " + discountId + " does not exist. No action taken.");
                return;
            }

            // Proceed to delete if exists
            deleteStmt.setInt(1, discountId);
            int rowsAffected = deleteStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Discount with ID " + discountId + " has been successfully deleted.");
            } else {
                System.out.println("Failed to delete the discount. Please try again.");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while deleting the discount", e);
        }
    }

    public static void updateDiscount() {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for discount ID to update
        System.out.print("Enter the Discount ID to update: ");
        int discountId = scanner.nextInt();

        // Check if discount exists in the database before updating
        boolean exists = false;
        String checkQuery = "SELECT COUNT(*) FROM discount WHERE DISCOUNT_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(checkQuery)) {

            stmt.setInt(1, discountId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                exists = true;
            }

        } catch (SQLException e) {
            System.out.println("Error checking if discount exists.");
            logger.log(Level.SEVERE, "SQL Exception occurred while showing discount details", e);
        }

        if (!exists) {
            System.out.println("Discount with ID " + discountId + " does not exist.");
            return;
        }

        // Prompt user for new discount details
        System.out.print("Enter the new discount name: ");
        scanner.nextLine();  // Consume newline character left by nextInt
        String newDiscountName = scanner.nextLine();

        // Validate discount percentage between 0 and 1 (inclusive)
        double newDiscountPercentage;
        while (true) {
            System.out.print("Enter the new discount percentage (between 0 and 1): ");
            newDiscountPercentage = scanner.nextDouble();

            if (newDiscountPercentage >= 0 && newDiscountPercentage <= 1) {
                break; // Valid input
            } else {
                System.out.println("Invalid discount percentage. Please enter a value between 0 and 1.");
            }
        }

        // Attempt to update the discount record in the database
        String updateQuery = "UPDATE discount SET DISCOUNT_NAME = ?, DISCOUNT_PERCENTAGE = ? WHERE DISCOUNT_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, newDiscountName);
            stmt.setDouble(2, newDiscountPercentage);
            stmt.setInt(3, discountId);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Discount updated successfully.");
            } else {
                System.out.println("Failed to update discount.");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while updating discount.");
            logger.log(Level.SEVERE, "SQL Exception occurred while showing discount details", e);
        }
    }


}
