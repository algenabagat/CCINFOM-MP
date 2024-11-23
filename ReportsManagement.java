import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportsManagement {

    private static final Logger logger = Logger.getLogger(ReportsManagement.class.getName());

    // Method to generate hotel revenue report
    public static void generateHotelRevenueReport() {
        // Get the start and end dates based on the selected period
        String[] dateRange = getStartAndEndDates();
        String startDate = dateRange[0];
        String endDate = dateRange[1];
        String period = dateRange[2];

        System.out.println("Generating Hotel Revenue Report for " + period + "...");

        // SQL query to get hotel revenue and room count for the selected time period
        String sql = "SELECT h.HOTEL_ID, h.HOTEL_NAME, COUNT(r.ROOM_ID) AS NUM_ROOMS, "
                + "SUM(p.AMOUNT) AS TOTAL_EARNINGS "
                + "FROM hotel h "
                + "JOIN rooms r ON h.HOTEL_ID = r.HOTEL_ID "
                + "LEFT JOIN reservation res ON r.ROOM_ID = res.ROOM_ID "
                + "LEFT JOIN payment p ON res.RESERVATION_ID = p.RESERVATION_ID "
                + "AND p.PAYMENT_DATE BETWEEN ? AND ? "
                + "AND res.RESERVATION_STATUS = 'Paid' "
                + "GROUP BY h.HOTEL_ID";

        // Print the table border
        System.out.println("+------------+--------------------------------+-----------------+-----------------+");
        System.out.printf("| %-10s | %-30s | %-15s | %-15s |\n", "Hotel ID", "Hotel Name", "Num of Rooms", "Total Earnings");
        System.out.println("+------------+--------------------------------+-----------------+-----------------+");

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

                // Print each row of data in the table
                System.out.printf("| %-10d | %-30s | %-15d | $%-14.2f |\n", hotelId, hotelName, numRooms, totalEarnings);
                System.out.println("+------------+--------------------------------+-----------------+-----------------+");
            }

        } catch (SQLException e) {
            System.out.println("Error while generating the report: " + e.getMessage());
        }
    }

    // Method to generate employee hiring report for a specific hotel
    public static void generateEmployeeHiringReport() {
        HotelManagement.viewHotelDetails();  // Display hotel details to get the hotel ID
        // Input the HOTEL_ID using InputValidator
        int hotelId = InputValidator.getValidIntInput("Enter the Hotel ID to see employee details: ");

        // Validate the hotel ID
        if (!InputValidator.validateHotel(hotelId)) {
            System.out.println("Invalid Hotel ID. Report generation aborted.");
            return; // Exit the method if the hotel ID is invalid
        }

        // Get the start and end dates based on the selected period
        String[] dateRange = getStartAndEndDates();
        String startDate = dateRange[0];
        String endDate = dateRange[1];
        String period = dateRange[2];

        System.out.println("Generating Employee Hiring Report for " + period + "...");

        // SQL query to get employees hired after the selected start date and filter by HOTEL_ID, along with job names
        String sql = "SELECT e.EMPLOYEE_ID, e.EMPLOYEE_NAME, e.EMAIL, e.CONTACT_NO, j.JOB_NAME, e.HIRE_DATE "
                + "FROM employee e "
                + "JOIN jobs j ON e.JOB_ID = j.JOB_ID "
                + "WHERE e.HIRE_DATE BETWEEN ? AND ? AND e.HOTEL_ID = ? "
                + "ORDER BY e.HIRE_DATE";

        // Print the table border
        System.out.println("+--------------+----------------------------------+------------------------------------------+------------------+--------------------------------+------------+");
        System.out.printf("| %-12s | %-32s | %-40s | %-16s | %-30s | %-10s |\n", "Employee ID", "Employee Name", "Email", "Contact No", "Job Title", "Hire Date");
        System.out.println("+--------------+----------------------------------+------------------------------------------+------------------+--------------------------------+------------+");

        // Establish database connection
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            // Set the start date, end date, and hotel ID for the query
            stmt.setString(1, startDate);
            stmt.setString(2, endDate);
            stmt.setInt(3, hotelId);

            // Execute the query and process the result
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int employeeId = rs.getInt("EMPLOYEE_ID");
                String employeeName = rs.getString("EMPLOYEE_NAME");
                String email = rs.getString("EMAIL");
                String contactNo = rs.getString("CONTACT_NO");
                String jobName = rs.getString("JOB_NAME");
                String hireDate = rs.getString("HIRE_DATE");

                // Print each row of data in the table
                System.out.printf("| %-12d | %-32s | %-40s | %-16s | %-30s | %-10s |\n",
                        employeeId, employeeName, email, contactNo, jobName, hireDate);
                System.out.println("+--------------+----------------------------------+------------------------------------------+------------------+--------------------------------+------------+");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while generating the report: ", e);
        }
    }

    // Method to generate reservations report
    public static void generateReservationsReport() {
        // Get the start and end dates based on the selected period
        String[] dateRange = getStartAndEndDates();
        String startDate = dateRange[0];
        String endDate = dateRange[1];
        String period = dateRange[2];

        // Ask the user to input hotel ID
        HotelManagement.viewHotelDetails();  // Display hotel details to get the hotel ID
        int hotelId = InputValidator.getValidIntInput("Enter the Hotel ID to generate the reservations report: ");

        // Validate the hotel ID
        if (!InputValidator.validateHotel(hotelId)) {
            System.out.println("Invalid Hotel ID. Report generation aborted.");
            return; // Exit the method if the hotel ID is invalid
        }

        System.out.println("Generating Reservations Report for " + period + "...");

        // SQL query to get reservations based on hotel ID and time period, replacing GUEST_ID with GUEST_NAME
        String sql = "SELECT r.RESERVATION_ID, g.GUEST_NAME, r.CHECKIN_DATE, r.CHECKOUT_DATE, r.RESERVATION_STATUS, ro.ROOM_NO " +
                "FROM reservation r " +
                "JOIN guest g ON r.GUEST_ID = g.GUEST_ID " +
                "JOIN rooms ro ON r.ROOM_ID = ro.ROOM_ID " +
                "WHERE ro.HOTEL_ID = ? AND r.CHECKIN_DATE BETWEEN ? AND ? " +
                "ORDER BY r.CHECKIN_DATE";

        // Print the table border
        System.out.println("+----------------+-----------------------------+------------+------------+------------------+----------+");
        System.out.printf("| %-14s | %-27s | %-10s | %-10s | %-16s | %-8s |\n",
                "Reservation ID", "Guest Name", "Check-in", "Check-out", "Reservation Status", "Room No");
        System.out.println("+----------------+-----------------------------+------------+------------+------------------+----------+");

        // Establish database connection
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            // Set the parameters for the query
            stmt.setInt(1, hotelId);  // Hotel ID
            stmt.setString(2, startDate);  // Start date
            stmt.setString(3, endDate);  // End date

            // Execute the query and process the result
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int reservationId = rs.getInt("RESERVATION_ID");
                String guestName = rs.getString("GUEST_NAME");
                String checkinDate = rs.getString("CHECKIN_DATE");  // Retrieving as String
                String checkoutDate = rs.getString("CHECKOUT_DATE");  // Retrieving as String
                String reservationStatus = rs.getString("RESERVATION_STATUS");
                int roomNo = rs.getInt("ROOM_NO");

                // Print each row of data in the table
                System.out.printf("| %-14d | %-27s | %-10s | %-10s | %-16s | %-8d |\n",
                        reservationId, guestName, checkinDate, checkoutDate, reservationStatus, roomNo);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while generating the report: ", e);
        }

        // Print the table border at the end
        System.out.println("+----------------+-----------------------------+------------+------------+------------------+----------+");
    }

    // Method to generate payments report
    public static void generatePaymentsReport() {
        // Ask the user for the report period
        String[] dateRange = getStartAndEndDates();
        String startDate = dateRange[0];
        String endDate = dateRange[1];
        String period = dateRange[2];

        // Ask the user for the Hotel ID
        HotelManagement.viewHotelDetails(); // Display available hotels
        int hotelId = InputValidator.getValidIntInput("Enter the Hotel ID to generate the payments report: ");

        // Validate the hotel ID
        if (!InputValidator.validateHotel(hotelId)) {
            System.out.println("Invalid Hotel ID. Report generation aborted.");
            return; // Exit if the hotel ID is invalid
        }

        System.out.println("Generating Payments Report for " + period + "...");

        // SQL query to fetch payments for the given time period and hotel
        String sql = "SELECT p.PAYMENT_ID, g.GUEST_NAME, p.PAYMENT_DATE, p.AMOUNT, " +
                "IFNULL(d.DISCOUNT_NAME, 'None') AS DISCOUNT_NAME " +
                "FROM payment p " +
                "JOIN reservation r ON p.RESERVATION_ID = r.RESERVATION_ID " +
                "JOIN guest g ON r.GUEST_ID = g.GUEST_ID " +
                "LEFT JOIN discount d ON p.DISCOUNT_ID = d.DISCOUNT_ID " +
                "WHERE r.ROOM_ID IN (SELECT ROOM_ID FROM rooms WHERE HOTEL_ID = ?) " +
                "AND p.PAYMENT_DATE BETWEEN ? AND ? " +
                "ORDER BY p.PAYMENT_DATE";

        // Print the table header
        System.out.println("+------------+-----------------------------+------------+------------+---------------------+");
        System.out.printf("| %-10s | %-27s | %-10s | %-10s | %-19s |\n",
                "Payment ID", "Guest Name", "Date", "Amount", "Discount");
        System.out.println("+------------+-----------------------------+------------+------------+---------------------+");

        // Establish database connection
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            // Set query parameters
            stmt.setInt(1, hotelId);
            stmt.setString(2, startDate);
            stmt.setString(3, endDate);

            // Execute the query and process the results
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int paymentId = rs.getInt("PAYMENT_ID");
                String guestName = rs.getString("GUEST_NAME");
                String paymentDate = rs.getString("PAYMENT_DATE");
                double amount = rs.getDouble("AMOUNT");
                String discountName = rs.getString("DISCOUNT_NAME");

                // Print each row
                System.out.printf("| %-10d | %-27s | %-10s | %-10.2f | %-19s |\n",
                        paymentId, guestName, paymentDate, amount, discountName);
                System.out.println("+------------+-----------------------------+------------+------------+---------------------+");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while generating the payments report: ", e);
        }
    }

    // Ask the user to choose daily, monthly, or yearly report
    public static String getTimePeriod() {
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

    // Helper function to get the start and end dates for the selected time period
    public static String[] getStartAndEndDates() {
        String period = getTimePeriod();
        String startDate, endDate;

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

        return new String[] { startDate, endDate, period };
    }
}
