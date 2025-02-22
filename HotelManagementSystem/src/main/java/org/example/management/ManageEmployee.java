package org.example.management;

import org.example.database.EmployeeService;

import java.util.Scanner;

public class ManageEmployee {
    private EmployeeService employeeService;
    private Scanner scanner;

    public ManageEmployee(EmployeeService employeeService) {
        this.employeeService = employeeService;
        this.scanner = new Scanner(System.in);
    }

    public void manage() {
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. Back to previous Menu");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    updateEmployee();
                    break;
                case 3:
                    deleteEmployee();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addEmployee() {
        String empName;
        while (true) {
            System.out.print("Enter Employee Name (3-30 characters, letters and spaces only): ");
            empName = scanner.nextLine();
            if (Validation.isValidName(empName)) {
                break;
            } else {
                System.out.println("Invalid name format. Please try again.");
            }
        }

        System.out.print("Enter Position: ");
        String empPosition = scanner.nextLine();

        String empContact;
        while (true) {
            System.out.print("Enter Contact (10-digit number): ");
            empContact = scanner.nextLine();
            if (Validation.isValidMobileNumber(empContact)) {
                break;
            } else {
                System.out.println("Invalid contact. Please enter a valid 10-digit number.");
            }
        }

        double empSalary;
        while (true) {
            System.out.print("Enter Salary: ");
            try {
                empSalary = Double.parseDouble(scanner.nextLine());
                if (empSalary >= 0) break;
                else System.out.println("Salary must be non-negative.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary. Please enter a valid number.");
            }
        }

        System.out.print("Enter Address: ");
        String empAddress = scanner.nextLine();

        boolean addSuccess = employeeService.addEmployee(empName, empPosition, empContact, empSalary, empAddress);
        System.out.println(addSuccess ? "Employee added successfully." : "Failed to add employee.");
    }

    private void updateEmployee() {
        int empId;
        while (true) {
            System.out.print("Enter Employee ID to Update: ");
            try {
                empId = Integer.parseInt(scanner.nextLine());
                if (empId > 0) break;
                else System.out.println("Employee ID must be a positive integer.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID. Please enter a valid number.");
            }
        }

        String newName;
        while (true) {
            System.out.print("Enter New Employee Name (3-30 characters, letters and spaces only): ");
            newName = scanner.nextLine();
            if (Validation.isValidName(newName)) {
                break;
            } else {
                System.out.println("Invalid name format. Please try again.");
            }
        }

        System.out.print("Enter New Position: ");
        String newPosition = scanner.nextLine();

        String newContact;
        while (true) {
            System.out.print("Enter New Contact (10-digit number): ");
            newContact = scanner.nextLine();
            if (Validation.isValidMobileNumber(newContact)) {
                break;
            } else {
                System.out.println("Invalid contact. Please enter a valid 10-digit number.");
            }
        }

        double newSalary;
        while (true) {
            System.out.print("Enter New Salary: ");
            try {
                newSalary = Double.parseDouble(scanner.nextLine());
                if (newSalary >= 0) break;
                else System.out.println("Salary must be non-negative.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary. Please enter a valid number.");
            }
        }

        System.out.print("Enter New Address: ");
        String newAddress = scanner.nextLine();

        boolean updateSuccess = employeeService.updateEmployee(empId, newName, newPosition, newContact, newSalary, newAddress);
        System.out.println(updateSuccess ? "Employee updated successfully." : "Failed to update employee.");
    }

    private void deleteEmployee() {
        int empId;
        while (true) {
            System.out.print("Enter Employee ID to Delete: ");
            try {
                empId = Integer.parseInt(scanner.nextLine());
                if (empId > 0) break;
                else System.out.println("Employee ID must be a positive integer.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID. Please enter a valid number.");
            }
        }

        boolean deleteSuccess = employeeService.deleteEmployee(empId);
        System.out.println(deleteSuccess ? "Employee deleted successfully." : "Failed to delete employee.");
    }
}
