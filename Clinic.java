import java.util.ArrayList;
import java.util.List;

public class Clinic {
    private static Clinic instance;

    private List<Medication> medications = new ArrayList<>();

    private Clinic() {}

    public static Clinic getInstance() {
        if (instance == null) {
            synchronized (Clinic.class) {
                if (instance == null) {
                    instance = new Clinic();
                }
            }
        }
        return instance;
    }

    public void addMedication(Medication medication) {
        medications.add(medication);
    }

    public List<Medication> getAllMedications() {
        return medications;
    }

    public Medication getMedicationByName(String name) {
        for (Medication med : medications) {
            if (med.getName().equals(name)) {
                return med;
            }
        }
        return null;
    }
}