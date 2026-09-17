CREATE DATABASE IF NOT EXISTS echallan_db;
USE echallan_db;

CREATE TABLE IF NOT EXISTS Vehicles (
                                        LicensePlate VARCHAR(20) PRIMARY KEY,
    OwnerName VARCHAR(100) NOT NULL,
    Contact VARCHAR(15) NOT NULL,
    VehicleModel VARCHAR(50)
    );

CREATE TABLE IF NOT EXISTS OffenseTypes (
                                            OffenseID INT PRIMARY KEY AUTO_INCREMENT,
                                            OffenseName VARCHAR(100) NOT NULL,
    BaseFineAmount DECIMAL(10, 2) NOT NULL
    );

CREATE TABLE IF NOT EXISTS IncidentLogs (
                                            IncidentID INT PRIMARY KEY AUTO_INCREMENT,
                                            LicensePlate VARCHAR(20),
    OffenseID INT,
    IncidentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Status VARCHAR(20) DEFAULT 'UNPAID',
    FOREIGN KEY (LicensePlate) REFERENCES Vehicles(LicensePlate),
    FOREIGN KEY (OffenseID) REFERENCES OffenseTypes(OffenseID)
    );

INSERT INTO OffenseTypes (OffenseName, BaseFineAmount) VALUES
                                                           ('Speeding', 1000.00),
                                                           ('Running Red Light', 500.00),
                                                           ('No Helmet', 1000.00),
                                                           ('Driving Without License', 5000.00);
