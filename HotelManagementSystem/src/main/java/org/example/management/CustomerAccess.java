package org.example.management;

import java.util.List;
import java.util.Scanner;
import org.example.database.*;

public class CustomerAccess {
    private RoomService roomService = new RoomService();
    CustomerForm customerForm = new CustomerForm();
    private CheckInService checkInService = new CheckInService();

    public void showCustomerMenu(Scanner scanner) {
        while (true) {
            System.out.println("1. Check Room Availability");
            System.out.println("2. Booking Room");
            System.out.println("3. Booking Cancel");
            System.out.println("4. Logout");

            System.out.print("Please select an option (1-4): ");
            int choice= scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    try {
                        // Use RoomService to get the list of available rooms
                        List<String> availableRooms = roomService.getAvailableRooms();
                        System.out.println("Room Availability:");
                        if (availableRooms.isEmpty()) {
                            System.out.println("No rooms available.");
                        } else {
                            for (String room : availableRooms) {
                                System.out.println(room);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("An error occurred while retrieving room availability. Please try again.");
                    }
                    break;

                case 2: // Customer Details IN CustomerInfo Table
                    System.out.println("Enter Customer Details.. ");
                    customerForm.fillCustomerForm(scanner);
                    break;

                case 3: // Booking Cancel
                    System.out.print("Enter Booking ID to cancel: ");
                    int bookingId;

                    try {
                        bookingId = scanner.nextInt();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a valid Booking ID.");
                        scanner.nextLine();
                        continue;
                    }

                    boolean cancelSuccess = checkInService.cancelBooking(bookingId);
                    if (cancelSuccess) {
                        System.out.println("Booking cancelled successfully.");
                    } else {
                        System.out.println("Booking cancellation failed.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Please select again.");
            }

            System.out.println();
        }
    }
}
