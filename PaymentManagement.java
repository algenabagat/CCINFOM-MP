import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentManagement {
    private static final Logger logger = Logger.getLogger(PaymentManagement.class.getName());

    public static void addPayment() {
        // Ask the user for the Reservation ID
        int reservationId = InputValidator.getValidIntInput("Enter Reservation ID to make payment: ");

        // Ask the user for the Discount ID
        int discountId = InputValidator.getValidIntInput("Enter Discount ID: ");

        // Ask the user for the Payment Method ID
        int paymentMethodId = InputValidator.getValidIntInput("Enter Payment Method ID: ");

        // Establishing database connection inside try-catch block
        try (Connection con = DatabaseConnection.getConnection()) {

            // SQL query to get the ROOM_ID from the reservation for the given RESERVATION_ID
            String roomIdQuery = "SELECT ROOM_ID FROM reservation WHERE RESERVATION_ID = ?";

            try (PreparedStatement roomStmt = con.prepareStatement(roomIdQuery)) {
                roomStmt.setInt(1, reservationId);
                ResultSet roomRs = roomStmt.executeQuery();

                if (!roomRs.next()) {
                    System.out.println("Reservation ID not found. Aborting payment.");
                    return; // Abort if reservation ID is not found
                }

                // Get the ROOM_ID from the reservation table
                int roomId = roomRs.getInt("ROOM_ID");

                // SQL query to get the TYPE_ID for the given ROOM_ID from the rooms table
                String roomTypeQuery = "SELECT TYPE_ID FROM rooms WHERE ROOM_ID = ?";
                try (PreparedStatement roomTypeStmt = con.prepareStatement(roomTypeQuery)) {
                    roomTypeStmt.setInt(1, roomId);
                    ResultSet roomTypeRs = roomTypeStmt.executeQuery();

                    if (!roomTypeRs.next()) {
                        System.out.println("Room type not found. Aborting payment.");
                        return; // Abort if room type is not found
                    }

                    // Get the TYPE_ID from the rooms table
                    int typeId = roomTypeRs.getInt("TYPE_ID");

                    // SQL query to get the ROOM_PRICE for the given TYPE_ID from the roomtype table
                    String roomPriceQuery = "SELECT ROOM_PRICE FROM roomtype WHERE ROOMTYPE_ID = ?";
                    try (PreparedStatement priceStmt = con.prepareStatement(roomPriceQuery)) {
                        priceStmt.setInt(1, typeId);
                        ResultSet priceRs = priceStmt.executeQuery();

                        if (!priceRs.next()) {
                            System.out.println("Room price not found. Aborting payment.");
                            return; // Abort if room price is not found
                        }

                        // Get the room price
                        double roomPrice = priceRs.getDouble("ROOM_PRICE");

                        // SQL query to get the discount percentage
                        String discountQuery = "SELECT DISCOUNT_PERCENTAGE FROM discount WHERE DISCOUNT_ID = ?";
                        try (PreparedStatement discountStmt = con.prepareStatement(discountQuery)) {
                            discountStmt.setInt(1, discountId);
                            ResultSet discountRs = discountStmt.executeQuery();

                            if (!discountRs.next()) {
                                System.out.println("Discount ID not found. Aborting payment.");
                                return; // Abort if discount ID is not found
                            }

                            // Get the discount percentage
                            double discountPercentage = discountRs.getDouble("DISCOUNT_PERCENTAGE");

                            // Calculate the amount after applying the discount
                            double finalAmount = roomPrice * (1 - discountPercentage);

                            // SQL query to insert the payment record
                            String paymentQuery = "INSERT INTO payment (RESERVATION_ID, PAYMENT_DATE, AMOUNT, DISCOUNT_ID, PAYMENT_METHOD_ID) " +
                                    "VALUES (?, CURDATE(), ?, ?, ?)";
                            try (PreparedStatement paymentStmt = con.prepareStatement(paymentQuery)) {
                                paymentStmt.setInt(1, reservationId);
                                paymentStmt.setDouble(2, finalAmount);
                                paymentStmt.setInt(3, discountId);
                                paymentStmt.setInt(4, paymentMethodId);

                                // Execute the payment insertion
                                int rowsAffected = paymentStmt.executeUpdate();
                                if (rowsAffected > 0) {
                                    System.out.println("Payment added successfully.");

                                    // Update the reservation status to 'Paid'
                                    String updateStatusQuery = "UPDATE reservation SET RESERVATION_STATUS = 'Paid' WHERE RESERVATION_ID = ?";
                                    try (PreparedStatement updateStmt = con.prepareStatement(updateStatusQuery)) {
                                        updateStmt.setInt(1, reservationId);
                                        updateStmt.executeUpdate();
                                        System.out.println("Reservation status updated to 'Paid'.");
                                    }
                                } else {
                                    System.out.println("Failed to add payment.");
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            // Log and handle exceptions that may arise during database operations
            logger.log(Level.SEVERE, "SQL Exception occurred while adding payment", e);
            System.out.println("An error occurred while processing the payment.");
        }
    }

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

    public static void addDiscount() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Discount Name: ");
        String discountName = scanner.nextLine();

        double discountPercentage = InputValidator.getValidDoubleInput("Enter Discount Percentage (e.g., 0.10 for 10%): "); // Validate discount percentage
        if (discountPercentage < 0 || discountPercentage > 1) {
            System.out.println("Error: Discount percentage must be between 0 and 1.");
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
        int discountId = InputValidator.getValidIntInput("Enter the Discount ID to delete: ");

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
        int discountId = InputValidator.getValidIntInput("Enter the Discount ID to update: ");

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
            logger.log(Level.SEVERE, "SQL Exception occurred while checking discount", e);
        }

        if (!exists) {
            System.out.println("Discount with ID " + discountId + " does not exist.");
            return;
        }

        // Prompt user for new discount details
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the new discount name: ");
        String newDiscountName = scanner.nextLine();

        // Validate new discount percentage
        double newDiscountPercentage;
        while (true) {
            newDiscountPercentage = InputValidator.getValidDoubleInput("Enter the new discount percentage (between 0 and 1): ");
            if (newDiscountPercentage >= 0 && newDiscountPercentage <= 1) {
                break; // Valid input
            } else {
                System.out.println("Error: Discount percentage must be between 0 and 1.");
            }
        }

        // Attempt to update the discount record
        String updateQuery = "UPDATE discount SET DISCOUNT_NAME = ?, DISCOUNT_PERCENTAGE = ? WHERE DISCOUNT_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, newDiscountName);
            stmt.setDouble(2, newDiscountPercentage);
            stmt.setInt(3, discountId);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Discount updated successfully.");
            } else {
                System.out.println("Failed to update discount.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating discount.");
            logger.log(Level.SEVERE, "SQL Exception occurred while updating the discount", e);
        }
    }
}
