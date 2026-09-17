# Command-Line E-Challan & Traffic Log System

## Overview
The Command-Line E-Challan & Traffic Log System is a robust Java-based terminal application designed to digitize and manage traffic violations. It provides a centralized interface for traffic police and RTO administrators to manage vehicle registries, log traffic incidents, and automatically generate formatted e-challans. 

## Features
* **Vehicle & Owner Registry:** Perform full CRUD operations to register new vehicles, update owner details, or delete scrapped vehicles.
* **Incident Logger:** Record specifics of traffic violations, including the offense type, license plate, and timestamp.
* **Automated Challan Generation:** Automatically calculates fines based on the offense type and repeat offender status.
* **Offline Receipt Backup:** Uses Java File I/O streams to generate and save text-based challan receipts locally.
* **Pure CLI Interface:** Fully navigable via terminal using structured, numeric menu prompts.

## Technologies Used
* **Language:** Java (JDK 11 or higher)
* **Database:** MySQL (via JDBC)
* **Libraries/Drivers:** MySQL Connector/J (`mysql-connector-java.jar`)
* **Concepts Applied:** Object-Oriented Programming (OOP), JDBC Database Connectivity, File I/O Streams, Exception Handling, Collections Framework.

## Environment Setup & Requirements
Before running the project, ensure you have the following installed:
1. **Java Development Kit (JDK):** Version 11 or higher. Verify by running `java -version` in your terminal.
2. **MySQL Server:** Installed and running locally.
3. **MySQL JDBC Driver:** Download the `.jar` file and place it in a `lib/` directory inside the project root.

## Installation & Configuration
**1. Clone the Repository:**
```bash
git clone https://github.com/{your-username}/e-challan-system.git
cd e-challan-system
```

**2. Database Configuration:**
* Log into your MySQL CLI: `mysql -u root -p`
* Create the database and tables using the provided SQL script (assuming you have a `schema.sql` file):
  ```sql
  source database/schema.sql;
  ```
* Open the `src/DatabaseConnection.java` (or equivalent) file and update the database credentials to match your local MySQL setup:
  ```java
  String url = "jdbc:mysql://localhost:3306/echallan_db";
  String user = "root";
  String password = "your_password";
  ```

## Execution Instructions
This project is designed to be fully executable from the command line without relying on a GUI or IDE.

**1. Compile the Source Code:**
Navigate to the root directory of the project and compile the Java files, including the JDBC driver in the classpath.
* **Windows:**
  ```cmd
  javac -d bin -cp "lib/mysql-connector-java.jar" src/*.java
  ```
* **macOS/Linux:**
  ```bash
  javac -d bin -cp "lib/mysql-connector-java.jar" src/*.java
  ```

**2. Run the Application:**
Execute the compiled classes, ensuring the driver remains in the classpath.
* **Windows:**
  ```cmd
  java -cp "bin;lib/mysql-connector-java.jar" Main
  ```
* **macOS/Linux:**
  ```bash
  java -cp "bin:lib/mysql-connector-java.jar" Main
  ```

## Instructions for Testing
To evaluate the system, follow this testing workflow:
1. **Option 1 (Register Vehicle):** From the main menu, register a test vehicle (e.g., License Plate: `RJ-14-XYZ-1234`).
2. **Option 2 (Log Incident):** Log an incident for the newly registered vehicle (e.g., Offense: "Speeding").
3. **Option 3 (Generate Challan):** Select the option to generate a challan. The system will retrieve the base fine from the database and print the calculated ticket to the terminal.
4. **Option 4 (View Backups):** Check the `challans/` folder in the project directory to verify that the offline text-based receipt was successfully written to the local disk.

## Screenshots
<img width="695" height="633" alt="Screenshot 2026-09-14 163235" src="https://github.com/user-attachments/assets/5e7e1d1c-7284-4569-8396-8687d6cf4ac2" />
