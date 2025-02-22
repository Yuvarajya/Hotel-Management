package org.example.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerFormDAO {
    public boolean addCustomerToDatabase(String name, String email, String phone, String address, String dob, String gender, String proofId) {
        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "INSERT INTO customerinfo (Name, email, phone, address, date_of_birth, gender, proof_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, phone);
            statement.setString(4, address);
            statement.setString(5, dob);
            statement.setString(6, gender);
            statement.setString(7, proofId);

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
