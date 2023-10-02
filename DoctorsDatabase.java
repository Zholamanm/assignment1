import db.DatabaseConnection;

import java.sql.*;
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

    public boolean isTimeAvailable(int doctorId, Timestamp time) {
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_time = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, doctorId);
            stmt.setTimestamp(2, time);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void addAppointment(int doctorId, Timestamp time, String patientName) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO appointments (doctor_id, appointment_time, patient_name) VALUES (?, ?, ?)")) {
            stmt.setInt(1, doctorId);
            stmt.setTimestamp(2, time);
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

    public Doctor getDoctorById(int doctorId) {
        String query = "SELECT * FROM doctors WHERE id = ?";
        Doctor doctor = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, doctorId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String speciality = rs.getString("speciality");
                String equipmentType = rs.getString("equipment_type");
                MedicalEquipment equipment = createEquipment(equipmentType);

                switch (speciality) {
                    case "Окулист":
                        doctor = new Oculist(name, equipment);
                        break;
                    case "Терапевт":
                        doctor = new Therapist(name, equipment);
                        break;
                    case "Хирург":
                        doctor = new Surgeon(name, equipment);
                        break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctor;
    }


    private Doctor createDoctorInstance(String speciality, String name, MedicalEquipment equipment) {
        switch (speciality) {
            case "Окулист":
                return new Oculist(name, equipment);
            case "Хирург":
                return new Surgeon(name, equipment);
            case "Терапевт":
                return new Therapist(name, equipment);
            default:
                throw new IllegalArgumentException("Unknown doctor speciality: " + speciality);
        }
    }

    public List<Doctor> getAllDoctors() {
        String query = "SELECT * FROM doctors";
        List<Doctor> doctorsList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String speciality = rs.getString("speciality");
                String equipmentType = rs.getString("equipment_type");
                MedicalEquipment equipment = createEquipment(equipmentType);

                Doctor doctor = createDoctorInstance(speciality, name, equipment);
                doctorsList.add(doctor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctorsList;
    }


    private MedicalEquipment createEquipment(String equipmentType) {
        switch (equipmentType) {
            case "SurgeryTools":
                return new SurgeryTools();
            case "VisionChecker":
                return new VisionChecker();
            case "DiagnosticEquipment":
                return new DiagnosticEquipment();
            default:
                throw new IllegalArgumentException("Unknown equipment type: " + equipmentType);
        }
    }


    public void closeConnection() {
        DatabaseConnection.closeConnection();
    }

}