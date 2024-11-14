import java.util.Scanner;

public class PaymentManagement {
    private final Scanner scanner;

    public PaymentManagement(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nPayment Management Menu:");
            System.out.println("1. Process Payment");
            System.out.println("2. View Payment Records");
            System.out.println("0. Return to Main Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> processPayment();
                case 2 -> viewPaymentRecords();
                case 0 -> { return; }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void processPayment() {
        System.out.println("Processing payment...");
    }

    private void viewPaymentRecords() {
        System.out.println("Viewing payment records...");
    }
}