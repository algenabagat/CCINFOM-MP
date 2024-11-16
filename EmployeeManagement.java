import java.sql.*;
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
                "JOIN hotel h ON e.HOTEL_ID = h.HOTEL_ID";

        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Print the table header with partitions
            System.out.println("+--------------+-------------------------------------+--------------------------------+----------------------------+---------------------+----------------------+--------------+------------+------------+");
            System.out.printf("| %-12s | %-35s | %-30s | %-26s | %-19s | %-20s | %-12s | %-10s | %-10s |\n",
                    "Employee ID", "Employee Name", "Job", "Email", "Phone Number", "Hotel Name", "Salary", "Hire Date", "End Date");
            System.out.println("+--------------+-------------------------------------+--------------------------------+----------------------------+---------------------+----------------------+--------------+------------+------------+");

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
                System.out.printf("| %-12d | %-35s | %-30s | %-26s | %-19s | %-20s | %-12.2f | %-10s | %-10s |\n",
                        employeeId, employeeName, job, email, phoneNumber, hotelName, salary,
                        (hireDate != null ? hireDate.toString() : "null"), (endDate != null ? endDate.toString() : "null"));
                System.out.println("+--------------+-------------------------------------+--------------------------------+----------------------------+---------------------+----------------------+--------------+------------+------------+");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception occurred while viewing employee details", e);
        }
    }
}