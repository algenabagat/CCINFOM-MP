import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportsManagement {
    // Method to generate hotel revenue report
    // Generate Hotel Revenue Report
    private static void generateHotelRevenueReport() {
        String period = getTimePeriod();
        System.out.println("Generating Hotel Revenue Report for " + period + "...");

        // Get the start and end dates based on the selected period
        String startDate = "", endDate = "";
        switch (period) {
            case "daily":
                startDate = InputValidator.getValidStringInput("Enter the date (YYYY-MM-DD) for the daily report: ");
                endDate = startDate; // Same day for daily report
                break;
            case "monthly":
                String monthYear = InputValidator.getValidStringInput("Enter the month and year (YYYY-MM) for the monthly report: ");
                startDate = monthYear + "-01";  // First day of the month
                endDate = monthYear + "-31";  // Assuming 31 days (you could adjust for month length)
                break;
            case "yearly":
                String year = InputValidator.getValidStringInput("Enter the year (YYYY) for the yearly report: ");
                startDate = year + "-01-01";  // First day of the year
                endDate = year + "-12-31";  // Last day of the year
                break;
            default:
                System.out.println("Invalid period. Defaulting to daily.");
                startDate = endDate = InputValidator.getValidStringInput("Enter the date (YYYY-MM-DD): ");
                break;
        }

        // Construct the SQL query based on the selected period
        String sql = "SELECT h.HOTEL_ID, h.HOTEL_NAME, COUNT(r.ROOM_ID) AS NUM_ROOMS, SUM(p.AMOUNT) AS TOTAL_EARNINGS "
                + "FROM hotel h "
                + "JOIN rooms r ON h.HOTEL_ID = r.HOTEL_ID "
                + "JOIN reservation res ON r.ROOM_ID = res.ROOM_ID "
                + "JOIN payment p ON res.RESERVATION_ID = p.RESERVATION_ID "
                + "WHERE p.PAYMENT_DATE BETWEEN ? AND ? "
                + "GROUP BY h.HOTEL_ID";

        // Establish database connection
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            // Set the date range for the query
            stmt.setString(1, startDate);
            stmt.setString(2, endDate);

            // Execute the query and process the result
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int hotelId = rs.getInt("HOTEL_ID");
                String hotelName = rs.getString("HOTEL_NAME");
                int numRooms = rs.getInt("NUM_ROOMS");
                double totalEarnings = rs.getDouble("TOTAL_EARNINGS");

                System.out.printf("Hotel ID: %d\nHotel Name: %s\nNumber of Rooms: %d\nTotal Earnings: %.2f\n\n",
                        hotelId, hotelName, numRooms, totalEarnings);
            }

        } catch (SQLException e) {
            System.out.println("Error while generating the report: " + e.getMessage());
        }
    }


    // Method to generate employee hiring report
    private void generateEmployeeHiringReport() {
        // Implement logic for generating employee hiring report
        System.out.println("Generating Employee Hiring Report...");
        // Example logic
        // fetch employee data from database
        // display the report
    }

    // Method to generate reservations report
    private void generateReservationsReport() {
        // Implement logic for generating reservations report
        System.out.println("Generating Reservations Report...");
        // Example logic
        // fetch reservation data from database
        // display the report
    }

    // Method to generate payments report
    private void generatePaymentsReport() {
        // Implement logic for generating payments report
        System.out.println("Generating Payments Report...");
        // Example logic
        // fetch payments data from database
        // display the report
    }

    // Ask the user to choose daily, monthly, or yearly report
    private static String getTimePeriod() {
        System.out.println("Select Time Period:");
        System.out.println("1. Daily");
        System.out.println("2. Monthly");
        System.out.println("3. Yearly");
        int periodChoice = InputValidator.getValidIntInput("Enter your choice: ");

        String period;
        switch (periodChoice) {
            case 1:
                period = "daily";
                break;
            case 2:
                period = "monthly";
                break;
            case 3:
                period = "yearly";
                break;
            default:
                System.out.println("Invalid choice. Defaulting to daily.");
                period = "daily";
                break;
        }
        return period;
    }
}
