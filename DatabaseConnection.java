import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Update the password below to match what you created in the MySQL Installer
    private static final String URL = "jdbc:mysql://localhost:3306/echallan_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Ayush@3918";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Establishes the connection using the JDBC API
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("[ERROR] Database Connection Failed!");
            System.out.println("Reason: " + e.getMessage());
        }
        return connection;
    }
}