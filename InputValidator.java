import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidator {

    private static final Scanner scanner = new Scanner(System.in);

    // Get a valid integer input
    public static int getIntInput(String prompt, String errorMessage) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(errorMessage);
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    // Get a valid double input
    public static double getDoubleInput(String prompt, String errorMessage) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println(errorMessage);
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    // Get a valid string input
    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Get and validate a date input
    public static String getDateInput(String prompt, String errorMessage) {
        while (true) {
            System.out.print(prompt);
            String dateInput = scanner.nextLine();
            try {
                validateDate(dateInput);
                return dateInput;
            } catch (IllegalArgumentException e) {
                System.out.println(errorMessage);
            }
        }
    }

    // Validate a date format (YYYY-MM-DD)
    public static void validateDate(String date) {
        try {
            Date.valueOf(date); // Check if the date is valid
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid date format.");
        }
    }
}
