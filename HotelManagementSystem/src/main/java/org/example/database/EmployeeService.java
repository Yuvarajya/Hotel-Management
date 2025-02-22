package org.example.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeService {

    // Method to get employee information
    public void getEmployeeInformation() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM EMPLOYEEINFO";

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Print employee details
                System.out.println("EmployeeID: " + resultSet.getInt("EmployeeID"));
                System.out.println("Name: " + resultSet.getString("EmployeeNAME"));
                System.out.println("Position: " + resultSet.getString("POSITION"));
                System.out.println("Salary: " + resultSet.getBigDecimal("Salary"));
                System.out.println("Contact: " + resultSet.getString("CONTACT"));
                System.out.println("Address: " + resultSet.getString("address"));
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

    // Method to add an employee
    public boolean addEmployee(String name, String position, String contact, double salary, String address) {
        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "INSERT INTO EMPLOYEEINFO (EmployeeNAME, POSITION, CONTACT, Salary, address) VALUES (?, ?, ?, ?, ?)";

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, position);
            statement.setString(3, contact);
            statement.setDouble(4, salary);
            statement.setString(5, address);
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

    public boolean updateEmployee(int employeeId, String newName, String position, String contact, double salary, String address) {
        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "UPDATE EMPLOYEEINFO SET EmployeeNAME = ?, POSITION = ?, CONTACT = ?, Salary = ?, address = ? WHERE EmployeeID = ?";

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, newName);
            statement.setString(2, position);
            statement.setString(3, contact);
            statement.setDouble(4, salary);
            statement.setString(5, address);
            statement.setInt(6, employeeId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
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

    // Method to delete an employee
    public boolean deleteEmployee(int employeeId) {
        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "DELETE FROM EMPLOYEEINFO WHERE EmployeeID = ?";

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, employeeId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
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
