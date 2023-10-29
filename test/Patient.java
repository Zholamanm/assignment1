package test;

public class Patient implements Observer{
    private String name;
    public Patient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(String message) {
        System.out.println("Уведомление для " + name + ": " + message);
    }
}
