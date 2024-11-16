import java.util.Scanner;

public class HotelManagement {
    private final Scanner scanner;

    public HotelManagement(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nHotel Management Menu:");
            System.out.println("1. View Hotel Details");
            System.out.println("2. Update Hotel Details");
            System.out.println("3. Create a New Room");
            System.out.println("4. Update Room Details");
            System.out.println("5. Delete a Room");
            System.out.println("0. Return to Main Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewHotelDetails();
                case 2 -> updateHotelDetails();
                case 3 -> createRoom();
                case 4 -> updateRoomDetails();
                case 5 -> deleteRoom();
                case 0 -> { return; }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewHotelDetails() {
        System.out.println("Viewing hotel details...");
    }

    private void updateHotelDetails() {
        System.out.println("Updating hotel details...");
    }

    private void createRoom() {
        System.out.println("Creating a new room...");
    }

    private void updateRoomDetails() {
        System.out.println("Updating room details...");
    }

    private void deleteRoom() {
        System.out.println("Deleting a room...");
    }
}