import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentManagement {
    private static final Logger logger = Logger.getLogger(PaymentManagement.class.getName());

    public static void addPayment() {
        ReservationManagement.viewReservations();
        int reservationId = InputValidator.getValidIntInput("Enter Reservation ID to make payment: ");

        try (Connection con = DatabaseConnection.getConnection()) {

            // Check if the reservation exists and its status
            String checkReservationQuery = "SELECT RESERVATION_STATUS FROM reservation WHERE RESERVATION_ID = ?";
            try (PreparedStatement checkReservationStmt = con.prepareStatement(checkReservationQuery)) {
                checkReservationStmt.setInt(1, reservationId);
                ResultSet rs = checkReservationStmt.executeQuery();

                if (!rs.next()) {
                    System.out.println("Reservation ID not found. Aborting payment.");
                    return;
                }

                String reservationStatus = rs.getString("RESERVATION_STATUS");
                if ("Paid".equalsIgnoreCase(reservationStatus)) {
                    System.out.println("This reservation is already paid. No further payment is allowed.");
                    return;
                }
            }

            // Ask for Discount ID (allow blank)
            showDiscount();
            System.out.print("Enter Discount ID (or leave blank for no discount): ");
            String discountInput = InputValidator.getInput();
            Integer discountId = (discountInput.isEmpty()) ? null : Integer.parseInt(discountInput);

            if (discountId != null) {
                // Validate Discount ID
                String discountValidationQuery = "SELECT 1 FROM discount WHERE DISCOUNT_ID = ?";
                try (PreparedStatement discountStmt = con.prepareStatement(discountValidationQuery)) {
                    discountStmt.setInt(1, discountId);
                    ResultSet discountRs = discountStmt.executeQuery();
                    if (!discountRs.next()) {
                        System.out.println("Invalid Discount ID. Aborting payment.");
                        return;
                    }
                }
            }

            // Ask for Payment Method ID
            showPaymentMethods();
            int paymentMethodId = InputValidator.getValidIntInput("Enter Payment Method ID: ");

            // Validate Payment Method ID
            String paymentMethodValidationQuery = "SELECT 1 FROM payment_method WHERE PAYMENT_METHOD_ID = ?";
            try (PreparedStatement paymentMethodStmt = con.prepareStatement(paymentMethodValidationQuery)) {
                paymentMethodStmt.setInt(1, paymentMethodId);
                ResultSet paymentMethodRs = paymentMethodStmt.executeQuery();
                if (!paymentMethodRs.next()) {
                    System.out.println("Invalid Payment Method ID. Aborting payment.");
                    return;
                }
            }

            // Fetch ROOM_ID and calculate price
            String fetchRoomQuery = "SELECT r.ROOM_ID, rt.ROOM_PRICE FROM reservation r "
                    + "JOIN rooms ro ON r.ROOM_ID = ro.ROOM_ID "
                    + "JOIN roomtype rt ON ro.TYPE_ID = rt.ROOMTYPE_ID WHERE r.RESERVATION_ID = ?";
            try (PreparedStatement fetchRoomStmt = con.prepareStatement(fetchRoomQuery)) {
                fetchRoomStmt.setInt(1, reservationId);
                ResultSet roomRs = fetchRoomStmt.executeQuery();

                if (!roomRs.next()) {
                    System.out.println("Room details for the reservation not found. Aborting payment.");
                    return;
                }

                double roomPrice = roomRs.getDouble("ROOM_PRICE");

                // Apply discount if applicable
                double discountPercentage = 0;
                if (discountId != null) {
                    String discountQuery = "SELECT DISCOUNT_PERCENTAGE FROM discount WHERE DISCOUNT_ID = ?";
                    try (PreparedStatement discountStmt = con.prepareStatement(discountQuery)) {
                        discountStmt.setInt(1, discountId);
                        ResultSet discountRs = discountStmt.executeQuery();
                        if (discountRs.next()) {
                            discountPercentage = discountRs.getDouble("DISCOUNT_PERCENTAGE");
                        }
                    }
                }

                double finalAmount = roomPrice * (1 - discountPercentage / 100);

                // Insert payment record
                String paymentQuery = "INSERT INTO payment (RESERVATION_ID, PAYMENT_DATE, AMOUNT, DISCOUNT_ID, PAYMENT_METHOD_ID) "
                        + "VALUES (?, CURDATE(), ?, ?, ?)";
                try (PreparedStatement paymentStmt = con.prepareStatement(paymentQuery)) {
                    paymentStmt.setInt(1, reservationId);
                    paymentStmt.setDouble(2, finalAmount);
                    if (discountId != null) {
                        paymentStmt.setInt(3, discountId);
                    } else {
                        paymentStmt.setNull(3, java.sql.Types.INTEGER);
                    }
                    paymentStmt.setInt(4, paymentMethodId);

                    int rowsAffected = paymentStmt.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Payment added successfully.");

                        // Update reservation status to 'Paid'
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
        } catch (SQLException e) {
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
        // Require user to input a non-empty Discount Name
        String discountName;
        while (true) {
            System.out.print("Enter Discount Name: ");
            discountName = InputValidator.getInput();
            if (!discountName.isEmpty()) {
                break;
            }
            System.out.println("Error: Discount name cannot be empty. Please try again.");
        }

        // Validate discount percentage input
        double discountPercentage = InputValidator.getValidDoubleInput("Enter Discount Percentage (e.g., 0.10 for 10%): ");
        if (discountPercentage < 0 || discountPercentage > 1) {
            System.out.println("Error: Discount percentage must be between 0 and 1.");
            return;
        }

        // Add the discount to the database
        try (Connection con = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO discount (DISCOUNT_NAME, DISCOUNT_PERCENTAGE) VALUES (?, ?)";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setString(1, discountName);
                stmt.setDouble(2, discountPercentage);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Discount added successfully.");
                } else {
                    System.out.println("Failed to add discount.");
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception occurred while adding discount: " + e.getMessage());
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

    public static void showPaymentMethods() {
        // Establish database connection inside a try-catch block
        try (Connection con = DatabaseConnection.getConnection()) {

            // SQL query to fetch all payment methods
            String paymentMethodQuery = "SELECT PAYMENT_METHOD_ID, METHOD_NAME FROM payment_method";

            try (PreparedStatement stmt = con.prepareStatement(paymentMethodQuery);
                 ResultSet rs = stmt.executeQuery()) {

                // Check if there are payment methods to display
                if (!rs.isBeforeFirst()) {
                    System.out.println("No payment methods found.");
                    return; // Exit if no payment methods exist
                }

                // Display table header with borders
                System.out.println("+--------------------+--------------------+");
                System.out.printf("| %-18s | %-18s |%n", "Payment Method ID", "Method Name");
                System.out.println("+--------------------+--------------------+");

                // Loop through the result set and display each payment method
                while (rs.next()) {
                    int paymentMethodId = rs.getInt("PAYMENT_METHOD_ID");
                    String methodName = rs.getString("METHOD_NAME");

                    // Print each row in tabular format
                    System.out.printf("| %-18d | %-18s |%n", paymentMethodId, methodName);
                    System.out.println("+--------------------+--------------------+");
                }
            } catch (SQLException e) {
                // Handle SQL exceptions during query execution
                logger.log(Level.SEVERE, "SQL Exception occurred while fetching payment methods", e);
                System.out.println("An error occurred while retrieving the payment methods.");
            }
        } catch (SQLException e) {
            // Handle exceptions during database connection
            logger.log(Level.SEVERE, "SQL Exception occurred while connecting to the database", e);
            System.out.println("An error occurred while connecting to the database.");
        }
    }

}
