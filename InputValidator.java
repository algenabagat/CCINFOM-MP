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
    public static boolean checkReservationOverlap(Connection con, int roomId, String checkinDate, String checkoutDate, int reservationId) {
        String checkOverlapQuery = "SELECT COUNT(*) AS overlap_count FROM reservation " +
                "WHERE ROOM_ID = ? AND RESERVATION_ID != ? AND " + // Exclude the current reservation
                "((CHECKIN_DATE < ? AND CHECKOUT_DATE > ?) OR " + // Overlap starts before checkout
                "(CHECKIN_DATE < ? AND CHECKOUT_DATE > ?))";       // Overlap starts during the stay

        try (PreparedStatement checkStmt = con.prepareStatement(checkOverlapQuery)) {
            checkStmt.setInt(1, roomId);
            checkStmt.setInt(2, reservationId);
            checkStmt.setDate(3, Date.valueOf(checkoutDate));
            checkStmt.setDate(4, Date.valueOf(checkinDate));
            checkStmt.setDate(5, Date.valueOf(checkoutDate));
            checkStmt.setDate(6, Date.valueOf(checkinDate));

            ResultSet overlapRs = checkStmt.executeQuery();
            if (overlapRs.next()) {
                int overlapCount = overlapRs.getInt("overlap_count");
                return overlapCount > 0; // True if there are overlaps
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while checking reservation overlap", e);
        }
        return false; // Assume no overlap if an error occurs
    }


    public static boolean validateRoom(Connection con, int selectedRoomId, int selectedHotelId) {
        String validateRoomQuery = "SELECT COUNT(*) AS room_count FROM rooms WHERE ROOM_ID = ? AND HOTEL_ID = ?";

        try (PreparedStatement validateRoomStmt = con.prepareStatement(validateRoomQuery)) {
            validateRoomStmt.setInt(1, selectedRoomId);
            validateRoomStmt.setInt(2, selectedHotelId);
            ResultSet validateRoomRs = validateRoomStmt.executeQuery();

            validateRoomRs.next();
            int roomCount = validateRoomRs.getInt("room_count");

            if (roomCount == 0) {
                System.out.println("Invalid Room ID! Aborting reservation.");
                return false;  // Room is not valid
            }
        } catch (SQLException e) {
            System.out.println("Error validating room: " + e.getMessage());
            return false;  // Return false if there is a database error
        }

        return true;  // Room is valid
    }

    public static boolean validateReservationId(Connection con, int reservationId) {
        String validateReservationQuery = "SELECT COUNT(*) AS reservation_count FROM reservation WHERE RESERVATION_ID = ?";

        try (PreparedStatement stmt = con.prepareStatement(validateReservationQuery)) {
            stmt.setInt(1, reservationId);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            int reservationCount = rs.getInt("reservation_count");

            if (reservationCount > 0) {
                return true; // Reservation ID is valid
            } else {
                System.out.println("Invalid Reservation ID! No such reservation found.");
                return false; // Reservation ID is invalid
            }
        } catch (SQLException e) {
            System.out.println("Error validating Reservation ID: " + e.getMessage());
            return false; // Return false in case of an error
        }
    }

    // Function to get user input as a String
    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    public static String getValidStringInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static boolean validateGuestId(int guestId) {
        String query = "SELECT COUNT(*) FROM guest WHERE GUEST_ID = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            // Set the guest ID parameter
            stmt.setInt(1, guestId);

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true; // Guest ID exists
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while validating Guest ID: " + e.getMessage());
        }

        return false; // Guest ID does not exist
    }

}
