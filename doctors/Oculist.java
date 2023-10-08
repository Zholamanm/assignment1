package doctors;

import equipment.MedicalEquipment;

class Oculist extends Doctor {
    public Oculist(String name, MedicalEquipment equipment) {
        super(name, equipment, "Окулист");
    }

    @Override
    public void applyTreatment() {
        System.out.println("Окулист " + name + " начинает осмотр.");
        equipment.use();
    }
}
