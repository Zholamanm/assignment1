package medications;

public class BuyMedicationCommand implements Command {
    private IMedication medication;
    private double price;

    public BuyMedicationCommand(IMedication medication) {
        this.medication = medication;
        this.price = medication.getPrice();
    }

    @Override
    public void execute() {
        printReceipt();
    }

    private void printReceipt() {
        System.out.println("--------Чек--------");
        System.out.println("Название: " + medication.getName());
        System.out.println("Цена: " + price + " евро");
        System.out.println("-------------------");
    }
}

