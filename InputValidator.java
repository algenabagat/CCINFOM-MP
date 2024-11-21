import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputValidator {

    // Initialize the logger
    private static final Logger logger = Logger.getLogger(InputValidator.class.getName());

    // Validate Salary method
    public static boolean validateSalary(int jobId, double salary) {
        String salaryRangeQuery = "SELECT MIN_SALARY, MAX_SALARY FROM jobs WHERE JOB_ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement salaryStmt = con.prepareStatement(salaryRangeQuery)) {

            salaryStmt.setInt(1, jobId);
            try (ResultSet jobRs = salaryStmt.executeQuery()) {
                if (jobRs.next()) {
                    double minSalary = jobRs.getDouble("MIN_SALARY");
                    double maxSalary = jobRs.getDouble("MAX_SALARY");

                    // Check if salary is within the valid range
                    if (salary < minSalary || salary > maxSalary) {
                        System.out.printf("Salary must be between %.2f and %.2f for this job.%n", minSalary, maxSalary);
                        return false;
                    }
                } else {
                    System.out.println("Invalid Job ID. Salary validation failed.");
                    return false;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while validating salary", e);
            return false;
        }

        return true; // Salary is valid
    }

    // Validate Employee method
    public static boolean validateEmployee(int employeeId) {
        String query = "SELECT COUNT(*) FROM employee WHERE EMPLOYEE_ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, employeeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true; // Employee exists
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while validating Employee ID", e);
        }
        return false; // Employee does not exist
    }

    // Validate Job method
    public static boolean validateJob(int jobId) {
        String query = "SELECT COUNT(*) FROM jobs WHERE JOB_ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, jobId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true; // Job ID is valid
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while validating Job ID", e);
        }
        return false; // Job ID is invalid
    }

    // Validate Date Format method
    public static boolean validateDateFormat(String dateStr) {
        // Define the expected date format
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false); // Make the parser strictly follow the format

        try {
            // Try to parse the date string
            sdf.parse(dateStr);
            return true; // If parsing succeeds, the date is valid
        } catch (ParseException e) {
            // If parsing fails, the date is invalid
            System.out.println("Invalid date format. Expected format is " + dateFormat);
            return false;
        }
    }

    public static boolean validateHotel(int hotelId) {
        String validateHotelQuery = "SELECT COUNT(*) FROM hotel WHERE HOTEL_ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement hotelStmt = con.prepareStatement(validateHotelQuery)) {

            hotelStmt.setInt(1, hotelId);
            try (ResultSet hotelRs = hotelStmt.executeQuery()) {
                if (!hotelRs.next() || hotelRs.getInt(1) == 0) {
                    System.out.println("Invalid Hotel ID.");
                    return false; // Invalid hotel ID
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while validating Hotel ID", e);
            return false; // Error during validation
        }
        return true; // Valid hotel ID
    }

    // Method to get a valid integer from the user
    public static int getValidIntInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        int input = -1;
        boolean validInput = false;

        // Keep prompting until a valid integer is entered
        while (!validInput) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                validInput = true; // Valid integer input
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Consume the invalid input
            }
        }
        return input; // Return the valid integer
    }

    public static double getValidDoubleInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        double input = -1;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                input = scanner.nextDouble();
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter a valid number (e.g., 10.50).");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        return input;
    }

    // Validates that the room is not already booked for the selected dates
    public static boolean checkReservationOverlap(int roomId, String checkinDate, String checkoutDate) {
        String checkOverlapQuery = "SELECT COUNT(*) AS overlap_count FROM reservation " +
                "WHERE ROOM_ID = ? AND " +
                "((CHECKIN_DATE <= ? AND CHECKOUT_DATE > ?) OR " +
                "(CHECKIN_DATE < ? AND CHECKOUT_DATE >= ?))";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = con.prepareStatement(checkOverlapQuery)) {
            checkStmt.setInt(1, roomId);
            checkStmt.setDate(2, Date.valueOf(checkinDate));
            checkStmt.setDate(3, Date.valueOf(checkinDate));
            checkStmt.setDate(4, Date.valueOf(checkoutDate));
            checkStmt.setDate(5, Date.valueOf(checkoutDate));

            ResultSet overlapRs = checkStmt.executeQuery();
            overlapRs.next();
            int overlapCount = overlapRs.getInt("overlap_count");

            return overlapCount == 0; // True if no overlap
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while checking reservation overlap", e);
            return false;
        }
    }

}
