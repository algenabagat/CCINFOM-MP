import java.util.Scanner;

public class ReservationManagement {
    private final Scanner scanner;

    public ReservationManagement(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nReservation Management Menu:");
            System.out.println("1. Add a Reservation");
            System.out.println("2. View Reservations");
            System.out.println("3. Update Reservation");
            System.out.println("4. Cancel Reservation");
            System.out.println("0. Return to Main Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addReservation();
                case 2 -> viewReservations();
                case 3 -> updateReservation();
                case 4 -> cancelReservation();
                case 0 -> { return; }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addReservation() {
        System.out.println("Adding a reservation...");
    }

    private void viewReservations() {
        System.out.println("Viewing reservations...");
    }

    private void updateReservation() {
        System.out.println("Updating reservation...");
    }

    private void cancelReservation() {
        System.out.println("Cancelling reservation...");
    }
}