package doctors;

import doctors.Doctor;
import equipment.MedicalEquipment;

class Therapist extends Doctor {
    public Therapist(String name, MedicalEquipment equipment) {
        super(name, equipment, "Окулист");
    }

    @Override
    public void applyTreatment() {
        System.out.println("Терапевт " + name + " начинает консультацию.");
        equipment.use();
    }
}