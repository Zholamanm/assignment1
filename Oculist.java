public class Oculist extends Doctor {
    public Oculist() {
        setStrategy(new OculistAppointment());
    }
}