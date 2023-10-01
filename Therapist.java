public class Therapist extends Doctor {
    public Therapist() {
        setStrategy(new TherapistAppointment());
    }
}