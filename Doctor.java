public abstract class Doctor {
    protected AppointmentStrategy strategy;

    public void setStrategy(AppointmentStrategy strategy) {
        this.strategy = strategy;
    }

    public void makeAppointment() {
        strategy.makeAppointment();
    }
}