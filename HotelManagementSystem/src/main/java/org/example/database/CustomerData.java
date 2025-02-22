package org.example.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerData {
    // Method to get all customers (for demonstration)
    public List<String> getAllCustomers() {
        List<String> customers = new ArrayList<>();
        String sql = "SELECT * FROM customerinfo";

        try (Connection conn = DatabaseConnection.getConnection(); // Use DBConnection to get the connection
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String customerInfo =
                        ", Name: " + rs.getString("name") +
                                ", Email: " + rs.getString("email") +
                                ", Phone: " + rs.getString("phone") +
                                ", Address: " + rs.getString("address") +
                                ", DOB: " + rs.getDate("date_of_birth") +
                                ", Gender: " + rs.getString("gender") +
                                ", Proof ID: " + rs.getString("proof_id");
                ;
                customers.add(customerInfo);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving customers: " + e.getMessage());
        }
        return customers;
    }
}