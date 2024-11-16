import java.util.Scanner;

public class HotelDBDriver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Call login method before proceeding to main menu
        if (!DatabaseConnection.login()) {
            System.out.println("Login failed. Exiting the system.");
            scanner.close();
            return; // Exit the program if login fails
        }

        // Instantiate management classes after successful login
        HotelManagementView hotelManagement = new HotelManagementView(scanner);
        EmployeeManagement employeeManagement = new EmployeeManagement(scanner);
        ReservationManagement reservationManagement = new ReservationManagement(scanner);
        PaymentManagement paymentManagement = new PaymentManagement(scanner);

        // Main menu loop
        System.out.println("Welcome to the Hotel Management System");
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Hotel Management");
            System.out.println("2. Employee Management");
            System.out.println("3. Reservation Management");
            System.out.println("4. Payment Management");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            // Read integer option and consume the newline left by nextInt()
            int choice = scanner.nextInt();
            scanner.nextLine(); // This consumes the remaining newline character after nextInt()

            switch (choice) {
                case 1 -> hotelManagement.showMenu();
                case 2 -> employeeManagement.showMenu();
                case 3 -> reservationManagement.showMenu();
                case 4 -> paymentManagement.showMenu();
                case 0 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
