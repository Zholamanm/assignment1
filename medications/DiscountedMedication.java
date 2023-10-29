package medications;

public class DiscountedMedication extends MedicationDecorator {
    private final double discount;

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
    public String getName() {
        return baseMedication.getName();
    }

    @Override
    public String toString() {
        return baseMedication.toString() + " СКИДКА - " + getPrice();
    }
}
