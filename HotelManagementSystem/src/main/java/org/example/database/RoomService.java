package org.example.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomService {

    // Method to get all available rooms
    public List<String> getAvailableRooms() {
        List<String> availableRooms = new ArrayList<>();
        String sql = "SELECT * FROM room_details where ROOM_STATUS = 'Available'";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String roomInfo = "Room No: " + rs.getInt("room_no") +
                        ", Room Type: " + rs.getString("room_type") +
                        ", Capacity: " + rs.getInt("room_capacity") +
                        ", Price Per Day: " + rs.getDouble("price_per_day") +
                        ", Status: " + rs.getString("room_status");
                availableRooms.add(roomInfo);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving available rooms: " + e.getMessage());
        }
        return availableRooms;
    }
}
