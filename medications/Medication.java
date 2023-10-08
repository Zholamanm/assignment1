package medications;

import medications.IMedication;

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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return  name + " - " + description + " - " + price;
    }

}
