package org.example.management;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validation {

    public static boolean isValidName(String name) {
        if (name == null || name.length() < 3 || name.length() > 30) {
            System.out.println("Name must be between 3 and 30 characters and only contain letters and spaces.");
            return false;
        }
        String pattern = "^[A-Za-z ]+$";
        return name.matches(pattern);
    }

    public static boolean isValidEmail(String email) {
        if (email == null) {
            System.out.println("Email cannot be null.");
            return false;
        }
        String pattern = "^[a-zA-Z][a-zA-Z0-9]+@(gmail|yahoo)\\.com$";
        if (!email.matches(pattern)) {
            System.out.println("Email must be in the format: name123@gmail.com or name123@yahoo.com.");
            return false;
        }
        return true;
    }

    public static boolean isValidMobileNumber(String mobileNumber) {
        if (mobileNumber == null) {
            System.out.println("Mobile number cannot be null.");
            return false;
        }
        String pattern = "^[6-9][0-9]{9}$";
        if (!mobileNumber.matches(pattern)) {
            System.out.println("Mobile number must be a 10-digit number starting with 6, 7, 8, or 9.");
            return false;
        }
        return true;
    }

    public static boolean isValidAddress(String address) {
        if (address == null || address.length() < 5 || address.length() > 255) {
            System.out.println("Address must be between 5 and 255 characters.");
            return false;
        }
        return true;
    }

    public static boolean isValidDateOfBirth(String dob) {
        if (dob == null) {
            System.out.println("Date of Birth cannot be null.");
            return false;
        }

        String pattern = "^\\d{4}-\\d{2}-\\d{2}$";
        if (!dob.matches(pattern)) {
            System.out.println("Date of Birth must be in the format YYYY-MM-DD.");
            return false;
        }

        try {
            LocalDate birthDate = LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (birthDate.isAfter(LocalDate.now())) {
                System.out.println("Date of Birth cannot be a future date.");
                return false;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date of Birth format.");
            return false;
        }

        return true;
    }

    public static boolean isValidCheckInDate(String checkInDate) {
        if (checkInDate == null) {
            System.out.println("Check-In Date cannot be null.");
            return false;
        }

        String pattern = "^\\d{4}-\\d{2}-\\d{2}$";
        if (!checkInDate.matches(pattern)) {
            System.out.println("Check-In Date must be in the format YYYY-MM-DD.");
            return false;
        }

        try {
            LocalDate date = LocalDate.parse(checkInDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (date.isBefore(LocalDate.now())) {
                System.out.println("Check-In Date cannot be a past date.");
                return false;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Check-In Date format.");
            return false;
        }

        return true;
    }


    public static boolean isValidGender(String gender) {
        if (!(gender.equals("Male") || gender.equals("Female") || gender.equals("Other"))) {
            System.out.println("Gender must be either Male, Female, or Other.");
            return false;
        }
        return true;
    }

    public static boolean isValidProofId(String proofId) {
        if (!(proofId.equals("Aadhar") || proofId.equals("Driving License") || proofId.equals("Passport"))) {
            System.out.println("Proof ID must be either Aadhar, Driving License, or Passport.");
            return false;
        }
        return true;
    }
}
