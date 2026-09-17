import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRunning = true;

        System.out.println("==========================================");
        System.out.println("  E-CHALLAN & TRAFFIC LOG SYSTEM - CLI    ");
        System.out.println("==========================================");

        System.out.println("[SYSTEM] Testing Database Connection...");
        if (DatabaseConnection.getConnection() != null) {
            System.out.println("[SYSTEM] Database Connected Successfully!\n");
        } else {
            System.out.println("[ERROR] Could not connect to the database. Exiting...");
            return; // Stops the program if the database isn't working
        }


        while (isRunning) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Register a New Vehicle");
            System.out.println("2. Log a Traffic Incident");
            System.out.println("3. Generate E-Challan (Penalty)");
            System.out.println("4. View Offline Backups");
            System.out.println("5. Exit System");
            System.out.print("\nEnter your choice (1-5): ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("\n--- REGISTER NEW VEHICLE ---");
                        System.out.print("Enter License Plate (e.g., MP-43-XY-1234): ");
                        String plate = scanner.nextLine();
                        System.out.print("Enter Owner Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Contact Number: ");
                        String contact = scanner.nextLine();
                        System.out.print("Enter Vehicle Model: ");
                        String model = scanner.nextLine();

                        VehicleManager.registerVehicle(plate, name, contact, model);
                        break;

                    case 2:
                        System.out.println("\n--- LOG TRAFFIC INCIDENT ---");
                        System.out.print("Enter Offending License Plate: ");
                        String offendingPlate = scanner.nextLine();

                        // Fetch and display the list of offenses from MySQL
                        IncidentManager.displayOffenses();

                        System.out.print("\nEnter Offense ID (1-4): ");
                        int offenseId = scanner.nextInt();
                        scanner.nextLine();
                        IncidentManager.logIncident(offendingPlate, offenseId);
                        break;

                    case 3:
                        System.out.println("\n--- GENERATE E-CHALLAN ---");
                        System.out.print("Enter License Plate to check unpaid challans: ");
                        String checkPlate = scanner.nextLine();
                        ChallanGenerator.generateAndSaveChallan(checkPlate);
                        break;

                    case 4:
                        System.out.println("\n[SYSTEM] Fetching Offline Backups...");
                        BackupUtil.listBackups();
                        System.out.print("\nEnter the full filename to view (e.g., Challan_RJ-14-AB-1234_1.txt) or type 'cancel': ");
                        String fileName = scanner.nextLine();

                        if (!fileName.equalsIgnoreCase("cancel")) {
                            BackupUtil.viewBackup(fileName);
                        }
                        break;

                    case 5:
                        System.out.println("\n[SYSTEM] Exiting System. Goodbye!");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("\n[ERROR] Invalid option. Please enter a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n[ERROR] Invalid input format. Please enter numerical values only.");
                scanner.nextLine(); // Clear the bad input to prevent an infinite loop
            }
        }
        scanner.close();
    }
}