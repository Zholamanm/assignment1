package medications;

import medications.IMedication;
import medications.MedicationDecorator;

public class DiscountedMedication extends MedicationDecorator {
    private double discount; // Например, 0.1 для 10%

    public DiscountedMedication(IMedication baseMedication, double discount) {
        super(baseMedication);
        this.discount = discount;
    }

    @Override
    public double getPrice() {
        return baseMedication.getPrice() * (1 - discount);
    }

    @Override
    public String getDescription() {
        return baseMedication.getDescription() + " (со скидкой)";
    }

    @Override
    public String toString() {
        return baseMedication.toString() + " СКИДКА - " + getPrice();
    }
}
