import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IncidentManager {

    // Reads and displays the types of offenses available in the database
    public static void displayOffenses() {
        String query = "SELECT * FROM OffenseTypes";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("\n--- AVAILABLE OFFENSES ---");
            while (rs.next()) {
                System.out.println(rs.getInt("OffenseID") + ". " + rs.getString("OffenseName") + " (Base Fine: Rs." + rs.getDouble("BaseFineAmount") + ")");
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Could not load offenses.");
        }
    }

    // Logs the actual incident into the database
    public static void logIncident(String plate, int offenseId) {
        String query = "INSERT INTO IncidentLogs (LicensePlate, OffenseID) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, plate);
            pstmt.setInt(2, offenseId);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("\n[SUCCESS] Traffic incident logged successfully for vehicle " + plate + "!");
            }
        } catch (SQLException e) {
            System.out.println("\n[ERROR] Could not log incident. Ensure the license plate exists in the system.");
            System.out.println("Database Reason: " + e.getMessage());
        }
    }
}