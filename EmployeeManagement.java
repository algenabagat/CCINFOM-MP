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
            System.out.println("+--------------+-------------------------------------+-----------------------------------------+----------------------------+---------------------+----------------------+--------------+------------+------------+");
            System.out.printf("| %-12s | %-35s | %-40s | %-26s | %-19s | %-20s | %-12s | %-10s | %-10s |\n",
                    "Employee ID", "Employee Name", "Job", "Email", "Phone Number", "Hotel Name", "Salary", "Hire Date", "End Date");
            System.out.println("+--------------+-------------------------------------+-----------------------------------------+----------------------------+---------------------+----------------------+--------------+------------+------------+");

            // Loop through the result set and print each employee record in tabular format with partitions
            while (rs.next()) {
                int employeeId = rs.getInt("EMPLOYEE_ID");
                String employeeName = rs.getString("EMPLOYEE_NAME");
                String job = rs.getString("JOB");
                String email = rs.getString("EMAIL");
                String phoneNumber = rs.getString("PHONE_NUMBER");
                String hotelName = rs.getString("HOTEL_NAME");
                double salary = rs.getDouble("SALARY");
                Date hireDate = rs.getDate("HIRE_DATE");
                Date endDate = rs.getDate("END_DATE");

                // Print the employee details with partitions and proper borders
                System.out.printf("| %-12d | %-35s | %-40s | %-26s | %-19s | %-20s | %-12.2f | %-10s | %-10s |\n",
                        employeeId, employeeName, job, email, phoneNumber, hotelName, salary,
                        (hireDate != null ? hireDate.toString() : "null"), (endDate != null ? endDate.toString() : "null"));
                System.out.println("+--------------+-------------------------------------+-----------------------------------------+----------------------------+---------------------+----------------------+--------------+------------+------------+");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while viewing employee details", e);
        }
    }

    public static void addEmployee() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Employee Name: ");
        String employeeName = scanner.nextLine();

        System.out.print("Enter Job ID: ");
        int jobId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter Hotel ID: ");
        int hotelId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Hire Date (YYYY-MM-DD): ");
        String hireDate = scanner.nextLine();

        System.out.print("Enter End Date (YYYY-MM-DD) or leave blank: ");
        String endDate = scanner.nextLine();

        String query = "INSERT INTO employee (EMPLOYEE_NAME, JOB_ID, EMAIL, CONTACT_NO, HOTEL_ID, SALARY, HIRE_DATE, END_DATE) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, employeeName);
            pstmt.setInt(2, jobId);
            pstmt.setString(3, email);
            pstmt.setString(4, phoneNumber);
            pstmt.setInt(5, hotelId);
            pstmt.setDouble(6, salary);
            pstmt.setDate(7, Date.valueOf(hireDate));
            if (endDate.isEmpty()) {
                pstmt.setNull(8, Types.DATE);
            } else {
                pstmt.setDate(8, Date.valueOf(endDate));
            }

            int rowsAffected = pstmt.executeUpdate();
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

        System.out.print("Enter New Employee Name: ");
        String employeeName = scanner.nextLine();

        System.out.print("Enter New Job ID: ");
        int jobId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter New Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter New Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter New Hotel ID: ");
        int hotelId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter New Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter New Hire Date (YYYY-MM-DD): ");
        String hireDate = scanner.nextLine();

        System.out.print("Enter New End Date (YYYY-MM-DD) or leave blank: ");
        String endDate = scanner.nextLine();

        String query = "UPDATE employee " +
                "SET EMPLOYEE_NAME = ?, JOB_ID = ?, EMAIL = ?, CONTACT_NO = ?, HOTEL_ID = ?, SALARY = ?, HIRE_DATE = ?, END_DATE = ? " +
                "WHERE EMPLOYEE_ID = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, employeeName);
            pstmt.setInt(2, jobId);
            pstmt.setString(3, email);
            pstmt.setString(4, phoneNumber);
            pstmt.setInt(5, hotelId);
            pstmt.setDouble(6, salary);
            pstmt.setDate(7, Date.valueOf(hireDate));
            if (endDate.isEmpty()) {
                pstmt.setNull(8, Types.DATE);
            } else {
                pstmt.setDate(8, Date.valueOf(endDate));
            }
            pstmt.setInt(9, employeeId);

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