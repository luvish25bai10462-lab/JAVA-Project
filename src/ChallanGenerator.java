import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChallanGenerator {

    public static void generateAndSaveChallan(String plate) {
        // Advanced JDBC: Joining 3 tables to get the full incident report
        String query = "SELECT i.IncidentID, i.IncidentDate, v.OwnerName, o.OffenseName, o.BaseFineAmount " +
                "FROM IncidentLogs i " +
                "JOIN Vehicles v ON i.LicensePlate = v.LicensePlate " +
                "JOIN OffenseTypes o ON i.OffenseID = o.OffenseID " +
                "WHERE i.LicensePlate = ? AND i.Status = 'UNPAID'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, plate);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("\n[INFO] No unpaid challans found for vehicle: " + plate);
                return;
            }

            while (rs.next()) {
                int incidentId = rs.getInt("IncidentID");
                String date = rs.getString("IncidentDate");
                String owner = rs.getString("OwnerName");
                String offense = rs.getString("OffenseName");
                double fine = rs.getDouble("BaseFineAmount");

                // Format the terminal output
                String receipt = String.format(
                        "====================================\n" +
                                "        E-CHALLAN RECEIPT           \n" +
                                "====================================\n" +
                                "Incident ID : %d\n" +
                                "Date        : %s\n" +
                                "Vehicle     : %s\n" +
                                "Owner Name  : %s\n" +
                                "Offense     : %s\n" +
                                "Total Fine  : Rs. %.2f\n" +
                                "Status      : UNPAID\n" +
                                "====================================\n",
                        incidentId, date, plate, owner, offense, fine
                );

                System.out.println("\n" + receipt);

                // Trigger Java I/O to save the text file backup
                saveReceiptToFile(plate, incidentId, receipt);
            }

        } catch (SQLException e) {
            System.out.println("[ERROR] Database error during challan generation.");
            System.out.println("Reason: " + e.getMessage());
        }
    }

    // Java I/O Streams implementation to write data to a text file
    private static void saveReceiptToFile(String plate, int incidentId, String receiptData) {
        String folderName = "challans";
        File directory = new File(folderName);
        if (!directory.exists()) {
            directory.mkdir(); // Creates the folder automatically if it doesn't exist
        }

        String fileName = folderName + "/Challan_" + plate + "_" + incidentId + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(receiptData);
            System.out.println("[SUCCESS] Offline backup saved via File I/O to: " + fileName);
        } catch (IOException e) {
            System.out.println("[ERROR] Failed to save offline backup via File I/O.");
        }
    }
}