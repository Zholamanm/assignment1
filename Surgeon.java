public class Surgeon extends Doctor {
    public Surgeon() {
        setStrategy(new SurgeonAppointment());
    }
}
