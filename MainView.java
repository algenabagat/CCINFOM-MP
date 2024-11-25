import java.util.Scanner;

public class MainView {

    public static void mainView() {
        Scanner scanner = new Scanner(System.in);
    // Call login method before proceeding to main menu
    System.out.println("Enter Database Credentials");
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();

    if (!DatabaseConnection.login(username, password)) {
        System.out.println("Login failed. Exiting the system.");
        scanner.close(); // Close the scanner before exiting
        return; // Exit the program if login fails
    } else {
        System.out.println("Login successful!\n");
    }

    // Instantiate management classes after successful login
    HotelManagementView hotelManagement = new HotelManagementView(scanner);
    EmployeeManagementView employeeManagement = new EmployeeManagementView(scanner);
    ReservationManagementView reservationManagement = new ReservationManagementView(scanner);
    PaymentManagementView paymentManagement = new PaymentManagementView(scanner);
    ReportManagementView reportManagement = new ReportManagementView(scanner);

    // Main menu loop
    System.out.print("Welcome to the Hotel Management!");
    while (true) {
        System.out.println("\nMain Menu:");
        System.out.println("1. Hotel Management");
        System.out.println("2. Employee Management");
        System.out.println("3. Reservation Management");
        System.out.println("4. Payment Management");
        System.out.println("5. Reports");
        System.out.println("0. Exit");

        System.out.print("Choose an option: ");
        String input = scanner.nextLine();  // Read the whole line

        // Try to parse the input as an integer
        try {
            int choice = Integer.parseInt(input);  // Parse the input as integer

            switch (choice) {
                case 1:
                    hotelManagement.showMenu();
                    break;
                case 2:
                    employeeManagement.showMenu();
                    break;
                case 3:
                    reservationManagement.showMenu();
                    break;
                case 4:
                    paymentManagement.showMenu();
                    break;
                case 5:
                    reportManagement.showMenu();
                    break;
                case 0:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close(); // Close the scanner before exiting
                    return; // Exit the program
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (NumberFormatException e) {
            // Handle invalid input (non-integer input)
            System.out.println("Invalid input. Please enter a valid number.");
            }
    }
}
}