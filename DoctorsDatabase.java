import db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DoctorsDatabase {
    private HashMap<String, List<String>> doctors;

    public DoctorsDatabase() {
        doctors = new HashMap<>();
    }

    public List<String> getDoctorsBySpeciality(String speciality) {
        List<String> result = new ArrayList<>();
        String query = "SELECT name FROM doctors WHERE speciality = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, speciality);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean isTimeAvailable(int doctorId, String time) {
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_time = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, doctorId);
            stmt.setString(2, time);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void addAppointment(int doctorId, String time, String patientName) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO appointments (doctor_id, appointment_time, patient_name) VALUES (?, ?, ?)")) {
            stmt.setInt(1, doctorId);
            stmt.setString(2, time);
            stmt.setString(3, patientName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getDoctorIdByName(String doctorName) {
        int doctorId = -1;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT id FROM doctors WHERE name = ?")) {
            stmt.setString(1, doctorName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                doctorId = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctorId;
    }

    public void closeConnection() {
        DatabaseConnection.closeConnection();
    }

}