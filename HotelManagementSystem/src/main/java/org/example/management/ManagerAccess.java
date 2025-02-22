package org.example.management;

import java.util.List;
import java.util.Scanner;
import org.example.database.*;

public class ManagerAccess {
    private RoomService roomService = new RoomService();
    private CheckInService checkInService = new CheckInService();
    private CheckOutService checkOutService = new CheckOutService();
    private HistoryService historyService = new HistoryService();
    private EmployeeService employeeService = new EmployeeService();
    private ManageEmployee manageEmployee = new ManageEmployee(employeeService);

    public void showManagerMenu(Scanner scanner){
        while (true) {
            System.out.println("1. Check Room Details");
            System.out.println("2. To add room for customer ");
            System.out.println("3. Check Out Date");
            System.out.println("4. View Check-In/Out Details");
            System.out.println("5. View Employee Information");
            System.out.println("6. Manage Employee Details");
            System.out.println("7. Logout");

            System.out.print("Please select an option (1-7): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    // Check room availability
                    try {
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

                case 2:
                    // Check in customer
                    System.out.print("Enter Customer Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Room No: ");
                    String room_no = scanner.nextLine();
                    System.out.print("Enter Check-In Date (YYYY-MM-DD): ");
                    String checkInDate = scanner.nextLine();
                    boolean checkInSuccess = checkInService.checkInCustomer(name,room_no,checkInDate);
                    if (checkInSuccess) {
                        System.out.println("Customer checked in successfully.");
                    } else {
                        System.out.println("Check-in failed.");
                    }
                    break;

                case 3:
                    // Check out customer
                    System.out.print("Enter Room No to check out: ");
                    String roomNoCheckOut = scanner.nextLine();
                    boolean checkOutSuccess = checkOutService.checkOutCustomer(roomNoCheckOut);
                    if (checkOutSuccess) {
                        System.out.println("Customer checked out successfully.");
                    } else {
                        System.out.println("Check-out failed.");
                    }
                    break;

                case 4:
                    // View check-in/out history
                    System.out.println("Check-In/Out History:");
                    historyService.getCheckInOutHistory();
                    break;

                case 5:
                    // View employee information
                    System.out.println("Employee Information:");
                    employeeService.getEmployeeInformation();
                    break;

                case 6:
                    System.out.println("Managing Employee Details...");
                    manageEmployee.manage();
                    break;

                case 7:
                    System.out.println("Exiting the program. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Please select again.");
            }

            System.out.println();
        }
    }
}