package org.example.management;

import org.example.database.CheckInService;
import org.example.database.CustomerFormDAO;

import java.util.Scanner;

public class CustomerForm {
    private CheckInService checkInService = new CheckInService();

    public void fillCustomerForm(Scanner scanner) {
        String customerName;
        while (true) {
            System.out.print("Enter Customer Name (3-30 characters, letters and spaces only): ");
            customerName = scanner.nextLine();
            if (Validation.isValidName(customerName)) {
                break;
            }
        }

        String email;
        while (true) {
            System.out.print("Enter valid Email format: ");
            email = scanner.nextLine();
            if (Validation.isValidEmail(email)) {
                break;
            }
        }

        String phone;
        while (true) {
            System.out.print("Enter Contact 10-digit number: ");
            phone = scanner.nextLine();
            if (Validation.isValidMobileNumber(phone)) {
                break;
            }
        }

        String address;
        while (true) {
            System.out.print("Enter Address (5-255 characters): ");
            address = scanner.nextLine();
            if (Validation.isValidAddress(address)) {
                break;
            }
        }

        String dob;
        while (true) {
            System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
            dob = scanner.nextLine();
            if (Validation.isValidDateOfBirth(dob)) {
                break;
            }
        }

        String gender;
        while (true) {
            System.out.print("Enter Gender (Male/Female/Other): ");
            gender = scanner.nextLine();
            if (Validation.isValidGender(gender)) {
                break;
            }
        }

        String proofId;
        while (true) {
            System.out.print("Enter Proof ID (Aadhar/Driving License/Passport): ");
            proofId = scanner.nextLine();
            if (Validation.isValidProofId(proofId)) {
                break;
            }
        }

        CustomerFormDAO customerFormDAO = new CustomerFormDAO();
        boolean success = customerFormDAO.addCustomerToDatabase(customerName, email, phone, address, dob, gender, proofId);

        if (success) {
            System.out.println("Customer added successfully.");
        } else {
            System.out.println("Failed to add customer.");
        }

        System.out.println("Enter Check-In Details..");
        System.out.print("Enter Room No: ");
        String room_no = scanner.nextLine();

        String checkInDate;
        while (true) {
            System.out.print("Enter Check-In Date (YYYY-MM-DD): ");
            checkInDate = scanner.nextLine();
            if (Validation.isValidCheckInDate(checkInDate)) {
                break;
            } else {
                System.out.println("Invalid Check-In Date. Please enter today's date or a future date in the format YYYY-MM-DD.");
            }
        }

        boolean checkInSuccess = checkInService.checkInCustomer(customerName, room_no, checkInDate);
        if (checkInSuccess) {
            System.out.println("Customer checked in successfully.");
        } else {
            System.out.println("Check-in failed.");
        }
    }
}
