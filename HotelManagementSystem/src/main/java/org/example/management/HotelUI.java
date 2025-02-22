package org.example.management;

import org.example.database.LoginDAO;

import java.util.Scanner;

public class HotelUI {
    Scanner scanner = new Scanner(System.in);
    String username = null;
    String password = null;

    public HotelUI(){
        System.out.println("1. Admin/Manager Login");
        System.out.println("2. Customer Login");
        System.out.print("Enter your choice(1 or 2): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Enter Username: ");
            username= scanner.nextLine();
            System.out.print("Enter Password: ");
            password= scanner.nextLine();
            LoginDAO loginDAO = new LoginDAO();
            String role= loginDAO.loginHandler(username,password);

            switch (role) {
                case "admin":
                    System.out.println("Welcome to the Admin Panel.");
                    AdminAccess adminAccess = new AdminAccess();
                    adminAccess.showAdminMenu(scanner);
                    break;
                case "manager":
                    System.out.println("Welcome to Manager Panel.");
                    ManagerAccess managerAccess = new ManagerAccess();
                    managerAccess.showManagerMenu(scanner);
                    break;
                default:
                    System.out.println("Invalid role.\nExiting...");
            }
        } else if (choice == 2) {
            System.out.println("Welcome to the Hotel Management System");
            CustomerAccess customerAccess = new CustomerAccess();
            customerAccess.showCustomerMenu(scanner);

        } else {
            System.out.println("Invalid choice. Exiting.");
        }

    }
}
