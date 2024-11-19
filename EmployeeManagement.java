import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeManagement {
    private static final Logger logger = Logger.getLogger(EmployeeManagement.class.getName());

        public static void viewEmployeeDetails() {
            String query = "SELECT " +
                    "e.EMPLOYEE_ID, e.EMPLOYEE_NAME, j.JOB_NAME AS JOB, " +
                    "e.EMAIL, e.CONTACT_NO AS PHONE_NUMBER, h.HOTEL_NAME, " +
                    "e.SALARY, e.HIRE_DATE, e.END_DATE " +
                    "FROM employee e " +
                    "JOIN jobs j ON e.JOB_ID = j.JOB_ID " +
                    "JOIN hotel h ON e.HOTEL_ID = h.HOTEL_ID " +
                    "ORDER BY e.EMPLOYEE_ID"; // Added ORDER BY clause

            try (Connection con = DatabaseConnection.getConnection();
                 Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                // Print the table header with partitions
                // Print the table header with adjusted widths
                System.out.println("+--------------+-------------------------------------+------------------------------------------+-------------------------------------------+---------------------+-------------------------------------+--------------+------------+------------+");
                System.out.printf("| %-12s | %-35s | %-40s | %-41s | %-19s | %-35s | %-12s | %-10s | %-10s |\n",
                        "Employee ID", "Employee Name", "Job", "Email", "Phone Number", "Hotel Name", "Salary", "Hire Date", "End Date");
                System.out.println("+--------------+-------------------------------------+------------------------------------------+-------------------------------------------+---------------------+-------------------------------------+--------------+------------+------------+");

                // Loop through the result set and print each employee record
                while (rs.next()) {
                    System.out.printf("| %-12d | %-35s | %-40s | %-41s | %-19s | %-35s | %-12.2f | %-10s | %-10s |\n",
                            rs.getInt("EMPLOYEE_ID"),
                            rs.getString("EMPLOYEE_NAME"),
                            rs.getString("JOB"),
                            rs.getString("EMAIL"),
                            rs.getString("PHONE_NUMBER"),
                            rs.getString("HOTEL_NAME"),
                            rs.getDouble("SALARY"),
                            rs.getString("HIRE_DATE"),
                            rs.getString("END_DATE") == null ? "null" : rs.getString("END_DATE"));
                    System.out.println("+--------------+-------------------------------------+------------------------------------------+-------------------------------------------+---------------------+-------------------------------------+--------------+------------+------------+");
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "SQL Exception occurred while viewing employee details", e);
            }
        }

    public static void addEmployee() {
        Scanner scanner = new Scanner(System.in);

        // Validate Hotel ID as soon as it's entered
        System.out.print("Enter Hotel ID: ");
        int hotelId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String validateHotelQuery = "SELECT COUNT(*) FROM hotel WHERE HOTEL_ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement hotelStmt = con.prepareStatement(validateHotelQuery)) {

            hotelStmt.setInt(1, hotelId);
            try (ResultSet hotelRs = hotelStmt.executeQuery()) {
                if (!hotelRs.next() || hotelRs.getInt(1) == 0) {
                    System.out.println("Invalid Hotel ID. Employee creation aborted.");
                    return; // Abort if the hotel ID is invalid
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while validating Hotel ID", e);
            return;
        }

        System.out.print("Enter Employee Name: ");
        String employeeName = scanner.nextLine();

        // Validate Job ID as soon as it's entered
        System.out.print("Enter Job ID: ");
        int jobId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String validateJobQuery = "SELECT COUNT(*) FROM jobs WHERE JOB_ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement jobStmt = con.prepareStatement(validateJobQuery)) {

            jobStmt.setInt(1, jobId);
            try (ResultSet jobRs = jobStmt.executeQuery()) {
                if (!jobRs.next() || jobRs.getInt(1) == 0) {
                    System.out.println("Invalid Job ID. Employee creation aborted.");
                    return; // Abort if the job ID is invalid
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while validating Job ID", e);
            return;
        }

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Hire Date (YYYY-MM-DD): ");
        String hireDate = scanner.nextLine();

        System.out.print("Enter End Date (YYYY-MM-DD) or leave blank: ");
        String endDate = scanner.nextLine();

        // Query for salary range validation
        String salaryRangeQuery = "SELECT MIN_SALARY, MAX_SALARY FROM jobs WHERE JOB_ID = ?";
        String insertEmployeeQuery = "INSERT INTO employee (EMPLOYEE_NAME, JOB_ID, EMAIL, CONTACT_NO, HOTEL_ID, SALARY, HIRE_DATE, END_DATE) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement salaryStmt = con.prepareStatement(salaryRangeQuery);
             PreparedStatement insertStmt = con.prepareStatement(insertEmployeeQuery)) {

            // Validate JOB_ID salary range
            salaryStmt.setInt(1, jobId);
            double minSalary, maxSalary;
            try (ResultSet jobRs = salaryStmt.executeQuery()) {
                if (jobRs.next()) {
                    minSalary = jobRs.getDouble("MIN_SALARY");
                    maxSalary = jobRs.getDouble("MAX_SALARY");

                    // Check salary range
                    if (salary < minSalary || salary > maxSalary) {
                        System.out.printf("Salary must be between %.2f and %.2f for this job.%n", minSalary, maxSalary);
                        return;
                    }
                } else {
                    System.out.println("Invalid Job ID. Employee creation aborted.");
                    return;
                }
            }

            // Insert Employee
            insertStmt.setString(1, employeeName);
            insertStmt.setInt(2, jobId);
            insertStmt.setString(3, email);
            insertStmt.setString(4, phoneNumber);
            insertStmt.setInt(5, hotelId);
            insertStmt.setDouble(6, salary);
            insertStmt.setDate(7, Date.valueOf(hireDate));
            if (endDate.isEmpty()) {
                insertStmt.setNull(8, Types.DATE);
            } else {
                insertStmt.setDate(8, Date.valueOf(endDate));
            }

            int rowsAffected = insertStmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee added successfully.");
            } else {
                System.out.println("Failed to add employee.");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while adding employee", e);
        }
    }


    public static void updateEmployeeDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Employee ID to update: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Validate Employee Existence
        String validateEmployeeQuery = "SELECT COUNT(*) FROM employee WHERE EMPLOYEE_ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement employeeStmt = con.prepareStatement(validateEmployeeQuery)) {

            employeeStmt.setInt(1, employeeId);
            try (ResultSet employeeRs = employeeStmt.executeQuery()) {
                if (!employeeRs.next() || employeeRs.getInt(1) == 0) {
                    System.out.println("Employee does not exist. Update aborted.");
                    return; // Abort if the employee does not exist
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while validating Employee ID", e);
            return;
        }

        // Validate Hotel ID
        System.out.print("Enter New Hotel ID: ");
        int hotelId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String validateHotelQuery = "SELECT COUNT(*) FROM hotel WHERE HOTEL_ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement hotelStmt = con.prepareStatement(validateHotelQuery)) {

            hotelStmt.setInt(1, hotelId);
            try (ResultSet hotelRs = hotelStmt.executeQuery()) {
                if (!hotelRs.next() || hotelRs.getInt(1) == 0) {
                    System.out.println("Invalid Hotel ID. Update aborted.");
                    return; // Abort if the hotel ID is invalid
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while validating Hotel ID", e);
            return;
        }

        // Validate Job ID
        System.out.print("Enter New Job ID: ");
        int jobId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String validateJobQuery = "SELECT COUNT(*) FROM jobs WHERE JOB_ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement jobStmt = con.prepareStatement(validateJobQuery)) {

            jobStmt.setInt(1, jobId);
            try (ResultSet jobRs = jobStmt.executeQuery()) {
                if (!jobRs.next() || jobRs.getInt(1) == 0) {
                    System.out.println("Invalid Job ID. Update aborted.");
                    return; // Abort if the job ID is invalid
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while validating Job ID", e);
            return;
        }

        // Read other details
        System.out.print("Enter New Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter New Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter New Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter New Hire Date (YYYY-MM-DD): ");
        String hireDate = scanner.nextLine();

        System.out.print("Enter New End Date (YYYY-MM-DD) or leave blank: ");
        String endDate = scanner.nextLine();

        String query = "UPDATE employee " +
                "SET JOB_ID = ?, EMAIL = ?, CONTACT_NO = ?, HOTEL_ID = ?, SALARY = ?, HIRE_DATE = ?, END_DATE = ? " +
                "WHERE EMPLOYEE_ID = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, jobId);
            pstmt.setString(2, email);
            pstmt.setString(3, phoneNumber);
            pstmt.setInt(4, hotelId);
            pstmt.setDouble(5, salary);
            pstmt.setDate(6, Date.valueOf(hireDate));
            if (endDate.isEmpty()) {
                pstmt.setNull(7, Types.DATE);
            } else {
                pstmt.setDate(7, Date.valueOf(endDate));
            }
            pstmt.setInt(8, employeeId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee details updated successfully.");
            } else {
                System.out.println("Failed to update employee details.");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while updating employee details", e);
        }
    }

    public static void deleteEmployee() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Employee ID to delete: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String query = "DELETE FROM employee WHERE EMPLOYEE_ID = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, employeeId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("Failed to delete employee.");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while deleting employee", e);
        }
    }
}