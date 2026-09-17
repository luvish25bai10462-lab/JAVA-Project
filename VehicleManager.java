import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VehicleManager {

    // The 'C' in CRUD: Create a new vehicle record
    public static void registerVehicle(String plate, String owner, String contact, String model) {
        String query = "INSERT INTO Vehicles (LicensePlate, OwnerName, Contact, VehicleModel) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, plate);
            pstmt.setString(2, owner);
            pstmt.setString(3, contact);
            pstmt.setString(4, model);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\n[SUCCESS] Vehicle registered successfully in the database!");
            }
        } catch (SQLException e) {
            System.out.println("\n[ERROR] Could not register vehicle. The license plate may already exist.");
            System.out.println("Database Reason: " + e.getMessage());
        }
    }
}