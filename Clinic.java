import medications.IMedication;

import java.util.ArrayList;
import java.util.List;

public class Clinic {
    private static volatile Clinic instance;

    private final List<IMedication> medications = new ArrayList<>();

    private final List<IMedication> discountedMedications = new ArrayList<>();


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

    public void addMedication(IMedication medication) {
        medications.add(medication);
    }

    public void addDiscountedMedication(IMedication discountedMedication) {
        discountedMedications.add(discountedMedication);
    }

    public List<IMedication> getAllMedications() {
        return medications;
    }

    public List<IMedication> getAllMedicationsDiscount() {
        return discountedMedications;
    }
}