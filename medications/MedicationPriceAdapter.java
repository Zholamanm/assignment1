package medications;

public class MedicationPriceAdapter implements IMedicationPriceInDollars {
    private final IMedication medication;
    private static final double EURO_TO_DOLLAR_RATE = 1.1;

    public MedicationPriceAdapter(IMedication medication) {
        this.medication = medication;
    }

    @Override
    public double getPriceInDollars() {
        return medication.getPrice() * EURO_TO_DOLLAR_RATE;
    }

    @Override
    public String toString() {
        return medication.getName() + " " + medication.getDescription() + " " + getPriceInDollars();
    }
}
