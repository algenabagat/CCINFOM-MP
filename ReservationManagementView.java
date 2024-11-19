import java.util.Scanner;

public class ReservationManagementView {
    private final Scanner scanner;

    public ReservationManagementView(Scanner scanner) {
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
                case 1:
                    addReservation();
                    ReservationManagement.addReservation();
                    break;
                case 2:
                    viewReservations();
                    ReservationManagement.viewReservations();
                    break;
                case 3:
                    updateReservation();
                    ReservationManagement.viewReservations();
                    ReservationManagement.updateReservation();
                    break;
                case 4:
                    cancelReservation();
                    ReservationManagement.viewReservations();
                    ReservationManagement.cancelReservation();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
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