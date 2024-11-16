import java.util.Scanner;

public class HotelDBDriver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HotelManagement hotelManagement = new HotelManagement(scanner);
        EmployeeManagement employeeManagement = new EmployeeManagement(scanner);
        ReservationManagement reservationManagement = new ReservationManagement(scanner);
        PaymentManagement paymentManagement = new PaymentManagement(scanner);

        System.out.println("Welcome to the Hotel Management System");
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Hotel Management");
            System.out.println("2. Employee Management");
            System.out.println("3. Reservation Management");
            System.out.println("4. Payment Management");
            System.out.println("0. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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