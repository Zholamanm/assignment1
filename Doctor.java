public abstract class Doctor implements TreatmentStrategy {
    protected String name;
    protected MedicalEquipment equipment;
    protected String speciality;

    public Doctor(String name, MedicalEquipment equipment, String speciality) {
        this.name = name;
        this.equipment = equipment;
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getName() {
        return name;
    }

    public MedicalEquipment getEquipment() {
        return equipment;
    }

}