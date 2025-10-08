package com.example.bookingservice.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainFileSample {

    static class MySQLConnectionTest {
        public static void main(String[] args) {
            String url = "jdbc:mysql://localhost:3306/your_database_name"; // Replace with your database name
            String user = "your_username"; // Replace with your MySQL username
            String password = "your_password"; // Replace with your MySQL password

            try {
                // Load the JDBC driver (optional for newer JDBC versions, but good practice)
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establish the connection
                Connection connection = DriverManager.getConnection(url, user, password);

                if (connection != null) {
                    System.out.println("JDBC connection to MySQL successful!");
                    connection.close(); // Close the connection
                }

            } catch (ClassNotFoundException e) {
                System.err.println("MySQL JDBC Driver not found. Make sure the JAR is in the classpath.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Error connecting to MySQL database:");
                e.printStackTrace();
            }
        }
    }

}
