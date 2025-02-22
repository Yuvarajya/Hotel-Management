package org.example.database;

import org.example.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EarningsService {
    public String generateEarningsReport() {
        StringBuilder report = new StringBuilder("Earnings Report:\n");
        String query = "SELECT ROOM_NO,ROOM_TYPE,CHECKEDIN_DATE,checkedout_date,PRICE_PER_DAY, TOTAL_DAYS, TOTAL_PRICE FROM checkinoutinfo";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                report.append("Room No: ").append(resultSet.getString("ROOM_NO"))
                        .append(",  ROOMTYPE : ").append(resultSet.getString("ROOM_TYPE"))
                        .append(",  CHECKEDIN : ").append(resultSet.getString("CHECKEDIN_DATE"))
                        .append(",  CHECKEDOUT : ").append(resultSet.getString("CHECKEDOUT_DATE"))
                        .append(", PRICEPERDAY : ").append(resultSet.getString("PRICE_PER_DAY"))
                        .append(", Days: ").append(resultSet.getInt("TOTAL_DAYS"))
                        .append(", Total Price: ").append(resultSet.getDouble("TOTAL_PRICE"))
                        .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report.toString();
    }
}
