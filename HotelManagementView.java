// Source code is decompiled from a .class file using FernFlower decompiler.
import java.util.Scanner;

public class HotelManagementView {
   private final Scanner scanner;

   public HotelManagementView(Scanner var1) {
      this.scanner = var1;
   }

   public void showMenu() {
      while(true) {
         System.out.println("\nHotel Management Menu:");
         System.out.println("1. View Hotel Details");
         System.out.println("2. Update Hotel Details");
         System.out.println("3. Create a New Hotel");
         System.out.println("4. Delete a Hotel");
         System.out.println("0. Return to Main Menu");
         System.out.print("Choose an option: ");
         int var1 = this.scanner.nextInt();
         this.scanner.nextLine();
         switch (var1) {
            case 0:
               return;
            case 1:
               this.viewHotelDetails();
               HotelManagement.viewHotelDetails();
               break;
            case 2:
               this.updateHotelDetails();
               HotelManagement.viewHotelDetails();
               HotelManagement.updateHotelDetails();
               break;
            case 3:
               this.createHotel();
               HotelManagement.createHotel();
               break;
            case 4:
               this.deleteHotel();
               HotelManagement.viewHotelDetails();
               HotelManagement.deleteHotel();
               break;
            default:
               System.out.println("Invalid choice. Please try again.");
         }
      }
   }

   private void viewHotelDetails() {
      System.out.println("Viewing hotel details...");
   }

   private void updateHotelDetails() {
      System.out.println("Updating hotel details...");
   }

   private void createHotel() { System.out.println("Creating a new hotel..."); }

   private void deleteHotel() {
      System.out.println("Deleting a Hotel...");
   }
}
