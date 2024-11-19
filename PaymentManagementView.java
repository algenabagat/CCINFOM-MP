import java.util.Scanner;

public class PaymentManagementView {
    private final Scanner scanner;

    public PaymentManagementView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nPayment Management Menu:");
            System.out.println("1. Process Payment");
            System.out.println("2. View Payment Records");
            System.out.println("3. Add Discount");
            System.out.println("4. Update Discount");
            System.out.println("5. Delete Discount");
            System.out.println("0. Return to Main Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    processPayment();
                    break;
                case 2:
                    viewPaymentRecords();
                    PaymentManagement.viewPayments();
                    break;
                case 3:
                    addDiscount();
                    PaymentManagement.addDiscount();
                    break;
                case 4:
                    updateDiscount();
                    PaymentManagement.showDiscount();
                    PaymentManagement.updateDiscount();
                    break;
                case 5:
                    deleteDiscount();
                    PaymentManagement.showDiscount();
                    PaymentManagement.deleteDiscount();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void processPayment() {
        System.out.println("Processing payment...");
    }
    private void viewPaymentRecords() {
        System.out.println("Viewing payment records...");
    }
    private void addDiscount() { System.out.println("Adding discount...");}
    private void updateDiscount() { System.out.println("Updating discount...");}
    private void deleteDiscount() { System.out.println("Deleting discount...");}
}