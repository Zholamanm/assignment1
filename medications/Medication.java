package medications;

public class Medication implements IMedication {
    private String name;
    private String description;
    private double price;

    public Medication(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return  name + " - " + description + " - " + price;
    }

}