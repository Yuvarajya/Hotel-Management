package org.example.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryService {
    public void getCheckInOutHistory() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM CHECKINOUTINFO ORDER BY checkedin_date DESC";

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Print the check-in/out history details
                System.out.println("Customer_Name: " + resultSet.getString("NAME"));
                System.out.println("Room No: " + resultSet.getString("ROOM_NO"));
                System.out.println("Check-In Date: " + resultSet.getString("CHECKEDIN_DATE"));
                System.out.println("Check-Out Date: " + resultSet.getString("CHECKEDOUT_DATE"));
                System.out.println("Room Type: " + resultSet.getString("ROOM_TYPE"));
                System.out.println("----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
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
