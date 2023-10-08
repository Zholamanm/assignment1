package medications;

import medications.IMedication;

public abstract class MedicationDecorator implements IMedication {
    protected IMedication baseMedication;

    public MedicationDecorator(IMedication baseMedication) {
        this.baseMedication = baseMedication;
    }
}