import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BackupUtil {

    // Lists all files currently in the challans folder
    public static void listBackups() {
        File folder = new File("challans");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles == null || listOfFiles.length == 0) {
            System.out.println("\n[INFO] No offline backups found in the system.");
            return;
        }

        System.out.println("\n--- AVAILABLE OFFLINE BACKUPS ---");
        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println("- " + file.getName());
            }
        }
    }

    // Reads a specific file and prints its contents
    public static void viewBackup(String fileName) {
        String path = "challans/" + fileName;
        File file = new File(path);

        if (!file.exists()) {
            System.out.println("\n[ERROR] Backup file not found. Check the spelling and try again.");
            return;
        }

        System.out.println("\n--- READING BACKUP: " + fileName + " ---\n");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("[ERROR] Failed to read the backup file.");
        }
    }
}