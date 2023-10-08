package medications;


public abstract class MedicationDecorator implements IMedication {
    protected IMedication baseMedication;

    public MedicationDecorator(IMedication baseMedication) {
        this.baseMedication = baseMedication;
    }
}