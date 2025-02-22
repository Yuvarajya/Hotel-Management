package org.example.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckInService {

    public boolean checkInCustomer(String name, String room_no, String checkInDate) {
        Connection connection = null;
        PreparedStatement insertStatement = null;
        PreparedStatement updateStatement = null;
        PreparedStatement selectStatement = null;
        ResultSet resultSet = null;

        String selectRoomDetailsSql = "SELECT ROOM_TYPE, PRICE_PER_DAY FROM ROOM_DETAILS WHERE ROOM_NO = ?";
        String insertCheckInSql = "INSERT INTO CHECKINOUTINFO (NAME, ROOM_NO, CHECKEDIN_DATE, ROOM_TYPE, PRICE_PER_DAY) VALUES (?, ?, ?, ?, ?)";
        String updateRoomStatusSql = "UPDATE ROOM_DETAILS SET ROOM_STATUS = 'Booked' WHERE ROOM_NO = ?";

        try {
            connection = DatabaseConnection.getConnection(); // Establish the connection

            // Fetch room details like room type and price per day
            selectStatement = connection.prepareStatement(selectRoomDetailsSql);
            selectStatement.setString(1, room_no);
            resultSet = selectStatement.executeQuery();

            String roomType = null;
            double pricePerDay = 0.0;

            if (resultSet.next()) {
                roomType = resultSet.getString("ROOM_TYPE");
                pricePerDay = resultSet.getDouble("PRICE_PER_DAY");
            } else {
                System.out.println("Room not found. Please check the room number.");
                return false;
            }

            // Update room status to "Booked"
            updateStatement = connection.prepareStatement(updateRoomStatusSql);
            updateStatement.setString(1, room_no);
            updateStatement.executeUpdate();

            // Insert the check-in details with room type and price per day
            insertStatement = connection.prepareStatement(insertCheckInSql);
            insertStatement.setString(1, name); // Customer Name
            insertStatement.setString(2, room_no); // Room number
            insertStatement.setString(3, checkInDate); // Check-in date
            insertStatement.setString(4, roomType); // Room type
            insertStatement.setDouble(5, pricePerDay); // Price per day
            insertStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (selectStatement != null) selectStatement.close();
                if (updateStatement != null) updateStatement.close();
                if (insertStatement != null) insertStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean cancelBooking(int bookingId) {
        Connection connection = null;
        PreparedStatement deleteStatement = null;
        PreparedStatement updateStatement = null;

        // SQL queries
        String deleteBookingSql = "DELETE FROM CHECKINOUTINFO WHERE BOOKING_ID = ?";
        String updateRoomStatusSql = "UPDATE ROOM_DETAILS SET ROOM_STATUS = 'Available' WHERE ROOM_NO = (SELECT ROOM_NO FROM CHECKINOUTINFO WHERE BOOKING_ID = ?)";

        try {
            connection = DatabaseConnection.getConnection(); // Establish the connection

            // Delete the booking from CHECKINOUTINFO
            deleteStatement = connection.prepareStatement(deleteBookingSql);
            deleteStatement.setInt(1, bookingId);
            int rowsAffected = deleteStatement.executeUpdate();

            // Check if the booking was deleted
            if (rowsAffected > 0) {
                // Update the room status to "Available"
                updateStatement = connection.prepareStatement(updateRoomStatusSql);
                updateStatement.setInt(1, bookingId);
                updateStatement.executeUpdate();

                return true;
            } else {
                System.out.println("Booking ID not found.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (deleteStatement != null) deleteStatement.close();
                if (updateStatement != null) updateStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
