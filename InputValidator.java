import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
}
