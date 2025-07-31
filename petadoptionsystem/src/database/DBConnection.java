package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/pet_adoption";
    private static final String username = "root";
    private static final String password = "shruti@2003*";

// Establish the connection
public static Connection getConnection() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    } catch (ClassNotFoundException | SQLException e) {
        System.out.println("Database connection error: " + e.getMessage());
        return null;
    }
}
}
