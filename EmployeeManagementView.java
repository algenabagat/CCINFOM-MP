import java.util.Scanner;

public class EmployeeManagementView {
    private final Scanner scanner;

    public EmployeeManagementView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nEmployee Management Menu:");
            System.out.println("1. View Employee List");
            System.out.println("2. Add Employee");
            System.out.println("3. Update Employee Details");
            System.out.println("4. Delete Employee");
            System.out.println("0. Return to Main Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewEmployeeList();
                    EmployeeManagement.viewEmployeeDetails();
                    break;
                case 2:
                    addEmployee();
                    break;
                case 3:
                    updateEmployeeDetails();
                    EmployeeManagement.updateEmployeeDetails();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void viewEmployeeList() {
        System.out.println("Viewing employee list...");
    }

    private void addEmployee() {
        System.out.println("Adding a new employee...");
    }

    private void updateEmployeeDetails() {
        System.out.println("Updating employee details...");
    }

    private void deleteEmployee() {
        System.out.println("Deleting an employee...");
    }
}