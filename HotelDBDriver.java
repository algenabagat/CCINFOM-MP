import java.util.Scanner;

public class HotelDBDriver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Call login method before proceeding to main menu
        if (!DatabaseConnection.login()) {
            System.out.println("Login failed. Exiting the system.");
            scanner.close(); // Close the scanner before exiting
            return; // Exit the program if login fails
        }

        // Instantiate management classes after successful login
        HotelManagementView hotelManagement = new HotelManagementView(scanner);
        EmployeeManagementView employeeManagement = new EmployeeManagementView(scanner);
        ReservationManagement reservationManagement = new ReservationManagement(scanner);
        PaymentManagement paymentManagement = new PaymentManagement(scanner);

        // Main menu loop
        System.out.println("Welcome to the Hotel Management !");
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Hotel Management");
            System.out.println("2. Employee Management");
            System.out.println("3. Reservation Management");
            System.out.println("4. Payment Management");
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
