package org.example.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CheckOutService {
    public boolean checkOutCustomer(String room_no) {
        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "UPDATE ROOM_DETAILS SET ROOM_STATUS = 'Available' WHERE ROOM_NO = ?";

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, room_no);
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
