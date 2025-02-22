package org.example.management;

import org.example.database.*;

import java.util.List;
import java.util.Scanner;

public class AdminAccess {
    private EmployeeService employeeService = new EmployeeService();
    private EarningsService earningsService = new EarningsService();
    private ManageEmployee manageEmployee = new ManageEmployee(employeeService);
    private CustomerData customerData = new CustomerData();

    public void showAdminMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Manage Employee Details");
            System.out.println("2. View Customer Information");
            System.out.println("3. Earnings Reports");
            System.out.println("4. Logout");
            System.out.print("Please select an option (1-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Managing Employee Details...");
                    manageEmployee.manage();
                    break;

                case 2:
                    System.out.println("Viewing Customer Information...");
                    List<String> customers = customerData.getAllCustomers();
                    if (customers.isEmpty()) {
                        System.out.println("No customers found.");
                    } else {
                        for (String customer : customers) {
                            System.out.println(customer);
                        }
                    }
                    break;

                case 3:
                    System.out.println("Generating Earnings Reports...");
                    String report = earningsService.generateEarningsReport();
                    System.out.println(report);
                    break;

                case 4:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}