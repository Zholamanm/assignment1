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
                    "appointment_time TIME," +  // новая колонка для времени записи
                    "initial_fee DECIMAL(10, 2) NOT NULL DEFAULT 0.00" +  // новая колонка для цены
                    ")");
            stmt.execute("CREATE TABLE IF NOT EXISTS appointments (" +
                    "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "doctor_id INTEGER," +
                    "appointment_time TIME," +
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

            // Проверяем, есть ли уже записи в таблице doctors
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM doctors");
            rs.next();
            int rowCount = rs.getInt(1);

            // Если записей нет, то добавляем начальные данные
            if (rowCount == 0) {
                stmt.execute("INSERT INTO doctors (name, speciality) VALUES ('Окулист Анна', 'Окулист'), ('Окулист Виктор', 'Окулист'), ('Терапевт Ирина', 'Терапевт'), ('Терапевт Максим', 'Терапевт'), ('Хирург Олег', 'Хирург'), ('Хирург Лилия', 'Хирург')");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

