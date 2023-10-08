package doctors;

import equipment.MedicalEquipment;

class Surgeon extends Doctor {
    public Surgeon(String name, MedicalEquipment equipment) {
        super(name, equipment, "Окулист");
    }

    @Override
    public void applyTreatment() {
        System.out.println("Хирург " + name + " начинает операцию.");
        equipment.use();
    }
}