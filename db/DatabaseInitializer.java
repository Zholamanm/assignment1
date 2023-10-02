package db;

import java.sql.*;

public class DatabaseInitializer {
    public static void initialize() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS doctors (" +
                    "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(255) NOT NULL," +
                    "speciality VARCHAR(255) NOT NULL," +
                    "appointment_time TIME," +
                    "initial_fee DECIMAL(10, 2) NOT NULL DEFAULT 0.00," +
                    "equipment_type VARCHAR(255)" +
                    ")");
            stmt.execute("CREATE TABLE IF NOT EXISTS appointments (" +
                    "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "doctor_id INTEGER," +
                    "appointment_time TIMESTAMP," +
                    "patient_name VARCHAR(255)," +
                    "FOREIGN KEY(doctor_id) REFERENCES doctors(id)" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertInitialData() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM doctors");
            rs.next();
            int rowCount = rs.getInt(1);

            if (rowCount == 0) {
                stmt.execute("INSERT INTO doctors (name, speciality, equipment_type) VALUES ('Окулист Анна', 'Окулист', 'VisionChecker'), ('Окулист Виктор', 'Окулист', 'VisionChecker'), ('Терапевт Ирина', 'Терапевт', 'DiagnosticEquipment'), ('Терапевт Максим', 'Терапевт', 'DiagnosticEquipment'), ('Хирург Олег', 'Хирург', 'SurgeryTools'), ('Хирург Лилия', 'Хирург', 'SurgeryTools')");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

